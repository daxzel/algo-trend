package algo.algorithm.graph;

import algo.datastructures.graph.Graph;
import algo.datastructures.graph.Graph.Arc;
import algo.datastructures.graph.Graph.Node;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CycleDFS {

    public static <T> boolean containsCycles(Graph<T> graph) {

        if (graph.size() == 0) {
            return false;
        }

        Set<Node<T>> visited = new HashSet<>();
        Set<Node<T>> nodes = graph.getNodes();
        for (Node<T> node : nodes) {
            if (!visited.contains(node)) {
                if (internalContainsCycle(node, visited, new HashSet<>())) {
                    return true;
                }
            }
        }

        return false;
    }

    private static <T> boolean internalContainsCycle(Node<T> node,
            Set<Node<T>> visited,
            Set<Node<T>> visitedInternal) {
        if (visitedInternal.contains(node)) {
            return true;
        } else {
            visitedInternal.add(node);
            visited.add(node);
            List<Arc<T>> connections = node.getOut();
            for (Arc<T> connection : connections) {
                Node<T> right = connection.getRight();
                if (internalContainsCycle(right, visited, visitedInternal)) {
                    return true;
                }
            }
        }
        return false;
    }

}
