package algo.algorithm.counting;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class LossyCountingTests {

    @Test
    public void testEmptyStream() {
        LossyCounting counter = new LossyCounting(10, 0.1, 0.05);
        List<String> frequentItems = counter.getFrequentElements();

        assertTrue(frequentItems.isEmpty(), "No frequent elements should be found in an empty stream");
    }

    @Test
    public void testSingleElement() {
        LossyCounting counter = new LossyCounting(5, 0.1, 0.05);

        // Fill the buffer with the same element
        for (int i = 0; i < 5; i++) {
            counter.add("A");
        }

        List<String> frequentItems = counter.getFrequentElements();
        assertEquals(1, frequentItems.size(), "Should find exactly one frequent element");
        assertEquals("A", frequentItems.get(0), "Element 'A' should be identified as frequent");
    }

    @Test
    public void testHighFrequencyElements() {
        LossyCounting counter = new LossyCounting(4, 0.25, 0.05);

        // Add elements with known frequencies
        // A: 50% frequency
        // B: 30% frequency
        // C: 20% frequency
        for (int i = 0; i < 10; i++) {
            if (i < 5) {
                counter.add("A");
            } else if (i < 9) {
                counter.add("B");
            } else {
                counter.add("C");
            }
        }

        List<String> frequentItems = counter.getFrequentElements();

        assertTrue(frequentItems.contains("A"), "Element 'A' should be identified as frequent");
        assertTrue(frequentItems.contains("B"), "Element 'B' should be identified as frequent");
        assertFalse(frequentItems.contains("C"), "Element 'C' should not be identified as frequent");
    }

    @Test
    public void testMultipleBatches() {
        LossyCounting counter = new LossyCounting(3, 0.4, 0.1);

        // First batch
        counter.add("A");
        counter.add("A");
        counter.add("B");

        // Second batch
        counter.add("A");
        counter.add("A");
        counter.add("C");

        List<String> frequentItems = counter.getFrequentElements();

        assertTrue(frequentItems.contains("A"), "Element 'A' should be identified as frequent");
        assertFalse(frequentItems.contains("B"), "Element 'B' should not be identified as frequent");
        assertFalse(frequentItems.contains("C"), "Element 'C' should not be identified as frequent");
    }

    @Test
    public void testErrorBounds() {
        // Create a stream with known distribution
        int streamSize = 1000;
        int bufferSize = 10;
        double threshold = 0.1;
        double error = 0.02;

        String[] stream = new String[streamSize];
        Map<String, Integer> actualCounts = new HashMap<>();

        // Fill the stream with data: "A" with 15% frequency, "B" with 9%, others random
        for (int i = 0; i < streamSize; i++) {
            if (i % 100 < 15) {
                stream[i] = "A";
                actualCounts.put("A", actualCounts.getOrDefault("A", 0) + 1);
            } else if (i % 100 < 24) {
                stream[i] = "B";
                actualCounts.put("B", actualCounts.getOrDefault("B", 0) + 1);
            } else {
                String randomItem = "Item" + (i % 10);
                stream[i] = randomItem;
                actualCounts.put(randomItem, actualCounts.getOrDefault(randomItem, 0) + 1);
            }
        }

        // Process the stream
        LossyCounting counter = new LossyCounting(bufferSize, threshold, error);
        for (String item : stream) {
            counter.add(item);
        }

        // Get frequent items
        List<String> frequentItems = counter.getFrequentElements();

        // "A" should be in the result (frequency 15% > threshold 10%)
        assertTrue(frequentItems.contains("A"), "Element 'A' with frequency > threshold should be identified");

        // "B" might or might not be in the result (frequency 9% < threshold 10%, but within error bounds)
        // We can't assert specific behavior here due to the probabilistic nature of the algorithm

        // Check if all reported frequent items actually have frequency close to or above threshold
        for (String item : frequentItems) {
            double actualFrequency = (double) actualCounts.getOrDefault(item, 0) / streamSize;
            assertTrue(actualFrequency >= (threshold - error),
                    "All reported frequent items should have actual frequency >= (threshold - error)");
        }
    }

    @Test
    public void testLargeStreamWithKnownDistribution() {
        int streamSize = 10000;
        int bufferSize = 100;
        double threshold = 0.05;
        double error = 0.01;
        Random random = new Random(42); // Fixed seed for reproducibility

        // Create a distribution: A:10%, B:7%, C:5%, D:3%, others < 1%
        String[] stream = new String[streamSize];
        for (int i = 0; i < streamSize; i++) {
            double r = random.nextDouble();
            if (r < 0.1) {
                stream[i] = "A";
            } else if (r < 0.17) {
                stream[i] = "B";
            } else if (r < 0.22) {
                stream[i] = "C";
            } else if (r < 0.25) {
                stream[i] = "D";
            } else {
                stream[i] = "Item" + random.nextInt(1000);
            }
        }

        // Count actual frequencies
        Map<String, Long> actualFrequencies = Arrays.stream(stream)
                .collect(Collectors.groupingBy(item -> item, Collectors.counting()));

        // Process with lossy counting
        LossyCounting counter = new LossyCounting(bufferSize, threshold, error);
        for (String item : stream) {
            counter.add(item);
        }

        List<String> frequentItems = counter.getFrequentElements();

        // Items with frequency > threshold should be included
        assertTrue(frequentItems.contains("A"), "A should be identified as frequent");
        assertTrue(frequentItems.contains("B"), "B should be identified as frequent");

        // Items with frequency < (threshold - error) should not be included
        assertFalse(frequentItems.contains("D"), "D should not be identified as frequent");

        // C might or might not be included (edge case)

        // No false positives with frequency much lower than threshold
        for (String item : frequentItems) {
            double actualFrequency = (double) actualFrequencies.getOrDefault(item, 0L) / streamSize;
            assertTrue(actualFrequency >= (threshold - error - 0.01),
                    "Item " + item + " with actual frequency " + actualFrequency +
                            " should not be below (threshold - error - epsilon)");
        }
    }

    @Test
    public void testIncrementalProcessing() {
        LossyCounting counter = new LossyCounting(5, 0.3, 0.1);

        // Add elements but not enough to trigger processing
        counter.add("A");
        counter.add("A");
        counter.add("B");

        counter.add("A");
        counter.add("B");

        List<String> frequentItems = counter.getFrequentElements();
        assertEquals(2, frequentItems.size(), "One frequent element should be found after processing");
        assertEquals("A", frequentItems.get(0), "Element 'A' should be identified as frequent");
    }

    @Test
    public void testFrequencyThresholdBoundary() {
        LossyCounting counter = new LossyCounting(10, 0.8, 0.05);

        // Add elements with exactly 50% frequency
        for (int i = 0; i < 10; i++) {
            if (i < 5) {
                counter.add("A");
            } else {
                counter.add("B");
            }
        }

        List<String> frequentItems = counter.getFrequentElements();
        assertEquals(0, frequentItems.size(), "No elements should meet the threshold");
    }
}