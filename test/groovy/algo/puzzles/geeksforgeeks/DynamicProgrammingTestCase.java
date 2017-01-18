package algo.puzzles.geeksforgeeks;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by andrey tsarevskiy
 */
public class DynamicProgrammingTestCase extends Assert {

    @Test
    public void matrixChainOrder() {
        int arr[] = new int[] {1, 2, 3, 4, 3};
        int result = DynamicProgramming.matrixChainOrder(arr);
        assertEquals(result, 86);
    }

}
