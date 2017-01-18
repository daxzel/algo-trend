package algo.algorithm.math;

/**
 * Created by andrey tsarevskiy
 */
public class NewtonMethod {

    public static double getSquareRoot(int number, double epsilon) {
        double currentValue = 1;
        double nextValue = currentValue - ((currentValue  * currentValue) - number / (2 * currentValue));
        while (Math.abs(nextValue - currentValue) > epsilon) {
            currentValue = nextValue;
            nextValue = currentValue - (((currentValue  * currentValue) - number) / (2 * currentValue));
        }
        return nextValue;
    }
}
