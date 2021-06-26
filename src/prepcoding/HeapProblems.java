package prepcoding;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class HeapProblems {

    public static void printKLargest(int[] arr, int k) {
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

        while(!pq.isEmpty()) {
            System.out.println(pq.remove());
        }
    }


    public static List<Integer> printKLargestElement(int[] arr, int K) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        for(int num : arr) {
            if (priorityQueue.size() < K) {
                priorityQueue.add(num);
            } else {
                if (priorityQueue.peek() < num) {
                    priorityQueue.remove();
                    priorityQueue.add(num);
                }
            }
        }

        List<Integer> result = new ArrayList<>();
        while(!priorityQueue.isEmpty()) {
            result.add(priorityQueue.remove());
        }

        return result;
    }


    public static void sortArrayK(int[] arr, int k) {
        if (arr.length == 0) {
            return;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i = 0; i < k; i++) {
            pq.add(arr[i]);
        }

        int index = 0;
        for(int i = k; i < arr.length; i++) {
            pq.add(arr[i]);
            arr[index++] = pq.remove();
        }

        while(!pq.isEmpty()) {
            arr[index++] = pq.remove();
        }
    }

    /**
    * Explain it with an example of funnel
    * */
    public static void sortArray(int[] arr, int k) {

        if (arr.length == 0) {
            return;
        }

        int index = 0;

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        int i = 0;
        for(i = 0; i <= k && i < arr.length; i++) {
            priorityQueue.add(arr[i]);
        }

        while(i < arr.length) {
            arr[index++] = priorityQueue.remove();
        }

        while(!priorityQueue.isEmpty()) {
            arr[index++] = priorityQueue.remove();
        }
    }

    class StreamMedian {

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b, a));

        public void insert(int num) {
            maxHeap.add(num);

            if (maxHeap.size() > minHeap.size() + 1) {
                minHeap.add(maxHeap.remove());
            }
        }

        public double getMedian() {
            if (maxHeap.size() > minHeap.size()) {
                return maxHeap.peek();
            } else {
                return (maxHeap.peek() + minHeap.peek())/2;
            }
        }
    }

    private static class ListPair {
        int val;
        int listIndex;
        int elementIndex;

        public ListPair(int val, int listIndex, int elementIndex) {
            this.val = val;
            this.listIndex = listIndex;
            this.elementIndex = elementIndex;
        }
    }


    public static List<Integer> mergeKSortedList(List<Integer>[] arr) {
        List<Integer> result = new ArrayList<>();

        PriorityQueue<ListPair> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.val, b.val));

        int k = arr.length;

        for(int i = 0; i < k; i++) {
            ListPair lp = new ListPair(arr[i].get(0), i, 0);
            pq.add(lp);
        }

        while(!pq.isEmpty()) {
            ListPair lp = pq.remove();

            result.add(lp.val);

            if (lp.elementIndex + 1 < arr[lp.listIndex].size()) {
                List<Integer> list = arr[lp.listIndex];
                pq.add(new ListPair(list.get(lp.elementIndex + 1), lp.listIndex, lp.elementIndex + 1));
            }
        }

        return result;
    }

    public static List<Integer> mergeKSortedArrayList(List<Integer>[] arr) {
        int k = arr.length;

        List<Integer> result = new ArrayList<>();

        PriorityQueue<ListPair> priorityQueue = new PriorityQueue<>((a, b) -> Integer.compare(a.val, b.val));

        for(int i = 0; i < k; i++) {
            int elementIndex = 0;
            if (elementIndex < arr[i].size()) {
                priorityQueue.add(new ListPair(arr[i].get(elementIndex), i, elementIndex));
            }
        }

        while(!priorityQueue.isEmpty()) {
            ListPair rem = priorityQueue.remove();

            result.add(rem.val);

            int elementIndex = rem.elementIndex + 1;

            if ( elementIndex < arr[rem.listIndex].size()) {
                priorityQueue.add(new ListPair(arr[rem.listIndex].get(elementIndex), rem.listIndex, elementIndex));
            }
        }

        return result;
    }

    static class MyPriorityQueue {

        ArrayList<Integer> data;

        public int getParent(int i) {
            if (i == 0) {
                return -1;
            }

            return i/2;
        }

        public int getLeftChild(int i) {
            if (2 * i >= data.size()) {
                return -1;
            }

            return 2 * i + 1;
        }

        public int getRightChild(int i) {
            if (2 * i + 1 >= data.size()) {
                return -1;
            }

            return 2 * i + 2;
        }

        public MyPriorityQueue() {
            data = new ArrayList<>();
        }

        public void add(int val) {
            data.add(val);

            int lastIndex = data.size() - 1;
            int parentIndex = getParent(lastIndex);

            while( parentIndex != -1 && data.get(parentIndex) < data.get(lastIndex)) {
                swap(data, parentIndex, lastIndex);
                lastIndex = parentIndex;
                parentIndex = getParent(lastIndex);
            }
        }

        private void swap(List<Integer> list, int i, int j) {
            int temp = data.get(i);
            data.set(i, data.get(j));
            data.set(i, temp);
        }

        private void heapify(int index) {
            int largest = index;

            int leftIndex = getLeftChild(index);
            if (leftIndex != -1 && data.get(leftIndex) > data.get(largest)) {
                swap(data, largest, leftIndex);
            }

            int rightIndex = getRightChild(index);
            if (rightIndex != -1 && data.get(rightIndex) > data.get(largest)) {
                swap(data, largest, rightIndex);
            }

            if (largest != index) {
                heapify(largest);
            }
        }

        public int remove() {
            int lastElement = data.get(data.size() - 1);
            data.remove(data.size() - 1);
            int val = data.get(0); // check if array is empty
            data.set(0, lastElement);
            return val;
        }

        public int peek() {

            if (data.isEmpty())
                return -1;

            return data.get(0);
        }

        public int size() {
            return data.size();
        }
    }
}
