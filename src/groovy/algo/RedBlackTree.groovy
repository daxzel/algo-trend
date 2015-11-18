package groovy.algo

/**
 * Created by tsarevskiy on 18/11/15.
 */
class RedBlackTree {

    class Element {
        boolean red = false
        Element root
        Element left
        Element right
        int value

        Element(int value, boolean red) {
            this.value = value
        }

        boolean black() { !red }

        boolean red() { red }

        void toBlack() {
            red = false
        }

        void toRed() {
            red = true
        }
    }

    Element root

    void add(int value) {
        Element element
        if (root == null) {
            root = new Element(value, true)
            element = root
        } else {
            element = addInternal(root, value)
        }
        fix(element)
    }

    boolean contains(int value) {
        return containsInternal(root, value)
    }

    boolean containsInternal(Element root, int value) {
        if (root == null) {
            return false
        } else {
            if (root.value == value) {
                return true
            } else {
                if (value < root.value) {
                    return containsInternal(root.left, value)
                } else {
                    return containsInternal(root.right, value)
                }
            }
        }
    }

    /**
     * Case 1: The current node N is at the root of the tree. In this case, it is repainted black to satisfy property
     * 2 (the root is black). Since this adds one black node to every path at once, property 5 (all paths from any
     * given node to its leaf nodes contain the same number of black nodes) is not violated.
     */
    void fix(Element element) {
        if (element.root == null) {
            element.red = false
        } else {
            fixCase2(element)
        }
    }

    /**
     * The current node's parent P is black, so property 4 (both children of every red node are black) is not
     * invalidated. In this case, the tree is still valid. Property 5 (all paths from any given node to its leaf nodes
     * contain the same number of black nodes) is not threatened, because the current node N has two black leaf
     * children, but because N is red, the paths through each of its children have the same number of black nodes
     * as the path through the leaf it replaced, which was black, and so this property remains satisfied.
     */
    void fixCase2(Element element) {
        if (element.root.red()) {
            fixCase3(element)
        }
    }

    /**
     *  If both the parent P and the uncle U are red, then both of them can be repainted black and the grandparent G
     *  becomes red (to maintain property 5 (all paths from any given node to its leaf nodes contain the same number of
     *  black nodes)). Now, the current red node N has a black parent. Since any path through the parent or uncle must
     *  pass through the grandparent, the number of black nodes on these paths has not changed. However, the grandparent
     *  G may now violate properties 2 (The root is black) or 4 (Both children of every red node are black) (property 4
     *  possibly being violated since G may have a red parent). To fix this, the entire procedure is recursively
     *  performed on G from case 1. Note that this is a tail-recursive call, so it could be rewritten as a loop; since
     *  this is the only loop, and any rotations occur after this loop, this proves that a constant number of rotations
     *  occur.
     */
    void fixCase3(Element element) {
        Element uncle = uncle(element)

        if ((uncle != null) && (uncle.red())) {
            element.root.toBlack()
            uncle.toBlack()
            Element g = grandParent(element)
            g.toRed()
            fix(g)
        } else {
            fixCase4(element)
        }
    }

    /**
     * The parent P is red but the uncle U is black; also, the current node N is the right child of P, and P in turn
     * is the left child of its parent G. In this case, a left rotation on P that switches the roles of the current
     * node N and its parent P can be performed; then, the former parent node P is dealt with using case 5 (relabeling
     * N and P) because property 4 (both children of every red node are black) is still violated. The rotation causes
     * some paths (those in the sub-tree labelled "1") to pass through the node N where they did not before. It also
     * causes some paths (those in the sub-tree labelled "3") not to pass through the node P where they did before.
     * However, both of these nodes are red, so property 5 (all paths from any given node to its leaf nodes contain
     * the same number of black nodes) is not violated by the rotation. After this case has been completed, property
     * 4 (both children of every red node are black) is still violated, but now we can resolve this by continuing to
     * case 5.
     */
    void fixCase4(Element element) {
        Element g = grandParent(element)
        if ((element == element.root.right) && (element.root == g.left)) {
            rotate_left(element)
            element = element.left
        } else {
            if ((element == element.root.left) && (element.root == g.right)) {
                rotate_right(element)
                element = element.right
            }
        }
        fixCase5(element)
    }

    /**
     *  The parent P is red but the uncle U is black, the current node N is the left child of P, and P is the left
     *  child of its parent G. In this case, a right rotation on G is performed; the result is a tree where the former
     *  parent P is now the parent of both the current node N and the former grandparent G. G is known to be black,
     *  since its former child P could not have been red otherwise (without violating property 4). Then, the colors of
     *  P and G are switched, and the resulting tree satisfies property 4 (both children of every red node are black).
     *  Property 5 (all paths from any given node to its leaf nodes contain the same number of black nodes) also
     *  remains satisfied, since all paths that went through any of these three nodes went through G before, and now
     *  they all go through P. In each case, this is the only black node of the three.
     */
    void fixCase5(Element element) {
        Element grandpa = grandParent(element);

        element.root.toBlack()
        grandpa.toRed()
        if (element == element.root.left)
            rotate_right(element.root);
        else
            rotate_left(element.root);
    }

    void rotate_left(Element element) {
        Element g = grandParent(element)
        Element saved_p = g.left
        Element saved_left_n = element.left
        g.left = element;
        element.left = saved_p;
        saved_p.right = saved_left_n;
    }

    void rotate_right(Element element) {
        Element g = grandParent(element)
        Element saved_p = g.right
        Element saved_right_n = element.right
        g.right = element;
        element.right = saved_p;
        saved_p.left = saved_right_n;
    }

    Element addInternal(Element element, int value) {
        if (value < element.value) {
            if (element.left != null) {
                return addInternal(element.left, value)
            } else {
                return element.left = new Element(value, true)
            }
        } else {
            if (element.right != null) {
                return addInternal(element.right, value)
            } else {
                return element.right = new Element(value, true)
            }
        }
    }

    Element uncle(Element element) {
        Element grandPa = grandParent(element)
        if (grandPa == null) {
            return null
        }
        if (element.root == grandPa.left) {
            return grandPa.right
        } else {
            return grandPa.left
        }
    }

    Element grandParent(Element element) {
        if (element != null && element.root != null) {
            return element.root.root
        } else {
            return null
        }
    }
}
