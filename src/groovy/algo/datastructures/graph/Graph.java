package algo.datastructures.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Tsarevskiy
 */
public class Graph<T> {

    private Set<Node<T>> nodes = new HashSet<>();

    public Set<Node<T>> getNodes() {
        return nodes;
    }

    public Set<Arc> getArcs() {
        Set<Arc> result = new HashSet<>();
        for (Node<T> node : nodes) {
            result.addAll(node.connections);
        }
        return result;
    }

    public class Node<C> {

        List<Arc<C>> connections = new ArrayList<>();
        C value;

        public List<Arc<C>> getConnections() {
            return connections;
        }

        public C getValue() {
            return value;
        }
    }

    public class Arc<C> {

        Node<C> left;
        Node<C> right;
        Integer weight;

        Arc(Node<C> left, Node<C> right, Integer weight) {
            this.left = left;
            this.right = right;
            this.weight = weight;
        }

        public Node<C> getLeft() {
            return left;
        }

        public Node<C> getRight() {
            return right;
        }

        public Integer getWeight() {
            return weight;
        }
    }

    public Node<T> createNewNode(T value) {
        Node<T> node = new Node<>();
        node.value = value;
        nodes.add(node);
        return node;
    }

    public void connect(Node<T> from, Node<T> to, Integer weight) {
        Arc<T> arc = new Arc<>(from, to, weight);
        from.connections.add(arc);
        to.connections.add(arc);
    }

}
