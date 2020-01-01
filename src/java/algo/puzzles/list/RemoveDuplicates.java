package algo.puzzles.list;

import algo.datastructures.list.SimpleList;
import algo.puzzles.CrackingCodingInterview;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * Created by andrey tsarevskiy
 */
@CrackingCodingInterview
public class RemoveDuplicates {

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
     * Remove duplicates. Constant memory
     */
    public static <S> void question1_2(SimpleList.SimpleListNode head) {
        if (head == null) {
            return;
        }
        while (head != null && head.next != null) {
            SimpleList.SimpleListNode current = head;
            do {
                if (Objects.equals(current.next.data, head.data)) {
                    SimpleList.SimpleListNode next = current.next;
                    current.next = next.next;
                } else {
                    current = current.next;
                }
            } while (current.next != null);
            head = head.next;
        }
    }

}
