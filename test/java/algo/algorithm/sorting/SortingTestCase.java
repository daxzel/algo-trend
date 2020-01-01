package algo.algorithm.sorting;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

/**
 * Created by Tsarevskiy
 */
public class SortingTestCase {

    @Test
    public void testBubbleSort() {
        int[] array = new int[]{5, 2, 3, 6, 3, 2, 4, 10, 2, 3, 5, 3, 4, 7, 8, 60};
        Sorting.bubble(array);
        int[] sortedArray = new int[]{2, 2, 2, 3, 3, 3, 3, 4, 4, 5, 5, 6, 7, 8, 10, 60};
        assertArrayEquals(array, sortedArray);
    }

    @Test
    public void testInsertionSort() {
        int[] array = new int[]{5, 2, 3, 6, 3, 2, 4, 10, 2, 3, 5, 3, 4, 7, 8, 60};
        Sorting.insertion(array);
        int[] sortedArray = new int[]{2, 2, 2, 3, 3, 3, 3, 4, 4, 5, 5, 6, 7, 8, 10, 60};
        assertArrayEquals(array, sortedArray);
    }

    @Test
    public void testSelectionSort() {
        int[] array = new int[]{5, 2, 3, 6, 3, 2, 4, 10, 2, 3, 5, 3, 4, 7, 8, 60};
        Sorting.selection(array);
        int[] sortedArray = new int[]{2, 2, 2, 3, 3, 3, 3, 4, 4, 5, 5, 6, 7, 8, 10, 60};
        System.out.println(Arrays.toString(array));
        assertArrayEquals(array, sortedArray);
    }

    @Test
    public void testMergeSort() {
        int[] array = new int[]{5, 2, 3, 6, 3, 2, 4, 10, 2, 3, 5, 3, 4, 7, 8, 60};
        MergeSort.sort(array);
        System.out.println(Arrays.toString(array));
        int[] sortedArray = new int[]{2, 2, 2, 3, 3, 3, 3, 4, 4, 5, 5, 6, 7, 8, 10, 60};
        assertArrayEquals(array, sortedArray);
    }

    //TODO fix it
//    @Test
//    public void testCountingSorting() {
//        int[] array = new int[]{5, 2, 3, 6, 3, 2, 4, 10, 2, 3, 5, 3, 4, 7, 8, 60};
//        Sorting.counting(array);
//        DefaultGroovyMethods.print(this, array);
//        int[] sortedArray = new int[]{2, 2, 2, 3, 3, 3, 3, 4, 4, 5, 5, 6, 7, 8, 10, 60};
//        assertArrayEquals(array, sortedArray);
//    }

    @Test
    public void testHeapSort() {
        int[] array = new int[]{5, 2, 3, 6, 3, 2, 4, 10, 2, 3, 5, 3, 4, 7, 8, 60};
        HeapSort.sort(array);
        System.out.println(Arrays.toString(array));
        int[] sortedArray = new int[]{2, 2, 2, 3, 3, 3, 3, 4, 4, 5, 5, 6, 7, 8, 10, 60};
        assertArrayEquals(array, sortedArray);
    }

}
