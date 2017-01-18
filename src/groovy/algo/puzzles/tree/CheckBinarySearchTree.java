package algo.puzzles.tree;

import algo.datastructures.tree.SimpleTree;
import algo.puzzles.CrackingCodingInterview;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by andrey tsarevskiy
 */
@CrackingCodingInterview
public class CheckBinarySearchTree {
    /**
     * Check whether tree is binary search
     */
    public static boolean firstMethod(SimpleTree treeHead) {

        LinkedList<Integer> list = new LinkedList<>();
        firstMethodDeep(treeHead, list);
        Iterator<Integer> iterator = list.iterator();
        int current = iterator.next();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            if (current > next) {
                return false;
            }
            current = next;
        }
        return true;
    }

    /**
     * Check whether tree is binary search, min max approach
     */
    public static boolean secondMethod(SimpleTree treeHead) {
        return secondMethodDeep(treeHead, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    /**
     * Check whether tree is binary search
     */
    static void firstMethodDeep(SimpleTree treeHead, LinkedList<Integer> linkedList) {
        if (treeHead.left != null) {
            firstMethodDeep(treeHead.left, linkedList);
        }
        linkedList.add(treeHead.value);
        if (treeHead.right != null) {
            firstMethodDeep(treeHead.right, linkedList);
        }
    }

    /**
     * Check whether tree is binary search
     */
    public static boolean secondMethodDeep(SimpleTree treeHead, int min, int max) {
        if (treeHead == null) {
            return true;
        }
        int value = treeHead.value;
        if (value <= min || value > max) {
            return false;
        }
        if (treeHead.left != null && treeHead.left.value > value) {
            return false;
        }

        if (treeHead.right != null && treeHead.right.value <= value) {
            return false;
        }

        return secondMethodDeep(treeHead.left, min, value) && secondMethodDeep(treeHead.right, value, max);
    }
}
