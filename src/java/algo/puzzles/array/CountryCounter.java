package algo.puzzles.array;

import algo.puzzles.Codility;

/**
 * Created by andrey tsarevskiy
 */
@Codility
public class CountryCounter {

    public static int countCountries(int[][] A) {
        // write your code in Java SE 8

        int N = A.length;
        int M = A[0].length;

        int[][] B = new int[N][M];

        int countryCounter = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (countCountryFromThisPoint(A, B, N, M, i, j, A[i][j])) {
                    countryCounter++;
                }

            }

        }

        return countryCounter;
    }

    public static boolean countCountryFromThisPoint(int[][] A, int[][] B, int N, int M, int i, int j,
            int color) {
        if ((B[i][j] == 0) && (A[i][j] == color)) {
            B[i][j] = 1;
            if (i + 1 < N) {
                countCountryFromThisPoint(A, B, N, M, i + 1, j, color);
            }

            if (j + 1 < M) {
                countCountryFromThisPoint(A, B, N, M, i, j + 1, color);
            }

            if (j - 1 >= 0) {
                countCountryFromThisPoint(A, B, N, M, i, j - 1, color);
            }

            if (i - 1 >= 0) {
                countCountryFromThisPoint(A, B, N, M, i - 1, j, color);
            }

            return true;
        }

        return false;
    }

}
