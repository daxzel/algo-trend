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

}
