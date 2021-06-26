package prepcoding;

import java.util.*;

public class Graph {

    public static boolean isPath(List<Integer>[] graph, int src, int dst) {
        HashSet<Integer> visited = new HashSet<>();

        return isPathInternal(graph, src, dst, visited);
    }

    private static boolean isPathInternal(List<Integer>[] graph, int src, int dst, HashSet<Integer> visited) {
        if (src == dst) {
            return true;
        }

        for(int neigh : graph[src]) {
            if (!visited.contains(neigh) && isPathInternal(graph, neigh, dst, visited)) {
                return true;
            }
        }

        return false;
    }


    public static void printPathDFS(List<Integer>[] graph) {
        int V = graph.length;

        HashSet<Integer> visitedSet = new HashSet<>();

        for(int v = 0; v < V; v++) {
            if (!visitedSet.contains(v)) {
                printDFSInternal(graph, v, visitedSet);
            }
        }
    }

    public static void printDFSInternal(List<Integer>[] graph, int v, HashSet<Integer> visited) {
        if (visited.contains(v)) {
            return;
        }

        System.out.print(v);

        for(int neigh : graph[v]) {
            if (!visited.contains(neigh)) {
                printDFSInternal(graph, neigh, visited);
            }
        }
    }

    public static void printAllPaths(List<Integer>[] graph, int src, int dst) {
        printAllPathsInternal(graph, src, dst, new HashSet<>(), "");
    }

    private static void printAllPathsInternal(List<Integer>[] graph, int src, int dst, HashSet<Integer> visited, String psf) {
        if (src == dst) {
            System.out.println(psf);
            return;
        }

        visited.add(src);

        for(int neigh : graph[src]) {
            if( !visited.contains(neigh)) {
                printAllPathsInternal(graph, neigh, dst, visited, psf + src);
            }
        }

        visited.remove(Integer.valueOf(src));
    }

    public static int getConnectedComponents(List<Integer>[] graph) {

        HashSet<Integer> visited = new HashSet<>();

        int V = graph.length;

        int componentCounts = 0;

        for(int v = 0; v < V; v++) {

            if (!visited.contains(v)) {
                componentCounts++;
                connectedComponentInternal(graph, v, visited);
            }
        }

        return componentCounts;
    }

    public static void connectedComponentInternal(List<Integer>[] graph, int vertex, HashSet<Integer> visited) {
        visited.add(vertex);

        for(int neigh : graph[vertex]) {
            if (!visited.contains(neigh)) {
                connectedComponentInternal(graph, neigh, visited);
            }
        }
    }

    public static boolean isGraphConnected(List<Integer>[] graph) {

        HashSet<Integer> visited = new HashSet<>();

        int connectedComponents = 0;

        for(int v = 0; v < graph.length; v++) {
            if (!visited.contains(v)) {
                connectedComponents++;
                connectedComponentInternal(graph, v, visited);
            }
        }

        return connectedComponents == 1;
    }

