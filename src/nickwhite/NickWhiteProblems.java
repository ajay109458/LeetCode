package nickwhite;

import linkedlist.ListNode;

import java.util.*;

public class NickWhiteProblems {

    private class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    public static boolean repeatedSubstringPattern(String s) {
        int len = s.length();

        for(int i = len/2; i >= 1; i--) {
            if (len % i == 0) {
                int numRepeats = len / i;

                String substring = s.substring(0, i);

                StringBuilder sb = new StringBuilder();
                for(int j = 0; j < numRepeats; j++) {
                    sb.append(substring);
                }

                if (sb.toString().equals(s))
                    return true;
            }
        }

        return false;
    }

    public static boolean isSubsequence(String pattern, String text) {
        int i = 0;
        int j = 0;

        while(i < pattern.length() && j < text.length()) {

            char aCh = pattern.charAt(i);
            char bCh = text.charAt(j);

            if (aCh == bCh) {
                i++;

                if (i == pattern.length())
                    return true;
            }

            j++;
        }

        return (i == pattern.length());
    }

    public static int longestContinuousSubseq(int[] arr) {

        int result = 0;
        int anchor = 0;

        for(int i = 0; i < arr.length; i++) {
            if (i > 0 && arr[i-1] >= arr[i]) {
                anchor = i;
            }

            result = Math.max(result, i - anchor + 1);
        }

        return result;
    }

    public static int maxDepth(Node node) {

        if (node == null)
            return 0;

        int max = 0;

        for(Node child : node.children) {
            max = Math.max(max, maxDepth(child));
        }

        return max + 1;
    }


    public int[] numberOfLines(int[] widths, String s) {
        int[] result = new int[2];

        int line = 1;
        int offset = 0;

        for(char ch : s.toCharArray()) {
            if (offset + widths[ch] <= 100) {
                offset += widths[ch];
            } else {
                offset = widths[ch];
                line++;
            }
        }

        result[0] =  line;
        result[1] = offset;

        return result;
    }

    public int longestPalindrome(String s) {
        int[] cache = new int[128];

        for(char ch : s.toCharArray()) {
            cache[ch]++;
        }

        boolean hasOdd = false;

        int len = 0;

        for(int i =0 ; i < 128; i++) {
            len +=  cache[i] - ((cache[i] % 2 == 0) ? 0 : 1);
            hasOdd = hasOdd || (cache[i] % 2 == 1);
        }

        return len + (hasOdd ? 1 : 0);
    }

    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        int r1x1 = rec1[0];
        int r1y1 = rec1[1];

        int r1x2 = rec1[2];
        int r1y2 = rec1[3];


        int r2x1 = rec2[0];
        int r2y1 = rec2[1];

        int r2x2 = rec2[2];
        int r2y2 = rec2[3];

