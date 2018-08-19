package algo.util;

public class Printer {

    public static void printArray(Integer[][] array) {
        for (Integer[] anArray : array) {
            for (Integer anAnArray : anArray) {
                if (anAnArray != null) {
                    System.out.printf("%5d", anAnArray);
                } else {
                    System.out.print(" null");
                }
            }
            System.out.println();
        }
    }

}
