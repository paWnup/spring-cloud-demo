package com.ftx.solution.kata;

/**
 * The Fibonacci numbers are the numbers in the following integer sequence (Fn):
 * <p>
 * 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, ...
 * such as
 * <p>
 * F(n) = F(n-1) + F(n-2) with F(0) = 0 and F(1) = 1.
 * Given a number, say prod (for product), we search two Fibonacci numbers F(n) and F(n+1) verifying
 * <p>
 * F(n) * F(n+1) = prod.
 * Your function productFib takes an integer (prod) and returns an array:
 * <p>
 * [F(n), F(n+1), true] or {F(n), F(n+1), 1} or (F(n), F(n+1), True)
 * depending on the language if F(n) * F(n+1) = prod.
 * <p>
 * If you don't find two consecutive F(m) verifying F(m) * F(m+1) = prodyou will return
 * <p>
 * [F(m), F(m+1), false] or {F(n), F(n+1), 0} or (F(n), F(n+1), False)
 * F(m) being the smallest one such as F(m) * F(m+1) > prod.
 * <p>
 * Examples
 * <p>
 * productFib(714) # should return {21, 34, 1},
 * # since F(8) = 21, F(9) = 34 and 714 = 21 * 34
 * <p>
 * productFib(800) # should return {34, 55, 0},
 * # since F(8) = 21, F(9) = 34, F(10) = 55 and 21 * 34 < 800 < 34 * 55
 * <p>
 * Notes: Not useful here but we can tell how to choose the number n up to which to go:
 * we can use the "golden ratio" phi which is (1 + sqrt(5))/2 knowing that F(n) is asymptotic to:
 * phi^n / sqrt(5). That gives a possible upper bound to n.
 *
 * @author puan
 * @date 2019-04-10 14:10
 **/
public class ProdFib {

    /**
     * 性能不够好
     * @param prod
     * @return
     */
    public static long[] productFib(long prod) {
        long a = 0L;
        long b = 1L;
        while (a * b < prod) {
            long tmp = a;
            a = b;
            b = tmp + b;
        }
        return new long[]{a, b, a * b == prod ? 1 : 0};
    }

    private static final double PHI = 0.5 + Math.sqrt(5) / 2;

    public static long[] productFib1(long prod) {
        // your code
        int i = getUpperBound(prod);
        for (; i > 0; i--) {
            long[] next = getFib(i - 1);
            long x = next[1] * next[2];
            long nextX = next[0] * next[1];
            if (nextX < prod) {
                return new long[]{next[1], next[2], x == prod ? 1 : 0};
            }
        }
        return new long[3];
    }

    private static int getUpperBound(long prod) {
        for (int i = 0; ; i++) {
            double first = Math.pow(PHI, i) / Math.sqrt(5);
            double second = Math.pow(PHI, i + 1) / Math.sqrt(5);
            if (first * second > prod) {
                return i;
            }
        }
    }

    private static long[] getFib(int i) {
        long first = 0;
        long second = 1;
        long third = 1;
        for (int j = 1; j <= i; j++) {
            long a = third;
            third = second + third;
            first = second;
            second = a;
        }
        return new long[]{first, second, third};
    }
}
