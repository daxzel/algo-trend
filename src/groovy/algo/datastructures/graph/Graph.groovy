package algo.datastructures.graph

/**
 * Created by Tsarevskiy
 */
class Graph {

    Set<Node> nodes = new HashSet<>()

    class Node {
        List<Arc> connections = new ArrayList<>()
        Object value
    }

    class Arc {
        Node left
        Node right
        Integer weight

        public Arc(Node left, Node right, Integer weight) {
            this.left = left
            this.right = right
            this.weight = weight
        }
    }

    public Node createNewNode(Object value) {
        Node node = new Node()
        node.value = value
        nodes.add(node)
        return node
    }

    public void connect(Node from, Node to, Integer weight) {
        Arc arc = new Arc(from, to, weight)
        from.connections.add(arc)
        to.connections.add(arc)
    }

}
