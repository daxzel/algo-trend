package algo.algorithm.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import algo.datastructures.Graph;
import algo.datastructures.Graph.Node;
import org.junit.jupiter.api.Test;

public class PrimTests {

    @Test
    public void testPrim() {
        Graph graph = new Graph();
        Node firstNode = graph.createNewNode(1);
        Node secondNode = graph.createNewNode(2);
        Node thirdNode = graph.createNewNode(3);

        graph.connect(firstNode, secondNode, 7);
        graph.connect(firstNode, thirdNode, 9);
        graph.connect(thirdNode, secondNode, 1);
        graph.connect(secondNode, thirdNode, 5);
        graph.connect(thirdNode, firstNode, 30);

        Graph<Object> spanningTree = Prim.minSpanningTree(graph);
        assertEquals(spanningTree.size(), 3);
        assertTrue(CycleDFS.containsCycles(graph));
        assertFalse(CycleDFS.containsCycles(spanningTree));
    }

}
