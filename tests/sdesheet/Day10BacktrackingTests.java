package sdesheet;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class Day10BacktrackingTests {

    @Test
    public void allPermutation() {
        Day10Backtracking.printAllPermutation("abc");
    }

    @Test
    public void nQueenTest() {
        Day10Backtracking.nQueen(4);
    }

    @Test
    public void wordBreak() {
        System.out.println(Day10Backtracking.wordBreak("leetcode", Arrays.asList("leet","code")));
    }
}
