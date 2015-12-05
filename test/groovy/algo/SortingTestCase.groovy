package groovy.algo

import groovy.algo.sorting.HeapSort
import groovy.algo.sorting.MergeSort

/**
 * Created by Tsarevskiy
 */
class SortingTestCase extends GroovyTestCase {

    void testBubbleSort() {
        int[] array = [5,2,3,6,3,2,4,10,2,3,5,3,4,7,8,60]
        Sorting.bubble(array)
        int[] sortedArray = [2,2,2,3,3,3,3,4,4,5,5,6,7,8,10,60]
        assertTrue(array.equals(sortedArray))
    }

    void testInsertionSort() {
        int[] array = [5,2,3,6,3,2,4,10,2,3,5,3,4,7,8,60]
        Sorting.insertion(array)
        int[] sortedArray = [2,2,2,3,3,3,3,4,4,5,5,6,7,8,10,60]
        assertTrue(array.equals(sortedArray))
    }

    void testSelectionSort() {
        int[] array = [5,2,3,6,3,2,4,10,2,3,5,3,4,7,8,60]
        Sorting.selection(array)
        int[] sortedArray = [2,2,2,3,3,3,3,4,4,5,5,6,7,8,10,60]
        assertTrue(array.equals(sortedArray))
    }

    void testMergeSort() {
        int[] array = [5,2,3,6,3,2,4,10,2,3,5,3,4,7,8,60]
        MergeSort.sort(array)
        print array
        int[] sortedArray = [2,2,2,3,3,3,3,4,4,5,5,6,7,8,10,60]
        assertTrue(array.equals(sortedArray))
    }

    void testHeapSort() {
        int[] array = [5,2,3,6,3,2,4,10,2,3,5,3,4,7,8,60]
        HeapSort.sort(array)
        print array
        int[] sortedArray = [2,2,2,3,3,3,3,4,4,5,5,6,7,8,10,60]
        assertTrue(array.equals(sortedArray))
    }


}
