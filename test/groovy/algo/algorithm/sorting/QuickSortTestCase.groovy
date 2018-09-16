package algo.algorithm.sorting

class QuickSortTestCase extends GroovyTestCase {

    void testSimple() {
        int[] array = [5,2,3,6,3,2,4,10,2,3,5,3,4,7,8,60]
        QuickSort.sort(array)
        int[] sortedArray = [2,2,2,3,3,3,3,4,4,5,5,6,7,8,10,60]
        assertTrue(array.equals(sortedArray))
    }

}
