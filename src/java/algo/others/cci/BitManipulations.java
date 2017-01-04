package algo.others.cci;


/**
 * Created by andrey tsarevskiy
 */
public class BitManipulations {

    /**
     * Copy M to N from i to j bits
     */
    public static int question1(int m, int n, int i, int j) {

        int allOnes = ~0;

        int left = ~(allOnes << i );
        int right = allOnes << j + 1;
        int mask = left | right;

        n = n & mask;

        m = m & ~mask;

        return n | m;
    }

}
