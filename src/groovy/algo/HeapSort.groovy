package groovy.algo

/**
 * Created by Tsarevskiy
 */
class HeapSort {

    /**
     * Array
     * Worst case performance	O(nlog n)
     * Best case performance	Omega(n), O(nlog n)[1]
     * Average case performance	O(nlog n)
     * Worst case space complexity	O(1) auxiliary
     */
    static void sort(int[] array) {

        heapify(array)

        int end = array.length - 1
        while (end > 0) {
            println array
            swapFunc(array, end, 0)
            end = end - 1
            siftDown(array, 0, end)
        }

    }

    static void heapify(int[] array) {

        int start = (array.length - 2) / 2
        while (start >= 0) {
            siftDown(array, start, array.length - 1)
            start--
        }
    }

    static void siftDown(int[] array, int start, int end) {
        int root = start

        while (root * 2 + 1 <= end) {
            int child = root * 2 + 1
            int swap = root
            if (array[swap] < array[child]) {
                swap = child
            }
            if ((child + 1 <= end) && (array[swap] < array[child + 1])) {
                swap = child + 1
            }
            if (swap == root) {
                return
            } else {
                swapFunc(array, root, swap)
                root = swap
            }
        }
    }

    private static void swapFunc(int[] array, int i, int j) {
        int temp = array[j];
        array[j] = array[i]
        array[i] = temp
    }

}
