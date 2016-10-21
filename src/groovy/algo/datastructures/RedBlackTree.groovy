package algo.datastructures

/**
 * Created by Tsarevskiy
 *
 *         Average	    Worst
 *Space	    O(n)	    O(n)
 *Search	O(log n)	O(log n)
 *Insert	O(log n)    O(log n)
 *Delete	O(log n)	O(log n)
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
            this.red = red
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

    void removeElement(int value) {
        if (root) {
            Element removedElement = findInternal(root, value)
            if (removedElement) {
                removeInternal(removedElement)
            }
        }
    }

    boolean contains(int value) {
        return containsInternal(root, value)
    }

    private boolean containsInternal(Element root, int value) {
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
    private void fix(Element element) {
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
    private void fixCase2(Element element) {
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
    private void fixCase3(Element element) {
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
    private void fixCase4(Element element) {
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
    private void fixCase5(Element element) {
        Element grandpa = grandParent(element);

        element.root.toBlack()
        grandpa.toRed()
        if (element == element.root.left)
            rotate_right(element.root);
        else
            rotate_left(element.root);
    }

    private void rotate_left(Element element) {
        Element g = grandParent(element)
        Element saved_p = g.left
        Element saved_left_n = element.left
        g.left = element;
        element.root = g
        element.left = saved_p;
        saved_p.root = element
        saved_p.right = saved_left_n;
        saved_left_n.root = saved_p
    }

    private void rotate_right(Element element) {
        Element g = grandParent(element)
        Element saved_p = g.right
        Element saved_right_n = element.right
        g.right = element;
        element.root = g
        element.right = saved_p;
        saved_p.root = element
        saved_p.left = saved_right_n;
        if (saved_right_n) {
            saved_right_n.root = saved_p
        }
    }

    private Element addInternal(Element element, int value) {
        if (value < element.value) {
            if (element.left != null) {
                return addInternal(element.left, value)
            } else {
                element.left = new Element(value, true)
                element.left.root = element
                return element.left
            }
        } else {
            if (element.right != null) {
                return addInternal(element.right, value)
            } else {
                element.right = new Element(value, true)
                element.right.root = element
                return element.right
            }
        }
    }

    private Element uncle(Element element) {
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

    private Element grandParent(Element element) {
        if (element != null && element.root != null) {
            return element.root.root
        } else {
            return null
        }
    }

    private Element sibling(Element element) {
        if (element == element.root.left)
            return element.root.right;
        else
            return element.root.left;
    }

    private void replace_node(Element replacedElement, Element element) {
        if (replacedElement.left != element) {
            element.left = replacedElement.left
            if (element.left) element.left.root = element
        }
        if (replacedElement.right != element) {
            element.right = replacedElement.right
            if (element.right) element.right.root = element
        }
        if (replacedElement.root != null) {
            element.root = replacedElement
            if (replacedElement.root.left == replacedElement) {
                replacedElement.root.left = element
            } else {
                if (replacedElement.root.right == replacedElement) {
                    replacedElement.root.right = element
                }
            }
        } else {
            root = element
        }
    }

    private Element findInternal(Element element, int value) {
        if (element.value == value) {
            return element
        }
        if (value < element.value) {
            if (element.left != null) {
                return findInternal(element.left, value)
            }
        } else {
            if (element.right != null) {
                return findInternal(element.right, value)
            }
        }
    }

    static private Element successor(Element element) {
        if (element == null)
            return null;
        else if (element.right != null) {
            Element p = element.right;
            while (p.left != null)
                p = p.left;
            return p;
        } else if (element.left != null) {
            Element p = element.left;
            while (p.right != null)
                p = p.right;
            return p;
        }
        return null
    }


    private void removeInternal(Element removedElement) {
        if (removedElement.left != null && removedElement.right != null) {
            Element successor = successor(removedElement)
            removedElement.value = successor.value
            removedElement = successor
            removeInternal(removedElement)
        } else {
            Element child = removedElement.right != null ? removedElement.left : removedElement.right;

            if (child != null) {
                replace_node(removedElement, child);
                if (removedElement.black()) {
                    if (child.red()) {
                        child.toBlack()
                    } else {
                        deleteCase1(child);
                    }
                }
            } else if (removedElement.root == null) { // return if we are the only node.
                root = null;
            } else { //  No children. Use self as phantom replacement and unlink.
                if (removedElement.black())
                    deleteCase1(removedElement);

                if (removedElement.root != null) {
                    if (removedElement == removedElement.root.left)
                        removedElement.root.left = null;
                    else if (removedElement == removedElement.root.right)
                        removedElement.root.right = null;
                    removedElement.root = null;
                }
            }
        }
    }

    /**
     *  Case 1: N is the new root. In this case, we are done.
     *  We removed one black node from every path, and the new root is black, so the properties are preserved.
     */
    private void deleteCase1(Element element) {
        if (element.root != null) {
            deleteCase2(element);
        }
    }

    /**
     *  S is red. In this case we reverse the colors of P and S, and then rotate left at P, turning S into N's
     *  grandparent. Note that P has to be black as it had a red child. The resulting subtree has a path short one
     *  black node so we are not done. Now N has a black sibling and a red parent, so we can proceed to step 4, 5,
     *  or 6. (Its new sibling is black because it was once the child of the red S.) In later cases, we will relabel
     *  N's new sibling as S.
     * @param element
     */
    private void deleteCase2(Element element) {
        Element s = sibling(element);

        if (red(s)) {
            element.root.toRed()
            element.toBlack()
            if (element == element.root.left)
                rotate_left(element);
            else
                rotate_right(element);
        }
        deleteCase3(element);
    }

    /**
     * P, S, and S's children are black. In this case, we simply repaint S red. The result is that all paths passing
     * through S, which are precisely those paths not passing through N, have one less black node. Because deleting
     * N's original parent made all paths passing through N have one less black node, this evens things up. However,
     * all paths through P now have one fewer black node than paths that do not pass through P, so property 5 (all
     * paths from any given node to its leaf nodes contain the same number of black nodes) is still violated. To
     * correct this, we perform the rebalancing procedure on P, starting at case 1.
     *
     */
    private void deleteCase3(Element element) {
        Element s = sibling(element);

        if ((element.root.black()) &&
                (black(s)) &&
                (black(leftOf(s))) &&
                (black(rightOf(s)))) {
            toRed(s)
            deleteCase1(element.root);
        } else
            deleteCase4(element);
    }

    /**
     * S and S's children are black, but P is red. In this case, we simply exchange the colors of S and P. This does
     * not affect the number of black nodes on paths going through S, but it does add one to the number of black nodes
     * on paths going through N, making up for the deleted black node on those paths.
     * @param element
     */
    private void deleteCase4(Element element) {
        Element s = sibling(element);

        if ((element.root.red()) &&
                (black(s)) &&
                (black(leftOf(s))) &&
                (black(rightOf(s)))) {
            toRed(s)
            toBlack(element.root)
        } else
            deleteCase5(element);
    }

    /**
     * S is black, S's left child is red, S's right child is black, and N is the left child of its parent. In this
     * case we rotate right at S, so that S's left child becomes S's parent and N's new sibling. We then exchange
     * the colors of S and its new parent. All paths still have the same number of black nodes, but now N has a black
     * sibling whose right child is red, so we fall into case 6. Neither N nor its parent are affected by this
     * transformation. (Again, for case 6, we relabel N's new sibling as S.)
     * @param element
     */
    private void deleteCase5(Element element) {
        Element s = sibling(element);

        if (s.black()) {
            /* this if statement is trivial,
                due to case 2 (even though case 2 changed the sibling to a sibling's child,
                the sibling's child can't be red, since no red parent can have a red child). */
            /* the following statements just force the red to be on the left of the left of the parent,
               or right of the right, so case six will rotate correctly. */
            if ((element == element.root.left) &&
                    (black(rightOf(s))) &&
                    (red(leftOf(s)))) { /* this last test is trivial too due to cases 2-4. */
                s.toRed()
                s.left.toBlack()
                rotate_right(s.right);
            } else if ((element == element.root.right) &&
                    (black(leftOf(s))) &&
                    (red(rightOf(s)))) {/* this last test is trivial too due to cases 2-4. */
                s.toRed()
                s.right.toBlack()
                rotate_left(s.left);
            }
        }
        deleteCase6(element);
    }

    /**
     * Case 6: S is black, S's right child is red, and N is the left child of its parent P. In this case we rotate
     * left at P, so that S becomes the parent of P and S's right child. We then exchange the colors of P and S, and
     * make S's right child black. The subtree still has the same color at its root, so Properties 4 (Both children of
     * every red node are black) and 5 (All paths from any given node to its leaf nodes contain the same number of
     * black nodes) are not violated. However, N now has one additional black ancestor: either P has become black,
     * or it was black and S was added as a black grandparent. Thus, the paths passing through N pass through one
     * additional black node.
     * @param element
     */
    private void deleteCase6(Element element) {
        Element s = sibling(element);

        s.red = element.root.red
        element.root.toBlack()
        if (element == element.root.left) {
            s.right.toBlack()
            rotate_left(element.left);
        } else {
            s.left.toBlack()
            rotate_right(element.right);
        }
    }


    private static boolean red(Element element) {
        return element != null && element.red()
    }

    private static boolean black(Element element) {
        return element == null || element.black()
    }

    private static boolean toRed(Element element) {
        if (element) {
            element.toRed()
        }
    }

    private static boolean toBlack(Element element) {
        if (element) {
            element.toBlack()
        }
    }

    private static Element leftOf(Element p) {
        return (p == null) ? null: p.left;
    }

    private static Element rightOf(Element p) {
        return (p == null) ? null: p.right;
    }



}
