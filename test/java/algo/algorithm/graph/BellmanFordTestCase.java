package algo.algorithm.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;

import algo.algorithm.graph.shortest_path.BellmanFord;
import algo.datastructures.graph.Graph;
import algo.datastructures.graph.Graph.Node;
import org.junit.jupiter.api.Test;

public class BellmanFordTestCase {

    @Test
    public void testBellmanFord() {
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
        graph.connect(secondNode, thirdNode, 10);
        graph.connect(thirdNode, sixthNode, 2);
        graph.connect(sixthNode, fifthNode, 9);
        graph.connect(fifthNode, fourthNode, 6);
        graph.connect(thirdNode, fourthNode, 11);
        graph.connect(secondNode, fourthNode, 15);

        Integer distance = BellmanFord.returnMinDistance(graph, firstNode, fourthNode);

        assertEquals(20, distance);
    }

}
