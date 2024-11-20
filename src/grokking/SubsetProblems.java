package grokking;

import companies.ArrayProblems;

import java.util.*;
import java.util.stream.Collectors;

public class SubsetProblems {

    public static void printAllSubSets(int[] arr) {
        printSubsetsInternal(arr, 0, "");
    }

    private static void printSubsetsInternal(int[] arr, int index, String result) {
        if (index == arr.length) {
            System.out.println(result);
            return;
        }

        printSubsetsInternal(arr, index + 1, result);
        printSubsetsInternal(arr, index + 1, result + "," + arr[index]);
    }

    public List<List<Integer>> findSubsets(int[] nums) {
        // Create a list of the subsets results
        List<List<Integer>> subsets = new ArrayList<>();

        // Add empty set to the subset
        subsets.add(new ArrayList<>());

        // For each element
        for(int num : nums) {
            int n = subsets.size();

            // For each subset, create its copy and add element to each copy
            for (int i = 0; i < n; i++) {
                List<Integer> subset = subsets.get(i);
                List<Integer> subsetCopy = new ArrayList<>(subset);
                subsetCopy.add(num);
                subsets.add(subsetCopy);
            }
        }

        return subsets;
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        // Create a list of the subsets results
        Set<List<Integer>> subsets = new HashSet<>();

        // Add empty set to the subset
        subsets.add(new ArrayList<>());

        // For each element
        for(int num : nums) {
            int n = subsets.size();
            Set<List<Integer>> newSubsets = new HashSet<>();

            // For each subset, create its copy and add element to each copy
            for (List<Integer> subset : subsets) {
                List<Integer> subsetCopy = new ArrayList<>(subset);
                subsetCopy.add(num);
                Collections.sort(subsetCopy);
                newSubsets.add(subsetCopy);
            }

            subsets.addAll(newSubsets);
        }

        return new ArrayList<>(subsets);
    }

    public static List<List<Integer>> findUniqueSubsets(int[] arr) {
        Arrays.sort(arr);

        List<List<Integer>> subsets = new ArrayList<>();
        subsets.add(new ArrayList<>());

        for(int num : arr) {

        }

        return subsets;
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        populatePermutation(nums, 0, new ArrayList<>(), result);

        return result;
    }


    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void populatePermutation(int[] nums, int startIndex, List<Integer> list, List<List<Integer>> result) {
        if (startIndex == nums.length) {
            result.add(new ArrayList<>(list));
            return;
        }

        for (int i = startIndex; i < nums.length; i++) {
            swap(nums, i, startIndex);
            list.add(nums[startIndex]);
            populatePermutation(nums, startIndex + 1, list, result);
            list.remove(list.size() - 1);
            swap(nums, i, startIndex);
        }
    }

    public List<String> letterCasePermutation(String s) {
        List<String> result = new ArrayList<>();

        populatePermutationCase(s.toCharArray(), 0, new ArrayList<>(), result);

        return result;

    }

    private void populatePermutationCase(char[] nums, int startIndex, List<Character> list, List<String> result) {
        if (startIndex == nums.length) {
            // Convert list of characters to a string and add to result
            StringBuilder sb = new StringBuilder();
            for (Character ch : list) {
                sb.append(ch);
            }
            result.add(sb.toString());
            return;
        }

        char ch = nums[startIndex];

        // Add lowercase version
        list.add(Character.toLowerCase(ch));
        populatePermutationCase(nums, startIndex + 1, list, result);
        list.remove(list.size() - 1);

        // Add uppercase version (only if it's a letter)
        if (Character.isLetter(ch)) {
            list.add(Character.toUpperCase(ch));
            populatePermutationCase(nums, startIndex + 1, list, result);
            list.remove(list.size() - 1);
        }
    }

    private void swap(char[] nums, int i, int j) {
        char temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }



}
