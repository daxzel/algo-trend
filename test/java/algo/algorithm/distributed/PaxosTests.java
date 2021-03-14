package algo.algorithm.distributed;


import static org.junit.jupiter.api.Assertions.assertEquals;

import algo.algorithm.distributed.Paxos.Node;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class PaxosTests {

    @Test
    @Disabled
    public void testPaxos() throws InterruptedException {

        int nodes = 5;
        Random random = new Random();
        Paxos paxos = new Paxos(nodes);

        for (int i = 0; i < 100; i++) {
            Thread.sleep(random.nextInt(100));
            Lock writeLock = paxos.lock.writeLock();
            writeLock.lock();
            try {
                paxos.allNodes.stream().map(node -> Integer.toString(node.value.value))
                        .forEach(leader -> System.out.print(leader + ","));
                System.out.println();

                Map<Integer, List<Node>> collect = paxos.allNodes.stream()
                        .collect(Collectors.groupingBy(node -> node.value.value));

                assertEquals(1, collect.size());

            } finally {
                writeLock.unlock();
            }

        }

    }

}
