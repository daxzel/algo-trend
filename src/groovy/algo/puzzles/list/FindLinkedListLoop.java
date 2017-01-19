package algo.puzzles.list;

import algo.datastructures.list.SimpleList;
import algo.puzzles.CrackingCodingInterview;

/**
 * Created by andrey tsarevskiy
 */
@CrackingCodingInterview
public class FindLinkedListLoop {
    /**
     * Find the loop in the linked list
     */
    public static SimpleList.SimpleListNode question6(SimpleList.SimpleListNode node) {

        SimpleList.SimpleListNode slow = node;
        SimpleList.SimpleListNode fast = node;

        // Find meeting point
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break;
            }
        }

        // Error check - there is no meeting point, and therefore no loop
        if (fast == null || fast.next == null) {
            return null;
        }

		/* Move slow to Head. Keep fast at Meeting Point. Each are k steps
        /* from the Loop Start. If they move at the same pace, they must
		 * meet at Loop Start. */
        slow = node;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        // Both now point to the start of the loop.
        return fast;
    }
}
