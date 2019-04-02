package com.ftx.solution.kata;

/**
 * The maximum sum subarray problem consists in finding the maximum sum
 * of a contiguous subsequence in an array or list of integers:
 * <p>
 * Max.sequence(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4});
 * // should be 6: {4, -1, 2, 1}
 * Easy case is when the list is made up of only positive numbers
 * and the maximum sum is the sum of the whole array.
 * If the list is made up of only negative numbers, return 0 instead.
 * <p>
 * Empty list is considered to have zero greatest sum.
 * Note that the empty list or array is also a valid sublist/subarray.
 *
 * @author puan
 * @date 2019-04-02 10:03
 **/
public class Max {

    public static int sequence(int[] arr) {
        int max_ending_here = 0, max_so_far = 0;
        for (int v : arr) {
            max_ending_here = Math.max(0, max_ending_here + v);
            max_so_far = Math.max(max_so_far, max_ending_here);
        }
        return max_so_far;
    }

    public static int sequence1(int[] arr) {
        int max = 0;
        for (int i = 0; i <= arr.length; i++) {
            int n = getMaxSubSequenceIndexOfI(arr, i);
            if (n > max) {
                max = n;
            }
        }
        return max;
    }

    private static int getMaxSubSequenceIndexOfI(int[] arr, int i) {
        int max = 0;
        if (i > 0) {
            for (int j = 0; j <= arr.length - i; j++) {
                int sum = 0;
                for (int x = 0; x < i; x++) {
                    sum += arr[j + x];
                }
                if (sum > max) {
                    max = sum;
                }
            }
        }
        return max;
    }
}
