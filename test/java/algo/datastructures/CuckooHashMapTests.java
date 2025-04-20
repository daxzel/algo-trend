package algo.datastructures;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * Created by Tsarevskiy
 */
public class CuckooHashMapTests {

    @Test
    @Disabled
    public void testSimpleAdding() {
        CuckooHashMap map = new CuckooHashMap();
        map.put(23423, 11);
        map.put(12, 23423);
        map.put(423, 5675);
        map.put(11, 171);
        map.put(999, 834);
        map.put(2342342, 255);
        map.put(123, 255);

        assertEquals(map.get(23423), 11);
        assertEquals(map.get(12), 23423);
        assertEquals(map.get(423), 5675);
        assertEquals(map.get(11), 171);
        assertEquals(map.get(999), 834);
        assertEquals(map.get(2342342), 255);
        assertEquals(map.get(123), 255);
    }

    @Test
    public void testSimpleRemoving() {
        CuckooHashMap map = new CuckooHashMap();
        map.put(23423, 11);
        map.put(12, 23423);
        map.put(423, 5675);
        Integer removed = map.remove(423);

        assertNotNull(map.get(23423));
        assertNull(map.get(432));
        assertEquals(removed, 5675);
    }

    @Test
    public void testResize() {
        CuckooHashMap map = new CuckooHashMap();
        for (int i = 0; i < 10000; i++) {
            map.put(i, 10000 - i);
        }

        for (int i = 0; i < 10000; i++) {
            map.put(i, 10000 - i);
            assertEquals(map.get(i), 10000 - i);
        }

    }

    @Test
    public void testRandomValues() {
        Random r = new Random();
        HashMap<Integer, Integer> result = new HashMap<>();
        CuckooHashMap map = new CuckooHashMap();
        for (int i = 0; i < 10000; i++) {
            int key = r.nextInt();
            int value = r.nextInt();
            result.put(key, value);
            map.put(key, value);
        }

        for (Entry<Integer, Integer> entry : result.entrySet()) {
            Integer value = map.get(entry.getKey());
            assertEquals(value, entry.getValue());
        }
    }

}
