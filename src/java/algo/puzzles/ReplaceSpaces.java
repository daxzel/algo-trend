package algo.puzzles;

import algo.puzzles.CrackingCodingInterview;

/**
 * Created by andrey tsarevskiy
 */
@CrackingCodingInterview
public class ReplaceSpaces {

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
}
