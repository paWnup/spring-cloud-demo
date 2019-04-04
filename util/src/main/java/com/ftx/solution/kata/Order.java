package com.ftx.solution.kata;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * @author puan
 * @date 2019-04-04 10:03
 **/
public class Order {

    static String reg = "[a-zA-z]*(\\d)[a-zA-z]*";

    public static String order1(String words) {
        return Arrays.stream(words.split(" "))
                .filter(i -> i.matches(reg)).sorted((o1, o2) -> {
                    int i1 = Integer.parseInt(o1.replaceAll(reg, "$1"));
                    int i2 = Integer.parseInt(o2.replaceAll(reg, "$1"));
                    return i1 - i2;
                }).collect(Collectors.joining(" "));
    }

    public static String order2(String words) {
        return Arrays.stream(words.split(" "))
                .filter(i -> i.matches("[a-zA-z]*\\d[a-zA-z]*"))
                .sorted(Comparator.comparing(s -> Integer.parseInt(s.replaceAll("\\D", ""))))
                .collect(Collectors.joining(" "));
    }

    public static String order(String words) {
        String[] arr = words.split(" ");
        StringBuilder result = new StringBuilder("");
        for (int i = 0; i < 10; i++) {
            for (String s : arr) {
                if (s.contains(String.valueOf(i))) {
                    result.append(s).append(" ");
                }
            }
        }
        return result.toString().trim();
    }
}
