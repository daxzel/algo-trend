package algo.puzzles.array;

import algo.puzzles.Codility;
import java.util.Arrays;

/**
 * Created by andrey tsarevskiy
 */
@Codility
public class MinDistanceCounter {

    public static int countMinDistance(int[] A) {
        // write your code in Java SE 8
        Arrays.sort(A);
        int minDistance = Math.abs(A[0] - A[1]);
        if (minDistance == 0) {
            return minDistance;
        }

        for (int i = 1; i < (A.length - 1); i++) {
            int distance = Math.abs(A[i] - A[i + 1]);
            if (distance < minDistance) {
                minDistance = distance;
                if (minDistance == 0) {
                    return minDistance;
                }

            }

        }

        return minDistance;
    }

}
