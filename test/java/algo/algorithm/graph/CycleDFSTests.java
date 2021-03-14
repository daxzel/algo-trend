package algo.algorithm.graph;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import algo.datastructures.Graph;
import algo.datastructures.Graph.Node;
import org.junit.jupiter.api.Test;

public class CycleDFSTests {

    @Test
    public void testCycleTrueDFS() {
        Graph graph = new Graph();
        Node firstNode = graph.createNewNode(1);
        Node secondNode = graph.createNewNode(2);
        Node thirdNode = graph.createNewNode(3);
        Node sixthNode = graph.createNewNode(6);
        Node fifthNode = graph.createNewNode(5);
        Node fourthNode = graph.createNewNode(4);

        graph.connect(firstNode, secondNode, 7);
        graph.connect(firstNode, thirdNode, 9);
        graph.connect(firstNode, sixthNode, 14);
        graph.connect(sixthNode, secondNode, 14);
        graph.connect(fifthNode, fourthNode, 14);

        boolean containsCycles = CycleDFS.containsCycles(graph);
        assertTrue(containsCycles);
    }

    @Test
    public void testCycleFalseDFS() {
        Graph graph = new Graph();
        Node firstNode = graph.createNewNode(1);
        Node secondNode = graph.createNewNode(2);
        Node thirdNode = graph.createNewNode(3);
        Node sixthNode = graph.createNewNode(6);
        Node fifthNode = graph.createNewNode(5);
        Node fourthNode = graph.createNewNode(4);

        graph.connect(firstNode, secondNode, 7);
        graph.connect(firstNode, thirdNode, 9);
        graph.connect(firstNode, sixthNode, 14);
        graph.connect(fifthNode, fourthNode, 14);

        boolean containsCycles = CycleDFS.containsCycles(graph);
        assertFalse(containsCycles);
    }

}