    public static int numberOfIslands(int[][] graph) {

        int m = graph.length;
        if (m == 0) {
            return 0;
        }

        int n = graph[0].length;

        boolean[][] visited = new boolean[m][n];

        int numberOfIslands = 0;

        for(int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    numberOfIslands++;
                    numberOfIslandInternal(graph, i, j, visited);
                }
            }
        }

        return numberOfIslands;
    }

    static int[][] dirs = {{0, 1}, {0, -1}, {1,0}, {-1, 0}};
    private static void numberOfIslandInternal(int[][] graph, int i, int j, boolean[][] visited) {

        visited[i][j] = true;

        for(int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];

            if (x >= 0 && x < graph.length && y >= 0 && y < graph[0].length && !visited[x][y]) {
                numberOfIslandInternal(graph, x, y, visited);
            }
        }
    }

    public static int perfectFriendCounts(List<Integer>[] graph) {
        List<List<Integer>> allComponents = new ArrayList<>();
        HashSet<Integer> visited = new HashSet<>();

        for(int v = 0; v < graph.length; v++) {
            if (!visited.contains(v)) {
                List<Integer> component = new ArrayList<>();
                perfectFriendInternal(graph, v, component, visited);
                allComponents.add(component);
            }
        }

        int pairs = 0;

        for(int i = 0; i < allComponents.size(); i++) {
            for (int j = i + 1; j < allComponents.size(); j++) {
                pairs = allComponents.get(i).size() * allComponents.get(j).size();
            }
        }

        return pairs;
    }

    public static void perfectFriendInternal(List<Integer>[] graph, int vertex, List<Integer> component, HashSet<Integer> visited) {

        visited.add(vertex);
        component.add(vertex);

        for(int neigh : graph[vertex]) {
            if (!visited.contains(neigh)) {
                perfectFriendInternal(graph, neigh, component, visited);
            }
        }
    }

    /**
     * Hamiltonian : Visit all vertex, but no vertex should be visited more than once
     * @param graph
     */
    public static void printHamiltonianGraph(List<Integer>[] graph, int src) {
        HashSet<Integer> visited = new HashSet<>();

        printHamiltonianGraphInternal(graph, src, visited, "");
     }

    private static void printHamiltonianGraphInternal(List<Integer>[] graph, int src, HashSet<Integer> visited, String psf) {

        visited.add(src);

        if (visited.size() ==  graph.length) {
            // all vertex are visited
            System.out.println(psf + src);

            // if any of neigh is source then it is cycle
        }

        for(int neigh : graph[src]) {
            if (!visited.contains(src)) {
                printHamiltonianGraphInternal(graph, src, visited, psf + src);
            }
        }

        visited.remove(Integer.valueOf(src));

    }

    public static void printInOrderTraversal(List<Integer>[] graph, int src) {

        Queue<Integer> queue = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();

        queue.add(src);

        while(!queue.isEmpty()) {

            Integer rem = queue.remove();
            visited.add(rem);

            System.out.println(rem);

            for(int neigh : graph[rem]) {
                if (!visited.contains(neigh))
                    queue.add(neigh);
            }
        }
    }

    public static boolean hasCycle(List<Integer>[] graph) {
        HashSet<Integer> visited = new HashSet<>();

        for(int v = 0; v < graph.length; v++) {
            if (hasCycleInternal(graph, v, visited)) {
                return true;
            }
        }

        return false;
    }

    public static boolean hasCycleInternal(List<Integer>[] graph, int vertex, HashSet<Integer> visited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(vertex);

        while (!queue.isEmpty()) {
            int rem = queue.remove();

            if (visited.contains(rem)) {
                return true;
            }

            visited.add(rem);

            for(int neigh : graph[vertex]) {
                queue.add(neigh);
            }
        }

        return false;
    }

    private static class GraphPair {
        int vertex;
        int level;

        public GraphPair(int v, int l) {
            this.vertex = v;
            this.level = l;
        }
    }

    public static boolean isGraphBipartite(List<Integer>[] graph) {

        int[] visited = new int[graph.length];

        for(int v = 0; v < graph.length; v++) {
            if (!isGraphBipartiteInternal(graph, v, visited)) {
                return false;
            }
        }

        return true;
    }

    private static boolean isGraphBipartiteInternal(List<Integer>[] graph, int vertex, int[] visited) {

        Queue<GraphPair> queue = new LinkedList<>();
        queue.add(new GraphPair(vertex, 0));

        /**
         *  Remove
         *  Mark
         *  Work
         *  Add
         */
        while(!queue.isEmpty()) {
            GraphPair rem = queue.remove();

            if (visited[rem.vertex] != -1) {
                return rem.level != visited[rem.vertex];
            } else {
                visited[rem.vertex] = rem.level;
            }

            for(int neigh : graph[rem.vertex]) {
                queue.add(new GraphPair(rem.vertex, rem.level));
            }
        }

        return true;
    }

    public static int numberOfPeopleAffected(List<Integer>[] graph, int src) {

        int[] visited = new int[graph.length];

        int count = 0;

        Queue<GraphPair> queue = new LinkedList<>();
        queue.add(new GraphPair(src, 0));

        while(!queue.isEmpty()) {

            GraphPair rem = queue.remove();

            if (visited[rem.vertex] != 0) {
                continue;
            }

            visited[rem.vertex] = rem.level;

            for(int neigh : graph[rem.vertex]) {
                if (visited[neigh] == 0) {
                    queue.add(new GraphPair(rem.vertex, rem.level));
                }
            }

        }

        return count;

    }

    private static class Edge {
        int target;
        int wt;

        public Edge(int target, int wt) {
            this.target = target;
            this.wt = wt;
        }
    }

    public static class DNode {
        int vertex;
        int dist;
        String psf;

        public DNode(int vertex, int dist, String psf) {
            this.vertex = vertex;
            this.dist = dist;
            this.psf = psf;
        }
    }

    public static void dijkstra(List<Edge>[] graph, int src) {

        PriorityQueue<DNode> priorityQueue = new PriorityQueue<>((a, b) -> Integer.compare(a.dist, b.dist));
        priorityQueue.add(new DNode(src, 0, ""));

        HashSet<Integer> visited = new HashSet<>();

        while(!priorityQueue.isEmpty()) {
            DNode rem = priorityQueue.remove();

            visited.add(rem.vertex);

            System.out.print(rem.vertex + "@" + rem.psf);

            for(Edge e : graph[rem.vertex]) {
                if (!visited.contains(e.target)) {
                    priorityQueue.add(new DNode(e.target, rem.dist + e.wt, rem.psf + "-" + e.target));
                }
            }
        }
    }

    private static class PrimsEdge {
        int src;
        int dst;
        int wt;

        public PrimsEdge(int src, int dst, int wt) {
            this.src = src;
            this.dst = dst;
            this.wt = wt;
        }
    }

    public static void prims(List<Edge>[] graph) {
        HashSet<Integer> visited = new HashSet<>();

        PriorityQueue<PrimsEdge> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.wt, b.wt));
        // add dummy edge
        pq.add(new PrimsEdge(-1, 0, 0));

        while(!pq.isEmpty()) {
            PrimsEdge rem = pq.remove();

            if (visited.contains(rem.dst)) {
                continue;
            }

            visited.add(rem.dst);

            if(rem.src != -1) {

            }

            for(Edge edge : graph[rem.dst]) {
                if (!visited.contains(edge.target)) {
                    pq.add(new PrimsEdge(rem.dst, edge.target, edge.wt));
                }
            }
        }

    }



}
