package com.ftx.solution.kata;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Sheldon, Leonard, Penny, Rajesh and Howard are in the queue for a "Double Cola" drink vending machine;
 * there are no other people in the queue.
 * The first one in the queue (Sheldon) buys a can, drinks it and doubles!
 * The resulting two Sheldons go to the end of the queue. Then the next in the queue (Leonard) buys a can,
 * drinks it and gets to the end of the queue as two Leonards, and so on.
 *
 * @author puan
 * @date 2019-04-08 16:46
 **/
public class Line {

    public static String WhoIsNext(String[] names, int n) {
        while (n > names.length) {
            n = (n - (names.length - 1)) / 2;
        }
        return names[n - 1];
    }

    public static String WhoIsNext0(String[] names, int n) {
        int i = n;
        while (n > names.length) {
            n = (n - names.length) / 2;
        }
        return names[i <= 5 ? n - 1 : n];
    }

    public static String WhoIsNext1(String[] names, int n) {
        int size = names.length;
        int i = 0;
        int dou = size * (int) Math.pow(2, i++);
        int left = n;
        while (left - dou > 0) {
            left = left - dou;
            dou = size * (int) Math.pow(2, i++);
        }
        int x = left / (int) Math.pow(2, i == 1 ? 1 : i - 1);
        return names[x];
    }

    public static String WhoIsNext2(String[] names, int n) {
        Queue<String> queue = new LinkedList<>(Arrays.asList(names));
        int i = 1;
        String name = null;
        while (i++ <= n) {
            name = queue.poll();
            queue.add(name);
            queue.add(name);
        }
        return name;
    }
}
