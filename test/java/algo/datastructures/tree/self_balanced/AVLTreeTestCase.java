package algo.datastructures.tree.self_balanced;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by Tsarevskiy
 */
public class AVLTreeTestCase {

    public void testAdding() {
        AVLTree tree = new AVLTree();

        tree.add(232);
        tree.add(2);
        tree.add(1234);
        tree.add(55);
        tree.add(231414);
        tree.add(515);
        tree.add(2222);
        tree.add(1);
        tree.add(6);
        tree.add(888);
        tree.add(1231);
        tree.add(89);
        tree.add(44);

        assertTrue(tree.contains(1));
        assertTrue(tree.contains(1234));
        assertTrue(tree.contains(2));
        assertTrue(tree.contains(6));
        assertTrue(tree.contains(44));

        assertFalse(tree.contains(7));
        assertFalse(tree.contains(99));
        assertFalse(tree.contains(4));
        assertFalse(tree.contains(45));

    }

}
