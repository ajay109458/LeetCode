package sdesheet;

import java.beans.PropertyEditorSupport;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Day9Recursion {


    public static void printSubSets(int[] nums) {
        printSubSet(nums, 0, new ArrayList<>());
    }

    private static void printSubSet(int[] nums, int index, List<Integer> list) {

        if (index >= nums.length) {
            System.out.println(list);
            return;
        }

        printSubSet(nums, index + 1, list);
        list.add(nums[index]);
        printSubSet(nums, index  + 1, list);
        list.remove(Integer.valueOf(nums[index]));
    }

    public static void subsetSum(int[] nums) {
        List<Integer> subSetSum = new ArrayList<>();

        populateSubSetSum(nums, 0, subSetSum, 0);

        Collections.sort(subSetSum);
    }

    private static void populateSubSetSum(int[] nums, int index, List<Integer> list, int sum) {


        if (index >= nums.length) {
            list.add(sum);
            return;
        }

        populateSubSetSum(nums, index + 1, list, sum);
        populateSubSetSum(nums, index  + 1, list, sum + nums[index]);
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        HashSet<List<Integer>> hashSet = new HashSet<>();
        populateSubSets(nums, 0, new ArrayList<>(), hashSet);

        return new ArrayList<>(hashSet);
    }

    private static void populateSubSets(int[] nums, int index, List<Integer> list, HashSet<List<Integer>> hashSet) {

        if (index >= nums.length) {
            List<Integer> newList = new ArrayList<>();
            newList.addAll(list);
            hashSet.add(newList);
            return;
        }

        populateSubSets(nums, index + 1, list, hashSet);
        list.add(nums[index]);
        populateSubSets(nums, index  + 1, list, hashSet);
        list.remove(Integer.valueOf(nums[index]));
    }


    public List<List<Integer>> combinationSum(int[] candidates, int target)     {
        List<List<Integer>> res = new ArrayList<>();
        populateCombindationSum(candidates, target, res, new ArrayList<>(), 0);
        return res;
    }

    public static void populateCombindationSum(int[] candidates, int target, List<List<Integer>> result, List<Integer> list, int index) {

        if (target == 0) {

            List<Integer> newArrayList = new ArrayList<>();
            newArrayList.addAll(list);

            result.add(newArrayList);

        } else if (target < 0) {
            return;
        }

        if (index > candidates.length) {
            return;
        }

        list.add(candidates[index]);
        populateCombindationSum(candidates, target - candidates[index], result, list, index);
        list.remove(Integer.valueOf(candidates[index]));

        populateCombindationSum(candidates, target, result, list, index +1 );

    }

    public List<List<String>> partition(String s) {

        List<List<String>> result = new ArrayList<>();

        populatePalindromicPartition(s, 0, new ArrayList<>(), result);

        return result;

    }

    public static boolean isPalindrome(String s) {
        if (s.length() == 0)
            return true;

        int i = 0;
        int j = s.length() -1;

        while( i < j) {
            if (s.charAt(i) != s.charAt(j))
                return false;
            i++;
            j--;
        }

        return true;
    }

    public void populatePalindromicPartition(String s, int index, List<String> list, List<List<String>> result) {

        if (index == s.length()) {
            List<String> clone = new ArrayList<>();
            clone.addAll(list);

            result.add(clone);
            return;
        }

        for(int i = index; i < s.length(); i++) {
            String sub = s.substring(index, i + 1);
            if (isPalindrome(sub)) {
                list.add(sub);
                populatePalindromicPartition(s, i+1, list, result);
                list.remove(sub);
            }
        }

    }

    public String getPermutation(int n, int k) {
        List<Character> v = new ArrayList<>();

        char c = '1';

        for (int i = 1; i <= n; ++i) {
            v.add(c);
            c++;
        }

        StringBuilder result = new StringBuilder();

        return result.toString();
    }

    private static int factorial(int n) {
        if (n == 0 || n == 1) return 1;
        return n * factorial(n -1 );
    }


    private static void findKthPermutation(List<Character> characters, int k, StringBuilder builder) {
        if (characters.isEmpty()) {
            return;
        }

        int n = characters.size();

        int count = factorial(n  - 1);
        int selected = (k - 1) / count;

        builder.append(characters.get(selected));
        characters.remove(selected);

        k = k - (count * selected);
        findKthPermutation(characters, k, builder);
    }
}
