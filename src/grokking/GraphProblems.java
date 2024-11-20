package grokking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphProblems {

    public boolean validPath(int n, int[][] edges, int source, int destination) {
        if (n == 0 || edges.length == 0) {
            return false;
        }

        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i = 0; i < n; i++) {
            map.put(i, new ArrayList<>());
        }

        for(int[] edge : edges) {
            map.get(edge[0]).add(edge[1]);
        }

        boolean[] visited = new boolean[n];
        return dfs(map, source, destination, visited);
    }

    private boolean dfs(Map<Integer, List<Integer>> map, int source, int destination, boolean[] visited) {
        if (source == destination) {
            return true;
        }

        if (visited[source]) {
            return false;
        }

        visited[source] = true;

        for (int neighbour : map.get(source)) {
            if (dfs(map, neighbour, destination, visited)) {
                return true;
            }
        }

        return false;
    }

    public int findCircleNum(int[][] isConnected) {
        if (isConnected.length == 0) {
            return 0;
        }

        return 1;
    }



}
