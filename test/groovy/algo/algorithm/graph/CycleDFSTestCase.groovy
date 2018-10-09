package algo.algorithm.graph

import algo.datastructures.graph.Graph

class CycleDFSTestCase extends GroovyTestCase {

    void testCycleTrueDFS() {
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
        graph.connect(sixthNode, secondNode, 14)
        graph.connect(fifthNode, fourthNode, 14)

        def containsCycles = CycleDFS.containsCycles(graph)
        assertTrue(containsCycles)
    }

    void testCycleFalseDFS() {
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
        graph.connect(fifthNode, fourthNode, 14)

        def containsCycles = CycleDFS.containsCycles(graph)
        assertFalse(containsCycles)
    }

}
