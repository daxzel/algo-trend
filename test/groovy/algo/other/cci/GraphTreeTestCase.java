package algo.other.cci;

import algo.datastructures.SimpleTreeElement;
import algo.others.cci.GraphTree;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by andrey tsarevskiy
 */
public class GraphTreeTestCase extends Assert {

    @Test
    public void checkWhetherTreeIsBinarySearch1() {
        SimpleTreeElement head = new SimpleTreeElement(5);
        SimpleTreeElement left = new SimpleTreeElement(2);
        SimpleTreeElement right = new SimpleTreeElement(8);
        SimpleTreeElement leftRight = new SimpleTreeElement(9);

        head.left = left;
        head.right = right;
        left.right = leftRight;

        assertFalse(GraphTree.question5(head));
    }


    @Test
    public void checkWhetherTreeIsBinarySearch2() {
        SimpleTreeElement head = new SimpleTreeElement(5);
        SimpleTreeElement left = new SimpleTreeElement(2);
        SimpleTreeElement right = new SimpleTreeElement(8);
        SimpleTreeElement leftRight = new SimpleTreeElement(3);

        head.left = left;
        head.right = right;
        left.right = leftRight;

        assertTrue(GraphTree.question5(head));
    }
}
