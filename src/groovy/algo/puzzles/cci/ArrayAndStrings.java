package algo.puzzles.cci;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by andrey tsarevskiy
 */
public class ArrayAndStrings {

    /**
     * If one is permutation of another
     */
    public static boolean isPermutation(String s1, String s2) {
        char[] chars1 = s1.toCharArray();
        Arrays.sort(chars1);
        char[] chars2 = s2.toCharArray();
        Arrays.sort(chars2);
        return Arrays.equals(chars1, chars2);
    }

}
