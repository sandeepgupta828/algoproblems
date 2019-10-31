package com.example.problems;

import java.util.Collections;
import java.util.List;

public class AllPasswords {
    /**
     * algo:
     * Do DFS starting from (0,0) to (n,n)
     * when one cannot move to a next node because all of them have already been part of path -- save it as a path
     *
     * @param matrix
     */
    public List<String> getAllPasswords(int[][] matrix) {

        return Collections.emptyList();
    }

    public static void main(String[] args) {
        AllPasswords allPasswords = new AllPasswords();
        allPasswords.getAllPasswords(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
    }
}
