package algo.algorithm.distributed;


import algo.algorithm.distributed.LamportClock.EventTime;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

class LamportClockTestCase extends Assert {

    @Test
    public void testLamport() throws InterruptedException {

        int nodes = 5;
        Random random = new Random();
        LamportClock lamportClock = new LamportClock(nodes);

        System.out.print("------------------------------------");
        for (int i = 0; i < 100; i++) {
            Thread.sleep(random.nextInt(3000));
            Lock writeLock = lamportClock.lock.writeLock();
            writeLock.lock();
            try {

                for (LamportClock.Node node : lamportClock.allNodes) {
                    System.out.println();
                    System.out.print(node.hostIndex + ": [");
                    node.eventTimes.stream().map(time -> "(" + time.nodeOrigin + ", " + time.time + ", " +
                            time.event.id + ")")
                            .forEach(leader -> System.out.print(leader + ","));
                    System.out.print("]");
                }

                System.out.println();
                System.out.print("------------------------------------");

                for (LamportClock.Node node : lamportClock.allNodes) {
                    int lastBiggestTime = 0;

                    for (EventTime eventTime : node.eventTimes) {
                        // Events are sorted as they appear and according to the rule lamport time should obey
                        // happens-before.
                        if (eventTime.nodeOrigin == node.hostIndex) {
                            assertTrue(eventTime.time > lastBiggestTime);
                        }
                        lastBiggestTime = Math.max(lastBiggestTime, eventTime.time);
                    }
                }
            } finally {
                writeLock.unlock();
            }

        }

    }

}
