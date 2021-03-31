package stack;

import utils.Pair;

import java.util.*;

public class QueueHelper {

    class MovingAverage {

        private Queue<Integer> queue;
        int size = 0;
        int sum = 0;

        /** Initialize your data structure here. */
        public MovingAverage(int size) {
            queue = new LinkedList<>();
            this.size = size;
        }

        public double next(int val) {
            if (queue.size() >= size) {
                int rVal = queue.remove();
                sum -= rVal;
            }

            queue.add(val);
            sum += val;

            return (sum * 1.0) / queue.size();
        }
    }

    class SnakeGame {

        private int width;
        private int height;
        private Queue<Pair> foods;

        int snakeX = 0;
        int snakeY = 0;

        int eaten = 0;

        /** Initialize your data structure here.
         @param width - screen width
         @param height - screen height
         @param food - A list of food positions
         E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
        public SnakeGame(int width, int height, int[][] food) {
            this.width = width;
            this.height = height;

            foods = new LinkedList<>();

            for(int[] f : food) {
                foods.add(new Pair(f[0], f[1]));
            }

            snakeX = 0;
            snakeY = 0;

            eaten = 0;
        }

        /** Moves the snake.
         @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
         @return The game's score after the move. Return -1 if game over.
         Game over when snake crosses the screen boundary or bites its body. */
        public int move(String direction) {
            switch (direction) {
                case "U":
                    snakeY--;
                    break;
                case "L":
                    snakeX--;
                    break;
                case "R":
                    snakeX++;
                    break;
                case "D":
                    snakeY++;
                    break;
            }

            if (snakeX < 0 || snakeX >= width) {
                return -1;
            }

            if (snakeY < 0 || snakeY >= height) {
                return -1;
            }

            if (foods.isEmpty()) {
                //return -1;
            }

            Pair p = foods.peek();
            if (p.x == snakeX && p.y == snakeY) {
                eaten++;
                foods.remove();
            }

            return eaten;
        }
    }

    public static int leastInterval(char[] tasks, int n) {
         Queue<Character>[] tasksQueue = new Queue[26];
         for(char task : tasks) {
             if(tasksQueue[task-'A'] == null) {
                 tasksQueue[task-'A'] = new LinkedList<>();
             }

             tasksQueue[task-'A'].add(task);
         }

         int[] taskLastOffset = new int[26];
        Arrays.fill(taskLastOffset, -1);

        int curr = 0;

        while(true) {
            Character currTask = null;
            int idleTime = Integer.MAX_VALUE;
            // find a task
            for(int i = 0; i <26; i++) {
                char ch = (char) (i);

                if (tasksQueue[ch] == null) {
                    continue;
                }

                if (taskLastOffset[ch] == -1 && !tasksQueue[ch].isEmpty()) {
                    currTask = ch;
                    idleTime = 0;
                    break;
                } else if (taskLastOffset[ch] != -1 && !tasksQueue[ch].isEmpty()) {
                    int diff = curr - taskLastOffset[ch] - 1;
                    if (diff >= n) {
                        currTask = ch;
                        idleTime = 0;
                        break;
                    } else if (diff < n) {
                        if (n - diff < idleTime) {
                            idleTime = n - diff;
                            currTask = ch;
                        }
                    }
                }
            }

            if (currTask == null) {
                break;
            }

            curr += idleTime ;
            tasksQueue[currTask].remove();
            taskLastOffset[currTask] = curr;

            curr += 1;
        }

        return curr;

    }

    public static int leastInterval1(char[] tasks, int n) {

        int[] charMap = new int[26];

        for(char ch : tasks) {
            charMap[ch - 'A']++;
        }

        Arrays.sort(charMap);
        int maxVal = charMap[25] - 1;

        int idleSlots = maxVal * n;

        for (int i = 24; i >= 0; i--) {
            idleSlots -= Math.min(charMap[i], maxVal);
        }

        return idleSlots > 0 ? idleSlots + tasks.length : tasks.length;
    }

    public int[] mostCompetitive(int[] nums, int k) {
        Stack<Integer> stack = new Stack<>();

        int elementsCanBeRemoved = nums.length - k;

        for(int num : nums) {
            while(!stack.isEmpty() && num < stack.peek() && elementsCanBeRemoved > 0) {
                stack.pop();
                elementsCanBeRemoved--;
            }

            stack.push(num);
        }

        while (elementsCanBeRemoved > 0) {
            stack.pop();
            elementsCanBeRemoved--;
        }

        int[] res = new int[k];
        for(int i = k -1; i >=0 ; i--) {
            res[i] = stack.pop();
        }

        return res;
    }

    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        Map<Integer, List<Integer>> childsByParentMap = new HashMap<>();

        for(int i = 0; i < pid.size(); i++) {
            Integer id = pid.get(i);
            Integer parentId = ppid.get(i);

            List<Integer> children = childsByParentMap.get(parentId);
            if (children == null) {
                children = new ArrayList<>();
                childsByParentMap.put(parentId, children);
            }

            children.add(id);
        }

        List<Integer> killed = new ArrayList<>();
        populateChildren(childsByParentMap, killed, kill);
        return killed;
    }

    private void populateChildren(Map<Integer, List<Integer>> map, List<Integer> result, int kill) {

        result.add(kill);

        List<Integer> children = map.get(kill);
        if (children == null) {
            return;
        }

        for(Integer child : children) {
            populateChildren(map, result, child);
        }
    }

    public static int shortestSubarray(int[] A, int K) {
        int n = A.length;

        int[] P = new int[A.length + 1];

        P[0] = 0;
        for(int i = 0; i < n; i++) {
            P[i + 1] = P[i] + A[i];
        }

        Deque<Integer> deque =  new LinkedList<>();
        int ans = Integer.MAX_VALUE;

        for(int i = 0; i < n + 1; i++) {
            while (!deque.isEmpty() && P[i] - P[deque.peekFirst()] >=K){
                ans = Math.min(ans, i - deque.peekFirst());
                deque.removeFirst();
            }

            while(!deque.isEmpty() && P[i] - P[deque.peekLast()] <=0 ) {
                deque.removeLast();
            }

            deque.push(i);
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
