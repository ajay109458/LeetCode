package sdesheet;

import array.ArrayHelper;

import java.util.Arrays;

public class Day11 {

    public static double squareRoot(int n) {

        double answer = 0;

        int left = 0;
        int right = n;

        while(left <= right) {
            int mid = (left + right)/2;

            if (mid * mid == n) {
                answer = mid;
                break;
            } else if (mid * mid > n) {
                right = mid - 1;
            } else {
                answer = mid;
                left = mid + 1;
            }
        }

        return answer;
    }

    /**
     * Matrix height and width are of odd length
     *
     * Median at index = (1 + r*c) / 2
     *
     * @param matrix
     * @return
     */
    public static int getMatrixMedian(int[][] matrix) {
        // Find the min element in first column
        // Find the max element in the last column

        int m = matrix.length;
        int n = matrix[0].length;

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for(int i = 0; i < matrix.length; i++) {
            max = Math.max(max, matrix[i][n-1]);
            min = Math.min(min, matrix[i][0]);
        }

        int totalCount = 0;
        int median = (1 + matrix.length * matrix[0].length)/2;

        while(min < max) {
            int mid = (min + max) / 2;

            for(int i = 0; i < matrix.length; i++) {
                int index = Arrays.binarySearch(matrix[i], mid);

                if (index < 0) {
                    index = Math.abs(index) - 1;
                } else {
                    while(index < matrix[i].length && matrix[i][index] == mid) {
                        index++;
                    }
                }

                totalCount += index;
            }

            if (totalCount < median) {
                min = mid + 1;
            } else {
                max = mid;
            }

        }

        return min;
    }

    public static double getMedianSortedArray(int[] input1, int[] input2) {
        if (input1.length > input2.length) {
            getMedianSortedArray(input2, input1);
        }

        int x = input1.length;
        int y = input2.length;

        int low = 0;
        int high = x;

        while(low <= high) {

            int partitionX = (low + high) / 2;
            int partitionY = (x + y + 1)/2 - partitionX;

            int maxLeftX = (partitionX == 0) ? Integer.MIN_VALUE : input1[partitionX-1];
            int minRightX = (partitionX == x) ? Integer.MAX_VALUE : input1[partitionX];

            int maxLeftY = (partitionY == 0) ? Integer.MIN_VALUE : input2[partitionY-1];
            int minRightY = (partitionY == y) ? Integer.MAX_VALUE : input2[partitionY];

            if (maxLeftX <= minRightY && maxLeftY <= minRightX) {
                if ((x + y) % 2 == 0) {
                    return (double) (Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY)) / 2;
                } else {
                    return (double) Math.max(maxLeftX, maxLeftY);
                }
            } else if (maxLeftX > minRightY) {
                high = partitionX - 1;
            } else {
                low = partitionX + 1;
            }

        }

        return -1;
    }

}
