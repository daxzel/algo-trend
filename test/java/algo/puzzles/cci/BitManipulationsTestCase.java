package algo.puzzles.cci;

import static org.junit.jupiter.api.Assertions.assertEquals;

import algo.puzzles.bitwise.CopyPartOfTheNumber;
import org.junit.jupiter.api.Test;


/**
 * Created by andrey tsarevskiy
 */
public class BitManipulationsTestCase {

    @Test
    public void testUniqueCharacters() {

        int n = 0b10001111000101010101010;
        int m = 0b10001111000111110111010;

        int result = CopyPartOfTheNumber.question1(m, n, 4, 6);
        System.out.print(Integer.toBinaryString(result));
        assertEquals(result, 0b10001111000101010111010);
    }

}
