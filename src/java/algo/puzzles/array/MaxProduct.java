package algo.puzzles.array;

import algo.puzzles.Codility;

/**
 * Created by andrey tsarevskiy
 */
@Codility
public class MaxProduct {

    /**
     * @param A consecutive elements
     * @return The max product of consecutive elements in an array
     */
    public static double maxProduct(double[] A) {
        if (A.length == 0) {
            return 0;
        }
        double maxCurrent;
        double prevMax = A[0];
        double prevMin = A[0];
        double result = A[0];

        for (int i = 1; i < A.length; i++) {

            maxCurrent = Math.max(Math.max(prevMax * A[i], prevMin * A[i]), A[i]);
            prevMin = Math.min(Math.min(prevMax * A[i], prevMin * A[i]), A[i]);
            result = Math.max(result, maxCurrent);
            prevMax = maxCurrent;
        }

        return result < 1000000000.0 ? result : 1000000000.0;
    }
}
