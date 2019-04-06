package algo.algorithm.distributed;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import jline.internal.Nullable;

public class VectorClocks {

    public final ReadWriteLock lock = new ReentrantReadWriteLock();

    public final List<Node> allNodes;

    public VectorClocks(int numberOfNodes) {
        this.allNodes = new ArrayList<>();
        for (int i = 0; i < numberOfNodes; i++) {
            allNodes.add(new Node(i, numberOfNodes));
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

        public final Vector vector;
        public final Event event;
        public final int nodeOrigin;

        public EventTime(int nodeOrigin, Vector vector, Event event) {
            this.nodeOrigin = nodeOrigin;
            this.vector = vector;
            this.event = event;
        }
    }

    public class Vector {

        public final int[] time;

        public Vector(int[] time) {
            this.time = time;
        }

        public Vector increaseTime(int index) {
            int[] copy = Arrays.copyOf(time, time.length);
            copy[index]++;
            return new Vector(copy);
        }

        public Vector merge(Vector other) {
            int[] copy = Arrays.copyOf(time, time.length);

            for (int i = 0; i < other.time.length; i++) {
                copy[i] = Math.max(other.time[i], copy[i]);
            }
            return new Vector(copy);
        }

        @Override
        public String toString() {
            return Arrays.toString(time);
        }
    }


    public class Node {

        List<EventTime> eventTimes = new ArrayList<>();

        private volatile Vector vector;

        final int hostIndex;

        public Node(int hostIndex, int numberOfNodes) {
            this.hostIndex = hostIndex;
            this.vector = new Vector(new int[numberOfNodes]);
        }

        @Nullable
        public synchronized void sentEvents(List<EventTime> eventTimes, Vector vector) {

            for (EventTime eventTime : eventTimes) {
                if (!this.eventTimes.contains(eventTime)) {
                    this.eventTimes.add(eventTime);
                }
            }
            this.vector = this.vector.merge(vector);
            this.vector = this.vector.increaseTime(hostIndex);
        }


        public void doJob() {
            OUTER:
            for (; ; ) {
                Lock lock = VectorClocks.this.lock.readLock();
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
                vector = vector.increaseTime(hostIndex);
                EventTime time = new EventTime(hostIndex, vector, event);
                eventTimes.add(time);
            } else {
                int size = allNodes.size();
                int i = ThreadLocalRandom.current().nextInt(size);
                vector = vector.increaseTime(hostIndex);
                allNodes.get(i).sentEvents(eventTimes, vector);

            }

        }

    }


}

