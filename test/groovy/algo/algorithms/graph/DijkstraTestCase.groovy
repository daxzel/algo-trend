package algo.algorithms.graph

import algo.algorithms.graph.Dijkstra
import algo.datastructures.graph.Graph

/**
 * Created by Tsarevskiy
 */
class DijkstraTestCase extends GroovyTestCase {

    void testDijkstra() {
        Graph graph = new Graph()
        def firstNode = graph.createNewNode(1)
        def secondNode = graph.createNewNode(2)
        def thirdNode = graph.createNewNode(3)
        def sixthNode = graph.createNewNode(6)
        def fifthNode = graph.createNewNode(5)
        def fourthNode = graph.createNewNode(4)

        graph.connect(firstNode, secondNode, 7)
        graph.connect(firstNode, thirdNode, 9)
        graph.connect(firstNode, sixthNode, 14)
        graph.connect(secondNode, thirdNode, 10)
        graph.connect(thirdNode, sixthNode, 2)
        graph.connect(sixthNode, fifthNode, 9)
        graph.connect(fifthNode, fourthNode, 6)
        graph.connect(thirdNode, fourthNode, 11)
        graph.connect(secondNode, fourthNode, 15)

        def distance = Dijkstra.returnMinDistance(graph, firstNode, fourthNode);
        assertEquals(distance.pop().value, 4)
        assertEquals(distance.pop().value, 3)
        assertEquals(distance.pop().value, 1)
    }
}
