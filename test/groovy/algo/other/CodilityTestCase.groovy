package algo.other

/**
 * Created by Tsarevskiy
 */
class CodilityTestCase extends GroovyTestCase {

    void testAnagramOfPalindrome() {
        GroovyTestCase.assertEquals(Codility.AnagramOfPalindrome.isAnagramOfPalindrome("dooernedeevrvn"), 1)
        GroovyTestCase.assertEquals(Codility.AnagramOfPalindrome.isAnagramOfPalindrome("aabcba"), 0)
    }

    void testLineIntersection() {
        GroovyTestCase.assertEquals(Codility.LineIntersection.isLineIntersect(2,5,6,2,3,1,5,2), false)
        GroovyTestCase.assertEquals(Codility.LineIntersection.isLineIntersect(0,0,5,5,0,5,5,0), true)
        GroovyTestCase.assertEquals(Codility.LineIntersection.isLineIntersect(0,0,0,0,0,0,0,0), true)
    }

    void testCountryCounter() {
        int[][] A = [[2,1,2],[3,1,4]];
        GroovyTestCase.assertEquals(Codility.CountryCounter.countCountries(A), 5)

        A = [[1,1,2],[3,1,4],[1,1,4]];
        GroovyTestCase.assertEquals(Codility.CountryCounter.countCountries(A), 4)
    }

    void testDistanceCounter() {
        int[] A = [2,1,2,3,1,4];
        GroovyTestCase.assertEquals(Codility.DistanceCounter.countMinDistance(A), 0)

        A = [100,0,23,111111,22222,-400];
        GroovyTestCase.assertEquals(Codility.DistanceCounter.countMinDistance(A), 23)
    }
}
