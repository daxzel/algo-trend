package algo.datastructures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HyperLogLogTests {

    @Test
    public void testCardinality10() {
        HyperLogLog hll = new HyperLogLog(4); // Using 16 registers for small cardinality

        // Add 10 distinct elements
        for (int i = 1; i <= 10; i++) {
            hll.count("Element-" + i);
        }

        int estimate = hll.getEstimate();
        System.out.println("Actual: 10, Estimated: " + estimate);

        // Allow for some error margin (±20% for small cardinality)
        assertTrue(estimate >= 8 && estimate <= 12,
                "Estimate " + estimate + " should be between 8 and 12");
    }

    @Test
    public void testCardinality100() {
        HyperLogLog hll = new HyperLogLog(6); // Using 64 registers for medium cardinality

        // Add 100 distinct elements
        for (int i = 1; i <= 100; i++) {
            hll.count("Element-" + i);
        }

        int estimate = hll.getEstimate();
        System.out.println("Actual: 100, Estimated: " + estimate);

        // Allow for some error margin (±15% for medium cardinality)
        assertTrue(estimate >= 85 && estimate <= 115,
                "Estimate " + estimate + " should be between 85 and 115");
    }

    @Test
    public void testCardinality1000() {
        HyperLogLog hll = new HyperLogLog(8); // Using 256 registers for larger cardinality

        // Add 1000 distinct elements
        for (int i = 1; i <= 1000; i++) {
            hll.count("Element-" + i);
        }

        int estimate = hll.getEstimate();
        System.out.println("Actual: 1000, Estimated: " + estimate);

        // Allow for some error margin (±10% for larger cardinality)
        assertTrue(estimate >= 900 && estimate <= 1100,
                "Estimate " + estimate + " should be between 900 and 1100");
    }

    @Test
    public void testDuplicates() {
        HyperLogLog hll = new HyperLogLog(5); // Using 32 registers

        // Add 100 elements but with only 50 distinct values
        for (int i = 1; i <= 50; i++) {
            hll.count("Element-" + i);
            hll.count("Element-" + i); // Add duplicate
        }

        int estimate = hll.getEstimate();
        System.out.println("Actual: 50, Estimated: " + estimate);

        // Allow for some error margin (±15% for medium cardinality)
        assertTrue(estimate >= 42 && estimate <= 58,
                "Estimate " + estimate + " should be between 42 and 58");
    }

    @Test
    public void testHigherCardinality() {
        HyperLogLog hll = new HyperLogLog(10); // Using 1024 registers for high cardinality

        // Add 10,000 distinct elements
        for (int i = 1; i <= 10000; i++) {
            hll.count("Element-" + i);
        }

        int estimate = hll.getEstimate();
        System.out.println("Actual: 10000, Estimated: " + estimate);

        // Allow for some error margin (±5% for high cardinality)
        assertTrue(estimate >= 9500 && estimate <= 10500,
                "Estimate " + estimate + " should be between 9500 and 10500");
    }

    @Test
    public void testOriginalSimple() {
        HyperLogLog hll = new HyperLogLog(3);
        hll.count("1");
        hll.count("2");
        hll.count("3");
        hll.count("4");
        hll.count("5");
        hll.count("6");
        hll.count("7");
        hll.count("8");
        hll.count("9");
        hll.count("10");

        int estimate = hll.getEstimate();
        System.out.println("Original test - Actual: 10, Estimated: " + estimate);

        // Allow for some error margin (±30% for very small cardinality with few registers)
        assertTrue(estimate >= 7 && estimate <= 13,
                "Estimate " + estimate + " should be between 7 and 13");
    }
}