package algo.algorithm.string;

public class KMPMatching {

    /**
     * The Knuth–Morris–Pratt string-searching algorithm (or KMP algorithm) searches for occurrences of a "word" W
     * within a main "text string" S by employing the observation that when a mismatch occurs, the word itself embodies
     * sufficient information to determine where the next match could begin, thus bypassing re-examination of previously
     * matched characters.
     * <p>
     * The algorithm was conceived in 1970 by Donald Knuth and Vaughan Pratt, and independently by James H. Morris. This
     * was the first linear-time algorithm for string matching. The three published it jointly in 1977. Independently,
     * in 1969, Matiyasevich discovered a similar algorithm, coded by a two-dimensional Turing machine, while studying a
     * string-pattern-matching recognition problem.
     * <p>
     * Performance: O(N + M)
     */
    public static int find(char[] word, char[] text) {

        int[] lps = getLPS(word);
        int i = 0;
        int j = 0;
        while (i < text.length) {
            if (word[j] == text[i]) {
                j++;
                if (j == word.length) {
                    return i - j + 1;
                }
            } else {
                if (j > 0) {
                    j = lps[j];
                }
            }
            i++;
        }
        return -1;
    }

    private static int[] getLPS(char[] word) {
        int[] result = new int[word.length];

        int i = 1;
        while (i < word.length) {
            if (word[i] == word[0] && result[i] == 0) {
                int j = 1;
                result[i] = j;
                j++;
                while (j + i < word.length && word[j + i] == word[j - 1]) {
                    result[j + i] = j;
                    j++;
                }
            }
            i++;
        }
        return result;

    }

}
