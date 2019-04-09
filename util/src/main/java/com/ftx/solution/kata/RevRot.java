package com.ftx.solution.kata;

/**
 * The input is a string str of digits.
 * Cut the string into chunks (a chunk here is a substring of the initial string) of size sz
 * (ignore the last chunk if its size is less than sz).
 * <p>
 * If a chunk represents an integer such as the sum of the cubes of its digits is divisible by 2,
 * reverse that chunk;
 * otherwise rotate it to the left by one position.
 * Put together these modified chunks and return the result as a string.
 * <p>
 * If
 * <p>
 * sz is <= 0 or if str is empty
 * return ""
 * sz is greater (>) than the length of str it is impossible to take a chunk of size sz
 * hence return "".
 *
 * @author puan
 * @date 2019-04-09 11:32
 **/
public class RevRot {

    public static String revRot(String strng, int sz) {
        // your code
        if (sz <= 0 || strng.length() < sz) {
            return "";
        }
        StringBuilder chunkStr = new StringBuilder();
        char[] strs = strng.toCharArray();
        for (int i = 0; i < strs.length / sz; i++) {
            int chunkSum = 0;
            StringBuilder chunk = new StringBuilder();
            char first = 0;
            for (int j = 0; j < sz; j++) {
                if (j == 0) {
                    first = strs[i * sz + j];
                }
                chunkSum += Character.getNumericValue(strs[i * sz + j]);
                chunk.append(strs[i * sz + j]);
            }
            if (chunkSum % 2 == 0) {
                chunk.reverse();
            } else {
                chunk.deleteCharAt(0).append(first);
            }
            chunkStr.append(chunk);
        }
        return chunkStr.toString();
    }
}
