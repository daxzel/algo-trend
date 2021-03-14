package algo.algorithm.graph;

import static algo.datastructures.Graph.Arc.MIN_COMPARATOR;

import algo.datastructures.Graph;
import algo.datastructures.Graph.Arc;
import algo.datastructures.Graph.Node;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Kruskal {

    public static <T> Graph<T> minSpanningTree(Graph<T> graph) {

        Graph<T> result = new Graph<>();

        Map<Node<T>, Node<T>> inToOutNodes = new HashMap<>();

        for (Node<T> in : graph.getNodes()) {
            Node<T> out = result.createNewNode(in.getValue());
            inToOutNodes.put(in, out);
        }

        PriorityQueue<Arc<T>> minHeap = new PriorityQueue<>(MIN_COMPARATOR);
        minHeap.addAll(graph.getArcs());

        Set<Node> added = new HashSet<>();

        while (!minHeap.isEmpty()) {
            Arc<T> arc = minHeap.poll();
            if (!added.contains(arc.getLeft()) && !added.contains(arc.getRight())) {
                Node<T> left = inToOutNodes.get(arc.getLeft());
                Node<T> right = inToOutNodes.get(arc.getRight());
                result.connect(left, right, arc.getWeight());
                added.add(arc.getLeft());
                added.add(arc.getRight());
            }
        }
        return result;

    }

}
