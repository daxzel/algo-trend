package algo.puzzles;

import algo.puzzles.Codility;

/**
 * Created by andrey tsarevskiy
 */
@Codility
public class AnagramOfPalindrome {

    /**
     * In this problem we consider only strings of lower-case English letters (a-z). A string is a palindrome if it has
     * exactly the same sequence of characters when traversed left-to-right as right-to-left. For example, the following
     * strings are palindromes:
     * <p>
     * "kayak" "codilitytilidoc" "neveroddoreven"
     * <p>
     * A string A is an anagram of a string B if it consists of exactly the same characters, but possibly in another
     * order. For example, the following strings are each other's anagrams:
     * <p>
     * A="mary" B="army" A="rocketboys" B="octobersky" A="codility" B="codility"
     * <p>
     * Write a function int isAnagramOfPalindrome(String S); which returns 1 if the string s is a anagram of some
     * palindrome, or returns 0 otherwise. For example your function should return 1 for the argument "dooernedeevrvn",
     * because it is an anagram of a palindrome "neveroddoreven". For argument "aabcba", your function should return 0.
     * <p>
     * Algorithmic complexity should be O(n) where n is S length Memory complexity constant
     */
    public static int isAnagramOfPalindrome(String S) {
        int[] A = new int[26];
        for (int i = 0; i < S.length(); i++) {
            char a = S.charAt(i);
            A[a - 97]++;
        }

        boolean evenArray = S.length() % 2 == 0;

        for (int a : A) {
            if (a % 2 != 0) {
                if (evenArray) {
                    return 0;
                } else {
                    evenArray = true;
                }

            }

        }

        return 1;
    }

}
