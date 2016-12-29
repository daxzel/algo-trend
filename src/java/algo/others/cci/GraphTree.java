package algo.others.cci;

import algo.datastructures.SimpleTreeElement;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by andrey tsarevskiy
 */
public class GraphTree {

//    /**
//     * Check whether tree is binary search
//     */
//    public static boolean question5_2(SimpleTreeElement treeHead) {
//        if (treeHead == null) {
//            return true;
//        }
//        int value = treeHead.value;
//        if (treeHead.left != null && treeHead.left.value > value) {
//            return false;
//        }
//
//        if (treeHead.right != null && treeHead.right.value <= value) {
//            return false;
//        }
//
//        return question5(treeHead.left) && question5(treeHead.right);
//    }

    /**
     * Check whether tree is binary search
     */
    public static boolean question5_1(SimpleTreeElement treeHead) {

        LinkedList<Integer> list = new LinkedList<>();
        q5DeepSearch(treeHead, list);
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
     * Check whether tree is binary search
     */
    public static void q5DeepSearch(SimpleTreeElement treeHead, LinkedList<Integer> linkedList) {
        if (treeHead.left != null) {
            q5DeepSearch(treeHead.left, linkedList);
        }
        linkedList.add(treeHead.value);
        if (treeHead.right != null) {
            q5DeepSearch(treeHead.right, linkedList);
        }
    }

    /**
     * Check whether tree is binary search, min max approach
     */
    public static boolean question5_2(SimpleTreeElement treeHead) {
        return q5_2DeepSearch(treeHead, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }


    /**
     * Check whether tree is binary search
     */
    public static boolean q5_2DeepSearch(SimpleTreeElement treeHead, int min, int max) {
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

        return q5_2DeepSearch(treeHead.left, min, value) && q5_2DeepSearch(treeHead.right, value, max);
    }


}
