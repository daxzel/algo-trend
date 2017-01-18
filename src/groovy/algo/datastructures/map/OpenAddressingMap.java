package algo.datastructures.map;

import java.util.Arrays;

import static algo.algorithms.hash.SimpleHash.getDoubleHash;

/**
 * Created by andrey tsarevskiy
 */
public class OpenAddressingMap {

    static class Element {
        int key;
        int value;
        boolean removed = false;


        Element(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private Element[] table;
    private int size = 0;

    public OpenAddressingMap() {
        int INITIAL_SIZE_ORDER = 3;
        int size = (int) Math.pow(2, INITIAL_SIZE_ORDER);
        table = new Element[size];
    }

    public void put(int key, int value) {
        resizeIfNeeded();

        int attempt = 0;
        int hash = getDoubleHash(key, table.length, attempt);
        Element element = table[hash];
        while (element != null && (element.removed || element.key != key)) {
            attempt++;
            hash = getDoubleHash(key, table.length, attempt);
            element = table[getDoubleHash(key, table.length, attempt)];
        }
        table[hash] = new Element(key, value);
        size++;
    }

    public Integer get(int key) {
        int attempt = 0;
        int hash = getDoubleHash(key, table.length, attempt);
        Element element = table[hash];
        while (element != null && (element.removed || element.key != key)) {
            attempt++;
            element = table[getDoubleHash(key, table.length, attempt)];
        }
        return (element == null) ? null : element.value;
    }

    public Integer remove(int key) {
        int attempt = 0;
        int hash = getDoubleHash(key, table.length, attempt);
        Element element = table[hash];
        while (element != null && (element.removed || element.key != key)) {
            attempt++;
            element = table[getDoubleHash(key, table.length, attempt)];
        }
        if (element != null) {
            element.removed = true;
        }
        return (element == null) ? null : element.value;
    }

    private void resizeIfNeeded() {
        if (( ((double)size) / table.length) > 0.7 ) {
            resize(table.length * 2);
        }
    }

    private void resize(int newSize) {
        Element[] oldTable = table;
        table = new Element[newSize];
        Arrays.stream(oldTable).forEach( element -> {
            if (element != null && !element.removed) {
                this.put(element.key, element.value);
            }
        });
    }

}
