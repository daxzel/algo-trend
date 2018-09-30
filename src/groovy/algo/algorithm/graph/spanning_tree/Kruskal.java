package algo.algorithm.graph.spanning_tree;

import algo.datastructures.graph.Graph;
import algo.datastructures.graph.Graph.Arc;
import algo.datastructures.graph.Graph.Node;
import algo.datastructures.tree.Tree;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Kruskal {

    public static <T> Tree<T> minSpanningTree(Graph<T> graph) {

        if (graph.size() == 0) {
            return new Tree<T>();
        }

        Node<T> first = graph.getNodes().iterator().next();

        Tree.Node<T> root = new Tree.Node<>(first.getValue());
        Map<Node<T>, Tree.Node<T>> visited = new HashMap<>();
        visited.put(first, root);

        // Inverted comparator to have min priority queue
        PriorityQueue<Graph.Arc<T>> minQueue = new PriorityQueue<>((o1, o2) -> {
            Integer w1 = o1.getWeight();
            Integer w2 = o2.getWeight();
            if (w1.equals(w2)) {
                return 0;
            } else {
                if (w1 > w2) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });

        minQueue.addAll(first.getOut());

        while (visited.size() < graph.size()) {
            Arc<T> arc = minQueue.poll();
            Node<T> node = arc.getRight();
            if (!visited.containsKey(node)) {
                Tree.Node<T> tNode = visited.get(arc.getLeft());
                Tree.Node<T> newTNode = new Tree.Node<>(node.getValue());
                visited.put(node, newTNode);
                tNode.addChild(newTNode);
                minQueue.addAll(node.getConnections());
            }
        }

        Tree<T> result = new Tree<>();
        result.setRoot(root);
        return result;
    }

}
