package algo.algorithm.sorting;

public class QuickSort {

    /**
     * Worst-case performance	O(n^2)
     * Best-case performance	O(n log n)
     * Average performance	O(n log n)
     * Worst-case space complexity	O(n) auxiliary (naive)
     * O(log n) auxiliary
     */
    public static void sort(int[] A) {
        internalSort(A, 0, A.length - 1);
    }

    static void internalSort(int[] A, int l, int h) {
        if (l < h) {
            int partition = partition(A, l, h);
            internalSort(A, l, partition);
            internalSort(A, partition + 1, h);
        }
    }

    static int partition(int[] A, int l, int h) {
        int i = l;
        int j = h;

        while (i < j) {
            int pivot = A[i];

            i++;
            while (A[i] <= pivot) {
                i++;
            }

            j--;
            while (A[j] > pivot) {
                j--;
            }
            if (i < j) {
                swap(A, i, j);
            }
        }
        swap(A, l, j);
        return j;
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[j];
        array[j] = array[i];
        array[i] = temp;
    }
}
