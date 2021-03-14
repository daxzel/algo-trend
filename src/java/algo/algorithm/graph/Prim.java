package algo.algorithm.graph;

import static algo.datastructures.Graph.Arc.MIN_COMPARATOR;

import algo.datastructures.Graph;
import algo.datastructures.Graph.Arc;
import algo.datastructures.Graph.Node;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Prim {

    public static <T> Graph<T> minSpanningTree(Graph<T> graph) {

        if (graph.size() == 0) {
            return new Graph<>();
        }

        Node<T> first = graph.getNodes().iterator().next();

        Graph<T> result = new Graph<T>();
        Node<T> root = result.createNewNode(first.getValue());
        Map<Node<T>, Node<T>> visited = new HashMap<>();
        visited.put(first, root);

        // Inverted comparator to have min priority queue
        PriorityQueue<Graph.Arc<T>> minQueue = new PriorityQueue<>(MIN_COMPARATOR);

        minQueue.addAll(first.getOut());

        while (visited.size() < graph.size()) {
            Arc<T> arc = minQueue.poll();
            Node<T> node = arc.getRight();
            if (!visited.containsKey(node)) {
                Node<T> tNode = visited.get(arc.getLeft());
                Node<T> newTNode = result.createNewNode(node.getValue());
                visited.put(node, newTNode);
                graph.connect(tNode, newTNode, arc.getWeight());
                minQueue.addAll(node.getConnections());
            }
        }

        return result;
    }

}
