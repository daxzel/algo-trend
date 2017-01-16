package algo.datastructures.map;

/**
 * Created by andrey tsarevskiy
 */
public class OpenAddressingMap {

    public static class Element {
        public int key;
        public int value;

        public Element(int key, int value) {
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
        int hash = getDoubleHash(key, attempt);
        Element element = table[hash];
        while (element != null && element.key != key) {
            attempt++;
            hash = getDoubleHash(key, attempt);
            element = table[getDoubleHash(key, attempt)];
        }
        table[hash] = new Element(key, value);
    }

    public Integer get(int key) {
        int attempt = 0;
        int hash = getDoubleHash(key, attempt);
        Element element = table[hash];
        while (element != null && element.key != key) {
            attempt++;
            element = table[getDoubleHash(key, attempt)];
        }
        return (element == null) ? null : element.value;
    }

    private int getDoubleHash(int key, int attempt) {
        return ((key & 0x7FFFFFFF) + attempt * (key & 0x7FFFFFFF)) % table.length;
    }

}
