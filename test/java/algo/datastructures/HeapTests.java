package algo.datastructures;

import static org.junit.jupiter.api.Assertions.assertEquals;

import algo.datastructures.BinaryHeap;
import algo.datastructures.FibonacciHeap;
import org.junit.jupiter.api.Test;

/**
 * Created by Tsarevskiy
 */
public class HeapTests {

    @Test
    public void testBinaryHeapAdding() {
        BinaryHeap heap = new BinaryHeap();

        heap.add(232, "232");
        heap.add(2, "2");
        heap.add(23, "23");
        heap.add(29932, "29932");
        heap.add(23992, "23992");

        assertEquals(heap.remove(), "29932");

        assertEquals(heap.remove(), "23992");
        assertEquals(heap.remove(), "232");
        assertEquals(heap.remove(), "23");
        assertEquals(heap.remove(), "2");

    }

    @Test
    public void testFibonacciHeapAdding() {
        FibonacciHeap heap = new FibonacciHeap();

        heap.add(232);
        heap.add(2);
        heap.add(23);
        heap.add(29932);
        heap.add(23992);

        assertEquals(heap.remove(), 2);
        assertEquals(heap.remove(), 23);
        assertEquals(heap.remove(), 232);
        assertEquals(heap.remove(), 23992);

        assertEquals(heap.remove(), 29932);


    }

}
