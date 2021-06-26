package grokking;

import array.ArrayHelper;

public class BitwiseXor {

    public static int findUniqueNumber(int[] arr) {
        int result = 0;

        for(int val : arr) {
            result ^= val;
        }

        return result;
    }

    public static int[] findTwoNumbers(int[] arr) {

        int xorValue = 0;

        for(int val : arr) {
            xorValue ^= val;
        }

        int bitIndex = 0;

        while((xorValue & (1 << bitIndex)) == 0) {
            bitIndex++;
        }

        int num1 = 0;
        int num2 = 0;

        for(int val : arr) {
            if ((val & (1 << bitIndex)) == 0) {
                num1 ^= val;
            } else {
                num2 ^= val;
            }
        }

        int[] result = {num1, num2};
        return result;
    }

    public static int complement(int num) {
        int bitCount = 0;

        int n = num;

        while(n > 0) {
            n >>= 1;
            bitCount++;
        }

        int allBitsSet = (int)Math.pow(2, bitCount) - 1;
        return num ^ allBitsSet;
    }


    public static void flipAndInvertImage(int[][] matrix) {
        for(int i = 0; i < matrix.length; i++) {
            ArrayHelper.reverse(matrix[i]);

            for(int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = matrix[i][j] ^ 1;
            }
        }
    }

}
