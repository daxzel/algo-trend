package algo.puzzles.geometry;

import algo.puzzles.Codility;

/**
 * Created by andrey tsarevskiy
 */
@Codility
public class LineIntersection {

    /**
     * Check whether lines intersect or not
     */
    public static boolean isLineIntersect(int K, int L, int M, int N, int P, int Q, int R, int S) {
        return simpleCase(K, M, P, R) && simpleCase(L, N, Q, S) && (
                intersect(K, L, M, N, P, Q) * intersect(K, L, M, N, R, S) <= 0) && (
                intersect(P, Q, R, S, K, L) * intersect(P, Q, R, S, M, N) <= 0);
    }

    private static int intersect(int ax, int ay, int bx, int by, int cx, int cy) {
        return (bx - ax) * (cy - ay) - (by - ay) * (cx - ax);
    }

    private static boolean simpleCase(int a, int b, int c, int d) {
        if (a > b) {
            int temp = a;
            a = b;
            b = temp;
        }

        if (c > d) {
            int temp = c;
            c = d;
            d = temp;
        }

        return Math.max(a, c) <= Math.min(b, d);
    }

}
