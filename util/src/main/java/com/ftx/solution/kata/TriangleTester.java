package com.ftx.solution.kata;

/**
 * Implement a method that accepts 3 integer values a, b, c.
 * The method should return true if a triangle can be built with the sides
 * of given length and false in any other case.
 * <p>
 * (In this case, all triangles must have surface greater than 0 to be accepted).
 *
 * @author puan
 * @date 2019-04-01 15:10
 **/
public class TriangleTester {

    public static boolean isTriangle(int a, int b, int c) {
        int[] ints = {a, b, c};
        sortAsc(ints);
        return ints[0] > 0 && ints[0] + ints[1] > ints[2];
    }

    private static void sortAsc(int[] ints) {
        for (int i = 0; i < ints.length - 1; i++) {
            for (int j = i + 1; j < ints.length; j++) {
                if (ints[i] > ints[j]) {
                    int x = ints[j];
                    ints[j] = ints[i];
                    ints[i] = x;
                }
            }
        }
    }
}
