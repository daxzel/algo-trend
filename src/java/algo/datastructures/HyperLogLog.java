package algo.datastructures;

import java.nio.charset.StandardCharsets;

public class HyperLogLog {

    private final int[] registers;
    private final int size;
    private final int sizeTwos;

    public HyperLogLog(int sizeTwos) {
        this.size = (int) Math.pow(2, sizeTwos);
        this.sizeTwos = sizeTwos;
        this.registers = new int[size];
    }

    public void count(String name) {
        int hash = murmurHash3(name);
        int index = hash >>> (32 - sizeTwos);
        int rank = 1;
        int mask = 1 << (31 - sizeTwos);
        int remainingBits = hash & ((1 << (32 - sizeTwos)) - 1); // Get only the remaining bits

        if (remainingBits == 0) {
            rank = 32 - sizeTwos + 1; // All zeros case
        } else {
            while ((remainingBits & mask) == 0) {
                mask >>>= 1;
                rank++;
            }
        }
        registers[index] = Math.max(rank, registers[index]);
    }

    public int getEstimate() {
        double sum = 0;
        for (int i = 0; i < size; i++) {
            sum += Math.pow(2, -registers[i]);
        }

        // Using the improved HyperLogLog estimation formula
        double alpha;
        if (size <= 16) {
            alpha = 0.673;
        } else if (size <= 32) {
            alpha = 0.697;
        } else if (size <= 64) {
            alpha = 0.709;
        } else {
            alpha = 0.7213 / (1.0 + 1.079 / size);
        }

        double estimate = alpha * size * size / sum;

        // Apply corrections for small and large cardinalities
        if (estimate <= 2.5 * size) {
            // Count zeros for small cardinality correction
            int zeros = 0;
            for (int register : registers) {
                if (register == 0) {
                    zeros++;
                }
            }
            if (zeros > 0) {
                estimate = size * Math.log(size / (double) zeros);
            }
        }

        return (int) Math.round(estimate);
    }

    /**
     * MurmurHash3 for 32-bit hash values.
     * This gives better distribution across the entire 32-bit range.
     */
    private int murmurHash3(String key) {
        byte[] data = key.getBytes(StandardCharsets.UTF_8);
        return murmurHash3(data, 0, data.length, 0x9747b28c); // Use a fixed seed
    }

    private int murmurHash3(byte[] data, int offset, int len, int seed) {
        final int c1 = 0xcc9e2d51;
        final int c2 = 0x1b873593;
        final int r1 = 15;
        final int r2 = 13;
        final int m = 5;
        final int n = 0xe6546b64;

        int hash = seed;
        int roundedEnd = offset + (len & ~3); // round down to 4 byte block

        for (int i = offset; i < roundedEnd; i += 4) {
            // little endian load order
            int k = (data[i] & 0xff) |
                    ((data[i + 1] & 0xff) << 8) |
                    ((data[i + 2] & 0xff) << 16) |
                    ((data[i + 3] & 0xff) << 24);
            k *= c1;
            k = (k << r1) | (k >>> (32 - r1));
            k *= c2;

            hash ^= k;
            hash = ((hash << r2) | (hash >>> (32 - r2))) * m + n;
        }

        // tail
        int k = 0;
        switch (len & 3) {
            case 3:
                k ^= (data[roundedEnd + 2] & 0xff) << 16;
                // fallthrough
            case 2:
                k ^= (data[roundedEnd + 1] & 0xff) << 8;
                // fallthrough
            case 1:
                k ^= (data[roundedEnd] & 0xff);
                k *= c1;
                k = (k << r1) | (k >>> (32 - r1));
                k *= c2;
                hash ^= k;
        }

        // finalization
        hash ^= len;
        hash ^= (hash >>> 16);
        hash *= 0x85ebca6b;
        hash ^= (hash >>> 13);
        hash *= 0xc2b2ae35;
        hash ^= (hash >>> 16);

        return hash;
    }

}
