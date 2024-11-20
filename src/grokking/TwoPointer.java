package grokking;

import array.ArrayHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TwoPointer {

    public static int[] searchInArray(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int sum = arr[left] + arr[right];

            if (sum == target) {
                return new int[]{left, right};
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }

        return null;
    }

    public static int moveElements(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int i = 0;
        int j = 0;

        while(j < arr.length) {
            if (j +1 == arr.length || arr[j] != arr[j+1]) {
                arr[i++] = arr[j];
            }

            j++;
        }

        return i;
    }

    public static int[] makeSquaresArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        int n = arr.length;
        int[] res = new int[n];

        int left = 0;
        int right = n - 1;

        int index = n-1;

        while (left <= right) {
            int leftSq = arr[left] * arr[left];
            int rightSq = arr[right] * arr[right];
            if (leftSq <= rightSq) {
                res[index--] = rightSq;
                right--;
            } else {
                res[index--] = leftSq;
                left++;
            }
        }

        return res;
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

    public static int searchClosestTriplet(int[] arr, int target) {
        Arrays.sort(arr);

        int closestSum = Integer.MAX_VALUE;

        for(int i = 0; i < arr.length; i++) {
            int left = i+1;
            int right = arr.length - 1;

            while(left < right) {
                int sum = arr[left] + arr[right] + arr[i];
                if (sum == target) {
                    return sum;
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }

                if (Math.abs(target - sum) < Math.abs(target - closestSum)) {
                    closestSum = sum;
                }
            }
        }

        return closestSum;

    }

    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int currProduct = 1;

        int s = 0;
        int e = 0;

        int count = 0;

        while(e < nums.length) {
            currProduct *= nums[e];

            while(currProduct >= k && s <= e) {
                currProduct /= nums[s++];
            }

            count += (e - s + 1);

            e++;
        }

        return count;
    }

    public static void sort012(int[] arr) {
        int i = -1;
        int j = 0;

        int k = arr.length;

        while(j < k) {

            if (arr[j] == 0) {
                ArrayHelper.swap(arr, ++i, j++);
            } else if (arr[j] == 1) {
                j++;
            } else if (arr[j] == 2) {
                ArrayHelper.swap(arr, j, --k);
            }
        }
    }


    public boolean backspaceCompare(String s, String t) {
        int last1 = s.length() - 1;
        int last2 = t.length() - 1;



        while(last1 >= 0 && last2 >= 0) {
            int backspace1 = 0;
            int backspace2 = 0;

            while(s.charAt(last1) == '#') {
                backspace1++;
                last1--;
            }

            while(t.charAt(last2) == '#') {
                backspace2++;
                last2--;
            }

            last1 = last1 - backspace1;
            last2 = last2 - backspace2;

            if (s.charAt(last1) != t.charAt(last2)) {
                return false;
            }

            last1--;
            last2--;
        }

        return last1 == last2;
    }

    public int findUnsortedSubarray(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        int result = 0;

        while(left < right) {
            if (nums[left] > nums[left+1]) {
                break;
            }

            left++;
        }

        while(left < right) {
            if (nums[right-1] > nums[right]) {
                break;
            }

            right--;
        }

        return 0;




    }
}
