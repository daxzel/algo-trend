package algo.datastructures.tree

import algo.datastructures.tree.self_balanced.RedBlackTree

/**
 * Created by Tsarevskiy
 */
class RedBlackTreeTestCase extends GroovyTestCase {

    void testAdding() {
        RedBlackTree tree = new RedBlackTree()

        tree.add(232)
        tree.add(2)
        tree.add(1234)
        tree.add(55)
        tree.add(231414)
        tree.add(515)
        tree.add(2222)
        tree.add(1)
        tree.add(6)

        assertTrue(tree.contains(1))
        assertTrue(tree.contains(1234))
        assertTrue(tree.contains(2))
        assertTrue(tree.contains(6))

        assertFalse(tree.contains(7))
        assertFalse(tree.contains(99))
        assertFalse(tree.contains(4))

    }

    void testRemoving() {
        RedBlackTree tree = new RedBlackTree()

        tree.add(232)
        tree.add(2)
        tree.add(1234)
        tree.add(55)
        tree.add(231414)
        tree.add(515)
        tree.add(2222)
        tree.add(1)
        tree.add(6)

        tree.removeElement(2)
        tree.removeElement(55)
        tree.removeElement(231414)
        tree.removeElement(515)
        tree.removeElement(4242424)

        assertFalse(tree.contains(2))
        assertTrue(tree.contains(6))

        assertFalse(tree.contains(55))
        assertTrue(tree.contains(232))
        assertFalse(tree.contains(515))

    }
}
