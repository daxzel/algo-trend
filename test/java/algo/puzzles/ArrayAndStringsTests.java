package algo.puzzles;

import static algo.puzzles.ReplaceSpaces.replaceSpaces;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

/**
 * Created by andrey tsarevskiy
 */
public class ArrayAndStringsTests {

    @Test
    public void testSpaceReplace() {
        String str = "abc d e f";
        char[] arr = new char[str.length() + 3 * 2];
        for (int i = 0; i < str.length(); i++) {
            arr[i] = str.charAt(i);
        }
        assertEquals(new String(replaceSpaces(arr, str.length())), "abc%20d%20e%20f");
    }

    @Test
    public void rotateMatrix() {
        int[][] test = {
                {1, 2, 3, 4},
                {1, 1, 1, 1},
                {1, 3, 3, 3},
                {4, 4, 4, 4},
        };
        int[][] result = {
                {4, 1, 1, 1},
                {4, 3, 1, 2},
                {4, 3, 1, 3},
                {4, 3, 1, 4},
        };
        ArrayRotation.rotate(test, 4);
        assertTrue(Arrays.deepEquals(test, result));
    }

    @Test
    public void testIsRotation() {
        assertTrue(StringsRotation.isRotation("test", "stte"));
        assertFalse(StringsRotation.isRotation("test", "test"));
        assertFalse(StringsRotation.isRotation("test", "sdfdsfsdfsd"));
    }
}

