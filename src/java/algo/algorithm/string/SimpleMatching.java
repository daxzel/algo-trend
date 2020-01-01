package algo.algorithm.string;

public class SimpleMatching {

    /**
     * Simple pattern matching algorithm.
     * <p>
     * Performance: O(N * M)
     */
    public static int find(char[] word, char[] text) {

        for (int i = 0; i < text.length; i++) {

            int j = 0;
            char t = text[i];
            char w = word[j];

            if (t == w) {
                j++;
                while (t == w && j + i < text.length && j < word.length) {
                    t = text[i + j];
                    w = word[j];
                    j++;
                }
                if (j == word.length && t == w) {
                    return i;
                }
            }
        }
        return -1;
    }

}
