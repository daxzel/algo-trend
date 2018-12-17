package algo.algorithm.distributed;


import algo.algorithm.distributed.Paxos.Node;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.stream.Collectors;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

class PaxosTestCase extends Assert {

    @Test
    public void testPaxos() throws InterruptedException {

        int nodes = 5;
        Random random = new Random();
        Paxos paxos = new Paxos(nodes);

        for (int i = 0; i < 100 ; i++) {
            Thread.sleep(random.nextInt(100));
            Lock writeLock = paxos.lock.writeLock();
            writeLock.lock();
            try {
                paxos.allNodes.stream().map(node -> Integer.toString(node.value.value))
                        .forEach(leader -> System.out.print(leader + ","));
                System.out.println();

                Map<Integer, List<Node>> collect = paxos.allNodes.stream()
                        .collect(Collectors.groupingBy(node -> node.value.value));

                Assert.assertEquals(1, collect.size());

            } finally {
                writeLock.unlock();
            }

        }

    }

}
