package algo.algorithm.graph.spanning_tree

import algo.datastructures.graph.Graph

class KruskalTestCase extends GroovyTestCase {

    void testKruskal() {
        Graph graph = new Graph()
        def firstNode = graph.createNewNode(1)
        def secondNode = graph.createNewNode(2)
        def thirdNode = graph.createNewNode(3)

        graph.connect(firstNode, secondNode, 7)
        graph.connect(firstNode, thirdNode, 9)
        graph.connect(thirdNode, secondNode, 1)
        graph.connect(secondNode, thirdNode, 5)
        graph.connect(thirdNode, firstNode, 30)

        Kruskal.minSpanningTree(graph)
    }

}
