package algo.other.cci;

import org.junit.Assert;
import org.junit.Test;

import static algo.others.cci.ArrayAndStrings.*;

/**
 * Created by andrey tsarevskiy
 */
public class ArrayAndStringsTestCase extends Assert {

    @Test
    public void testUniqueCharacters() {
        assertTrue(uniqueCharacters("abc"));
        assertFalse(uniqueCharacters("aba"));
        assertFalse(uniqueCharacters("abcdlpqowc"));
        assertTrue(uniqueCharacters("bcdlpqowa"));

        assertTrue(uniqueCharactersWithoutMemmory("abc"));
        assertFalse(uniqueCharactersWithoutMemmory("aba"));
        assertFalse(uniqueCharactersWithoutMemmory("abcdlpqowc"));
        assertTrue(uniqueCharactersWithoutMemmory("bcdlpqowa"));
    }

    @Test
    public void testIsStringsArePermutations() {
        assertTrue(isPermutation("abc", "bca"));
        assertFalse(isPermutation("abc2", "bca"));
    }

    @Test
    public void testSpaceReplace() {
        String str = "abc d e f";
        char[] arr = new char[str.length() + 3 * 2];
        for (int i = 0; i < str.length(); i++) {
            arr[i] = str.charAt(i);
        }
        assertEquals(new String(replaceSpaces(arr, str.length())), "abc%20d%20e%20f");
    }
}

