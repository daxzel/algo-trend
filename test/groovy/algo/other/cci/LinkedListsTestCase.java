package algo.other.cci;

import algo.others.cci.LinkedLists;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static algo.others.cci.LinkedLists.*;

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

}