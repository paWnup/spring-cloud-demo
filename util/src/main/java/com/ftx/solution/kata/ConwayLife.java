package com.ftx.solution.kata;

import java.util.Arrays;

/**
 * @author puan
 * @date 2019-04-10 10:20
 **/
public class ConwayLife {

    public static int[][] getGeneration(int[][] cells, int generations) {
        // your code goes here
        int[][] newCells = Arrays.copyOf(cells, cells.length);
        for (int n = 1; n <= generations; n++) {
            newCells = getNewArray(newCells);
        }
        return newCells;
    }

    private static int[][] getNewArray(int[][] cells) {
        int size1 = cells.length;
        int size2 = cells[0].length;
        int[][] newCells = new int[size1][size2];
        for (int i = 0; i < size1; i++) {
            for (int j = 0; j < size2; j++) {
                newCells[i][j] = getNewCell(cells, i, j);
            }
        }
        return newCells;
    }

    private static int getNewCell(int[][] cells, int i, int j) {
        int size1 = cells.length;
        int size2 = cells[0].length;
        int liveCount = 0;
        for (int index1 = i - 1; index1 <= i + 1; index1++) {
            for (int index2 = j - 1; index2 <= j + 1; index2++) {
                if (index1 >= 0 && index2 >= 0 && index1 < size1 && index2 < size2 && (index1 != i || index2 != j)) {
                    liveCount += cells[index1][index2];
                }
            }
        }
        if (liveCount == 3) {
            return 1;
        } else if (liveCount == 2) {
            return cells[i][j];
        } else {
            return 0;
        }
    }
}
