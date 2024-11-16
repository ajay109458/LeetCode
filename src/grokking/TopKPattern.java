package grokking;

import prepcoding.BinaryTree;
import utils.Pair;
import utils.PairSet;

import java.util.*;

public class TopKPattern {

    public int[] topKFrequent(int[] nums, int k) {

        if (nums.length == 0 || k == 0)
            return new int[0];

        Map<Integer, Integer> map = new HashMap<>();
        Arrays.stream(nums).forEach(num -> map.put(num, map.getOrDefault(num, 0) + 1));

        List<Integer> result = new ArrayList<>();
        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.y));

        map.forEach((key, value) -> {
            if (pq.size() < k) {
                pq.add(new Pair(key, value));
            } else {
                if (pq.peek().y < value) {
                    pq.poll();
                    pq.add(new Pair(key, value));
                }
            }
        });


        while (!pq.isEmpty()) {
            result.add(pq.poll().x);
        }

        return result.stream().mapToInt(i -> i).toArray();
    }

    public static List<Integer> findKLargestElements(int[] nums, int k) {
        if (nums.length == 0 || k == 0)
            return new ArrayList<>();

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        Arrays.stream(nums).forEach(num -> {
            if (pq.size() < k) {
                pq.add(num);
            } else {
                if (pq.peek() < num) {
                    pq.poll();
                } else {
                    pq.add(num);
                }
            }
        });

        List<Integer> result = new ArrayList<>();

        while(!pq.isEmpty()) {
            result.add(pq.poll());
        }

        return result;
    }

    public static List<Integer> findKLargest(int[] arr, int k) {
        List<Integer> result = new ArrayList<>();

        if (arr.length == 0 || k == 0) {
            return result;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int val : arr) {
            if (pq.size() < k) {
                pq.add(val);
            } else {
                if (val > pq.peek()) {
                    pq.remove();
                    pq.add(val);
                }
            }
        }

        while(!pq.isEmpty())
            result.add(pq.remove());

        return result;
    }


    public static List<Integer> getKLargest(int[] arr, int K) {

        List<Integer> result = new ArrayList<>();

        if (K == 0 || arr.length == 0)
            return result;

        // Min priority queue
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        int i = 0;
        for(i = 0; i < Math.min(K, arr.length); i++) {
            pq.add(arr[i]);
        }

        while(i < arr.length) {
            if (pq.peek() < arr[i]) {
                pq.remove();
                pq.add(arr[i]);
            }
            i++;
        }

        while(!pq.isEmpty()) {
            result.add(pq.remove());
        }

        return result;
    }

    public static int getKthSmallestElement(int[] arr, int k) {
        if (arr.length == 0 || k == 0 ) {
            return -1;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> Integer.compare(b, a));

        for(int val : arr) {
            if (pq.size() < k) {
                pq.add(val);
            } else {
                if (val < pq.peek()) {
                    pq.remove();
                    pq.add(val);
                }
            }
        }

        if (pq.isEmpty())
            return -1;

        return pq.peek();
    }

    public static int getKthSmallest(int[] arr, int K) {
        if (arr.length == 0 || K == 0)
            return -1;

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> Integer.compare(b, a));

        int i = 0;
        while(i < K && i < arr.length) {
            pq.add(arr[i++]);
        }

        while(i < arr.length) {
            if (pq.peek() > arr[i]) {
                pq.remove();
                pq.add(arr[i]);
            }
            i++;
        }

        if (pq.isEmpty()) {
            return  -1;
        }

        return pq.remove();
    }

    public static class Bike {
        public int x;
        public int y;

        public Bike(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Bike{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }

        @Override
        public boolean equals(Object obj) {

            Bike b = (Bike)obj;

            return this.x == b.x && this.y == b.y;
        }
    }

    private static double distanceFromOrigin(Bike bike) {
        return Math.sqrt(Math.pow(bike.x, 2) + Math.pow(bike.y, 2));
    }

    public static List<Bike> getKClosestBikesToOrigin(List<Bike> bikes, int K) {
        List<Bike> result = new ArrayList<>();

        if (bikes.size() == 0 || K == 0)
            return result;

        PriorityQueue<Bike> pq = new PriorityQueue<>((b1, b2) -> Double.compare(distanceFromOrigin(b2), distanceFromOrigin(b1)));

        int i = 0;
        while(i < K && i < bikes.size()) {
            pq.add(bikes.get(i));
            i++;
        }

        while (i < bikes.size()) {
            if (distanceFromOrigin(bikes.get(i)) < distanceFromOrigin(pq.peek())) {
                pq.remove();
                pq.add(bikes.get(i));
            }
            i++;
        }

        while(!pq.isEmpty()) {
            result.add(pq.remove());
        }

        return result;
    }

    public static int ropeConnectingCost(int[] arr) {

        if (arr.length < 2)
            return 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int val : arr) {
            pq.add(val);
        }

        int totalCost = 0;
        while(pq.size() > 1) {
            int a = pq.remove();
            int b = pq.remove();

            totalCost += (a + b);
            pq.add(a+b);
        }

        return totalCost;
    }

    public static List<Integer> getTopKFreqElements(int[] arr, int k) {
        Map<Integer, Integer> freqMap = new HashMap<>();

        for(int val : arr) {
            int count = freqMap.getOrDefault(val, 0);
            freqMap.put(val, count + 1);
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.y, b.y));

        for(Map.Entry<Integer, Integer> entry: freqMap.entrySet()) {
            if (pq.size() < k) {
                pq.add(new Pair(entry.getKey(), entry.getValue()));
            } else {
                if (pq.peek().y < entry.getValue()) {
                    pq.remove();
                    pq.add(new Pair(entry.getKey(), entry.getValue()));
                }
            }
        }

        List<Integer> result = new ArrayList<>();
        while(!pq.isEmpty()) {
            result.add(pq.peek().x);
            pq.remove();
        }

        return result;
    }

    public static List<Integer> KTopFrequencyElements(int[] nums, int K) {

        Map<Integer, Integer> map = new HashMap<>();
        for(int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        List<Pair> pairs = new ArrayList<>();
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            pairs.add(new Pair(entry.getKey(), entry.getValue()));
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>((x, y) -> Integer.compare(x.y, y.y));

        int i = 0 ;
        while(i < K && i < pairs.size()) {
            pq.add(pairs.get(i++));
        }

        while(i < pairs.size()) {
            if (pairs.get(i).y > pq.peek().y) {
                pq.remove();
                pq.add(pairs.get(i));
            }
        }

        List<Integer> result = new ArrayList<>();

        while(!pq.isEmpty()) {
            result.add(pq.remove().x);
        }

        return result;
    }

    public static String sortByFreq(String input) {
        Map<Character, Integer> freqMap = new HashMap<>();

        for (char ch : input.toCharArray()) {
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }

        List<Map.Entry<Character, Integer>> entrySet = new ArrayList<>(freqMap.entrySet());
        Collections.sort(entrySet, (a, b) -> Integer.compare(b.getValue(), a.getValue()));

        StringBuilder builder = new StringBuilder();

        for(Map.Entry<Character, Integer> entry : entrySet) {

            int k = entry.getValue();

            while(k-- > 0) {
                builder.append(entry.getKey());
            }
        }

        return builder.toString();
    }

    static class KthLargestInStream {

        PriorityQueue<Integer> pq;
        int k;

        public KthLargestInStream(int[] arr, int k) {
            pq = new PriorityQueue<>();
            this.k = k;

            for(int val : arr) {
                add(val);
            }
        }

        public int add(int num) {
            if (pq.size() < k) {
                pq.add(num);
            } else {
                if (pq.peek() < num) {
                    pq.remove();
                    pq.add(num);
                }
            }

            return pq.peek();
        }
    }

    public static List<Integer> getKClosestNumbers(int[] arr, int K, int X) {

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> Integer.compare(Math.abs(b-X), Math.abs(a-X)));

        List<Integer> result = new ArrayList<>();

        for(int val  : arr) {
            if (pq.size() < K) {
                pq.add(val);
            } else {
                if (Math.abs(val - X) < Math.abs(pq.peek() - X)) {
                    pq.remove();
                    pq.add(val);
                }
            }
        }

        while(!pq.isEmpty()) {
            result.add(pq.remove());
        }

        return result;
    }

    public static int findMaxDistinctElements(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new HashMap<>();

        for(int val : nums) {
            freqMap.put(val, freqMap.getOrDefault(val, 0) +1);
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.y, b.y));

        int count = 0;
        for(Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {

            if (entry.getValue() == 1) {
                count++;
            } else {
                pq.add(new Pair(entry.getKey(), entry.getValue()));
            }
        }

        while(!pq.isEmpty() && k > 0) {
            Pair pair = pq.remove();
            k -= (pair.y -1);
            if (k >= 0) {
                count++;
            }
        }

        if (k >= 0) {
            count -= k;
        }

        return count;
    }

    public static int findSumOfElements(int[] nums, int k1, int k2) {

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int val : nums) {
            pq.add(val);
        }

        for(int i = 0; i < k1; i++) {
            pq.remove();
        }

        int sum = 0;

        for(int i = 0; i < k2 - k1; i++) {
            sum += pq.remove();
        }

        return sum;
    }

    public static String reorganizeString(String input, int k) {
        if (k <= 1) {
            return input;
        }

        Map<Character, Integer> freqMap = new HashMap<>();
        for(char ch : input.toCharArray()) {
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> Integer.compare(b.y, a.y));

        for(Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            pq.add(new Pair(entry.getKey(), entry.getValue()));
        }

        Queue<Pair> queue = new LinkedList<>();

        StringBuilder builder = new StringBuilder();

        while(!pq.isEmpty()) {
            Pair rem = pq.remove();

            builder.append((char)rem.x);
            rem.y--;

            if (rem.y > 0) {
                queue.add(rem);
            }

            if (queue.size() == k) {
                pq.add(queue.remove());
            }
        }

        System.out.println(queue.size());

        return builder.toString();
    }

}
