package algo.others.cci;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by andrey tsarevskiy
 */
public class ArrayAndStrings {

    /**
     * If a string has unique characters
     */
    public static boolean uniqueCharacters(String s) {
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
    public static boolean uniqueCharactersWithoutMemmory(String s) {
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

}
