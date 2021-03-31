package arraytests;


import dp.DPProblems;
import org.junit.jupiter.api.Test;

public class DpHelperTests {

    @Test
    public void climbStairs() {
        System.out.println(DPProblems.climbPathTab(5));
    }

    @Test
    public void longestPalindromeSubstring() {
        System.out.println(DPProblems.longestPalindrome("aacabdkacaa"));
    }

    @Test
    public void longestIncreasingSubSeq() {
        int[] arr = {10, 22, 9, 33, 21, 50, 41, 60};
        System.out.println(DPProblems.LIS(arr));
    }

    @Test
    public void longestCommonSubsequence() {
        String a = "GXTXAYB";
        String b = "AGGTAB";

        System.out.println(DPProblems.longestCommonSubSequence(a, b));
    }
}
