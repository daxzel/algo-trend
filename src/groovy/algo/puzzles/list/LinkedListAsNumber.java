package algo.puzzles.list;

import algo.datastructures.list.SimpleList;
import algo.puzzles.CrackingCodingInterview;

/**
 * Created by andrey tsarevskiy
 */
@CrackingCodingInterview
public class LinkedListAsNumber {
    /**
     * Linked represents a number. Implement sum
     */
    public static void question5(SimpleList.SimpleListNode node, int a) {

        while (a > 0 && node != null) {
            int b = node.data;
            node.data = (a + b) % 10;
            a = (a + b) / 10;
            if (node.next == null && a > 0) {
                node.next = new SimpleList.SimpleListNode(a);
                node = null;
            } else {
                node = node.next;
            }
        }
    }
}
