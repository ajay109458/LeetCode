package graph;

import java.util.*;

public class GraphProblem {

    private HashMap<Node, Node> visited = new HashMap<>();

    public Node cloneGraph(Node node) {

        if (node == null)
            return null;

        if (visited.containsKey(node))
            return visited.get(node);

        Node clonedNode = new Node(node.val, new ArrayList<>());

        visited.put(node, clonedNode);

        for(Node neighbor : node.neighbors) {
            clonedNode.neighbors.add(cloneGraph(neighbor));
        }

        return clonedNode;
    }

    public String alienOrder(String[] words) {
        int[] indegree = new int[26]; //
        Map<Character, Set<Character>> g = new HashMap<>();
        buildGraph(g, words, indegree);
        return bfsWords(g, indegree);
    }

    public String bfsWords(Map<Character, Set<Character>> g, int[] indegree) {
        StringBuilder sb = new StringBuilder();
        int totalChars = g.size();


        Queue<Character> queue =  new LinkedList<>();

        for(char c : g.keySet()) {
            if (indegree[c - 'a'] == 0) {
                sb.append(c);
                queue.offer(c);
            }
        }

        while(!queue.isEmpty()) {
            char cur = queue.poll();

            if (g.get(cur) == null || g.get(cur).size() > 0) {
                continue;
            }

            for(char neig : g.get(cur)) {
                indegree[neig - 'a']--;
                if (indegree[neig - 'a'] == 0) {
                    queue.offer(neig);
                    sb.append(neig);
                }
            }
        }

        return sb.length() == totalChars ? sb.toString() : "";
    }

    public void buildGraph(Map<Character, Set<Character>> g, String[] words, int[] indegree) {
        for(String word : words) {
            for (char c : word.toCharArray()) {
                g.putIfAbsent(c, new HashSet<>());
            }
        }

        for(int i = 1; i < words.length; i++) {
            String first = words[i-1];
            String second = words[i];

            int len = Math.min(first.length(), second.length());

            for (int j = 0; j < len; j++) {
                if (first.charAt(j) != second.charAt(j)) {
                    char out = first.charAt(j);
                    char in = second.charAt(j);

                    if (!g.get(out).contains(in)) {
                        g.get(out).add(in);
                        indegree[in-'a']++;
                    }

                    break;
                }
            }
        }




    }


    public boolean validTree1(int n, int[][] edges) {

        DisjointSet ds = new DisjointSet();

        ds.makeSet(n);

        for(int[] edge : edges) {
            int x = ds.find(edge[0]);
            int y = ds.find(edge[1]);

            if (x == y)
                return false;

            ds.union(x, y);
        }

        return true;

    }

    public boolean validTree(int n, int[][] edges) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        populateMap(n, edges, map);

        boolean[] visited = new boolean[n];
        int[] parents = new int[n];
        Arrays.fill(parents, -1);

        boolean result = false;

        for(int i = 0; i < n; i++) {
            if (!visited[i])
                result = result || bfsValidTree(map, visited, parents, 0);

        }

