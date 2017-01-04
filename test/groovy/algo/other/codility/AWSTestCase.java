package algo.other.codility;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by andrey tsarevskiy
 */
public class AWSTestCase extends Assert {

    @Test
    public void maxProductTest() {
        double[] a = new double[] {2.2, 1, -4, 0.1, 5, 20};
        System.out.print(AWS.maxProduct(a));
        assertEquals(AWS.maxProduct(a), 100.0, 1.0);
    }

    @Test
    public void unsortedSizeTest() {
        int[] a = new int[] {1, 4, 7, 30, 50, 20, 60, 88, 99, 150, 190};
        assertEquals(AWS.sizeOfUnsortedArray(a), 3);
    }

}
