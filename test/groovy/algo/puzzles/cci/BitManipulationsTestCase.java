package algo.puzzles.cci;

import algo.puzzles.bitwise.CopyPartOfTheNumber;
import org.junit.Assert;
import org.junit.Test;


/**
 * Created by andrey tsarevskiy
 */
public class BitManipulationsTestCase extends Assert {


    @Test
    public void testUniqueCharacters() {

        int n = 0b10001111000101010101010;
        int m = 0b10001111000111110111010;

        int result = CopyPartOfTheNumber.question1(m, n, 4, 6);
        System.out.print(Integer.toBinaryString(result));
        assertEquals(result, 0b10001111000101010111010);
    }

}
