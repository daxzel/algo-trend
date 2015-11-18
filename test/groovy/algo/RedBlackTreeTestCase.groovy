package groovy.algo

/**
 * Created by tsarevskiy on 19/11/15.
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
}
