package algo.algorithm.sorting;

/**
 * Created by Tsarevskiy
 * <p>
 * Worst case performance	    O(n log n) Best case performance        O(n log n) typical, O(n) natural variant Average
 * case performance	    O(n log n) Worst case space complexity	О(n) total, O(n) auxiliary
 */
public class MergeSort {

    public static void sort(int[] A) {
        topDownSplitMerge(A, 0, A.length, new int[A.length]);
    }

    public static void topDownSplitMerge(int[] A, int iBegin, int iEnd, int[] B) {
        if (iEnd - iBegin < 2) {
            return;
        }

        int iMiddle = (iEnd + iBegin) / 2;
        topDownSplitMerge(A, iBegin, iMiddle, B);
        topDownSplitMerge(A, iMiddle, iEnd, B);

        topDownMerge(A, iBegin, iMiddle, iEnd, B);

        copyArray(A, iBegin, iEnd, B);
    }

    public static void copyArray(int[] A, int iBegin, int iEnd, int[] B) {
        for (int i = iBegin; i < iEnd; i++) {
            A[i] = B[i];
        }
    }

    /**
     * left half is A[iBegin :iMiddle-1] right half is A[iMiddle:iEnd-1]
     */
    public static void topDownMerge(int[] A, int iBegin, int iMiddle, int iEnd, int[] B) {

        int iA = iBegin;
        int jA = iMiddle;

        for (int j = iBegin; j < iEnd; j++) {
            if (iA == iMiddle) {
                B[j] = A[jA];
                jA++;
                continue;
            }

            if (jA == iEnd) {
                B[j] = A[iA];
                iA++;
                continue;
            }

            int left = A[iA];
            int right = A[jA];
            if (left < right) {
                B[j] = left;
                iA++;
            } else {
                B[j] = right;
                jA++;
            }

        }
    }

}
