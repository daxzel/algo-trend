package algo.algorithms.hash;

/**
 * Created by andrey tsarevskiy
 */
public class SimpleHash {

    public static int getHash(int key, int socketsNumber) {
        return (key & 0x7FFFFFFF) % socketsNumber;
    }

    public static int getDoubleHash(int key, int socketsNumber, int attempt) {
        return ((key & 0x7FFFFFFF) + attempt * (key & 0x7FFFFFFF)) % socketsNumber;
    }

}
