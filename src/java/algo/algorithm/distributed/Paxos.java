package algo.algorithm.distributed;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Paxos {

    public final ReadWriteLock lock = new ReentrantReadWriteLock();

    public final List<Node> allNodes;

    public Paxos(int numberOfNodes) {
        this.allNodes = new ArrayList<>();
        for (int i = 0; i < numberOfNodes; i++) {
            allNodes.add(new Node(i));
        }
        for (Node node : allNodes) {
            Executors.newSingleThreadExecutor().submit(node::doJob);
        }
    }

    public ReadWriteLock getLock() {
        return lock;
    }

    public static class Message {

        public final int hostIndex;
        public final int number;

        public Message(int hostIndex, int number) {
            this.hostIndex = hostIndex;
            this.number = number;
        }

    }

    public class Proposal {

        public final int host;
        public final int messageIndex;

        public Proposal(int host, int messageIndex) {
            this.host = host;
            this.messageIndex = messageIndex;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Proposal proposal = (Proposal) o;
            return host == proposal.host &&
                    messageIndex == proposal.messageIndex;
        }

        @Override
        public int hashCode() {
            return Objects.hash(host, messageIndex);
        }
    }

    public class Value {

        public final Proposal proposal;
        public final int value;

        public Value(Proposal proposal, int value) {
            this.proposal = proposal;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Value value1 = (Value) o;
            return value == value1.value &&
                    Objects.equals(proposal, value1.proposal);
        }

        @Override
        public int hashCode() {
            return Objects.hash(proposal, value);
        }
    }

    public class Node {

        final int hostIndex;

        volatile Value value = new Value(new Proposal(-1, -1), -1);

        private volatile int maxIndex = -1;
        public final AtomicInteger number = new AtomicInteger(0);

        public Node(int hostIndex) {
            this.hostIndex = hostIndex;
        }

        Lock lock = new ReentrantLock();

        public synchronized Value propose(Message message) {
            lock.lock();
            try {
                if (message.number > maxIndex) {
                    maxIndex = message.number;
                }
                return value;
            } finally {
                lock.unlock();
            }
        }

        public synchronized void accept(Message message) {
            lock.lock();
            try {
                if (message.number >= maxIndex) {
                    value = new Value(new Proposal(message.number, message.hostIndex), message.hostIndex);
                    maxIndex = message.number;
                }
            } finally {
                lock.unlock();
            }
        }

        public void doJob() {

            HashMap<Integer, Value> values = new HashMap<>();

            for (Node node : allNodes) {
                values.put(node.hostIndex, new Value(new Proposal(-1, -1), -1));
            }

            OUTER:
            for (; ; ) {
                Lock lock = Paxos.this.lock.readLock();
                lock.lock();
                try {
                    Thread.sleep(ThreadLocalRandom.current().nextLong(300));
                    int n = number.incrementAndGet();
                    int proposingValue = hostIndex;

                    for (Node node : allNodes) {
                        Thread.sleep(ThreadLocalRandom.current().nextLong(10));
                        Value propose = node.propose(new Message(hostIndex, n));
                        if (propose.proposal.messageIndex > n) {
                            number.set(propose.proposal.messageIndex);
                            continue OUTER;
                        }
                        if (!values.get(node.hostIndex).equals(propose)) {
                            proposingValue = propose.value;
                            values.put(node.hostIndex, propose);
                        }
                    }
                    for (Node node : allNodes) {
                        Thread.sleep(ThreadLocalRandom.current().nextLong(10));
                        node.accept(new Message(proposingValue, n));
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }

    }


}
