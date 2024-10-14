package hash;

import utils.Pair;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


public class HashHelper {

    public int smallestCommonElement(int[][] arr) {

        if (arr.length == 0) {
            return -1;
        }

        Map<Integer, Integer> countByElement  = new HashMap<>();

        for(int val : arr[0]) {
            countByElement.put(val, 1);
        }

        for(int i = 1; i < arr.length; i++) {
            Arrays
                    .stream(arr[i])
                    .forEach(val -> {
                        Integer count = countByElement.get(val);
                        if (count != null) {
                            countByElement.put(val, count + 1);
                        }
                    });
        }

        Integer minVal = null;

        List<Integer> list = countByElement.entrySet()
                .stream()
                .filter(entry -> arr.length == entry.getValue())
                .map(entry -> entry.getKey())
                .collect(Collectors.toList());

        for(Integer val : list) {
            if (minVal == null) {
                minVal = val;
            }

            minVal = Math.min(minVal, val);
        }


        return (minVal == null) ? -1 : minVal;
    }

    protected String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

    Map<String, String> map = new HashMap<>();
    String URLPrefix = "http://tinyurl.com/";

    public String encode(String longUrl) {
        String shortUrl = URLPrefix + getSaltString();
        map.put(shortUrl, longUrl );
        return shortUrl;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return map.get(shortUrl);
    }

    public int maxOperations(int[] nums, int k) {

        Map<Integer, Integer> countByValueMap = new HashMap<>();

        Arrays
                .stream(nums)
                .forEach(e -> {
                    Integer count = countByValueMap.get(e);
                    if (count == null) {
                        count = 0;
                    }

                    count += 1;
                    countByValueMap.put(e, count);
                });

        return 1;
    }

    public static int maximumUnits(int[][] boxTypes, int truckSize) {
        List<int[]> array = Arrays
                .stream(boxTypes)
                .sorted(new Comparator<int[]>() {
                    @Override
                    public int compare(int[] o1, int[] o2) {
                        double f1 = (o1[1] * 1.0 )/o1[0];
                        double f2 = (o2[1] * 1.0)/o2[0];
                        if (o1[1] > o2[1]) {
                            return -1;
                        } else if (o1[1] < o2[1]) {
                            return 1;
                        } else {
                            return 0;
                        }
                    }
                })
                .collect(Collectors.toList());

        int sum = 0;
        int boxCount = 0;

        boolean isLimitReached = false;

        for(int[] val : array) {
            int boxAdded = val[0] * val[1];

            if (boxCount + val[0] <= truckSize) {
                sum += boxAdded;
                boxCount += val[0];
            } else {
                for (int i = 0 ; i < val[0]; i++) {

                    if (boxCount + 1 <= truckSize) {
                        sum += val[1];
                        boxCount += 1;
                    } else {
                        isLimitReached = true;
                        break;
                    }
                }
            }

            if (isLimitReached) {
                break;
            }
        }

        return sum;
    }

    public static int countPairs(int[] deliciousness) {

        Map<Integer, Integer> map = new TreeMap<>();
        for(int val : deliciousness) {
            Integer count = map.get(val);
            if (count == null) {
                count = 0;
            }
            count += 1;
            map.put(val, count);
        }

        int count = 0;
        for(int i = 0; i < deliciousness.length; i++) {

            int currVal = deliciousness[i];

            if (map.get(currVal) < 1) {
                continue;
            }

            int curr =1;
            while(curr <= currVal) {
                curr <<= 1;
            }

            int lookupVal = curr - currVal;
            Integer lookupCount = map.get(lookupVal);

            if (lookupCount != null) {
                if (lookupVal == currVal && lookupCount == 1) {
                    continue;
                }

                if (currVal == lookupVal) {
                    lookupCount -=1;
                    lookupCount /= 2;
                }

                //System.out.println(currVal + " : " + lookupCount);

                count += lookupCount;
            }

        }

        return count;
    }

    /**
     *
     * 3 1 4 2 : Sub array remove st. remaining array sum is divisible by p
     *
     * 0 1 2 1
     *
     * @param nums
     * @param p
     * @return
     */
    public int minSubarray(int[] nums, int p) {
        int arrSum = Arrays.stream(nums)
                .sum();

        int mod = arrSum % p;

        if (mod == 0) {
            return 0;
        }

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0;
        int res = nums.length;

        for( int i = 0; i < nums.length; i++) {
            sum = (sum % p + nums[i]) % p;

            int temp = (sum - mod) % p;

            if (temp < 0) {
                temp += p;
            }

            if (map.containsKey(temp)) {
                res = Math.min(res, i - map.get(temp));
            }

            map.put(sum % p, i);
        }

        return res;
    }

