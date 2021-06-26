package challenges;


import java.util.*;

public class JuneChallenge {

    public int openLock(String[] deadends, String target) {
        HashSet<String> deadEndSets = new HashSet<>(Arrays.asList(deadends));
        StringBuilder builder = new StringBuilder("0000");

        HashSet<String> visited = new HashSet<>();
        visited.add("0000");

        Queue<String> queue = new LinkedList<>();
        queue.add("0000");

        int level = 0;

        while(!queue.isEmpty()) {

            int size = queue.size();

            while(size > 0) {
                String lockPosition = queue.poll();

                if (deadEndSets.contains(lockPosition)) {
                    size--;
                    continue;
                }

                if (lockPosition.equals(target)) {
                    return level;
                }

                StringBuilder sb = new StringBuilder();

                for(int i = 0; i < lockPosition.length(); i++) {
                    char curr_position = sb.charAt(i);
                    String s1 = sb.substring(0, i) + (curr_position == '9' ? 0 : (curr_position - '0' + 1)) + sb.substring(i+1);
                    String s2 = sb.substring(0, i) + (curr_position == '0' ? 9 : (curr_position - '0' - 1)) + sb.substring(i+1);

                    if (!visited.contains(s1) && !deadEndSets.contains(s1)) {
                        queue.offer(s1);
                        visited.add(s1);
                    }

                    if (!visited.contains(s2) && !deadEndSets.contains(s2)) {
                        queue.offer(s2);
                        visited.add(s2);
                    }
                }

                size--;
            }

            level++;

        }

        return -1;

    }

    public int longestConsecutive(int[] nums) {
        Map<Integer, Boolean> map = new HashMap<>();

        for(int num : nums) {
            map.put(num, true);
        }

        for(int num : nums) {
            if (map.containsKey(num-1)) {
                map.put(num, false);
            }
        }

        int maxLen = 0;
        for(int num : nums) {
            if (map.get(num)) {
                int len = 0;
                int currVal = num;
                while(map.containsKey(currVal)) {
                    len++;
                    currVal++;
                }

                maxLen = Math.max(maxLen, len);
            }
        }

        return maxLen;
    }


}
