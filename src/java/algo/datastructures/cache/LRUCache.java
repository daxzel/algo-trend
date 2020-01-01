package algo.datastructures.cache;

import java.util.HashMap;
import java.util.function.Function;

/**
 * Created by andrey tsarevskiy
 */
public class LRUCache {

    private Function<String, Integer> getDataFun;
    private HashMap<String, LRUListNode> keyToListNode = new HashMap<>();

    private LRUListNode head;
    private LRUListNode tail;
    private int size;
    private int maxSize;

    public static class LRUListNode {

        public LRUListNode next;
        public LRUListNode previous;
        public Integer data;
        public String key;

        public LRUListNode(Integer data, String key) {
            this.data = data;
            this.key = key;
        }
    }

    public LRUCache(Function<String, Integer> getDataFun, int maxSize) {
        this.maxSize = maxSize;
        this.getDataFun = getDataFun;
    }

    private void removeNode(LRUListNode node) {
        if (tail != node && head != node) {
            node.previous.next = node.next;
            node.next.previous = node.previous;
        }
        if (head == node) {
            if (head.next != null) {
                head.next.previous = null;
                head = head.next;
            } else {
                head = null;
            }
        }
        if (tail == node) {
            if (tail.previous != null) {
                tail.previous.next = null;
                tail = tail.previous;
            } else {
                tail = null;
            }
        }
        node.previous = null;
        node.next = null;
        size--;
    }

    private void setHead(LRUListNode node) {
        if (head == null) {
            head = node;
            tail = node;
        } else {
            node.next = head;
            head.previous = node;
            head = node;
        }
        size++;
    }

    public Integer get(String key) {
        LRUListNode keyNode = keyToListNode.get(key);
        if (keyNode != null) {
            removeNode(keyNode);
            setHead(keyNode);
        } else {
            Integer value = getDataFun.apply(key);
            keyNode = new LRUListNode(value, key);
            setHead(keyNode);
            if (size > maxSize) {
                keyToListNode.remove(tail.key);
                removeNode(tail);
            }
            keyToListNode.put(key, keyNode);
        }
        return keyNode.data;
    }
}
