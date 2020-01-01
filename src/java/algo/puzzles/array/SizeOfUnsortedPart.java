package algo.puzzles.array;

import algo.puzzles.Codility;

/**
 * Created by andrey tsarevskiy
 */
@Codility
public class SizeOfUnsortedPart {

    /**
     * A is partly sorted array. How big unsorted sub array is.
     *
     * @param A int array
     * @return size of unsorted part inside of array
     */
    public static int getSizeOfUnsortedSubArray(int[] A) {
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
            if (A[i] > max) {
                max = A[i];
            }
            if (A[i] < min) {
                min = A[i];
            }
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
