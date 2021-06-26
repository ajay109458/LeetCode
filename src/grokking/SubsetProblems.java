package grokking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

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

    public static List<List<Integer>> findSubsets(int[] nums) {
        List<List<Integer>> subsets = new ArrayList<>();

        subsets.add(new ArrayList<>());

        for(int num : nums) {
            int n = subsets.size();

            for(int i = 0; i < n; i++) {
                List<Integer> set = new ArrayList<>(subsets.get(i));
                set.add(num);
                subsets.add(set);
            }
        }

        return subsets;
    }

    public static List<List<Integer>> findUniqueSubsets(int[] arr) {
        Arrays.sort(arr);

        List<List<Integer>> subsets = new ArrayList<>();
        subsets.add(new ArrayList<>());

        for(int num : arr) {

        }

        return subsets;
    }



}
