package algo.datastructures.probabilistic;

import algo.algorithm.hash.SimpleHash;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Implementation of https://arxiv.org/pdf/1101.2245.pdf
 */
public class InvertibleBloomLookupTable {

    private final int[] count;
    private final int[] valueSum;
    private final int[] keySum;
    private final List<Integer> hashSalts = new ArrayList<>();

    public InvertibleBloomLookupTable(int size, int hashes) {
        count = new int[size];
        valueSum = new int[size];
        keySum = new int[size];
        Random random = new Random();
        for (int i = 0; i < hashes; i++) {
            int salt = random.nextInt(200);
            hashSalts.add(salt);
        }
    }

    public void addItem(int key, int value) {
        for (Integer hashSalt : hashSalts) {
            int index = SimpleHash.getDoubleHash(key, count.length, hashSalt);
            count[index]++;
            valueSum[index] += value;
            keySum[index] += key;
        }
    }

    public boolean contains(int key) {
        for (Integer hashSalt : hashSalts) {
            int index = SimpleHash.getDoubleHash(key, count.length, hashSalt);
            if (count[index] == 0) {
                return false;
            }
        }
        return true;
    }

    public Integer get(int key) {
        for (Integer hashSalt : hashSalts) {
            int index = SimpleHash.getDoubleHash(key,count.length, hashSalt);
            if (count[index] == 0) {
                return null;
            } else {
                if (count[index] == 1) {
                    if (keySum[index] == key) {
                        return valueSum[index];
                    } else {
                        return null;
                    }
                }
            }
        }
        throw new RuntimeException("not found");
    }

}
