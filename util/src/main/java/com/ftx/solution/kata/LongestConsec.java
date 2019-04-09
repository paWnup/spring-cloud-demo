package com.ftx.solution.kata;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author puan
 * @date 2019-04-09 9:16
 **/
public class LongestConsec {

    public static String longestConsec(String[] strarr, int k) {
        int lenth = strarr.length;
        String max = "";
        if (k <= lenth && k > 0) {
            StringBuilder subStr = new StringBuilder();
            for (int j = 0; j < k; j++) {
                subStr.append(strarr[j]);
            }
            max = subStr.toString();
            for (int i = k; i < lenth; i++) {
                subStr.delete(0, strarr[i - k].length()).append(strarr[i]);
                max = Math.max(max.length(), subStr.length()) == max.length() ? max : subStr.toString();
            }
        }
        return max;
    }

    public static String longestConsec1(String[] strarr, int k) {
        if (k > strarr.length || k <= 0) {
            return "";
        }
        String longestStr = "";
        for (int index = 0; index < strarr.length - k + 1; index++) {
            StringBuilder sb = new StringBuilder();
            for (int i = index; i < index + k; i++) {
                sb.append(strarr[i]);
            }
            if (sb.toString().length() > longestStr.length()) {
                longestStr = sb.toString();
            }
        }
        return longestStr;
    }

    public static String longestConsec2(String[] strarr, int k) {
        String maxStr = "";
        for (int i = 0; i <= strarr.length - k; i++) {
            String current = Arrays.stream(strarr, i, i + k).collect(Collectors.joining());
            if (current.length() > maxStr.length()) {
                maxStr = current;
            }
        }
        return maxStr;
    }
}
