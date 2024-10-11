package Grind75;

public class Week1 {

    /**
     * Leetcode 001
     * @param nums - Array
     * @param target - Target element
     * @return index of two elements whose sum will be equal to the target
     */
    public static int[] twoSum(int[] nums, int target) {

        int[] result = new int[2];

        // Iterate over each number
        for(int i = 0; i < nums.length; i++) {   // O(n)
            int num = nums[i];
            result[0] = i;

            // Check if target - num exists, if yes return the index.
            int index = getIndex(nums, target - num, i);

            if (index != -1) {
                result[1] = index;
                return result;
            }
        }

        return null;
    }

    private static int getIndex(int[] nums, int val, int currentIndex) {

        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == val && i != currentIndex) {
                return i;
            }
        }

        return -1;
   }
}
