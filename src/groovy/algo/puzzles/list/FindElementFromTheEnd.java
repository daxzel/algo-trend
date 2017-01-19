package algo.puzzles.list;

import algo.datastructures.list.SimpleList;
import algo.puzzles.CrackingCodingInterview;

/**
 * Created by andrey tsarevskiy
 */
@CrackingCodingInterview
public class FindElementFromTheEnd {
    /**
     * Find Kth element to the last
     */
    public static int question2(SimpleList.SimpleListNode node, int k) {
        int count = 0;
        SimpleList.SimpleListNode current = node;
        while (current != null) {
            count++;
            current = current.next;
        }
        count -= k;
        current = node;
        while (count > 0) {
            count--;
            current = current.next;
        }
        return current.data;
    }
}
