package algo.datastructures.tree.self_balanced;

/**
 * Named after Georgy Adelson-Velsky and Evgenii Landis.
 * <p>
 * Algorithm   	Worst case Space		O(n) Search		O(log n) Insert		O(log n) Delete		O(log n)
 */
public class AVLTree {

    class Element {

        int balanceFactor = 0;
        int height = 1;
        Element root;
        Element left;
        Element right;
        int value;

        Element(int value) {
            this.value = value;
        }

    }

    Element root;

    void add(int value) {
        Element element;
        if (root == null) {
            root = new Element(value);
            element = root;
        } else {
            element = addInternal(root, value);
        }
    }

    private Element addInternal(Element element, int value) {
        if (value < element.value) {
            if (element.left != null) {
                Element result = addInternal(element.left, value);
                updateBalance(element);
                rebalance(element);
                return result;
            } else {
                element.left = new Element(value);
                element.left.root = element;
                updateBalance(element);
                rebalance(element);
                return element.left;
            }
        } else {
            if (element.right != null) {
                Element result = addInternal(element.right, value);
                updateBalance(element);
                rebalance(element);
                return result;
            } else {
                element.right = new Element(value);
                element.right.root = element;
                updateBalance(element);
                rebalance(element);
                return element.right;
            }
        }
    }

    private void rebalance(Element node) {
        if (node.balanceFactor == 2) {
            if (node.right.balanceFactor == 1) {
                rotateLeft(node);
            } else {
                if (node.right.balanceFactor == -1) {
                    rotateRight(node.right);
                    rotateLeft(node);
                }
            }
        } else {
            if (node.balanceFactor == -2) {
                if (node.left.balanceFactor == 1) {
                    rotateRight(node);
                } else {
                    if (node.right.balanceFactor == 1) {
                        rotateLeft(node.left);
                        rotateRight(node);
                    }
                }
            }
        }
    }

    boolean contains(int value) {
        return containsInternal(root, value);
    }

    private boolean containsInternal(Element root, int value) {
        if (root == null) {
            return false;
        } else {
            if (root.value == value) {
                return true;
            } else {
                if (value < root.value) {
                    return containsInternal(root.left, value);
                } else {
                    return containsInternal(root.right, value);
                }
            }
        }
    }

    private void rotateLeft(Element node) {
        Element right = node.right;
        Element t23 = right.left;

        Element root = node.root;

        right.left = node;
        node.root = right;

        node.right = t23;
        if (t23 != null) {
            t23.root = node;
        }

        if (root.left == node) {
            root.left = right;
        } else {
            root.right = right;
        }

        updateBalance(node);
        updateBalance(right);
    }

    private void rotateRight(Element node) {
        Element left = node.left;
        Element t23 = left.right;

        Element root = node.root;

        left.right = node;
        node.root = left;

        node.left = t23;
        if (t23 != null) {
            t23.root = node;
        }

        if (root.left == node) {
            root.left = left;
        } else {
            root.right = left;
        }

        updateBalance(node);
        updateBalance(left);
    }

    void updateBalance(Element node) {
        int leftHeight = node.left != null ? node.left.height : 0;
        int rightHeight = node.right != null ? node.right.height : 0;
        node.height = Math.max(rightHeight, leftHeight) + 1;
        node.balanceFactor = rightHeight - leftHeight;
    }

}
