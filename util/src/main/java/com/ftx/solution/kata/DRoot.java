package com.ftx.solution.kata;

/**
 * A digital root is the recursive sum of all the digits in a number. Given n, take the sum of the digits of n. If that value has more than one digit, continue reducing in this way until a single-digit number is produced. This is only applicable to the natural numbers.
 *
 * @author puan
 * @date 2019-03-28 14:11
 **/
public class DRoot {

    public static int digital_root(int n) {
        return digital_root1(n);
    }

    public static int digital_root1(int n) {
        return (n != 0 && n % 9 == 0) ? 9 : n % 9;
    }

    public static int digital_root2(int n) {
        // ...
        String str = String.valueOf(n);
        int i = 0;
        for (int y = 0; y < str.length(); y++) {
            i += Integer.valueOf(String.valueOf(str.charAt(y)));
        }
        if (i >= 10) {
            i = digital_root(i);
        }
        return i;
    }

}