    List<String> getFileNames(List<String> fileNames) {

        Map<String, Integer> map = new HashMap<>();

        List<String> result = new ArrayList<>();

        for(String fileName: fileNames) {
            Integer count = map.get(fileName);

            if (count == null) {
                result.add(fileName);
                count = 0;
            } else {

                while(true) {
                    String newFileName = fileName + "(" + count + ")";

                    if (!map.containsKey(newFileName)) {
                        map.put(newFileName, 1);
                        break;
                    }
                    count++;
                }
            }

            map.put(fileName, count + 1);
        }

        return  result;

    }

    Map<String, Set<String>> stringsByAbbrr = new HashMap<>();

    public boolean isUnique(String word) {
        if (null == word || "".equals(word)) {
            return false;
        }

        int len = word.length();
        String abr = Character.toString(word.charAt(0)) + (len == 2 ? "": len - 2) + word.charAt(len - 1);

        Set<String> values = stringsByAbbrr.get(abr);

        if (values == null || values.size() == 1) {
            return true;
        }

        return false;
    }

    private String getAbr(String word) {
        int len = word.length();
        return Character.toString(word.charAt(0)) + (len == 2 ? "": len - 2) + word.charAt(len - 1);
    }

    private Map<String, Integer> pathMap = new HashMap<>();

    public boolean createPath(String path, int value) {
        if (null == path || "".equals(path) || "/".equals(path)) {
            return false;
        }

        if (pathMap.containsKey(path)) {
            return false;
        }

        int index = path.lastIndexOf("/");
        if (index == -1) {
            return false;
        }

        if (index > 0) {
           String parentPath = path.substring(0, index-1);
           if(pathMap.containsKey(parentPath)) {
               return false;
           }
        }

        pathMap.put(path, value);
        return true;
    }

    public int get(String path) {
        Integer val = pathMap.get(path);
        if (val == null) {
            return -1;
        }

        return val;
    }

    public int tupleSameProduct(int[] nums) {
        Map<Long, Set<Pair>> pairByProdMap = new HashMap<>();

        int count = 0;
        for(int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int fir = nums[i];
                int sec = nums[j];
                if (fir != sec) {
                    long product = fir * sec;
                    Set<Pair> existingPairs = pairByProdMap.get(product);
                    if (existingPairs == null) {
                        existingPairs = new HashSet<>();
                    }

                    Pair newPair = new Pair(fir, sec);
                    if (! (existingPairs.contains(newPair) || existingPairs.contains(new Pair(sec, fir)))) {
                        existingPairs.add(newPair);
                        pairByProdMap.put(product, existingPairs);
                    }

                }
            }
        }

        for(Map.Entry<Long, Set<Pair>> entry : pairByProdMap.entrySet()) {
            int size = entry.getValue().size();
            if (size > 1) {
                count += (size * (size - 1))/2;
            }
        }

