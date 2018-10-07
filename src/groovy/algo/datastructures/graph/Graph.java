package algo.datastructures.graph;

import org.testng.collections.Lists;

import java.util.ArrayList;
import java.util.Comparator;
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

    public Set<Arc<T>> getArcs() {
        Set<Arc<T>> result = new HashSet<>();
        for (Node<T> node : nodes) {
            result.addAll(node.getConnections());
        }
        return result;
    }

    public static class Node<C> {

        List<Arc<C>> out = new ArrayList<>();
        List<Arc<C>> in = new ArrayList<>();
        C value;

        public List<Arc<C>> getConnections() {
            List<Arc<C>> connections = Lists.newArrayList(out);
            connections.addAll(in);
            return connections;
        }

        public C getValue() {
            return value;
        }

        public List<Arc<C>> getOut() {
            return out;
        }

        public List<Arc<C>> getIn() {
            return in;
        }
    }

    public static class Arc<C> {

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

        public static Comparator<? super Arc<?>> MIN_COMPARATOR = (o1, o2) -> {
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
        };
    }

    public Node<T> createNewNode(T value) {
        Node<T> node = new Node<>();
        node.value = value;
        nodes.add(node);
        return node;
    }

    public void connect(Node<T> from, Node<T> to, Integer weight) {
        Arc<T> arc = new Arc<>(from, to, weight);
        from.out.add(arc);
        to.in.add(arc);
    }

    public int size() {
        return nodes.size();
    }

}
