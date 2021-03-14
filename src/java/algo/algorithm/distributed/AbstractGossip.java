package algo.algorithm.distributed;

import algo.algorithm.distributed.AbstractGossip.Node;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public abstract class AbstractGossip<T, N extends Node<T, N>> {

    public final ReadWriteLock lock = new ReentrantReadWriteLock();

    public final List<N> allNodes;

    public AbstractGossip(int numberOfNodes) {
        this.allNodes = new ArrayList<>();
        for (int i = 0; i < numberOfNodes; i++) {
            allNodes.add(createNode(allNodes, i, lock));
        }
        for (Node node : allNodes) {
            Executors.newSingleThreadExecutor().submit(node::doJob);
        }
    }

    public ReadWriteLock getLock() {
        return lock;
    }

    protected abstract N createNode(List<N> allNodes, int hostIndex, ReadWriteLock lock);

    public static abstract class Node<G, N extends Node<G, N>> {

        public final int hostIndex;
        public final List<N> allNodes;
        public final ReadWriteLock lock;

        public Node(List<N> allNodes, int hostIndex, ReadWriteLock lock) {
            this.allNodes = allNodes;
            this.hostIndex = hostIndex;
            this.lock = lock;
        }

        public abstract void sentGossip(G gossip);

        public void doJob() {
            OUTER:
            for (; ; ) {
                Lock lock = this.lock.readLock();
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
                generateEvent();
            } else {
                int size = allNodes.size();
                int i = ThreadLocalRandom.current().nextInt(size);
                G gossip = createGossip();
                allNodes.get(i).sentGossip(gossip);
            }
        }

        protected abstract G createGossip();

        protected abstract void generateEvent();

    }


}

