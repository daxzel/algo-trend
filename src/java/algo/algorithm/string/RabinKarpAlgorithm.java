package algo.algorithm.string;

public class RabinKarpAlgorithm {

    /**
     * In computer science, the Rabin–Karp algorithm or Karp–Rabin algorithm is a string-searching algorithm created by
     * Richard M. Karp and Michael O. Rabin (1987) that uses hashing to find any one of a set of pattern strings in a
     * text. For text of length n and p patterns of combined length m, its average and best case running time is O(n+m)
     * in space O(p), but its worst-case time is O(nm). In contrast, the Aho–Corasick string-matching algorithm has
     * asymptotic worst-time complexity O(n+m) in space O(m).
     * <p>
     * A practical application of the algorithm is detecting plagiarism. Given source material, the algorithm can
     * rapidly search through a paper for instances of sentences from the source material, ignoring details such as case
     * and punctuation. Because of the abundance of the sought strings, single-string searching algorithms are
     * impractical.
     */
    public static int find(char[] word, char[] text) {
        long hash = getHash(word);

        int i = 0;
        int l = word.length;
        if (i + l <= text.length) {
            long currentHash = getHash(text, i, i + l);
            if (currentHash == hash) {
                if (isEqual(text, i, word)) {
                    return i;
                }
            }
            i++;
            while (i + l < text.length) {
                currentHash = currentHash - text[i - 1] + text[i + l - 1];
                if (currentHash == hash) {
                    if (isEqual(text, i, word)) {
                        return i;
                    }
                }
                i++;
            }
        }
        return -1;
    }

    private static long getHash(char[] word) {
        long hash = 0;
        for (char c : word) {
            hash += c;
        }
        return hash;
    }

    private static long getHash(char[] text, int start, int end) {
        long hash = 0;
        for (int i = start; i < end; i++) {
            hash += text[i];
        }
        return hash;
    }

    private static boolean isEqual(char[] text, int start, char[] word) {
        for (int i = 0; i < word.length; i++) {
            if (text[i + start] == word[i]) {
                return true;
            }
        }
        return false;
    }

}
