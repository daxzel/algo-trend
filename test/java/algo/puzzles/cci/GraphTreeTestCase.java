package algo.puzzles.cci;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import algo.datastructures.tree.IntTree;
import algo.puzzles.tree.CheckBinarySearchTree;
import org.junit.jupiter.api.Test;

/**
 * Created by andrey tsarevskiy
 */
public class GraphTreeTestCase {

    @Test
    public void checkWhetherTreeIsBinarySearch1() {
        IntTree head = new IntTree(5);
        IntTree left = new IntTree(2);
        IntTree right = new IntTree(8);
        IntTree leftRight = new IntTree(9);

        head.left = left;
        head.right = right;
        left.right = leftRight;

        assertFalse(CheckBinarySearchTree.secondMethod(head));
    }


    @Test
    public void checkWhetherTreeIsBinarySearch2() {
        IntTree head = new IntTree(5);
        IntTree left = new IntTree(2);
        IntTree right = new IntTree(8);
        IntTree leftRight = new IntTree(3);

        head.left = left;
        head.right = right;
        left.right = leftRight;

        assertTrue(CheckBinarySearchTree.secondMethod(head));
    }

    @Test
    public void checkWhetherTreeIsBinarySearch3() {
        IntTree head = new IntTree(5);
        IntTree left = new IntTree(2);
        IntTree right = new IntTree(5);
        IntTree leftRight = new IntTree(3);

        head.left = left;
        head.right = right;
        left.right = leftRight;

        assertFalse(CheckBinarySearchTree.secondMethod(head));
    }
}
