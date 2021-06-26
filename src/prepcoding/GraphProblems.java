package prepcoding;

import utils.Pair;

import java.util.*;

public class GraphProblems {

    public static List<Integer>[] buildGraph(int vertex, int[][] edges) {
        List<Integer>[] graph = new List[vertex];
        for(int i = 0; i < vertex; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            graph[u].add(v);
            graph[v].add(u);
        }

        return graph;
    }


    public static boolean isPathExist(List<Integer>[] graph, int src, int dst) {
        int n = graph.length;

        boolean[] isVisited = new boolean[n];

        return isPathExistInternal(graph, src, dst, isVisited);
    }

    private static boolean isPathExistInternal(List<Integer>[] graph, int v, int dst, boolean[] visited) {
        if (v == dst) {
            return true;
        }

        if (visited[v] == true) {
            return false;
        }

        visited[v] = true;

        for(int neigh : graph[v]) {
            if (!visited[neigh]) {
                boolean isPathExist = isPathExistInternal(graph, neigh, dst, visited);
                if (isPathExist)
                    return true;
            }
        }

        return false;
    }

    public static void printAllPaths(List<Integer>[] graph, int src, int dst) {

        int n = graph.length;

        boolean[] visited = new boolean[n];
        printAllPathInternal(graph, src, dst, visited, "");
    }

    private static void printAllPathInternal(List<Integer>[] graph, int v, int dst, boolean[] visited, String psf) {

        if (v == dst) {
            // print the path
            psf += v;
            System.out.println(psf);
            return;
        }

        if (visited[v]) {
            return;
        }

        visited[v] = true;
        for(int neigh : graph[v]) {
            if (!visited[neigh]) {
                printAllPathInternal(graph, neigh, dst, visited, psf + v);
            }
        }
        visited[v] = false;
    }

    public static int getConnectedComponents(List<Integer>[] graph) {

        int n = graph.length;
        boolean[] visited = new boolean[n];

        int count = 0;
        for(int v = 0; v < n; v++) {

            if(!visited[v]) {
                count++;
                visitAllComponents(graph, v, visited);
            }

        }

        return count;
    }

    private static  void visitAllComponents(List<Integer>[] graph, int vertex, boolean[] visited) {

        if (visited[vertex])
            return;

        visited[vertex] = true;

        for (int neigh : graph[vertex]) {
            if (!visited[vertex]) {
                visitAllComponents(graph, neigh, visited);
            }
        }
    }

    public static boolean isGraphConnected(List<Integer>[] graph) {
        int countConnectedComponents = getConnectedComponents(graph);
        return countConnectedComponents == 1;
    }

