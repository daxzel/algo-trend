package algo.other.codility;

/**
 * Created by Tsarevskiy
 */
class T360 {

    class AnagramOfPalindrome {
        /**
         *
         * In this problem we consider only strings of lower-case English letters (a-z).
         A string is a palindrome if it has exactly the same sequence of characters when traversed left-to-right
         as right-to-left. For example, the following strings are palindromes:

         "kayak"
         "codilitytilidoc"
         "neveroddoreven"

         A string A is an anagram of a string B if it consists of exactly the same characters,
         but possibly in another order. For example, the following strings are each other's anagrams:

         A="mary" B="army" A="rocketboys" B="octobersky" A="codility" B="codility"

         Write a function
         int isAnagramOfPalindrome(String S);
         which returns 1 if the string s is a anagram of some palindrome, or returns 0 otherwise.
         For example your function should return 1 for the argument "dooernedeevrvn", because it is an anagram of a
         palindrome "neveroddoreven". For argument "aabcba", your function should return 0.

         Algorithmic complexity should be O(n) where n is S length
         Memory complexity constant
         */

        static int isAnagramOfPalindrome(String S) {
            int[] A = new int[26]
            for (int i = 0; i < S.length(); i++) {
                char a = S.charAt(i)
                A[a - 97]++
            }
            boolean evenArray = S.length() % 2 == 0;

            for (int a : A) {
                if (a % 2 != 0) {
                    if (evenArray) {
                        return 0
                    } else {
                        evenArray = true
                    }
                }
            }
            return 1
        }
    }

    class LineIntersection {
        static boolean isLineIntersect(int K, int L, int M, int N, int P, int Q, int R, int S) {
            return simpleCase(K, M, P, R) &&
                    simpleCase(L, N, Q, S) &&
                    (intersect(K, L, M, N, P, Q) * intersect(K, L, M, N, R, S) <= 0) &&
                    (intersect(P, Q, R, S, K, L) * intersect(P, Q, R, S, M, N) <= 0)
        }

        private static int intersect(int ax, int ay, int bx, int by, int cx, int cy) {
            return (bx - ax) * (cy - ay) - (by - ay) * (cx - ax)
        }

        private static boolean simpleCase(int a, int b, int c, int d) {
            if (a > b) {
                int temp = a
                a = b
                b = temp
            }
            if (c > d) {
                int temp = c
                c = d
                d = temp
            }
            return Math.max(a, c) <= Math.min(b, d)
        }

    }

    class CountryCounter {
        static int countCountries(int[][] A) {
            // write your code in Java SE 8

            int N = A.length
            int M = A[0].length

            int[][] B = new int[N][M]

            int countryCounter = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (countCountryFromThisPoint(A, B, N, M, i, j, A[i][j])) {
                        countryCounter++
                    }
                }
            }
            return countryCounter
        }

        static boolean countCountryFromThisPoint(int[][] A, int[][] B, int N, int M, int i, int j, int color) {
            if ((B[i][j] == 0) && (A[i][j] == color)) {
                B[i][j] = 1;
                if (i + 1 < N) {
                    countCountryFromThisPoint(A, B, N, M, i + 1, j, color)
                }
                if (j + 1 < M) {
                    countCountryFromThisPoint(A, B, N, M, i, j + 1, color)
                }
                if (j - 1 >= 0) {
                    countCountryFromThisPoint(A, B, N, M, i, j - 1, color)
                }
                if (i - 1 >= 0) {
                    countCountryFromThisPoint(A, B, N, M, i - 1, j, color)
                }
                return true
            }
            return false
        }
    }

    class DistanceCounter {
        static int countMinDistance(int[] A) {
            // write your code in Java SE 8
            Arrays.sort(A)
            int minDistance = Math.abs(A[0] - A[1])
            if (minDistance == 0) {
                return minDistance
            }
            for (int i = 1; i < (A.length - 1); i++) {
                int distance = Math.abs(A[i] - A[i + 1])
                if (distance < minDistance) {
                    minDistance = distance
                    if (minDistance == 0) {
                        return minDistance
                    }
                }
            }
            return minDistance
        }
    }

}
