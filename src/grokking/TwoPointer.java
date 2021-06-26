package grokking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TwoPointer {

    public static int[] search(int[] arr, int target) {
        if (arr.length == 0)
            return null;

        int left = 0;
        int right = arr.length -1;

        int[] result = new int[2];

        while(left <= right) {
            int curr =  arr[left] + arr[right];

            if (curr == target) {
                result[0] = left;
                result[1] = right;
                return result;
            } else if (curr < target) {
                left++;
            } else {
                right--;
            }
        }

        return null;
    }

    public static int countUniqueElements(int[] arr) {
        int i = 0;
        int j = 0;

        int count = 0;
        while(j < arr.length) {
            if (i != j && arr[i] == arr[j]) {

            } else {
                i = j;
                count++;
            }
            j++;
        }

        return count;
    }

    public static int[] makeSquares(int[] arr) {
        int n = arr.length;

        int[] result = new int[n];

        int left = 0;
        int right = n -1;

        int index = right;

        while(left <= right) {
            int leftProduct = arr[left] * arr[left];
            int rightProduct = arr[right] * arr[right];

            if (rightProduct >= leftProduct) {
                result[index--] = rightProduct;
                right--;
            } else {
                result[index--] = leftProduct;
                left++;
            }
        }

        return result;
    }

    public static List<List<Integer>> searchTriplets(int[] arr) {
        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(arr);

        int n = arr.length;

        for(int i = 0; i < arr.length; i++) {
            int remainingSum = -arr[i];

            int left = i+1;
            int right = n - 1;

            while(left <= right) {
                int sum = arr[left] + arr[right];
                if (sum == remainingSum) {
                    result.add(Arrays.asList(arr[i], arr[left], arr[right]));
                    left++;
                    right--;
                } else if (sum < remainingSum) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return result;
    }

    public static int searchTripletsClosestSum(int[] arr, int targetSum) {

        int closestSum = 0;

        Arrays.sort(arr);

        int n = arr.length;

        for(int i = 0; i < arr.length; i++) {
            int remainingSum = -arr[i];

            int left = i+1;
            int right = n - 1;

            while(left < right) {
                int sum = arr[left] + arr[right] + arr[i];

                if (Math.abs(targetSum-sum) < Math.abs(targetSum-closestSum)) {
                    closestSum = sum;
                }

                if (sum < remainingSum) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return closestSum;
    }
}