    public static int maxIslandArea(int[][] matrix) {

        int m = matrix.length;
        int n = matrix[0].length;

        if (m == 0 || n == 0) {
            return 0;
        }

        boolean[][] visited = new boolean[m][n];

        int max = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    int area = islandArea(matrix, i, j, visited);
                    max = Math.max(area, max);
                }
            }
        }

        return max;
    }

    public static int islandArea(int[][] matrix, int i, int j, boolean[][] visited) {
        if (i < 0 || i > matrix.length) {
            return 0;
        }

        if (j < 0 || j > matrix[0].length) {
            return 0;
        }

        if (matrix[i][j] == 0) {
            return 0;
        }

        if (visited[i][j]) {
            return 0;
        }

        visited[i][j] = true;

        return 1 + islandArea(matrix, i + 1, j, visited)
                + islandArea(matrix, i -1, j, visited)
                + islandArea(matrix, i , j + 1, visited)
                + islandArea(matrix, i , j-1, visited);
    }


    public static int getPerfectFriendPairProblem(List<Integer>[] graph) {
        int n = graph.length;

        boolean[] visited = new boolean[n];

        List<List<Integer>> result = new ArrayList<>();

        for(int v = 0; v < n; v++) {
            if(!visited[v]) {
                List<Integer> list = new ArrayList<>();
                populatePersonsInEachComponent(graph, v, visited, list);
                result.add(list);
            }
        }

        int count = 0;

        for(int i = 0; i < result.size(); i++) {
            for(int j = i + 1; j < result.size(); j++) {
                count += (result.get(i).size() * result.get(j).size());
            }
        }

        return count;
    }

    private static void populatePersonsInEachComponent(List<Integer>[] graph, int vertex, boolean[] visited, List<Integer> list) {

        if (visited[vertex]) {
            return;
        }

        visited[vertex] = true;
        list.add(vertex);

        for(int neigh : graph[vertex]) {
            if (!visited[vertex]) {
                populatePersonsInEachComponent(graph, neigh, visited, list);
            }
        }
    }

    public static void printAllHamiltonianPaths(List<Integer>[] graph, int src) {
        int n = graph.length;
        printHamiltonianPath(graph, src, src, new HashSet<>(), "");
    }

    private static void printHamiltonianPath(List<Integer>[] graph, int src, int v, HashSet<Integer> visited, String psf) {
        if(visited.contains(v))
            return;

        visited.add(v);

        if (visited.size() == graph.length) {
            psf += v;

            // Detect cycle
            for(int neigh : graph[v]) {
                if (visited.contains(neigh)) {
                    System.out.println("hamiltonian cycle");
                }
            }

            System.out.println(psf);
            return;
        }

        for(int neigh : graph[v]) {
            if (!visited.contains(neigh)) {
                printHamiltonianPath(graph, src, neigh, visited, psf + v);
            }
        }

        visited.remove(Integer.valueOf(v));
    }

    public static void printKnightTourPaths(int[][] board, int x, int y) {

        int m = board.length;
        int n = board[0].length;

        boolean[][] visited = new boolean[m][n];

        printKnightTourPathInternal(board, x, y, visited, 1);
    }

    private static int[][] DIRECTIONS = { {2, 1}, {2, -1}, {-2, 1}, {-2, -1}, {1, 2}, {1, -2}, {-1, 2}, {-1, -2}};

    private static void printKnightTourPathInternal(int[][] graph, int i, int j, boolean[][] visited, int count) {

        if (count == graph.length * graph[0].length) {
            System.out.print("Full grid visited");
            return;
        }

        if (visited[i][j]) {
            return;
        }

        visited[i][j] = true;

        for(int[] dir : DIRECTIONS) {
            int x = dir[0];
            int y = dir[1];

            if ( x >= 0 && x < graph.length && y >= 0 && y < graph[0].length && !visited[x][y]) {
                printKnightTourPathInternal(graph, x, y, visited, count+1);
            }
        }

        visited[i][j] = false;
    }

    public static void printBFS(List<Integer>[] graph, int src) {

        int n = graph.length;
        boolean[] visited = new boolean[n];

        Queue<Integer> queue = new LinkedList<>();
        queue.add(src);

        while(!queue.isEmpty()) {

            int rem = queue.remove(); // Remove
            visited[rem] = true; // Mark

            // Work
            System.out.print(rem);

            // Add neighbor
            for(int neigh : graph[rem]) {
                if (!visited[neigh]) {
                    queue.add(neigh);
                }
            }
        }
    }

    public static boolean isGraphCycle(List<Integer>[] graph) {

        int n = graph.length;
        boolean[] visited = new boolean[n];

        for(int u = 0; u < graph.length; u++) {
            if (!visited[u]) {
                boolean isCycle = isGraphCycleInternal(graph, u, visited);
                if (isCycle)
                    return true;
            }
        }

        return false;

    }

    public static boolean isGraphCycleInternal(List<Integer>[] graph, int v, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(v);

        while(!queue.isEmpty()) {
            int rem = queue.remove();
            if (visited[rem]) {
                return true;
            }

            visited[rem] = true;

            for(int neigh : graph[rem]) {
                queue.add(neigh);
            }
        }

        return false;
    }

    public static boolean isGraphBipartite(List<Integer>[] graph) {
        int n = graph.length;

        int[] visited = new int[n];

        Arrays.fill(visited, -1);

        for(int u = 0; u < n; u++) {
            if (visited[u] == -1) {
                boolean isBipartite = isGraphBipartiteInternal(graph, u, visited);
                if (!isBipartite)
                    return false;
            }
        }

        return true;
    }

    private static boolean isGraphBipartiteInternal(List<Integer>[] graph, int v, int[] visited) {

        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(v, 0));

        while(!queue.isEmpty()) {
            Pair rem = queue.remove();

            if (visited[rem.x] != -1) {
                if (rem.y != visited[rem.x])
                    return false;
            }

            visited[rem.x] = rem.y;

            for(int neigh : graph[rem.x]) {
                queue.add(new Pair(neigh, rem.y + 1));
            }
        }

        return true;
    }

    public static int countInfectedPeoples(List<Integer>[] graph, int src, int t) {

        int n = graph.length;

        boolean[] visited = new boolean[n];

        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(src, 0));

        int count = 0;

        while(!queue.isEmpty()) {
            Pair pair = queue.remove();
            count++;
            visited[pair.x] = true;

            for(int neigh : graph[pair.x]) {
                if (!visited[neigh] && pair.y + 1 < t) {
                    queue.add(new Pair(neigh, pair.y + 1));
                }
            }
        }

        return count;
    }

    private class Edge {
        int src;
        int dst;
        int weight;
    }

    private static class Node  {
        int node;
        int distTillNode;

        public Node(int node, int distTillNode) {
            this.node = node;
            this.distTillNode = distTillNode;
        }
    }

    public static void findShortestPath(List<Edge>[] graph, int src, int dst) {

        int n = graph.length;

        int[] visited = new int[n];
        Arrays.fill(visited, -1);

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.distTillNode, b.distTillNode));
        pq.add(new Node(src, 0));

        while(!pq.isEmpty()) {
            Node node = pq.remove();

            visited[node.node] = node.distTillNode;

            for(Edge edge : graph[node.node]) {
                if (visited[edge.dst] != -1) {
                    pq.add(new Node(edge.dst, node.distTillNode + edge.weight));
                }
            }
        }

    }

}
