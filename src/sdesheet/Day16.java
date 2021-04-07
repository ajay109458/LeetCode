package sdesheet;

public class Day16 {

    public static void KMPSearch(String txt, String pattern) {
        int N = txt.length();
        int M = pattern.length();

        int[] lps = new int[M];
        int j = 0;

        int i = 0;

        while(i < N) {
            if(txt.charAt(i) == pattern.charAt(j)) {
                j++;
                i++;
            }

            if (j == M) {
                System.out.println("Found the pattern : " + (i-j));
                j = lps[j-1];
            } else if (i < N && pattern.charAt(j) != txt.charAt(i)) {
                if (j != 0) {
                    j = lps[j-1];
                } else {
                    i = i + 1;
                }
            }
        }

    }

    private void computeLPSArray(String pat, int M, int[] lps) {
        int len = 0;

        int i = 1;
        lps[0] = 0;

        while(i < M) {
            if (pat.charAt(len) == pat.charAt(i)) {
                len++;
                lps[i++] = len;
            } else {
                if (len != 0) {
                    len = lps[len-1];
                } else {
                    lps[i++] = len;
                }
            }
        }
    }

}
