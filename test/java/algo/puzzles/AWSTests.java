package algo.puzzles;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Created by andrey tsarevskiy
 */
public class AWSTests {

    @Test
    public void maxProductTest() {
        double[] a = new double[]{2.2, 1, -4, 0.1, 5, 20};
        System.out.print(MaxProduct.maxProduct(a));
        assertEquals(MaxProduct.maxProduct(a), 100.0, 1.0);
    }

    @Test
    public void unsortedSizeTest() {
        int[] a = new int[]{1, 4, 7, 30, 50, 20, 60, 88, 99, 150, 190};
        assertEquals(SizeOfUnsortedPart.getSizeOfUnsortedSubArray(a), 3);
    }

}
