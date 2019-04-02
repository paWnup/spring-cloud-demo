package com.ftx.solution.kata;

/**
 * @author puan
 * @date 2019-04-02 11:22
 **/
public class Solution {

    public static String whoLikesIt(String... names) {
        switch (names.length) {
            case 0:
                return "no one likes this";
            case 1:
                return String.format("%s likes this", names[0]);
            case 2:
                return String.format("%s and %s like this", names[0], names[1]);
            case 3:
                return String.format("%s, %s and %s like this", names[0], names[1], names[2]);
            default:
                return String.format("%s, %s and %d others like this", names[0], names[1], names.length - 2);
        }
    }

    public static String whoLikesIt1(String... names) {
        //Do your magic here
        String like = names.length > 1 ? " like this" : " likes this";
        String name;
        String and = " and ";
        String comma = ", ";
        if (names.length == 0) {
            name = "no one";
        } else if (names.length == 1) {
            name = names[0];
        } else if (names.length == 2) {
            name = names[0] + and + names[1];
        } else if (names.length == 3) {
            name = names[0] + comma + names[1] + and + names[2];
        } else {
            int n = names.length - 2;
            name = names[0] + comma + names[1] + and + n + " others";
        }
        return name + like;
    }
}
