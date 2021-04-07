package sdesheet;

import java.util.*;

public class Day4 {
    public int longestConsecutive(int[] nums) {
        boolean[] visited = new boolean[nums.length];

        Map<Integer, Integer> elementByIndex = new HashMap<>();

        for(int i = 0; i < nums.length; i++) {
            elementByIndex.putIfAbsent(nums[i], i);
        }

        Queue<Integer> queue = new LinkedList<>();

        int max = 0;

        for(int i = 0; i < nums.length; i++) {
            int count = 0;
            if (!visited[nums[i]]) {
                queue.add(nums[i]);
                visited[nums[i]] = true;
            }

            while(!queue.isEmpty()) {
                int curr = queue.poll();
                count++;

                if (elementByIndex.containsKey(curr - 1)) {
                    int prevIndex = elementByIndex.get(curr - 1);
                    if (!visited[prevIndex]) {
                        queue.add(curr-1);
                    }
                }

                if (elementByIndex.containsKey(curr + 1)) {
                    int next = elementByIndex.get(curr + 1);
                    if (!visited[next]) {
                        queue.add(curr + 1);
                    }
                }
            }

            max = Math.max(max, count);
        }

        return max;
    }

    int countXorSubArray(int[] nums, int k) {
        int prefixXor = 0;

        int count = 0;
        Set<Integer> xorSet = new HashSet<>();

        for(int num : nums) {
            prefixXor ^= num;

            if (xorSet.contains(prefixXor ^ k)) {
                count++;
            }
        }

        return count;
    }


}
