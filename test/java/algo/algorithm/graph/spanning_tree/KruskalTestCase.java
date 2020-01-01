package algo.algorithm.graph.spanning_tree;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import algo.algorithm.graph.CycleDFS;
import algo.datastructures.graph.Graph;
import algo.datastructures.graph.Graph.Node;
import org.junit.jupiter.api.Test;

public class KruskalTestCase {

    @Test
    public void testKruskal() {
        Graph graph = new Graph();
        Node firstNode = graph.createNewNode(1);
        Node secondNode = graph.createNewNode(2);
        Node thirdNode = graph.createNewNode(3);

        graph.connect(firstNode, secondNode, 7);
        graph.connect(firstNode, thirdNode, 9);
        graph.connect(thirdNode, secondNode, 1);
        graph.connect(secondNode, thirdNode, 5);
        graph.connect(thirdNode, firstNode, 30);

        Graph<Object> spanningTree = Kruskal.minSpanningTree(graph);

        assertEquals(spanningTree.size(), 3);
        assertFalse(CycleDFS.containsCycles(spanningTree));
    }

}
