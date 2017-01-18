package algo.datastructures.map;

import static algo.algorithms.hash.SimpleHash.getDoubleHash;

/**
 * Created by andrey tsarevskiy
 */
public class OpenAddressingMap {

    static class Element {
        int key;
        int value;

        Element(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private static int INITIAL_SIZE_ORDER = 3;

    private Element[] table;
    private int size;

    public OpenAddressingMap() {
        size = (int) Math.pow(2, INITIAL_SIZE_ORDER);
        table = new Element[size];
    }

    public void put(int key, int value) {
        int attempt = 0;
        int hash = getDoubleHash(key, table.length, attempt);
        Element element = table[hash];
        while (element != null && element.key != key) {
            attempt++;
            hash = getDoubleHash(key, table.length, attempt);
            element = table[getDoubleHash(key, table.length, attempt)];
        }
        table[hash] = new Element(key, value);
    }

    public Integer get(int key) {
        int attempt = 0;
        int hash = getDoubleHash(key, table.length, attempt);
        Element element = table[hash];
        while (element != null && element.key != key) {
            attempt++;
            element = table[getDoubleHash(key, table.length, attempt)];
        }
        return (element == null) ? null : element.value;
    }


}
