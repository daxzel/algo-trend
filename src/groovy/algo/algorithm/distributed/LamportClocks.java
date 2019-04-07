package algo.algorithm.distributed;

import algo.algorithm.distributed.LamportClocks.Gossip;
import algo.algorithm.distributed.LamportClocks.LamportNode;
import algo.algorithm.distributed.gossip.AbstractGossip;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReadWriteLock;

public class LamportClocks extends AbstractGossip<Gossip, LamportNode> {

    public LamportClocks(int numberOfNodes) {
        super(numberOfNodes);
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

    public class Gossip {

        public final List<EventTime> events;
        public final int time;

        public Gossip(List<EventTime> events, int time) {
            this.events = events;
            this.time = time;
        }
    }

    @Override
    protected LamportNode createNode(List<LamportNode> allNodes, int hostIndex, ReadWriteLock lock) {
        return new LamportNode(allNodes, hostIndex, lock);
    }

    public class LamportNode extends AbstractGossip.Node<Gossip, LamportNode> {

        public List<EventTime> eventTimes = new ArrayList<>();
        public int currentTime = 0;

        public LamportNode(List<LamportNode> allNodes, int hostIndex, ReadWriteLock lock) {
            super(allNodes, hostIndex, lock);
        }

        public synchronized void sentGossip(Gossip g) {
            List<EventTime> eventTimes = g.events;

            for (EventTime eventTime : eventTimes) {
                if (!this.eventTimes.contains(eventTime)) {
                    this.eventTimes.add(eventTime);
                }
            }
            currentTime = Math.max(g.time, currentTime);
            currentTime++;
        }

        @Override
        public synchronized void doIteration() {
            currentTime++;
            super.doIteration();
        }

        @Override
        protected Gossip createGossip() {
            return new Gossip(eventTimes, currentTime);
        }

        @Override
        protected void generateEvent() {
            Event event = new Event(ThreadLocalRandom.current().nextInt(0, Integer.MAX_VALUE));
            EventTime time = new EventTime(hostIndex, currentTime, event);
            eventTimes.add(time);
        }
    }

}

