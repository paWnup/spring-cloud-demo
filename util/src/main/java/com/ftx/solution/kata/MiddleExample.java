package com.ftx.solution.kata;

/**
 * You are going to be given a word.
 * Your job is to return the middle character of the word.
 * If the word's length is odd, return the middle character.
 * If the word's length is even, return the middle 2 characters.
 *
 * @author puan
 * @date 2019-04-01 15:28
 **/
public class MiddleExample {

    public static String getMiddle(String word) {
        if (word == null || "".equals(word.trim())) {
            return "";
        }
        int length = word.length();
        if (length % 2 == 0) {
            return word.substring(length / 2 - 1, length / 2 + 1);
        } else {
            return word.substring(length / 2, length / 2 + 1);
        }
    }

}
