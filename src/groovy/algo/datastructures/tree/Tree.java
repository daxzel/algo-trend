package algo.datastructures.tree;

import java.util.HashSet;
import java.util.Set;

public class Tree<T> {

    public static class Node<C> {

        private C value;
        private Set<Node<C>> children = new HashSet<>();

        public Node(C value) {
            this.value = value;
        }

        public C getValue() {
            return value;
        }

        public void addChild(Node<C> child) {
            children.add(child);
        }

        public void removeChild(Node<C> child) {
            children.remove(child);
        }

        public Set<Node<C>> getChildren() {
            return children;
        }
    }

    public Node<T> root;

    public void setRoot(Node<T> root) {
        this.root = root;
    }
}
