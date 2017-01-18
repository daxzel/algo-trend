package algo.puzzles.cci;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static algo.puzzles.cci.LinkedLists.*;

/**
 * Created by andrey tsarevskiy
 */
public class LinkedListsTestCase extends Assert {

    @Test
    public void testDuplicate() {
        List<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        question1(list);
        assertEquals(list.size(), 1);
    }

    @Test
    public void testDuplicate_2() {
        LinkedLists.LinkedListNode head = new LinkedLists.LinkedListNode(1);
        LinkedLists.LinkedListNode second = new LinkedLists.LinkedListNode(1);
        LinkedLists.LinkedListNode third = new LinkedLists.LinkedListNode(2);
        head.next = second;
        second.next = third;
        question1_2(head);
        head.print();
        assertEquals((long)head.next.data, 2);
    }

    @Test
    public void testKthElement() {
        LinkedLists.LinkedListNode head = new LinkedLists.LinkedListNode(1);
        LinkedLists.LinkedListNode second = new LinkedLists.LinkedListNode(1);
        LinkedLists.LinkedListNode third = new LinkedLists.LinkedListNode(2);
        LinkedLists.LinkedListNode fourth = new LinkedLists.LinkedListNode(6);
        head.next = second;
        second.next = third;
        third.next = fourth;
        assertEquals(question2(head, 2), 2);
    }

    @Test
    public void testRemoveElement() {
        LinkedLists.LinkedListNode head = new LinkedLists.LinkedListNode(1);
        LinkedLists.LinkedListNode second = new LinkedLists.LinkedListNode(1);
        LinkedLists.LinkedListNode third = new LinkedLists.LinkedListNode(2);
        LinkedLists.LinkedListNode fourth = new LinkedLists.LinkedListNode(6);
        head.next = second;
        second.next = third;
        third.next = fourth;
        question3(second);
        assertEquals((long)head.next.data, 2);
    }

    @Test
    public void testSum() {
        LinkedLists.LinkedListNode head = new LinkedLists.LinkedListNode(9);
        LinkedLists.LinkedListNode second = new LinkedLists.LinkedListNode(9);
        head.next = second;
        question5(head, 1);
        assertEquals((long)head.data, 0);
        assertEquals((long)head.next.data, 0);
        assertEquals((long)head.next.next.data, 1);
    }

    @Test
    public void testLoops() {
        LinkedLists.LinkedListNode head = new LinkedLists.LinkedListNode(9);
        LinkedLists.LinkedListNode n2 = new LinkedLists.LinkedListNode(9);
        LinkedLists.LinkedListNode n3 = new LinkedLists.LinkedListNode(2);
        LinkedLists.LinkedListNode n4 = new LinkedLists.LinkedListNode(1);
        LinkedLists.LinkedListNode n5 = new LinkedLists.LinkedListNode(5);
        LinkedLists.LinkedListNode n6 = new LinkedLists.LinkedListNode(3);

        head.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n4;

        question6(head);
        assertEquals((long)question6(head).data, 1);
    }


}