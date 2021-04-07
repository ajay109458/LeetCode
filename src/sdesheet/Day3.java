package sdesheet;

public class Day3 {

    public int reversePairs(int[] nums) {
        return mergeSort(nums, 0, nums.length -1);
    }

    public static int mergeSort(int[] arr, int left, int right) {
        if (left >= right)
            return 0;

        int mid = left + (right - left)/2;

        int count = 0;

        count += mergeSort(arr, left, mid);
        count += mergeSort(arr, mid + 1, right);
        count += merge(arr, left, mid, right);

        return count;
    }

    public static int merge(int[] arr, int left, int mid, int right) {

        int i = left;
        int j = mid + 1;

        int count = 0;

        int[] temp = new int[right - left + 1];
        int k = 0;

        while( i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                // no inversion
                temp[k++] = arr[i++];
            } else if (arr[i] > arr[j]) {

                if (arr[i] > 2 * arr[j]) {
                    count += (mid - i);
                }

                temp[k++] = arr[j++];
            }
        }

        return count;

    }
}
