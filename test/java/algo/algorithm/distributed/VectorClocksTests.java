package algo.algorithm.distributed;


import static org.junit.jupiter.api.Assertions.assertTrue;

import algo.algorithm.distributed.VectorClocks.EventTime;
import algo.algorithm.distributed.VectorClocks.Vector;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class VectorClocksTests {

    @Test
    @Disabled
    public void testVectorClocks() throws InterruptedException {

        int nodes = 5;
        Random random = new Random();
        VectorClocks vectorClocks = new VectorClocks(nodes);

        System.out.print("------------------------------------");
        for (int i = 0; i < 100; i++) {
            Thread.sleep(random.nextInt(3000));
            Lock writeLock = vectorClocks.lock.writeLock();
            writeLock.lock();
            try {

                for (VectorClocks.Node node : vectorClocks.allNodes) {
                    System.out.println();
                    System.out.print(node.hostIndex + ": [");
                    node.eventTimes.stream().map(time -> "(" + time.nodeOrigin + ", " + time.vector + ", " +
                            time.event.id + ")")
                            .forEach(leader -> System.out.print(leader + ","));
                    System.out.print("]");
                }

                System.out.println();
                System.out.print("------------------------------------");

                for (VectorClocks.Node node : vectorClocks.allNodes) {
                    // Events are sorted as they appear and according. Vector clocks allows us to understand which events
                    // were parallel and which didn't. At means from current event next should be either bigger or
                    // parallel
                    List<VectorClocks.EventTime> eventTimes = node.eventTimes;
                    for (int j = 0; j < eventTimes.size(); j++) {
                        VectorClocks.EventTime time = eventTimes.get(j);

                        if (time.nodeOrigin == node.hostIndex) {

                            for (int k = j + 1; k < eventTimes.size(); k++) {

                                EventTime otherTime = eventTimes.get(k);

                                assertTrue(parallelTime(time.vector, otherTime.vector) ||
                                        biggerTime(otherTime.vector, time.vector));

                                if (otherTime.nodeOrigin == node.hostIndex) {
                                    assertTrue(biggerTime(otherTime.vector, time.vector));
                                }
                            }

                        }
                    }
                }
            } finally {
                writeLock.unlock();
            }
        }
    }

    private boolean parallelTime(Vector vector1, Vector vector2) {
        boolean anySmaller = false;
        boolean anyBigger = false;
        for (int j = 0; j < vector1.time.length; j++) {
            if (vector2.time[j] < vector1.time[j]) {
                anyBigger = true;
            }
            if (vector1.time[j] < vector2.time[j]) {
                anySmaller = true;
            }
        }
        return anyBigger && anySmaller;
    }

    private boolean biggerTime(Vector vector1, Vector vector2) {
        for (int j = 0; j < vector1.time.length; j++) {
            if (vector1.time[j] < vector2.time[j]) {
                return false;
            }
        }
        return true;
    }

}
