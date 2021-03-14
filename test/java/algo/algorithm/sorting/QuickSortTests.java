package algo.algorithm.sorting;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class QuickSortTests {

    @Test
    public void testSimple() {
        int[] array = new int[]{5, 2, 3, 6, 3, 2, 4, 10, 2, 3, 5, 3, 4, 7, 8, 60};
        QuickSort.sort(array);
        int[] sortedArray = new int[]{2, 2, 2, 3, 3, 3, 3, 4, 4, 5, 5, 6, 7, 8, 10, 60};
        assertArrayEquals(array, sortedArray);
    }

}
