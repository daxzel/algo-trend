package groovy.algo

/**
 * Created by Tsarevskiy
 */
class Sorting {

    /**
     * Worst case performance	    O(n^2)
     * Best case performance	    O(n)
     * Average case performance	    O(n^2)
     * Worst case space complexity	O(1) auxiliary
     *
     */
    static void bubble(int [] array) {

        boolean swapped
        while(true) {
            swapped = false;
            for (int i = 0; i < array.size() - 1; i++) {
                if (array[i] > array[i + 1]) {
                    int next = i + 1
                    int temp = array[i]
                    array[i] = array[next]
                    array[next] = temp
                    swapped = true
                }
            }
            if (!swapped) {
                break;
            }
        }
    }

    /**
     * Worst case performance	    О(n2) comparisons, swaps
     * Best case performance	    Ω(n)  comparisons, O(1) swaps
     * Average case performance	    О(n2) comparisons, swaps
     * Worst case space complexity	О(n)  total, O(1) auxiliary
     *
     */
    static void insertion(int [] array) {
        for (int i = 1; i < array.size(); i++) {
            int j = i
            while (j > 0 && array[j - 1] > array[j]) {
                int temp = array[j - 1];
                array[j - 1] = array[j]
                array[j] = temp
                j--
            }
        }

    }

}
