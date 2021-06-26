package sdesheet;


import java.util.*;

public class Day14 {

    public int orangesRotting(int[][] grid) {

        int m = grid.length;

        if (m == 0)
            return 0;

        int n = grid[0].length;

        Queue<OrangeNode> queue = new LinkedList<>();

        boolean[][] visited = new boolean[m][n];

        for(int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 2) {
                    queue.add(new OrangeNode(i, j, 0));
                    visited[i][j] = true;
                }
            }
        }

        int max = 0;

        while(!queue.isEmpty()) {
            OrangeNode curr = queue.poll();

            max = Math.max(max, curr.level);

            for(int[] dir : directions) {
                int x = curr.x + dir[0];
                int y = curr.y + dir[1];

                if (x >= 0 && x < grid.length && y >= 0 && y < grid.length && grid[x][y] == 2 && !visited[x][y]) {
                    visited[x][y] = true;
                    queue.add(new OrangeNode(x, y, curr.level + 1));
                }
            }

        }

        return max;

    }

    static int[][]  directions = {
            {0, 1},
            {1, 0},
            {-1, 0},
            {0, -1}
    };

    private static class OrangeNode {
        public int x;
        public int y;
        public int level;

        public OrangeNode(int x, int y, int level) {
            this.x = x;
            this.y = y;
            this.level = level;
        }
    }

    public static class MinStack {

        Stack<Integer> stack = new Stack<>();
        List<Integer> arrayList = new ArrayList<>();

        public Integer pop() {
            arrayList.remove(stack.size()-1);
            return stack.pop();

        }

        public void push(int val) {

            int size = stack.size();

            if (size == 0) {
                arrayList.add(size, val);
            } else {
                if (stack.peek() < val) {
                    arrayList.add(size, stack.peek());
                } else {
                    arrayList.add(size, val);
                }
            }

            stack.push(val);

        }

        public int getMin() {
            return arrayList.get(stack.size() - 1);
        }

    }

    public static void printMaxSlidingWindow(int[] arr, int k) {
        Stack<Integer> stack = new Stack<>();
        int[] nge = new int[arr.length];

        for(int i = arr.length -2 ; i >= 0; i--) {
            while(!stack.isEmpty() && arr[i] >= stack.peek()) {
                stack.pop();
            }

            if (stack.isEmpty()) {
                nge[i] = arr.length;
            } else {
                nge[i] = stack.peek();
            }
        }

        for (int i = 0; i <= arr.length-k; i++) {
            int j = i;

            while (nge[j] < i + k) {
                j = nge[j];
            }

            System.out.println(arr[j]);
        }
    }

}
