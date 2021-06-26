package grokking;

import java.util.PriorityQueue;

public class TwoHeapPattern {

    private class StreamMedian {

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>();

        public void insert(int num) {
            if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
                maxHeap.add(num);
            } else {
                minHeap.add(num);
            }

            if (maxHeap.size() > minHeap.size() + 1) {
                minHeap.add(maxHeap.remove());
            } else if (minHeap.size() > maxHeap.size()) {
                maxHeap.add(minHeap.remove());
            }
        }

        public int getMedian() {
            if (minHeap.size() == maxHeap.size()) {
                return (minHeap.peek() + maxHeap.peek())/2;
            } else {
                return maxHeap.peek();
            }
        }

    }

    private class SlidingWindowProblem {

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b, a));

        public double[] findSlidingWindowMedian(int[] nums, int k) {
            double[] results = new double[nums.length - k + 1];

            for(int i = 0; i < nums.length; i++) {
                if (maxHeap.size() == 0 || maxHeap.peek() >= nums[i]) {
                    maxHeap.add(nums[i]);
                } else {
                    minHeap.add(nums[i]);
                }

                rebalanceHeap();

                if (i - k + 1 > 0) {
                    if (maxHeap.size() == minHeap.size()) {
                        results[i-k+1] = (maxHeap.peek() + minHeap.peek())/2;
                    } else {
                        results[i-k+1] = maxHeap.peek();
                    }
                }

                int elementToBeRemoved = nums[i-k+1];
                if (elementToBeRemoved <= maxHeap.peek()) {
                    maxHeap.remove(elementToBeRemoved);
                } else {
                    minHeap.remove(elementToBeRemoved);
                }

                rebalanceHeap();
            }

            return results;
        }

        public void rebalanceHeap() {
            if (maxHeap.size() > minHeap.size() + 1) {
                minHeap.add(maxHeap.remove());
            } else if (maxHeap.size() < minHeap.size()) {
                maxHeap.add(minHeap.remove());
            }
        }
    }



}
