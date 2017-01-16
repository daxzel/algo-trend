package algo.datastructures.map

/**
 * Created by Tsarevskiy
 */
class OpenAddressingMapTestCase extends GroovyTestCase {

    void testSimpleAdding() {
        OpenAddressingMap map = new OpenAddressingMap()
        map.put(23423, 11)
        map.put(12, 23423)
        map.put(423, 5675)
        map.put(11, 171)
        map.put(999, 834)
        map.put(2342342, 255)
        map.put(123, 255)

        assertEquals(map.get(23423), 11)
        assertEquals(map.get(12), 23423)
        assertEquals(map.get(423), 5675)
        assertEquals(map.get(11), 171)
        assertEquals(map.get(999), 834)
        assertEquals(map.get(2342342), 255)
        assertEquals(map.get(123), 255)
    }
}
