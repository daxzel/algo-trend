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
    public static boolean question5(SimpleTreeElement treeHead) {

        LinkedList<Integer> list = new LinkedList<>();
        deepSearh(treeHead, list);
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
    public static void deepSearh(SimpleTreeElement treeHead, LinkedList<Integer> linkedList) {
        if (treeHead.left != null) {
            deepSearh(treeHead.left, linkedList);
        }
        linkedList.add(treeHead.value);
        if (treeHead.right != null) {
            deepSearh(treeHead.right, linkedList);
        }
    }

}