        return count * 8;
    }

    public int maxAreaOfIsland(int[][] grid) {

        Set<String> pathCodeSet = new HashSet<>();
        for(int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    List<String> pathCode = new ArrayList<>();
                    String pathCodeString = String.join("", pathCode);
                    dfsGetArea(grid, i, j, pathCode, 'S');
                    if (!"".equals(pathCodeString)) {
                        pathCodeSet.add(pathCodeString);
                    }
                }
            }
        }

        return pathCodeSet.size();
    }

    private int dfsGetArea(int[][] grid, int i, int j, List<String> pathCode, char ch){

        if (i < 0 || i >= grid.length) {
            return 0;
        }

        if (j < 0 || j >= grid[0].length) {
            return 0;
        }

        if (grid[i][j] == 2 || grid[i][j] == 0) {
            return 0;
        }

        pathCode.add(ch + "");
        grid[i][j] = 2;

        return 1 + dfsGetArea(grid, i+1, j, pathCode, 'U')
                + dfsGetArea(grid, i - 1, j, pathCode, 'D')
                + dfsGetArea(grid, i, j+1, pathCode, 'R')
                + dfsGetArea(grid, i, j-1, pathCode, 'L');
    }

    public List<String> topKFrequent(String[] words, int k) {

        List<String> result = new ArrayList<>();

        if (words.length == 0) {
            return result;
        }

        Map<String, Long> freqByWordMap = Arrays.stream(words)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return freqByWordMap.entrySet()
                .stream()
                .sorted((e1, e2) -> {
                    if (e1.getValue().equals(e2.getValue())) {
                       return e1.getKey().compareTo(e2.getKey());
                    }

                    return e2.getValue().compareTo(e1.getValue());
                }).limit(k)
                .map(e -> e.getKey())
                .sorted()
                .collect(Collectors.toList());
    }

    class TimeMap {

        private Map<String, TreeMap<Integer, String>> map;

        public TimeMap() {
            map = new HashMap<>();
        }

        public void set(String key, String value, int timestamp) {
            if (!map.containsKey(key)) {
                map.put(key, new TreeMap<>());
            }

            map.get(key).put(timestamp, value);
        }

        public String get(String key, int timestamp) {
            if (!map.containsKey(key)) {
                return "";
            }

            TreeMap<Integer, String> treeMap = map.get(key);
            Integer t = treeMap.floorKey(timestamp);
            if (t == null) {
                return "";
            }
            return treeMap.get(t);
        }
    }

    class WordDistance {

        private String[] words;

        public WordDistance(String[] words) {
            this.words = words;
        }

        public int shortest(String word1, String word2) {
            int lastIndex = -1;
            int minDis= words.length;
            for(int i = 0; i < words.length; i++) {
                if (words[i].equals(word1) || words[i].equals(word2)) {
                    if (lastIndex != -1) {
                        if (!words[i].equals(words[lastIndex])) {
                            minDis = Math.min(i - lastIndex, minDis);
                        }
                    }

                    lastIndex = i;
                }
            }

            return minDis;
        }
    }

    public int[] anagramMappings(int[] A, int[] B) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        for(int i = 0; i < B.length; i++) {
            map.put(B[i], i);
        }

        int[] res = new int[A.length];

        for(int i = 0; i < A.length; i++) {
            res[i] = map.get(A[i]);
        }

        return res;
    }

    public List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
        int i = 0, j = 0, k=0;

        List<Integer> result = new ArrayList<>();

        while(i < arr1.length && j < arr2.length && k < arr3.length) {
            if (arr1[i] == arr2[j] && arr2[j] == arr3[k]) {
                result.add(arr1[i]);
                i++;
                j++;
                k++;
            }else if(arr1[i] < arr3[k] || arr2[j] < arr3[k]) {
                if (arr1[i] < arr3[k]) {
                    i++;
                }
                if (arr2[j] < arr3[k]) {
                    j++;
                }
            }else if(arr1[i] < arr2[j] || arr3[k] < arr2[j]) {
                if (arr1[i] < arr2[j]) {
                    i++;
                }
                if (arr3[k] < arr2[j]) {
                    k++;
                }
            }else if(arr3[k] < arr1[i] || arr2[j] < arr1[i]) {
                if (arr3[k] < arr1[i]) {
                    k++;
                }
                if (arr2[j] < arr1[i]) {
                    j++;
                }
            }
        }

        return result;
    }


    public int sumOfUnique(int[] nums) {
        Map<Integer, Long> result = Arrays.stream(nums)
                .mapToObj(num -> (Integer)num)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return result.entrySet().stream()
                .filter(e -> e.getValue() == 1)
                .map(e -> e.getKey())
                .mapToInt(Integer::intValue)
                .sum();
    }

    public int repeatedNTimes(int[] A) {
        Map<Integer, Long> result = Arrays.stream(A)
                .mapToObj(num -> (Integer)num)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return result.entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .findFirst().get().getKey();
    }

    class Logger {

        private Map<String, Integer> timestampByKeyMap;

        /** Initialize your data structure here. */
        public Logger() {
            timestampByKeyMap = new HashMap<>();
        }

        /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
         If this method returns false, the message will not be printed.
         The timestamp is in seconds granularity. */
        public boolean shouldPrintMessage(int timestamp, String message) {
            Integer lastTimestamp = timestampByKeyMap.get(message);
            if (lastTimestamp == null) {
                timestampByKeyMap.put(message, timestamp);
                return true;
            }

            if (timestamp >= lastTimestamp + 10) {
                timestampByKeyMap.put(message, timestamp);
                return true;
            }
            return false;
        }
    }

    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Long> freqByNumMap = Arrays.stream(arr)
                .mapToObj(val -> (Integer)val)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        Set<Long> set = new HashSet<>();
        for(Map.Entry<Integer, Long> entry: freqByNumMap.entrySet()) {
            if (set.contains(entry.getValue())) {
                return false;
            }

            set.add(entry.getValue());
        }

        return true;
    }

    public String[] findWords(String[] words) {
        Set<Character> row1 = new HashSet<>(Arrays.asList('q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p'));
        Set<Character> row2 = new HashSet<>(Arrays.asList('a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l'));
        Set<Character> row3 = new HashSet<>(Arrays.asList('z','x', 'c' , 'v', 'b', 'n', 'm'));

        List<String> result = new ArrayList<>();

        for(String s : words) {

            String word = s.toLowerCase();

            boolean first = true;
            boolean second = true;
            boolean third = true;

            for(char ch : word.toCharArray()) {
                if (!row1.contains(ch)) {
                    first = false;
                }

                if (!row2.contains(ch)) {
                    second = false;
                }

                if (!row3.contains(ch)) {
                    third = false;
                }


            }
            if (first || second || third) {
                result.add(s);
            }

        }

        return result.toArray(new String[result.size()]);
    }

    class MyHashMap {

        int[] map;

        /** Initialize your data structure here. */
        public MyHashMap() {
            map = new int[1000000];
        }

        /** value will always be non-negative. */
        public void put(int key, int value) {
            map[key] = value;
        }

        /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
        public int get(int key) {
            return map[key];
        }

        /** Removes the mapping of the specified value key if this map contains a mapping for the key */
        public void remove(int key) {
            map[key] = -1;
        }
    }

    public boolean canPermutePalindrome(String s) {
        Map<Character, Long> map = s.chars()
                .mapToObj(ch -> (char)ch)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        long oddEntries = map.entrySet().stream().filter(e -> e.getValue() % 2 != 0).count();

        return oddEntries < 2;
    }

    public int distributeCandies(int[] candyType) {
        Map<Integer, Long> freqByCandyTypeMap = Arrays.stream(candyType)
                .mapToObj(val -> (Integer)val)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        int k = candyType.length/2;
        int m = freqByCandyTypeMap.entrySet().size();

        if (m > k) {
            return k;
        }

        return m;
    }

    public String[] findRestaurant(String[] list1, String[] list2) {
        List<String> result = new ArrayList<>();
        int minValue = list1.length + list2.length;
        Map<String, Integer> indexByResturantMap = new HashMap<>();
        for(int i = 0; i < list1.length; i++) {
            indexByResturantMap.put(list1[i], i);
        }

        for(int i = 0; i < list2.length; i++) {
            Integer existingIndex = indexByResturantMap.get(list2[i]);

            if (existingIndex != null) {
                if (minValue > existingIndex + i) {
                    minValue = existingIndex + i;

                    result = new ArrayList<>();
                    result.add(list2[i]);
                } else if (minValue ==  existingIndex + i) {
                    result.add(list2[i]);
                }
            }
        }

        return result.toArray(new String[result.size()]);
    }

    class PairSet<T, U> {
        public T x;
        public U y;

        PairSet(T t, U u) {
            this.x = t;
            this.y = u;
        }
    }

    public List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> map = new HashMap<>();

        for(String str: strings) {
             String group = getGroupOfString(str);

             map.putIfAbsent(group, new LinkedList<>());
             map.get(group).add(str);
        }

        return new ArrayList<>(map.values());
    }

    public String getGroupOfString(String str) {
        char[] arr = str.toCharArray();

        StringBuilder builder = new StringBuilder();
        builder.append(arr.length);

        for (int i = 0; i < arr.length; i++) {
            int gap = arr[i] - arr[i-1];
            if (gap < 0) {
                gap += 26;
            }

            builder.append(String.valueOf(gap));
        }

        return builder.toString();
    }

    public int leastBricks(List<List<Integer>> wall) {
        Map<Integer, Integer> sumByCountMap = new HashMap<>();

        int maxValue = 0;
        for(List<Integer> brickLine: wall) {
            int currSum = 0;
            for(Integer brickLen: brickLine) {
                currSum += brickLen;
                Integer count = sumByCountMap.get(currSum);
                if (count  == null) {
                    count = 0;
                }

                sumByCountMap.put(currSum, count + 1);
                maxValue = Math.max(maxValue, count +1);
            }
        }

        return wall.size() - maxValue;
    }

    public boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {

            Set<Integer> set = new HashSet<>();
            for (int j = 0; j < 9; j++) {

                if (board[i][j] == '.') {
                    continue;
                }

                Integer digit = board[i][j] - '0';
                if (digit <= 0 || digit > 9) {
                    return false;
                }

                if (set.contains(digit)) {
                    return false;
                }

                set.add(digit);
            }
        }

        // Sum of each col
        for (int j = 0; j < 9; j++) {
            Set<Integer> set = new HashSet<>();

            for (int i = 0; i < 9; i++) {

                if (board[i][j] == '.') {
                    continue;
                }

                Integer digit = board[i][j] - '0';
                if (digit <= 0 || digit > 9) {
                    return false;
                }

                if (set.contains(digit)) {
                    return false;
                }

                set.add(digit);
            }
        }

        // validate sum in each box
        for (int m = 3; m <= 9; m = m +3) {
            for (int n = 3; n <=9; n = n+3) {
                Set<Integer> set = new HashSet<>();
                for (int i = m-3; i < m ; i++) {
                    for (int j = n -3; j < n; j++) {
                        if (board[i][j] == '.') {
                            continue;
                        }

                        Integer digit = board[i][j] - '0';
                        if (digit <= 0 || digit > 9) {
                            return false;
                        }

                        if (set.contains(digit)) {
                            return false;
                        }

                        set.add(digit);
                    }
                }
            }
        }


        return true;
    }

    public int lengthOfLongestSubstringTwoDistinct(String s) {
        Map<Character, Integer> countByCharMap = new HashMap<>();

        int maxLen = 0;
        int prevChar = 0;

        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            // add character
            Integer count = countByCharMap.get(ch);
            count = ((count == null) ? 0 : count)+1;
            countByCharMap.put(ch, count);

            // check if hash hash size 2
            while(countByCharMap.size() > 2) {
                countByCharMap.remove(s.charAt(prevChar));
                prevChar++;
            }

            maxLen = Math.max(maxLen, i - prevChar + 1);
        }

        return maxLen;
    }

    /**
     * Sort the array
     *
     * For each element Find the element in the array
     */



    public static int findLargestSubArrayWith0Sum(int[] arr) {
        Map<Integer, Integer> sumIndexMap = new HashMap<>();

        int currSum = 0;
        int maxLen = 0;

        for(int i = 0; i < arr.length; i++) {
            currSum += arr[i];

            Integer firstIndex = sumIndexMap.get(currSum);

            if (firstIndex != null) {
                maxLen = Math.max(maxLen, i - firstIndex);
            }

            if (firstIndex == null)
                 sumIndexMap.put(currSum, i);
        }

        return maxLen;
    }

    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> sumMapIndex = new HashMap<>();

        int currSum = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            currSum += nums[i];

            if (currSum == k) {
                count++;
            }

            Integer remainingCount = sumMapIndex.get(currSum - k);
            if (remainingCount != null) {
                count += remainingCount;
            }

            Integer tempCount = sumMapIndex.get(currSum);
            tempCount = ((tempCount == null) ? 0 : tempCount) + 1;
            sumMapIndex.put(currSum, tempCount);
        }

        return count;
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        Map<Integer, Set<Pair>> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            for(int j = i +1; j < nums.length; j++) {
                int sum = nums[i] + nums[j];
                Set<Pair> pairs = map.get(sum);
                if (pairs == null) {
                    pairs = new HashSet<>();
                    map.put(sum, pairs);
                }

                pairs.add(new Pair(nums[i], nums[j]));
            }
        }


        Set<List<Integer>> result = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            for(int j = i +1; j < nums.length; j++) {
                int sum = nums[i] + nums[j];
                int remainingSum = target - sum;

                Set<Pair> pairs = map.get(remainingSum);

                for(Pair pair : pairs) {
                    List<Integer> list = new ArrayList<>();

                    if (nums[i] == pair.x && nums[j] == pair.y) {
                        continue;
                    }
                    if (nums[i] == pair.y && nums[j] == pair.x) {
                        continue;
                    }


                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(pair.x);
                    list.add(pair.y);

                    Collections.sort(list);
                    result.add(list);
                }
            }
        }

        return new ArrayList<>(result);
    }

    class RandomizedSet {

        HashSet<Integer> set;

        /** Initialize your data structure here. */
        public RandomizedSet() {
            set = new HashSet<>();
        }

        /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
        public boolean insert(int val) {
            return set.add(val);
        }

        /** Removes a value from the set. Returns true if the set contained the specified element. */
        public boolean remove(int val) {
            return set.remove(val);
        }

        /** Get a random element from the set. */
        public int getRandom() {
            int size = set.size();

            int index = (int) (Math.random() * size);

            List<Integer> list = new ArrayList<>(set);
            return list.get(index);
        }
    }

    class Twitter {

        private Map<Integer, List<Integer>> followersByUserIdMap;
        private Map<Integer, List<Integer>> postsByUserIdMap;
        private Map<Integer, List<Integer>> homeTimelineMap;

        /** Initialize your data structure here. */
        public Twitter() {

        }

        /** Compose a new tweet. */
        public void postTweet(int userId, int tweetId) {

        }

        /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
        public List<Integer> getNewsFeed(int userId) {
            return new ArrayList<>();
        }

        /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
        public void follow(int followerId, int followeeId) {

        }

        /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
        public void unfollow(int followerId, int followeeId) {

        }
    }
}
