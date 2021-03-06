package algo.algorithm.graph;


import algo.datastructures.Graph;
import java.util.Collection;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/**
 * Created by Tsarevskiy
 */
@SuppressWarnings("unchecked")
public class Dijkstra {

    /**
     * Dijkstra's original algorithm does not use a min-priority queue and runs in time O(|V|^2) (where |V| is the
     * number of nodes).
     */
    public static <T> Deque<Graph.Node> returnMinDistance(Graph<T> graph, Graph.Node<T> from,
            Graph.Node<T> to) {
        Set<Graph.Node> unvisited = new HashSet<>();

        Map<Graph.Node, Integer> dist = new HashMap<>();
        Map<Graph.Node, Graph.Node> prev = new HashMap<>();

        graph.getNodes().forEach(node -> {
            dist.put(node, Integer.MAX_VALUE);
            unvisited.add(node);
        });

        Graph.Node firstNode = from;

        while (!unvisited.isEmpty()) {
            final Graph.Node<T> node;
            if (firstNode != null) {
                node = firstNode;
                firstNode = null;
            } else {
                node = getWithMinValue(unvisited, dist);
            }
            if (node == to) {
                break;
            }
            unvisited.remove(node);
            node.getConnections().forEach(arc -> {
                Graph.Node anotherNode = getAnotherEnd(arc, node);
                Integer distance = dist.get(node);

                Integer alt = (distance == Integer.MAX_VALUE ? 0 : distance) + arc.getWeight();
                if (alt < dist.get(anotherNode)) {
                    dist.put(anotherNode, alt);
                    prev.put(anotherNode, node);
                }
            });
        }

        Deque<Graph.Node> path = new LinkedList<>();
        Graph.Node current = to;
        while (prev.get(current) != null && current != from) {
            path.addFirst(current);
            Graph.Node prevNode = prev.get(current);
            prev.remove(current);
            current = prevNode;
        }
        path.addFirst(current);
        return path;
    }

    private static Graph.Node getWithMinValue(Collection<Graph.Node> unvisited, Map<Graph.Node, Integer> dist) {
        Graph.Node minNode = unvisited.iterator().next();
        Integer min = dist.get(minNode);
        for (Graph.Node node : unvisited) {
            if (dist.get(node) < min) {
                minNode = node;
            }
        }
        return minNode;
    }

    private static Graph.Node getAnotherEnd(Graph.Arc arc, Graph.Node node) {
        if (arc.getLeft() == node) {
            return arc.getRight();
        }
        if (arc.getRight() == node) {
            return arc.getLeft();
        }
        return null;
    }

}
