package algo.other.codility;

/**
 * Created by andrey tsarevskiy
 */
public class AWS {

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


    /**
     * A is partly sorted array. How big unsorted subarray is.
     *
     * @param A int array
     * @return size of unsorted part inside of array
     */
    public static int sizeOfUnsortedArray(int[] A) {
        int unsortedStart = -1;
        int unsortedEnd = -1;
        if (A.length == 0) {
            return 0;
        }
        for (int i = 0; i < (A.length - 1); i++) {
            if (A[i] > A[i + 1]) {
                unsortedStart = i;
                break;
            }
        }
        if (unsortedStart == -1) {
            return 0;
        }

        for (int i = A.length - 1; i > 0; i--) {
            if (A[i] < A[i - 1]) {
                unsortedEnd = i;
                break;
            }
        }
        int max = A[unsortedStart];
        int min = A[unsortedEnd];
        for (int i = unsortedStart + 1; i <= unsortedEnd; i++) {
            if (A[i] > max)
                max = A[i];
            if (A[i] < min)
                min = A[i];
        }
        int newUnsortedStart = unsortedStart;
        for (int i = 0; i < unsortedStart; i++) {
            if (A[i] > min) {
                newUnsortedStart = i;
                break;
            }
        }
        int newUnsortedEnd = unsortedEnd;
        for (int i = A.length - 1; i >= unsortedEnd + 1; i--) {
            if (A[i] < max) {
                newUnsortedEnd = i;
                break;
            }
        }
        return newUnsortedEnd - newUnsortedStart + 1;
    }


}
