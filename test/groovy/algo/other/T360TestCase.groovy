package algo.other

import algo.other.codility.T360

/**
 * Created by Tsarevskiy
 */
class T360TestCase extends GroovyTestCase {

    void testAnagramOfPalindrome() {
        assertEquals(T360.AnagramOfPalindrome.isAnagramOfPalindrome("dooernedeevrvn"), 1)
        assertEquals(T360.AnagramOfPalindrome.isAnagramOfPalindrome("aabcba"), 0)
    }

    void testLineIntersection() {
        assertEquals(T360.LineIntersection.isLineIntersect(2,5,6,2,3,1,5,2), false)
        assertEquals(T360.LineIntersection.isLineIntersect(0,0,5,5,0,5,5,0), true)
        assertEquals(T360.LineIntersection.isLineIntersect(0,0,0,0,0,0,0,0), true)
    }

    void testCountryCounter() {
        int[][] A = [[2,1,2],[3,1,4]];
        assertEquals(T360.CountryCounter.countCountries(A), 5)

        A = [[1,1,2],[3,1,4],[1,1,4]];
        assertEquals(T360.CountryCounter.countCountries(A), 4)
    }

    void testDistanceCounter() {
        int[] A = [2,1,2,3,1,4];
        assertEquals(T360.DistanceCounter.countMinDistance(A), 0)

        A = [100,0,23,111111,22222,-400];
        assertEquals(T360.DistanceCounter.countMinDistance(A), 23)
    }
}
