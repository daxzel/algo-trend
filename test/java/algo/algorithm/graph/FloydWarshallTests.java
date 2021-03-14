package algo.algorithm.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;

import algo.datastructures.Graph;
import algo.datastructures.Graph.Node;
import algo.utils.Printer;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * Created by Tsarevskiy
 */
public class FloydWarshallTests {

    @Test
    public void testFloydWarshall() {
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

        Integer[][] distance = FloydWarshall.returnMinDistance(graph);

        Printer.printArray(distance);
    }

    @Test
    @Disabled("Double check the example from youtube")
    public void testYouTubeExample() {
        Graph graph = new Graph();
        Node firstNode = graph.createNewNode(1);
        Node secondNode = graph.createNewNode(2);
        Node thirdNode = graph.createNewNode(3);
        Node fourthNode = graph.createNewNode(4);

        graph.connect(firstNode, secondNode, 3);
        graph.connect(firstNode, fourthNode, 2);
        graph.connect(secondNode, firstNode, 8);
        graph.connect(secondNode, thirdNode, 2);
        graph.connect(thirdNode, firstNode, 5);
        graph.connect(thirdNode, fourthNode, 1);
        graph.connect(fourthNode, firstNode, 2);

        Integer[][] distance = FloydWarshall.returnMinDistance(graph);

        Printer.printArray(distance);
        assertEquals(distance[0][0], 0);
        assertEquals(distance[1][1], 0);
        assertEquals(distance[2][2], 0);
        assertEquals(distance[3][3], 0);
        assertEquals(distance[0][2], 1);
        assertEquals(distance[3][0], 2);
        assertEquals(distance[3][2], 3);
        assertEquals(distance[1][3], 3);
    }

}
