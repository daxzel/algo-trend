package algo.puzzles.cci;

import algo.puzzles.array.ArrayRotation;
import algo.puzzles.string.StringsRotation;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static algo.puzzles.string.ReplaceSpaces.replaceSpaces;

/**
 * Created by andrey tsarevskiy
 */
public class ArrayAndStringsTestCase extends Assert {

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

