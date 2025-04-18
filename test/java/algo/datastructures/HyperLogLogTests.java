package algo.datastructures;

import static org.junit.jupiter.api.Assertions.assertEquals;

import algo.datastructures.HyperLogLog;
import org.junit.jupiter.api.Test;



public class HyperLogLogTests {

    @Test
    public void testSimple() {
        HyperLogLog hll = new HyperLogLog(1000);
        hll.count("1");
        hll.count("2");
        hll.count("3");
        hll.count("4");
        hll.count("5");
        hll.count("6");
        hll.count("7");
        hll.count("8");
        hll.count("9");
        hll.count("10");

        assertEquals(hll.getEstimate(), 10);
    }
}