        return r1x1 < r2x2 && r1y1 < r2y2 && r1x2 > r2x1 && r1y2 > r2y1;
    }

    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);

        int childIndex = g.length -1;
        int cookieIndex = s.length -1;

        int childGotCookie = 0;

        while(childIndex >= 0 && cookieIndex >= 0) {

            if (s[cookieIndex] >= g[childIndex]) {
                childGotCookie++;
                cookieIndex--;
            }
            childIndex--;
        }

        return childGotCookie;
    }

    public boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");

        if (words.length != s.length()) {
            return false;
        }

        Map<Character, String> map = new HashMap<>();

        for(int i = 0; i < pattern.length(); i++) {
            if (!map.containsKey(pattern.charAt(i))) {
                if (map.values().contains(words[i])){
                    return false;
                }
                map.put(pattern.charAt(i), words[i]);
            } else {
                if(!map.get(pattern.charAt(i)).equals(words[i])) {
                    return false;
                }
            }
        }

        return true;
    }

    public int thirdMax(int[] nums) {
        Integer max = null;
        Integer secondMax = null;
        Integer thirdMax = null;

        for(Integer num : nums) {

            if (num.equals(max) || num.equals(secondMax) || num.equals(thirdMax)) {
                continue;
            }

            if (max == null  || num > max) {
                thirdMax = secondMax;
                secondMax = max;
                max = num;
            } else if (secondMax == null || num > secondMax) {
                thirdMax = secondMax;
                secondMax = num;
            } else if(thirdMax == null || num > thirdMax) {
                thirdMax = num;
            }

            System.out.println(thirdMax + " : " + num);
        }

        if (thirdMax == null) {
            return max;
        }

        return thirdMax;
    }

    public int countSegments(String s) {
        return (int) Arrays.stream(s.split(" "))
                .filter(word -> (word != null && !"".equals(word.trim()) && Character.isAlphabetic(word.charAt(0))))
                .count();
    }

    public List<Integer> partitionLabels(String s) {
        if (s == null || s.length() == 0)
            return null;

        List<Integer> result = new ArrayList<>();

        int[] lastIndex = new int[26];


        return result;
    }

    public int findMinDifference(List<String> timePoints) {
        boolean[] timeSeen = new boolean[24 * 60];

        for(String timePoint : timePoints) {
            String[] timeSplit = timePoint.split(":");

            int hour = Integer.parseInt(timeSplit[0]);
            int minutes = Integer.parseInt(timeSplit[1]);

            if(timeSeen[hour * 60 + minutes])
                return 0;

            timeSeen[hour * 60 + minutes] = true;
        }

        Integer firstTimeSeen = null;
        Integer prevTimeSeen = null;

        Integer minDiff = Integer.MAX_VALUE;

        for(int i = 0; i < minDiff; i++) {
            if (timeSeen[i]) {

                if (firstTimeSeen == null) {
                    firstTimeSeen = i;
                }

                if (prevTimeSeen != null) {
                    minDiff = Math.min(minDiff, Math.min(i - prevTimeSeen, 1440 - i + prevTimeSeen));
                }

                prevTimeSeen = i;
            }
        }

        minDiff = Math.min(minDiff, Math.min(prevTimeSeen - firstTimeSeen, 1440 - prevTimeSeen + firstTimeSeen));

        return minDiff;
    }

    public int findMaxLength(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        int sum = 0;

        int maxLen = 0;
        for(int i = 0; i < nums.length; i++) {
            int num = nums[i];
            sum += (num == 0) ? -1 : 1;

            if (map.containsKey(sum)) {
                maxLen = Math.max(maxLen, i - map.get(sum) + 1);
            }

            map.putIfAbsent(sum, i);
        }

        return maxLen;
    }

    public int findShortestSubArray(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        Map<Integer, Integer> startMap = new HashMap<>();

        int degree = 0;
        int minLen = Integer.MAX_VALUE;
        for(int i = 0; i < nums.length; i++) {
            int num = nums[i];
            map.put(num, map.getOrDefault(num, 0) + 1);
            startMap.putIfAbsent(num, i);

            if (map.get(num) > degree) {
                degree = map.get(num);
                minLen = Math.min(minLen, i - startMap.get(num)+1);
            }
        }

        return minLen;
    }

    private static class LRUCache {

        private int capacity;

        ListNode head;
        ListNode tail;

        private int size;

        Map<Integer, ListNode> map;

        public LRUCache(int capacity) {
            this.capacity = capacity;

            head = new ListNode(-1);
            tail = new ListNode(-1);

            head.next = tail;
            tail.prev = head;

            size = 0;

            map = new HashMap<>();
        }

        public void addNode(ListNode node) {
            ListNode headNext = head.next;
            head.next = node;

            node.next = headNext;
            headNext.prev = node;

            node.prev = head;
        }

        public void removeNode(ListNode node) {
            ListNode prev = node.prev;
            ListNode next = node.next;

            prev.next = next;
            next.prev = prev;
        }

        public void moveToHead(ListNode node) {
            removeNode(node);
            addNode(node);
        }

        public void removeLastNode() {
            removeNode(tail.prev);
        }

        public int get(int key) {
            ListNode node = map.get(key);

            if (node == null)
                return -1;

            moveToHead(node);

            return node.val;
        }

        public void put(int key, int value) {

            ListNode node = map.get(key);

            if (node == null) {
                ListNode newNode = new ListNode(value);
                map.putIfAbsent(key, newNode);

                addNode(newNode);
                capacity++;

                if (size > capacity) {
                    removeLastNode();
                    size--;
                }
            } else {
                node.val = value;
                moveToHead(node);
            }

        }

    }

    public int[] distributeCandies(int candies, int num_people) {

        int[] res = new int[num_people];

        int index = 0;
        int currCandy = 1;
        while(candies > 0) {
            if (candies >= currCandy) {
                res[index] += currCandy;
                candies -= currCandy;
            } else {
                res[index] += candies;
                candies = 0;
            }

            currCandy += 1;
            index = (index + 1) % num_people;
        }

        return res;
    }


}
