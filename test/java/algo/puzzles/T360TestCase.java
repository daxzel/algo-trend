package algo.puzzles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import algo.puzzles.array.CountryCounter;
import algo.puzzles.array.MinDistanceCounter;
import algo.puzzles.geometry.LineIntersection;
import algo.puzzles.string.AnagramOfPalindrome;
import org.junit.jupiter.api.Test;

/**
 * Created by Tsarevskiy
 */
public class T360TestCase {

    @Test
    public void testAnagramOfPalindrome() {
        assertEquals(AnagramOfPalindrome.isAnagramOfPalindrome("dooernedeevrvn"), 1);
        assertEquals(AnagramOfPalindrome.isAnagramOfPalindrome("aabcba"), 0);
    }

    @Test
    public void testLineIntersection() {
        assertFalse(LineIntersection.isLineIntersect(2, 5, 6, 2, 3, 1, 5, 2));
        assertTrue(LineIntersection.isLineIntersect(0, 0, 5, 5, 0, 5, 5, 0));
        assertTrue(LineIntersection.isLineIntersect(0, 0, 0, 0, 0, 0, 0, 0));
    }

    @Test
    public void testCountryCounter() {
        int[][] A = new int[][]{
                {2, 1, 2},
                {3, 1, 4}
        };
        assertEquals(CountryCounter.countCountries(A), 5);

        A = new int[][]{
                {1, 1, 2},
                {3, 1, 4},
                {1, 1, 4},
        };
        assertEquals(CountryCounter.countCountries(A), 4);
    }

    @Test
    public void testDistanceCounter() {
        int[] A = new int[]{2, 1, 2, 3, 1, 4};
        assertEquals(MinDistanceCounter.countMinDistance(A), 0);

        A = new int[]{100, 0, 23, 111111, 22222, -400};
        assertEquals(MinDistanceCounter.countMinDistance(A), 23);
    }

}
