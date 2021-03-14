package algo.datastructures;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import algo.datastructures.OpenAddressingMap;
import org.junit.jupiter.api.Test;

/**
 * Created by Tsarevskiy
 */
public class OpenAddressingMapTests {

    @Test
    public void testSimpleAdding() {
        OpenAddressingMap map = new OpenAddressingMap();
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
        OpenAddressingMap map = new OpenAddressingMap();
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
        OpenAddressingMap map = new OpenAddressingMap();
        for (int i = 0; i < 10000; i++) {
            map.put(i, 10000 - i);
        }

        for (int i = 0; i < 10000; i++) {
            map.put(i, 10000 - i);
            assertEquals(map.get(i), 10000 - i);
        }

    }

}
