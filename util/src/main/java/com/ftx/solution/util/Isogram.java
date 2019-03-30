package com.ftx.solution.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * 判断字符串是否有重复字母
 *
 * @author puan
 * @date 2019-03-28 11:33
 **/
public class Isogram {

    public static Pattern pattern = Pattern.compile("(?i)\\w*(\\w)\\w*(?:\\1)\\w*");

    public static void main(String[] args) {
        String str = "asqeghusH";
        System.out.println(isIsogram1(str));
        System.out.println(isIsogram11(str));
        System.out.println(isIsogram2(str));
        System.out.println(isIsogram3(str));
        System.out.println(isIsogram4(str));
    }

    public static boolean isIsogram1(String str) {
        // ...
        Set<Character> charsSet = new HashSet<>();
        if (str == null) {
            return false;
        } else {
            char[] chars = str.toCharArray();
            for (char x : chars) {
                charsSet.add(x);
            }
            return charsSet.size() == chars.length;
        }
    }

    public static boolean isIsogram11(String str) {
        // ...
        List<Character> list = new ArrayList<>();
        if (str == null) {
            return false;
        } else {
            char[] chars = str.toCharArray();
            for (char x : chars) {
                if (list.contains(x)) {
                    return false;
                }
                list.add(x);
            }
            return true;
        }
    }


    public static boolean isIsogram2(String str) {
        // ...
        return str.length() == str.chars().distinct().count();
    }

    public static boolean isIsogram3(String str) {
        // ...
        for (int i = 0; i < str.length(); i++) {
            if (str.indexOf(str.charAt(i)) != str.lastIndexOf(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isIsogram4(String str) {
        return !pattern.matcher(str).matches();
    }

}
