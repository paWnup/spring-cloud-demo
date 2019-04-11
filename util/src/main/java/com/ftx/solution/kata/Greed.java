package com.ftx.solution.kata;

import java.util.HashMap;

/**
 * Greed is a dice game played with five six-sided dice.
 * Your mission, should you choose to accept it, is to score a throw according to these rules.
 * You will always be given an array with five six-sided dice values.
 * <p>
 * Three 1's => 1000 points
 * Three 6's =>  600 points
 * Three 5's =>  500 points
 * Three 4's =>  400 points
 * Three 3's =>  300 points
 * Three 2's =>  200 points
 * One   1   =>  100 points
 * One   5   =>   50 point
 * A single die can only be counted once in each roll.
 * For example, a "5" can only count as part of a triplet (contributing to the 500 points) or as a single 50 points,
 * but not both in the same roll.
 * <p>
 * Example scoring
 * <p>
 * Throw       Score
 * ---------   ------------------
 * 5 1 3 4 1   50 + 2 * 100 = 250
 * 1 1 1 3 1   1000 + 100 = 1100
 * 2 4 4 5 4   400 + 50 = 450
 *
 * @author puan
 * @date 2019-04-11 9:59
 **/
public class Greed {

    public static int greedy(int[] dice) {
        int[] n = new int[7];
        for (int d : dice) {
            n[d]++;
        }
        return n[1] / 3 * 1000 + n[1] % 3 * 100 + n[2] / 3 * 200 + n[3] / 3 * 300 + n[4] / 3 * 400 + n[5] / 3 * 500 + n[5] % 3 * 50 + n[6] / 3 * 600;
    }

    public static int greedy1(int[] dice) {
        int res = 0;
        int[] count = new int[]{0, 0, 0, 0, 0, 0};
        int[] weight = new int[]{100, 0, 0, 0, 50, 0};
        int[] weight3 = new int[]{1000, 200, 300, 400, 500, 600};

        for (int die : dice) {
            count[die - 1]++;
        }

        for (int i = 0; i < count.length; i++) {
            res += (count[i] / 3 * weight3[i]) + (count[i] % 3 * weight[i]);
        }

        return res;
    }

    public static int greedy2(int[] dice) {
        //code here
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : dice) {
            map.put(num, getFromMap(map, num) + 1);
        }
        int score = 0;
        for (int i = 1; i <= 6; i++) {
            score += score(i, map.get(i));
        }
        return score;
    }

    private static int score(int num, Integer count) {
        if (count == null) {
            return 0;
        }
        int point = 0;
        int triplePoint;
        switch (num) {
            case 1:
                point = 100;
                triplePoint = 1000;
                break;
            case 5:
                point = 50;
                triplePoint = 500;
                break;
            default:
                triplePoint = num * 100;
        }
        return (count / 3) * triplePoint + (count % 3) * point;
    }

    private static int getFromMap(HashMap<Integer, Integer> map, int i) {
        Integer count = map.get(i);
        return count == null ? 0 : count;
    }

}
