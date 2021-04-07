package sdesheet;

public class Day12 {

    public static boolean checkPowerOf2(int n) {
        if (n == 0)
            return true;

        return ((n & (n-1)) == 0);
    }

    public static int countSetBits(int n) {
        int count = 0;

        while(n != 0) {
            n = n & (n-1);
        }

        return count;
    }

    public void printPowerSet(char[] chars, int n) {

        int powerSetSize = (int) Math.pow(2, n);

        for(int counter = 0; counter < powerSetSize; counter++) {
            StringBuilder builder = new StringBuilder();
            for(int j = 0; j < n; j++) {
                if ((counter & (1 << j)) != 0) {
                    builder.append(chars[j]);
                }
            }

            System.out.println(chars);
        }
    }

}
