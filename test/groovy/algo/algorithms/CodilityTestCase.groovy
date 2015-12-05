package groovy.algo.algorithms

/**
 * Created by Tsarevskiy
 */
class CodilityTestCase extends GroovyTestCase {

    void testAnagramOfPalindrome() {
        assertEquals(Codility.AnagramOfPalindrome.isAnagramOfPalindrome("dooernedeevrvn"), 1)
        assertEquals(Codility.AnagramOfPalindrome.isAnagramOfPalindrome("aabcba"), 0)
    }

    void testLineIntersection() {
        assertEquals(Codility.LineIntersection.isLineIntersect(2,5,6,2,3,1,5,2), false)
        assertEquals(Codility.LineIntersection.isLineIntersect(0,0,5,5,0,5,5,0), true)
        assertEquals(Codility.LineIntersection.isLineIntersect(0,0,0,0,0,0,0,0), true)
    }

    void testCountryCounter() {
        int[][] A = [[2,1,2],[3,1,4]];
        assertEquals(Codility.CountryCounter.countCountries(A), 5)

        A = [[1,1,2],[3,1,4],[1,1,4]];
        assertEquals(Codility.CountryCounter.countCountries(A), 4)
    }

    void testDistanceCounter() {
        int[] A = [2,1,2,3,1,4];
        assertEquals(Codility.DistanceCounter.countMinDistance(A), 0)

        A = [100,0,23,111111,22222,-400];
        assertEquals(Codility.DistanceCounter.countMinDistance(A), 23)
    }
}
