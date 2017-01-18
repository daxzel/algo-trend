package algo.puzzles.string;

import algo.puzzles.CrackingCodingInterview;

/**
 * Created by andrey tsarevskiy
 */
@CrackingCodingInterview
public class StringsRotation {
    /**
     * Check whether two string are rotations of each other?
     */
    public static boolean isRotation(String s1, String s2) {
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
