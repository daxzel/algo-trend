package algo.datastructures.heap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tsarevskiy
 */
public class FibonacciHeap {

    Node min;

    int rootCount;

    class Node {
        int value;
        Node parent;
        Node child;
        Node left;
        Node right;
        int childCount = 0;
        boolean mark = false;
    }

    public void add(int value) {
        Node node = new Node();
        node.value = value;
        node.left = node;
        node.right = node;
        min = mergeLists(min, node);
        rootCount++;
    }

    public int min() {
        return min.value;
    }

    public int remove() {
        Node tempMin = min;

        if (min.right == min) {
            min = null;
        } else {
            remove(min);
            min = min.right;
        }
        if (tempMin.child != null) {
            Node child = tempMin.child;
            do {
                child.parent = null;
                child = child.left;
            } while (tempMin.child != child);
        }

        min = mergeLists(min, tempMin.child);

        if (min != null) {
            List<Node> treeTable = new ArrayList<>();
            List<Node> toVisit = new ArrayList<>();

            for (Node curr = min; toVisit.isEmpty() || toVisit.get(0) != curr; curr = curr.right)
                toVisit.add(curr);

            for (Node curr : toVisit) {
            /* Keep merging until a match arises. */
                while (true) {
                /* Ensure that the list is long enough to hold an element of this
                 * degree.
                 */
                    while (curr.childCount >= treeTable.size())
                        treeTable.add(null);

                /* If nothing's here, we're can record that this tree has this size
                 * and are done processing.
                 */
                    if (treeTable.get(curr.childCount) == null) {
                        treeTable.set(curr.childCount, curr);
                        break;
                    }

                /* Otherwise, merge with what's there. */
                    Node other = treeTable.get(curr.childCount);
                    treeTable.set(curr.childCount, null); // Clear the slot

                /* Determine which of the two trees has the smaller root, storing
                 * the two tree accordingly.
                 */
                    Node min = (other.value < curr.value) ? other : curr;
                    Node max = (other.value < curr.value) ? curr : other;

                /* Break max out of the root list, then merge it into min's child
                 * list.
                 */
                    max.right.left = max.left;
                    max.left.right = max.right;

                /* Make it a singleton so that we can merge it. */
                    max.right = max.left = max;
                    min.child = mergeLists(min.child, max);

                /* Reparent max appropriately. */
                    max.parent = min;

                /* Clear max's mark, since it can now lose another child. */
                    max.mark = false;

                /* Increase min's degree; it now has another child. */
                    min.childCount = min.childCount + 1;

                /* Continue merging this tree. */
                    curr = min;
                }

            /* Update the global min based on this node.  Note that we compare
             * for <= instead of < here.  That's because if we just did a
             * reparent operation that merged two different trees of equal
             * priority, we need to make sure that the min pointer points to
             * the root-level one.
             */
                if (curr.value <= min.value) min = curr;
            }
        }
        return tempMin.value;
    }


    private Node mergeLists(Node first, Node second) {
        if (first == null && second == null) {
            return null;
        } else if (first != null && second == null) {
            return first;
        } else if (first == null && second != null) {
            return second;
        } else {
            Node oneNext = first.right;
            first.right = second.right;
            first.right.left = first;
            second.right = oneNext;
            second.right.left = second;

            return first.value < second.value ? first : second;
        }
    }

    private void remove(Node node) {
        node.right.left = node.left;
        node.left.right = node.right;
    }

}
