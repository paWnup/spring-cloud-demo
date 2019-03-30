package util;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;

/**
 * 判断字符串是否有重复字母
 *
 * @author puan
 * @date 2019-03-28 11:33
 **/
public class Isogram {

    public static Pattern pattern = Pattern.compile("(?i)\\b\\w*(\\w)\\w*(?=\\1)\\w*\\b");

    @Test
    public void FixedTests() {
        assertEquals(true, Isogram.isIsogram("Dermatoglyphics"));
        assertEquals(true, Isogram.isIsogram("isogram"));
        assertEquals(false, Isogram.isIsogram("moose"));
        assertEquals(false, Isogram.isIsogram("isIsogram"));
        assertEquals(false, Isogram.isIsogram("aba"));
        assertEquals(false, Isogram.isIsogram("moOse"));
        assertEquals(true, Isogram.isIsogram("thumbscrewjapingly"));
        assertEquals(true, Isogram.isIsogram(""));
    }

    public static boolean isIsogram(String str) {
//        return isIsogram11(str);
//        return isIsogram2(str);
//        return isIsogram3(str);
        return isIsogram4(str);
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
            char[] chars = str.toLowerCase().toCharArray();
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
        return str.length() == str.toLowerCase().chars().distinct().count();
    }

    public static boolean isIsogram3(String str) {
        // ...
        str = str.toLowerCase();
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
