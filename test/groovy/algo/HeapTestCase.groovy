package groovy.algo

/**
 * Created by tsarevskiy on 17/11/15.
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
