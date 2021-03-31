package array;

public class Search {

    /**
     * Given a sorted array, you have to search the number in the array
     *
     * Input - 0 4 7 10 14 23 45 47 53
     * Search - 47
     *
     * Ouput - 7
     */

    public static int search(int[] arr, int target) {
        for (int i = 0; i < arr.length ; i++) {
            if (arr[i] == target) {
                return i;
            }
        }

        return -1;
    }

    public static int binarySearch(int[] arr, int target) {

        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = (left + right) /2;

            if (arr[mid] == target) {
                return mid;
            } else if (target < arr[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return -1;
    }


    public static void main(String[] args) {
        int[] arr = {0, 4, 7, 10, 14, 23, 45, 47, 53};

        int target = 47;
        int index = binarySearch(arr, target);

        System.out.println( target  + " found at index : " + index);
    }

}
