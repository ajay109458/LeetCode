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

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        floodFill(image, sr, sc, image[sr][sc], newColor);
        return image;
    }

    private int[][] directions = {
            {0, 1},
            {1, 0},
            {0, -1},
            {-1, 0}
    };

    private void floodFill(int[][] image, int sr, int sc, int prevColor, int newColor) {


        image[sr][sc] = newColor;

        for(int[] dir : directions) {
            int x = sr + dir[0];
            int y = sr + dir[1];

            if (x >= 0 && x < image.length && y >= 0 && y < image[0].length && image[x][y] == prevColor) {
                floodFill(image, x, y, prevColor, newColor);
            }
        }
    }

}
