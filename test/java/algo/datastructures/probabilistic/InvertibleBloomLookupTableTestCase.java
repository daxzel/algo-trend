package algo.datastructures.probabilistic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InvertibleBloomLookupTableTestCase {

    @Test
    public void testSimple() {
        InvertibleBloomLookupTable filter = new InvertibleBloomLookupTable(100, 2);

        filter.addItem(2, 100);
        filter.addItem(1000, 9);
        filter.addItem(10, 120);
        filter.addItem(3534, 10);
        filter.addItem(12, 1);
        filter.addItem(99999, 0);

        assertTrue(filter.contains(2));
        assertEquals(filter.get(2), 100);
        assertTrue(filter.contains(12));
        assertFalse(filter.contains(1001));
    }

}
