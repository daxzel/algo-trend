package algo.datastructures.probabilistic;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BloomFilterTestCase {

    @Test
    public void testSimple() {
        BloomFilter filter = new BloomFilter(100, 2);

        filter.addItem(2);
        filter.addItem(1000);
        filter.addItem(10);
        filter.addItem(3534);
        filter.addItem(12);
        filter.addItem(99999);

        Assertions.assertTrue(filter.contains(2));
        Assertions.assertFalse(filter.contains(3));
        Assertions.assertTrue(filter.contains(12));
        Assertions.assertFalse(filter.contains(1001));
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

        Assertions.assertTrue(filter.contains(2));
        Assertions.assertFalse(filter.contains(3));
        Assertions.assertTrue(filter.contains(12));
        Assertions.assertFalse(filter.contains(1001));
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

        Assertions.assertTrue(filter.contains(2));
        Assertions.assertFalse(filter.contains(3));
        Assertions.assertTrue(filter.contains(12));
        Assertions.assertFalse(filter.contains(1001));
    }
}