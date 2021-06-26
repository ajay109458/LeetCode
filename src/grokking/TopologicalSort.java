package grokking;

import java.util.*;

public class TopologicalSort {

    public static List<Integer> topologicalOrder(Map<Integer, List<Integer>> graph) {

        Map<Integer, Integer> indegreeMap = new HashMap<>();

        for(Map.Entry<Integer, List<Integer>> entry : graph.entrySet()) {
            indegreeMap.putIfAbsent(entry.getKey(), 0);
            for(int node : entry.getValue()) {
                indegreeMap.put(node, indegreeMap.getOrDefault(node, 0) + 1);
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        for(Map.Entry<Integer, Integer> entry : indegreeMap.entrySet()) {
            if (entry.getValue() == 0) {
                queue.add(entry.getKey());
            }
        }

        List<Integer> result = new ArrayList<>();
        while(!queue.isEmpty()) {
            Integer node = queue.poll();

            result.add(node);

            for(Integer neigh : graph.get(node)) {
                indegreeMap.put(neigh, indegreeMap.get(neigh) - 1);
                if (indegreeMap.get(neigh) == 0) {
                    queue.add(neigh);
                }
            }
        }

        return result;
    }


    public static List<Integer> getTopologicalSort(Map<Integer, List<Integer>> graph) {
        Map<Integer, Integer> indegreeMap = new HashMap<>();

        for(int u = 0; u < graph.size(); u++) {
            if (!indegreeMap.containsKey(u)) {
                indegreeMap.put(u, 0);
            }

            for(int v : graph.get(u)) {
                indegreeMap.put(v, indegreeMap.getOrDefault(v, 0) + 1);
            }
        }

        Queue<Integer> queue = new LinkedList<>();

        for(Map.Entry<Integer, Integer> entry : indegreeMap.entrySet()) {
            if (entry.getValue() == 0) {
                queue.add(entry.getKey());
            }
        }

        List<Integer> result = new ArrayList<>();
        while(!queue.isEmpty()) {
            Integer u = queue.remove();
            result.add(u);

            for(int v : graph.get(u)) {
                indegreeMap.put(v, indegreeMap.get(v) -1);
                if (indegreeMap.get(v) == 0) {
                    queue.add(v);
                }
            }
        }

        if (result.size() != graph.size()) {
            return null;
        }

        return result;
    }

    public static List<Integer> getTopologicalSort2(Map<Integer, List<Integer>> graph) {

        Set<Integer> visited = new HashSet<>();
        Stack<Integer> stack = new Stack<>();

        for(int v : graph.keySet()) {
            if (!visited.contains(v)) {
                populateTopologicalSortStack(graph, stack, visited, v);
            }
        }

        List<Integer> result = new ArrayList<>();
        while(!stack.isEmpty()) {
            result.add(stack.pop());
        }

        return result;
    }

    private static void populateTopologicalSortStack(Map<Integer, List<Integer>> graph, Stack<Integer> stack, Set<Integer> visited, int v) {

        visited.add(v);

        for(int n : graph.get(v)) {
            if (!visited.contains(n)) {
                populateTopologicalSortStack(graph, stack, visited, n);
            }
        }

        stack.push(v);
    }

    public static boolean isTaskSchedulingPossible(Map<Integer, List<Integer>> graph) {

        Map<Integer, Integer> inMap = new HashMap<>();

        for(int u : graph.keySet()) {
            if (!inMap.containsKey(u)) {
                inMap.put(u, 0);
            }

            for(int v : graph.get(u)) {
                inMap.put(v, inMap.getOrDefault(v, 0) + 1);
            }
        }

        Queue<Integer> queue = new LinkedList<>();

        for(Map.Entry<Integer, Integer> entry : inMap.entrySet()) {
            if (entry.getValue() == 0) {
                queue.add(entry.getKey());
            }
        }

        List<Integer> result = new ArrayList<>();
        while(!queue.isEmpty()) {
            Integer rem = queue.poll();
            result.add(rem);

            for(int v : graph.get(rem)) {
                inMap.put(v, inMap.get(v) - 1);
                if (inMap.get(v) == 0) {
                    queue.add(v);
                }
            }
        }

        System.out.println(result);

        while(result.size() != graph.size()) {
            return false;
        }

        return true;

    }

    public static boolean isTaskSchedulePossible(int tasks, int[][] preReqs) {

        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> inDegreeMap = new HashMap<>();

        for(int i = 0; i < tasks; i++) {
            inDegreeMap.putIfAbsent(i, 0);
            graph.putIfAbsent(i, new ArrayList<>());
        }

        for(int[] edge : preReqs) {
            int u = edge[0];
            int v = edge[1];
            graph.get(u).add(v);
            inDegreeMap.put(v, inDegreeMap.getOrDefault(v, 0) + 1);
        }

        Queue<Integer> queue = new LinkedList<>();
        for(Map.Entry<Integer, Integer> entry : inDegreeMap.entrySet()) {
            if (entry.getValue() == 0) {
                queue.add(entry.getKey());
            }
        }

        List<Integer> results = new ArrayList<>();
        while(!queue.isEmpty()) {
            Integer remNode = queue.poll();

            results.add(remNode);

            for(int neigh : graph.get(remNode)) {
                inDegreeMap.put(neigh, inDegreeMap.get(neigh) - 1);

                if (inDegreeMap.get(neigh) == 0) {
                    queue.add(neigh);
                }
            }
        }

        if (results.size() !=  tasks) {
            return false;
        }

        return true;
    }

}