        return result;
    }

    private boolean bfsValidTree(Map<Integer, List<Integer>> map, boolean[]  visited, int[] parents, int curr) {
        List<Integer> children = map.get(curr);

        if (visited[curr]) {
            return true;
        }

        visited[curr] = true;

        if (children == null) {
            return false;
        }

        boolean result = false;
        for(Integer child : children) {
            if (parents[curr] != child)
                result = result || bfsValidTree(map, visited, parents, curr);
        }

        return result;
    }

    public int[] canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        populateMap(numCourses, prerequisites, map);

        int[] visited = new int[numCourses];

        boolean result = true;

        List<Integer> path = new ArrayList<>();

        for(int i = 0; i < numCourses; i++) {

            if (visited[i] == 0) {
                result = result && bfsCourses(map, visited, i, path);
            } else if (visited[i] == 1) {
                return new int[0];
            }
        }

        if (path.size() == numCourses) {
            int[] pathArray = new int[numCourses];

            for(int i = 0; i < path.size(); i++) {
                pathArray[i] = path.get(i);
            }

            return pathArray;
        }

        return new int[0];
    }

    private boolean bfsCourses(Map<Integer, List<Integer>> map, int[]  visited, int curr, List<Integer> path) {
        List<Integer> children = map.get(curr);

        if (visited[curr] == 2) {
            return true;
        } else if (visited[curr] == 1) {
            // cyclic dependencies
            return false;
        }

        visited[curr] = 1;

        if (children == null) {
            visited[curr] = 2;
            path.add(curr);
            return true;
        }

        boolean result = true;
        for(Integer child : children) {
            result = result && bfsCourses(map, visited, child, path);
        }

        visited[curr] = 2;
        path.add(curr);

        return result;
    }

    private void populateMap(int num, int[][] prereq, Map<Integer, List<Integer>> map) {
        for(int[] p : prereq) {
            List<Integer> list = map.get(p[0]);
            if (list == null) {
                list = new ArrayList<>();
                map.put(p[0], list);
            }

            list.add(p[1]);

            list = map.get(p[1]);
            if (list == null) {
                list = new ArrayList<>();
                map.put(p[1], list);
            }

            list.add(p[0]);
        }
    }

    public static void topologicalSort(Map<Integer, List<Integer>> graph) {

        int n = graph.size();
        boolean[] visited = new boolean[n];
        Stack<Integer> stack = new Stack<>();

        for(int node : graph.keySet()) {
            if (!visited[node]) {
                topologicalSortUtils(node, graph, visited, stack);
            }
        }

        while(!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
        System.out.println();
    }

    public static void topologicalSortUtils(int node, Map<Integer, List<Integer>> graph, boolean[] visited, Stack<Integer> s) {

        visited[node] = true;

        List<Integer> children = graph.get(node);

        if (children != null) {
            for(Integer child : children) {
                if (!visited[child]) {
                    topologicalSortUtils(child, graph, visited, s);
                }
            }
        }

        s.push(node);
    }

    public static void Dijkstra(Map<Integer, List<Node>> graph, int source) {
        int n = graph.size();

        int[] dis = new int[n];
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[source] = 0;

        boolean[] visited = new boolean[n];

        while(true) {

            // find minimum distance node
            Integer node = null;
            for(int i = 0; i < dis.length; i++) {
                if (!visited[i]) {
                    if (node == null) {
                        node = i;
                    } else {
                        if (dis[i] < dis[node]) {
                            node = i;
                        }
                    }
                }
            }

            if (node == null)
                break;

            // get all neighbors
            List<Node> neighbors = graph.get(node);
            if (neighbors != null) {
                for(Node neighbor : neighbors) {
                    if (dis[node] + neighbor.cost < dis[neighbor.val]) {
                        dis[neighbor.val] = dis[node] + neighbor.cost;
                    }
                }
            }
        }
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        Set<String> set = new HashSet<>(wordList);

        if (!set.contains(endWord))
            return 0;

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        int level = 1;

        while(!queue.isEmpty()) {

            int size  = queue.size();

            for(int i = 0; i < size; i++) {
                String currString = queue.poll();

                char[] charArr = currString.toCharArray();

                for(int j = 0; j < charArr.length; j++) {
                    char orignalChar = charArr[j];

                    for(char c = 'a'; c <= 'z'; c++) {
                        if (c == orignalChar)
                            continue;

                        charArr[j] = c;

                        String newWord = String.valueOf(charArr);

                        if (newWord.equals(endWord)) {
                            return level + 1;
                        }

                        if (set.contains(newWord)) {
                            queue.offer(newWord);
                            set.remove(newWord);
                        }
                    }
                }
            }

            level++;
        }

        return 0;
    }

    Map<Integer, Integer> f = new HashMap<>();
    int islands = 0;

    public int removeStones(int[][] stones) {
        for (int i = 0; i < stones.length; ++i)
            union(stones[i][0], ~stones[i][1]);
        return stones.length - islands;
    }

    public int find(int x) {
        if (f.putIfAbsent(x, x) == null)
            islands++;
        if (x != f.get(x))
            f.put(x, find(f.get(x)));
        return f.get(x);
    }

    public void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x != y) {
            f.put(x, y);
            islands--;
        }
    }

    public int findCelebrity(int n, boolean[][] graph) {
        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < n; i++)
            stack.push(i);

        while(stack.size() > 1) {
            int f = stack.pop();
            int s = stack.pop();

            if (graph[f][s]) {
                stack.push(s);
            } else if (!graph[f][s]) {
                stack.push(f);
            }
        }

        if (stack.isEmpty())
            return -1;

        int celebrity = stack.pop();

        for(int i = 0; i < graph[0].length; i++) {
            if (graph[celebrity][i]) {
                return -1;
            }
        }

        for(int i = 0; i < graph.length; i++) {
            if (celebrity != i) {
                if (!graph[i][celebrity]) {
                    return -1;
                }
            }
        }

        return celebrity;
    }


}
