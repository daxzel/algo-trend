package algo.algorithm.sorting;

/**
 * Created by Tsarevskiy
 */
public class Sorting {

    /**
     * Worst case performance	    O(n^2) Best case performance	    O(n) Average case performance	    O(n^2) Worst case
     * space complexity	O(1) auxiliary
     */
    public static void bubble(int[] array) {

        boolean swapped;
        while (true) {
            swapped = false;
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i] > array[i + 1]) {
                    int next = i + 1;
                    swap(array, i, next);
                    swapped = true;
                }

            }

            if (!swapped) {
                break;
            }

        }

    }

    /**
     * Worst case performance	    О(n^2) comparisons, swaps Best case performance	    Ω(n)  comparisons, O(1) swaps
     * Average case performance	    О(n^2) comparisons, swaps Worst case space complexity	О(n)  total, O(1) auxiliary
     */
    public static void insertion(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int j = i;
            while (j > 0 && array[j - 1] > array[j]) {
                swap(array, j, j - 1);
                j--;
            }

        }


    }

    /**
     * Worst case performance	    О(n^2) Best case performance	    О(n^2) Average case performance	    О(n^2) Worst case
     * space complexity	О(n) total, O(1) auxiliary
     */
    public static void selection(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int jmin = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[jmin] > array[j]) {
                    jmin = j;
                }
            }

            if (jmin != i) {
                swap(array, i, jmin);
            }

        }
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[j];
        array[j] = array[i];
        array[i] = temp;
    }

    //TODO fix it
//    /**
//     * Worst case performance	    О(n) Best case performance	    О(n) Average case performance	    О(n)
//     */
//    public static void counting(final int[] array) {
//        final ArrayList<LinkedList<Integer>> buckets = new ArrayList<LinkedList<Integer>>();
//
//        List<int[]> list = Arrays.asList(array);
//        list.forEach(new IntRange(0, 1000)), new Closure<Boolean>(null, null) {
//            public Boolean doCall(Integer it) {
//                return buckets.add(null);
//            }
//
//            public Boolean doCall() {
//                return doCall(null);
//            }
//
//        });
//
//        Collections .each(array, new Closure<Boolean>(null, null) {
//            public Boolean doCall(Integer it) {
//                if (!DefaultGroovyMethods.asBoolean(buckets.get(it))) {
//                    buckets.set(it, new LinkedList<>());
//                }
//
//                return buckets.get(it).add(it);
//            }
//
//            public Boolean doCall() {
//                return doCall(null);
//            }
//
//        });
//        final Reference<Integer> i = new Reference<int>(0);
//        buckets.forEach(new Closure<List<Integer>>(null, null) {
//            public List<Integer> doCall(Object bucket) {
//                if (DefaultGroovyMethods.asBoolean((Collection) bucket)) {
//                    return DefaultGroovyMethods.each((List<Integer>) bucket, new Closure<Integer>(null, null) {
//                        public Integer doCall(Integer it) {
//                            array[i.get()] = it;
//                            i.set(i.get() + 1);
//                            return i.get();
//                        }
//
//                        public Integer doCall() {
//                            return doCall(null);
//                        }
//
//                    });
//                }
//
//            }
//
//        });
//    }

}
