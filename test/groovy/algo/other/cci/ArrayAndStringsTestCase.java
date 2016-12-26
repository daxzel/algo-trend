package algo.other.cci;

import org.junit.Assert;
import org.junit.Test;

import static algo.others.cci.ArrayAndStrings.isPermutation;
import static algo.others.cci.ArrayAndStrings.uniqueCharacters;
import static algo.others.cci.ArrayAndStrings.uniqueCharactersWithoutMemmory;

/**
 * Created by andreytsarevskiy on 26/12/16.
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
}

