package groovy.algo

import groovy.algo.sorting.BinaryHeap

/**
 * Created by Tsarevskiy
 */
class HeapTestCase extends GroovyTestCase {

    void testAdding() {
        BinaryHeap heap = new BinaryHeap()

        heap.add(232, "232")
        heap.add(2, "2")
        heap.add(23, "23")
        heap.add(29932, "29932")
        heap.add(23992, "23992")

        assertEquals(heap.remove(), "29932")

        assertEquals(heap.remove(), "23992")
        assertEquals(heap.remove(), "232")
        assertEquals(heap.remove(), "23")
        assertEquals(heap.remove(), "2")

    }
}
