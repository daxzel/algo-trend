package algo.algorithm.math

/**
 * Created by Tsarevskiy
 */
class NewtonMethodTestCase extends GroovyTestCase {

    void testBubbleSort() {
        double result = NewtonMethod.getSquareRoot(5, 0.0000001)
        assertTrue(result > 2.2)
        assertTrue(result < 2.4)
    }

}
