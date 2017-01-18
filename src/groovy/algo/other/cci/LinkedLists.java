package algo.other.cci;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * Created by andrey tsarevskiy
 */
public class LinkedLists {

    public static class LinkedListNode {
        public LinkedListNode next;
        public Integer data;

        public LinkedListNode(Integer data) {
            this.data = data;
        }

        public void print() {
            System.out.print('[');
            System.out.print(data);
            LinkedListNode temp = this.next;
            while (temp != null) {
                System.out.print("," + temp.data);
                temp = temp.next;
            }
            System.out.print(']');
        }
    }

    /**
     * Remove duplicates
     */
    public static <S> void question1(List<S> s) {
        HashSet<S> found = new HashSet<>();
        Iterator<S> iterator = s.iterator();
        while (iterator.hasNext()) {
            S next = iterator.next();
            if (found.contains(next)) {
                iterator.remove();
            } else {
                found.add(next);
            }
        }
    }

    /**
     * Remove duplicates. Don't use additional memory
     */
    public static <S> void question1_2(LinkedListNode head) {
        if (head == null) {
            return;
        }
        while (head != null && head.next != null) {
            LinkedListNode current = head;
            do {
                if (Objects.equals(current.next.data, head.data)) {
                    LinkedListNode next = current.next;
                    current.next = next.next;
                } else {
                    current = current.next;
                }
            } while (current.next != null);
            head = head.next;
        }
    }


    /**
     * Find Kth element to the last
     */
    public static int question2(LinkedListNode node, int k) {
        int count = 0;
        LinkedListNode current = node;
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

    /**
     * Remove current node
     */
    public static void question3(LinkedListNode node) {
        node.data = node.next.data;
        node.next = node.next.next;
    }

    /**
     * Linked represents a number. Implement sum
     */
    public static void question5(LinkedListNode node, int a) {

        while (a > 0 && node != null) {
            int b = node.data;
            node.data = (a + b) % 10;
            a = (a + b) / 10;
            if (node.next == null && a > 0) {
                node.next = new LinkedListNode(a);
                node = null;
            } else {
                node = node.next;
            }
        }
    }

    /**
     * Linked represents a number. Implement sum
     */
    public static LinkedListNode question6(LinkedListNode node) {

        LinkedListNode slow = node;
        LinkedListNode fast = node;

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
