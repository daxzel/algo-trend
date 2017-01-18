package algo.puzzles.geeksforgeeks;

/**
 * Created by andrey tsarevskiy
 */
public class DynamicProgramming {

    /**
     * Given a sequence of matrices, find the most efficient way to multiply these matrices together.
     * The problem is not actually to perform the multiplications, but merely to decide in which order
     * to perform the multiplications.
     * <p>
     * We have many options to multiply a chain of matrices because matrix multiplication is associative.
     * In other words, no matter how we parenthesize the product, the result will be the same.
     * For example, if we had four matrices A, B, C, and D, we would have:
     * <p>
     * (ABC)D = (AB)(CD) = A(BCD) = ....
     * However, the order in which we parenthesize the product affects the number of simple arithmetic
     * operations needed to compute the product, or the efficiency. For example, suppose A is a 10 × 30
     * matrix, B is a 30 × 5 matrix, and C is a 5 × 60 matrix. Then,
     * <p>
     * (AB)C = (10×30×5) + (10×5×60) = 1500 + 3000 = 4500 operations
     * A(BC) = (30×5×60) + (10×30×60) = 9000 + 18000 = 27000 operations.
     */
    public static int matrixChainOrder(int p[]) {

        Integer[][] cache = new Integer[p.length][p.length];

        return matrixChainOrderInternal(p, 1, p.length - 1, cache);
    }

    public static int matrixChainOrderInternal(int p[], int i, int j, Integer[][] cache) {

        int min = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
            Integer firstCache = cache[i][k];
            if (firstCache == null) {
                firstCache = matrixChainOrderInternal(p, i, k, cache);
                cache[i][k] = firstCache;
            }
            Integer secondCache = cache[k + 1][j];
            if (secondCache == null) {
                secondCache = matrixChainOrderInternal(p, k + 1, j, cache);
                cache[k + 1][j] = secondCache;
            }

            int count = firstCache + secondCache + p[k] * p[k + 1] * p[j];

            if (count < min) {
                min = count;
            }
        }
        return min;
    }

}
