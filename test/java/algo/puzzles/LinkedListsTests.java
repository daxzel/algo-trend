package algo.puzzles;

import static org.junit.jupiter.api.Assertions.assertEquals;

import algo.datastructures.SimpleList;
import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Created by andrey tsarevskiy
 */
public class LinkedListsTests {

    @Test
    public void testDuplicate() {
        List<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        RemoveDuplicates.question1(list);
        assertEquals(list.size(), 1);
    }

    @Test
    public void testDuplicate_2() {
        SimpleList.SimpleListNode head = new SimpleList.SimpleListNode(1);
        SimpleList.SimpleListNode second = new SimpleList.SimpleListNode(1);
        SimpleList.SimpleListNode third = new SimpleList.SimpleListNode(2);
        head.next = second;
        second.next = third;
        RemoveDuplicates.question1_2(head);
        head.print();
        assertEquals((long) head.next.data, 2);
    }

    @Test
    public void testKthElement() {
        SimpleList.SimpleListNode head = new SimpleList.SimpleListNode(1);
        SimpleList.SimpleListNode second = new SimpleList.SimpleListNode(1);
        SimpleList.SimpleListNode third = new SimpleList.SimpleListNode(2);
        SimpleList.SimpleListNode fourth = new SimpleList.SimpleListNode(6);
        head.next = second;
        second.next = third;
        third.next = fourth;
        assertEquals(FindElementFromTheEnd.question2(head, 2), 2);
    }

    @Test
    public void testSum() {
        SimpleList.SimpleListNode head = new SimpleList.SimpleListNode(9);
        SimpleList.SimpleListNode second = new SimpleList.SimpleListNode(9);
        head.next = second;
        LinkedListAsNumber.question5(head, 1);
        assertEquals((long) head.data, 0);
        assertEquals((long) head.next.data, 0);
        assertEquals((long) head.next.next.data, 1);
    }

    @Test
    public void testLoops() {
        SimpleList.SimpleListNode head = new SimpleList.SimpleListNode(9);
        SimpleList.SimpleListNode n2 = new SimpleList.SimpleListNode(9);
        SimpleList.SimpleListNode n3 = new SimpleList.SimpleListNode(2);
        SimpleList.SimpleListNode n4 = new SimpleList.SimpleListNode(1);
        SimpleList.SimpleListNode n5 = new SimpleList.SimpleListNode(5);
        SimpleList.SimpleListNode n6 = new SimpleList.SimpleListNode(3);

        head.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n4;

        FindLinkedListLoop.question6(head);
        assertEquals((long) FindLinkedListLoop.question6(head).data, 1);
    }


}