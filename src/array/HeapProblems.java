package array;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class HeapProblems {

    public List<Double> findMedian(int[] arr) {

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(Integer::compare);

        List<Double> result = new ArrayList<>();

        for(int val : arr) {
            insertNum(maxHeap, minHeap, val);
            result.add(findMedian(maxHeap, minHeap));
        }

        return result;
    }

    private void insertNum(PriorityQueue<Integer> maxHeap, PriorityQueue<Integer> minHeap, int num) {
        if (maxHeap.isEmpty() || maxHeap.peek() >= num)
            maxHeap.add(num);
        else
            minHeap.add(num);

        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.add(maxHeap.poll());
        } else if (maxHeap.size() < minHeap.size()) {
            maxHeap.add(minHeap.poll());
        }
    }

    public double findMedian(PriorityQueue<Integer> maxHeap, PriorityQueue<Integer> minHeap) {
        if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek();
        }

        return (maxHeap.peek() + minHeap.peek())/2;
    }

}
