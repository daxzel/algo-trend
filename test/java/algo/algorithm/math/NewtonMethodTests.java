package algo.algorithm.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by Tsarevskiy
 */
public class NewtonMethodTests {

    @Test
    public void testSquareRoot() {
        double result = NewtonMethod.getSquareRoot(5, 0.0000001);
        assertTrue(result > 2.2);
        assertTrue(result < 2.4);
    }

}
