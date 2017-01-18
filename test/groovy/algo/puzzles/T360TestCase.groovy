package algo.puzzles

import algo.puzzles.array.CountryCounter
import algo.puzzles.array.MinDistanceCounter
import algo.puzzles.geometry.LineIntersection
import algo.puzzles.string.AnagramOfPalindrome

/**
 * Created by Tsarevskiy
 */
class T360TestCase extends GroovyTestCase {

    void testAnagramOfPalindrome() {
        assertEquals(AnagramOfPalindrome.isAnagramOfPalindrome("dooernedeevrvn"), 1)
        assertEquals(AnagramOfPalindrome.isAnagramOfPalindrome("aabcba"), 0)
    }

    void testLineIntersection() {
        assertEquals(LineIntersection.isLineIntersect(2,5,6,2,3,1,5,2), false)
        assertEquals(LineIntersection.isLineIntersect(0,0,5,5,0,5,5,0), true)
        assertEquals(LineIntersection.isLineIntersect(0,0,0,0,0,0,0,0), true)
    }

    void testCountryCounter() {
        int[][] A = [[2,1,2],[3,1,4]];
        assertEquals(CountryCounter.countCountries(A), 5)

        A = [[1,1,2],[3,1,4],[1,1,4]];
        assertEquals(CountryCounter.countCountries(A), 4)
    }

    void testDistanceCounter() {
        int[] A = [2,1,2,3,1,4];
        assertEquals(MinDistanceCounter.countMinDistance(A), 0)

        A = [100,0,23,111111,22222,-400];
        assertEquals(MinDistanceCounter.countMinDistance(A), 23)
    }
}
