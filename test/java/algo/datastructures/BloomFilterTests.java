package algo.datastructures;

import algo.datastructures.BloomFilter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BloomFilterTests {

    @Test
    public void testSimple() {
        BloomFilter filter = new BloomFilter(100, 2);

        filter.addItem(2);
        filter.addItem(1000);
        filter.addItem(10);
        filter.addItem(3534);
        filter.addItem(12);
        filter.addItem(99999);

        assertTrue(filter.contains(2));
        assertFalse(filter.contains(3));
        assertTrue(filter.contains(12));
        assertFalse(filter.contains(1001));
    }

    @Test
    public void testLargeBloomFilter() {
        BloomFilter filter = new BloomFilter(1000000, 2);

        filter.addItem(2);
        filter.addItem(1000);
        filter.addItem(10);
        filter.addItem(3534);
        filter.addItem(12);
        filter.addItem(99999);

        assertTrue(filter.contains(2));
        assertFalse(filter.contains(3));
        assertTrue(filter.contains(12));
        assertFalse(filter.contains(1001));
    }

    @Test
    public void testMultipleHashes() {
        BloomFilter filter = new BloomFilter(1000000, 20);

        filter.addItem(2);
        filter.addItem(1000);
        filter.addItem(10);
        filter.addItem(3534);
        filter.addItem(12);
        filter.addItem(99999);

        assertTrue(filter.contains(2));
        assertFalse(filter.contains(3));
        assertTrue(filter.contains(12));
        assertFalse(filter.contains(1001));
    }
}