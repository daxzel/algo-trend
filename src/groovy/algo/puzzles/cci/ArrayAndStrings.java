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

    /**
     * Replace spaces with %20
     */
    public static char[] replaceSpaces(char[] str, int length) {
        int spaceCount = 0, index, i;
        for (i = 0; i < length; i++) {
            if (str[i] == ' ') {
                spaceCount++;
            }
        }
        index = length + spaceCount * 2;
        for (i = length - 1; i >= 0; i--) {
            if (str[i] == ' ') {
                str[index - 1] = '0';
                str[index - 2] = '2';
                str[index - 3] = '%';
                index = index - 3;
            } else {
                str[index - 1] = str[i];
                index--;
            }
        }
        return str;
    }

    /**
     * Is rotation?
     */
    public static boolean question8(String s1, String s2) {
        if (s1.equals(s2)) {
            return false;
        }
        if (s1.length() == s2.length()) {
            String concat = s1 + s1;
            return concat.contains(s2);
        }
        return false;
    }

}
