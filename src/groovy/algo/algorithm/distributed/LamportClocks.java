package algo.algorithm.distributed;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import jline.internal.Nullable;

public class LamportClocks {

    public final ReadWriteLock lock = new ReentrantReadWriteLock();

    public final List<Node> allNodes;

    public LamportClocks(int numberOfNodes) {
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

    public class Event {

        public final int id;

        public Event(int id) {
            this.id = id;
        }
    }

    public class EventTime {

        public final int time;
        public final Event event;
        public final int nodeOrigin;

        public EventTime(int nodeOrigin, int time, Event event) {
            this.nodeOrigin = nodeOrigin;
            this.time = time;
            this.event = event;
        }
    }


    public class Node {

        List<EventTime> eventTimes = new ArrayList<>();

        private volatile int currentTime = 0;

        final int hostIndex;

        public Node(int hostIndex) {
            this.hostIndex = hostIndex;
        }

        @Nullable
        public synchronized void sentEvents(List<EventTime> eventTimes, int time) {

            for (EventTime eventTime : eventTimes) {
                if (!this.eventTimes.contains(eventTime)) {
                    this.eventTimes.add(eventTime);
                }
            }
            currentTime = Math.max(time, currentTime);
            currentTime++;
        }


        public void doJob() {
            OUTER:
            for (; ; ) {
                Lock lock = LamportClocks.this.lock.readLock();
                lock.lock();
                try {
                    Thread.sleep(ThreadLocalRandom.current().nextLong(3000));
                    doIteration();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    lock.unlock();
                }

            }
        }

        public synchronized void doIteration() {
            boolean eventGeneration = ThreadLocalRandom.current().nextBoolean();

            if (eventGeneration) {
                Event event = new Event(ThreadLocalRandom.current().nextInt(0, Integer.MAX_VALUE));
                currentTime++;
                EventTime time = new EventTime(hostIndex, currentTime, event);
                eventTimes.add(time);
            } else {
                int size = allNodes.size();
                int i = ThreadLocalRandom.current().nextInt(size);
                currentTime++;
                allNodes.get(i).sentEvents(eventTimes, currentTime);

            }

        }

    }


}

