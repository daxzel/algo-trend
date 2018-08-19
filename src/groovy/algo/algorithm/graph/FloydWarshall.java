package algo.algorithm.graph;

import algo.datastructures.graph.Graph;
import algo.datastructures.graph.Graph.Arc;
import algo.datastructures.graph.Graph.Node;

import java.util.Set;

public class FloydWarshall {

    /**
     * In computer science, the Floyd–Warshall algorithm is an algorithm for finding shortest paths in a weighted
     * graph with positive or negative edge weights (but with no negative cycles).[1][2] A single execution of the
     * algorithm will find the lengths (summed weights) of shortest paths between all pairs of vertices.
     * Although it does not return details of the paths themselves, it is possible to reconstruct the paths with
     * simple modifications to the algorithm. Versions of the algorithm can also be used for finding the transitive
     * closure of a relation {\displaystyle R} R, or (in connection with the Schulze voting system) widest paths
     * between all pairs of vertices in a weighted graph.
     *
     * Worst-case performance	    O(V^3)
     * Best-case performance	    O(V^3)
     * Average performance	        O(V^3)
     * Worst-case space complexity	O(V^2)
     */
    public static Integer[][] returnMinDistance(Graph graph) {

        Set<Node> graphNodes = graph.getNodes();
        Node[] nodes = graphNodes.toArray(new Node[0]);

        Integer[][] result = prepareMatrix(nodes);

        for (int i = 0; i < nodes.length; i++) {
            result = doOptimization(i, nodes, result);
        }

        return result;
    }

    private static Integer[][] doOptimization(int nodeIndex, Node[] nodes, Integer[][] previousResult) {
        Integer[][] result = new Integer[nodes.length][nodes.length];
        for (int i = 0; i < nodes.length; i++) {
            Node first = nodes[i];
            for (int j = 0; j < nodes.length; j++) {
                Node second = nodes[j];
                if (first == second) {
                    result[i][j] = 0;
                } else {
                    if (nodeIndex == i || nodeIndex == j) {
                        result[i][j] = previousResult[i][j];
                    } else {
                        Integer path = previousResult[i][j];

                        Integer possible1 = previousResult[i][nodeIndex];
                        Integer possible2 = previousResult[nodeIndex][j];
                        Integer possible = null;
                        if (possible1 != null && possible2 != null) {
                            possible = possible1 + possible2;
                        }

                        if (path != null && possible != null) {
                            result[i][j] = Math.min(path, possible);
                        } else {
                            if (path != null) {
                                result[i][j] = path;
                            } else {
                                if (possible != null) {
                                    result[i][j] = possible;
                                } else {
                                    result[i][j] = null;
                                }
                            }

                        }
                    }
                }
            }
        }
        return result;
    }

    private static Integer[][] prepareMatrix(Node[] nodes) {
        Integer[][] result = new Integer[nodes.length][nodes.length];

        for (int i = 0; i < nodes.length; i++) {
            Node first = nodes[i];
            for (int j = 0; j < nodes.length; j++) {
                Node second = nodes[j];
                result[i][j] = null;
                if (first == second) {
                    result[i][j] = 0;
                } else {
                    for (Arc arc : first.getConnections()) {
                        if (arc.getRight() == second) {
                            result[i][j] = arc.getWeight();
                        }
                    }
                }
            }
        }
        return result;
    }

}
