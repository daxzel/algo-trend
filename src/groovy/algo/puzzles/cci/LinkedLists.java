package algo.puzzles.cci;

import algo.datastructures.list.SimpleList;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * Created by andrey tsarevskiy
 */
public class LinkedLists {


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

    /**
     * Linked represents a number. Implement sum
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
