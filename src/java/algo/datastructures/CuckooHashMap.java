package algo.datastructures;

import static algo.algorithm.hash.SimpleHash.getDoubleHash;

import algo.algorithm.hash.SimpleHash;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by andrey tsarevskiy
 */
public class CuckooHashMap {

    static class Element {

        int key;
        int value;

        Element(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private Element[] table1;
    private Element[] table2;
    private int size = 0;

    public CuckooHashMap() {
        int INITIAL_SIZE_ORDER = 3;
        size = (int) Math.pow(2, INITIAL_SIZE_ORDER);
        table1 = new Element[size];
        table2 = new Element[size];
    }

    public void put(int key, int value) {

        if (get(key) != null) {
            return;
        }

        Element element = putInternal(new Element(key, value));
        if (element != null) {
            LinkedList<Element> allElements = new LinkedList<>();
            allElements.add(element);
            allElements.addAll(Arrays.stream(table1).filter(Objects::nonNull).collect(Collectors.toList()));
            allElements.addAll(Arrays.stream(table2).filter(Objects::nonNull).collect(Collectors.toList()));

            RESIZE: while (true) {
                size = size * 2;
                table1 = new Element[size];
                table2 = new Element[size];
                for (Element el : allElements) {
                    if (putInternal(el) != null) {
                        continue RESIZE;
                    }
                }
                break;
            }
        }

    }


    public Element putInternal(Element element) {
        Set<Integer> visited = new HashSet<>();

        Element currentValue = element;

        while (true) {
            int hash1 = SimpleHash.getHash(currentValue.key, table1.length);
            if (visited.contains(hash1)) {
                return currentValue;
            }
            visited.add(hash1);
            if (table1[hash1] == null) {
                table1[hash1] = currentValue;
                return null;
            } else {
                Element previousValue = table1[hash1];
                table1[hash1] = currentValue;
                currentValue = previousValue;
            }
            int hash2 = SimpleHash.getHash(currentValue.key, table2.length);
            if (table2[hash2] == null) {
                table2[hash2] = currentValue;
                return null;
            } else {
                Element previousValue = table2[hash2];
                table2[hash2] = currentValue;
                currentValue = previousValue;
            }
        }

    }


    public Integer get(int key) {
        int hash1 = SimpleHash.getHash(key, table1.length);
        if (table1[hash1] != null && table1[hash1].key == key) {
            return table1[hash1].value;
        }
        int hash2 = SimpleHash.getHash(key, table2.length);
        if (table2[hash2] != null && table2[hash2].key == key) {
            return table2[hash2].value;
        }
        return null;
    }

    public Integer remove(int key) {
        int hash1 = SimpleHash.getHash(key, table1.length);
        if (table1[hash1] != null && table1[hash1].key == key) {
            Element element = table1[hash1];
            table1[hash1] = null;
            return element.value;
        }
        int hash2 = SimpleHash.getHash(key, table2.length);
        if (table2[hash2] != null && table2[hash2].key == key) {
            Element element = table2[hash1];
            table2[hash1] = null;
            return element.value;
        }
        return null;
    }

}
