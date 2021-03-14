package algo.datastructures;

import static org.junit.jupiter.api.Assertions.assertEquals;

import algo.datastructures.LRUCache;
import org.junit.jupiter.api.Test;

/**
 * Created by andrey tsarevskiy
 */
public class LRUTests {

    @Test
    public void lruTestOne() {

        LRUCache cache = new LRUCache(Integer::valueOf, 3);
        assertEquals((int) cache.get("23"), 23);
        assertEquals((int) cache.get("5"), 5);
        assertEquals((int) cache.get("2"), 2);
        assertEquals((int) cache.get("1"), 1);
        assertEquals((int) cache.get("5"), 5);
        assertEquals((int) cache.get("1"), 1);
        assertEquals((int) cache.get("23"), 23);
        assertEquals((int) cache.get("1"), 1);

    }

}
