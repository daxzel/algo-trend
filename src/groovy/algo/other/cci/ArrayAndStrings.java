package algo.other.cci;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by andrey tsarevskiy
 */
public class ArrayAndStrings {

    /**
     * If a string has unique characters
     */
    public static boolean question1(String s) {
        HashSet<Character> uniqueness = new HashSet<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (uniqueness.contains(c)) {
                return false;
            } else {
                uniqueness.add(c);
            }
        }
        return true;
    }

    /**
     * If a string has unique characters. An implementation without additional memmory
     */
    public static boolean question1_2(String s) {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            for (int j = i + 1; j < s.length(); j++) {
                if (c == s.charAt(j)) {
                    return false;
                }
            }
        }
        return true;
    }

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
     * Rotate image 90 degrees
     */
    public static void question6(int[][] matrix, int n) {
        for (int layer = 0; layer < n / 2; ++layer) {
            int first = layer;
            int last = n - 1 - layer;
            for (int i = first; i < last; ++i) {
                int offset = i - first;
                int top = matrix[first][i]; // save top

                // left -> top
                matrix[first][i] = matrix[last - offset][first];

                // bottom -> left
                matrix[last - offset][first] = matrix[last][last - offset];

                // right -> bottom
                matrix[last][last - offset] = matrix[i][last];

                // top -> right
                matrix[i][last] = top; // right <- saved top
            }
        }
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
