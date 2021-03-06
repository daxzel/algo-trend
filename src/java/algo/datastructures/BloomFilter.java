package algo.datastructures;

import algo.algorithm.hash.SimpleHash;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class BloomFilter {

    private final boolean[] filter;
    private final HashSet<Integer> itemsAdded = new HashSet<>();
    private final List<Integer> hashSalts = new ArrayList<>();

    public BloomFilter(int size, int hashes) {
        filter = new boolean[size];
        Random random = new Random();
        for (int i = 0; i < hashes; i++) {
            int salt = random.nextInt(200);
            hashSalts.add(salt);
        }
    }

    public void addItem(int item) {
        for (Integer hashSalt : hashSalts) {
            int index = SimpleHash.getDoubleHash(item, filter.length, hashSalt);
            filter[index] = true;
        }
        itemsAdded.add(item);
    }

    public boolean contains(int item) {
        for (Integer hashSalt : hashSalts) {
            int index = SimpleHash.getDoubleHash(item, filter.length, hashSalt);
            if (!filter[index]) {
                return false;
            }
        }
        return itemsAdded.contains(item);
    }

}
