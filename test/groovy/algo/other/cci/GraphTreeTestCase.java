package algo.other.cci;

import algo.datastructures.tree.SimpleTree;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by andrey tsarevskiy
 */
public class GraphTreeTestCase extends Assert {

    @Test
    public void checkWhetherTreeIsBinarySearch1() {
        SimpleTree head = new SimpleTree(5);
        SimpleTree left = new SimpleTree(2);
        SimpleTree right = new SimpleTree(8);
        SimpleTree leftRight = new SimpleTree(9);

        head.left = left;
        head.right = right;
        left.right = leftRight;

        assertFalse(GraphTree.question5_2(head));
    }


    @Test
    public void checkWhetherTreeIsBinarySearch2() {
        SimpleTree head = new SimpleTree(5);
        SimpleTree left = new SimpleTree(2);
        SimpleTree right = new SimpleTree(8);
        SimpleTree leftRight = new SimpleTree(3);

        head.left = left;
        head.right = right;
        left.right = leftRight;

        assertTrue(GraphTree.question5_2(head));
    }

    @Test
    public void checkWhetherTreeIsBinarySearch3() {
        SimpleTree head = new SimpleTree(5);
        SimpleTree left = new SimpleTree(2);
        SimpleTree right = new SimpleTree(5);
        SimpleTree leftRight = new SimpleTree(3);

        head.left = left;
        head.right = right;
        left.right = leftRight;

        assertFalse(GraphTree.question5_2(head));
    }
}
