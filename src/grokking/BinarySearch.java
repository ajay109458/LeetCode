package grokking;

import utils.Pair;

public class BinarySearch {

    public static int searchIndex(int[] arr, int key) {
        int left = 0;
        int right = arr.length - 1;

        boolean isIncreasing = arr[left] <= arr[right];

        while(left <= right) {
            int mid = left  + (right - left)/2;

            if (arr[mid] == key) {
                return mid;
            }

            if (isIncreasing) {
                if (key < arr[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (key > arr[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

        }

        return -1;
    }

    public static int ciel(int[] arr, int key) {
        int left = 0;
        int right = arr.length -1;

        while(left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == key) {
                return mid;
            } else if ((mid == 0 || arr[mid-1] < key) && key < arr[mid]) {
                return mid;
            } else if (key < arr[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return -1;
    }

    public static char findNextChar(char[] input, char key) {
        int left = 0;
        int right = input.length - 1;

        int index = -1;

        while(left <= right) {
            int mid = left + (right - left)/2;

            if (input[mid] == key) {
                index = mid;
                break;
            } else if ((mid == input.length-1 || key < input[mid+1]) && input[mid ] < key ) {
                index = mid;
                break;
            }else if (key < input[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        index = (index + 1) % input.length;
        return input[index];
    }

    public static Pair findRange(int[] arr, int key) {

        Pair pair = new Pair(-1, -1);

        int startIndex = findStartIndex(arr, key);
        if (startIndex == -1)
            return pair;

        int endIndex = findEndIndex(arr, key);

        pair.x = startIndex;
        pair.y = endIndex;
        return pair;
    }

    private static int findStartIndex(int[] arr, int key) {

        int left = 0;
        int right = arr.length - 1;

        while(left <= right) {
            int mid = left + (right - left) / 2;

            if ((mid == 0 || arr[mid-1] != key) && arr[mid] == key) {
                return mid;
            } else if (key <= arr[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return -1;
    }

    private static int findEndIndex(int[] arr, int key) {

        int left = 0;
        int right = arr.length - 1;

        while(left <= right) {
            int mid = left + (right - left) / 2;

            if ((mid == arr.length-1 || arr[mid+1] != key) && arr[mid] == key) {
                return mid;
            } else if (key >= arr[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    public static int searchInInfiniteArray(int[] arr, int key) {

        int start = 0;
        int end = 1;

        while(end < arr.length && arr[end] <= key) {
            start = end;
            end = 2 * end;
        }

        end = Math.min(end, arr.length -1);

        while(start <= end) {
            int mid = start + (end - start)/2;

            if (arr[mid] == key) {
                return mid;
            } else if (key < arr[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return -1;
    }

    public static int findMinDiffElement(int[] arr, int key) {
        int left = 0;
        int right = arr.length -1;

        if (key < arr[left]) {
            return arr[left];
        }

        if (key > arr[right]) {
            return arr[right];
        }

        while(left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == key) {
                return arr[mid];
            } else if (key > arr[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        if (Math.abs(arr[left] - key) < (key - arr[right])) {
            return arr[left];
        }

        return arr[right];
    }

    public static int findMaximumInBitonicArray(int[] arr) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (mid == 0 || mid == arr.length - 1) {
                if (arr[0] > arr[arr.length-1]) {
                    return arr[0];
                } else {
                    return arr[arr.length-1];
                }
            } else {
                if (arr[mid] > arr[mid-1] && arr[mid] > arr[mid + 1]) {
                    return arr[mid];
                } else if (arr[mid-1] < arr[mid]) {
                    left = mid + 1;
                } else {
                    right = mid -1;
                }
            }
        }

        return -1;
    }

}
