package algo.algorithm.graph;

import algo.datastructures.graph.Graph;
import algo.datastructures.graph.Graph.Arc;
import algo.datastructures.graph.Graph.Node;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BellmanFord {

    /**
     * The Bellman–Ford algorithm is an algorithm that computes shortest paths from a single source vertex to all of
     * the other vertices in a weighted digraph.[1] It is slower than Dijkstra's algorithm for the same problem, but
     * more versatile, as it is capable of handling graphs in which some of the edge weights are negative numbers. The
     * algorithm was first proposed by Alfonso Shimbel in 1955, but is instead named after Richard Bellman and Lester
     * Ford, Jr., who published it in 1958 and 1956, respectively. Edward F. Moore also published the same algorithm
     * in 1957, and for this reason it is also sometimes called the Bellman–Ford–Moore algorithm.
     *
     * Negative edge weights are found in various applications of graphs, hence the usefulness of this algorithm.
     * If a graph contains a "negative cycle" (i.e. a cycle whose edges sum to a negative value) that is reachable
     * from the source, then there is no cheapest path: any path that has a point on the negative cycle can be made
     * cheaper by one more walk around the negative cycle. In such a case, the Bellman–Ford algorithm can detect
     * negative cycles and report their existence.
     *
     * Worst-case performance	     Theta (V * E)
     * Best-case performance	     Theta (E)
     * Worst-case space complexity	 Theta (V)
     */
    public static Integer returnMinDistance(Graph graph, Graph.Node from, Graph.Node to) {
        Set<Arc> arcs = graph.getArcs();

        Map<Node, Integer> result = new HashMap<>();
        result.put(from, 0);
        boolean mutation = true;
        while (mutation) {
            mutation = false;
            for (Arc arc : arcs) {
                Node left = arc.getLeft();
                Node right = arc.getRight();
                Integer leftValue = result.get(left);
                if (leftValue != null) {
                    Integer rightValue = result.get(right);
                    if (rightValue == null) {
                        result.put(right, arc.getWeight() + leftValue);
                        mutation = true;
                    } else {
                        int newValue = leftValue + arc.getWeight();
                        if (newValue < rightValue) {
                            result.put(right, newValue);
                            mutation = true;
                        }
                    }
                }
            }
        }
        return result.get(to);
    }

}
