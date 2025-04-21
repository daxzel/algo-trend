package algo.algorithm.counting;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class LossyCounting {

    private String[] buffer;
    private int currentIndex;
    private HashMap<String, Integer> counts = new HashMap<>();
    private double frequencyThreshold;
    private double frequencyError;
    private int totalProcessed = 0;

    public LossyCounting(int size, double frequencyThreshold, double frequencyError) {
        buffer = new String[size];
        currentIndex = 0;
        this.frequencyThreshold = frequencyThreshold;
        this.frequencyError = frequencyError;
    }

    public void add(String s) {
        buffer[currentIndex] = s;
        currentIndex++;
        if (currentIndex == buffer.length) {
            // Process the buffer when it's full
            for (String el : buffer) {
                if (el != null) {  // Check for null values
                    counts.put(el, counts.getOrDefault(el, 0) + 1);
                }
            }

            // Update total processed count
            totalProcessed += buffer.length;

            // Clean up low-frequency items according to lossy counting algorithm
            int bucket = totalProcessed / buffer.length;  // Current bucket
            int threshold = (int) (frequencyError * bucket);

            counts.entrySet().removeIf(entry -> entry.getValue() <= threshold);

            // Reset buffer index
            currentIndex = 0;
        }
    }

    public List<String> getFrequentElements() {
        flush();
        List<String> result = new ArrayList<>();
        double threshold = frequencyThreshold * totalProcessed - frequencyError * totalProcessed;

        for (String key : counts.keySet()) {
            // Use >= instead of > to catch boundary cases
            if (counts.get(key) >= threshold) {
                result.add(key);
            }
        }
        return result;
    }

    public void flush() {
        for (int i = 0; i < currentIndex; i++) {
            String el = buffer[i];
            if (el != null) {
                counts.put(el, counts.getOrDefault(el, 0) + 1);
            }
        }

        totalProcessed += currentIndex;

        int bucket = totalProcessed / buffer.length;
        int threshold = (int) (frequencyError * bucket);

        counts.entrySet().removeIf(entry -> entry.getValue() <= threshold);

        currentIndex = 0;
    }

}