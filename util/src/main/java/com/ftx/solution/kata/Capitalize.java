package com.ftx.solution.kata;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 首字母大写
 * @author puan
 * @date 2019-03-28 9:08
 **/
public class Capitalize {

    private static String toJadenCase1(String str) {
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (i == 0 || chars[i - 1] == ' ') {
                chars[i] = String.valueOf(chars[i]).toUpperCase().charAt(0);
            }
        }
        return new String(chars);
    }

    private static String toJadenCase2(String str) {
        return Arrays.stream(str.split(" "))
                .map(c -> c.substring(0, 1).toUpperCase() + c.substring(1))
                .collect(Collectors.joining(" "));
    }
}
