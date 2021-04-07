package sdesheet;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Day24 {

    private static class Edge {
        int start;
        int end;
        int wt;

        public Edge(int start, int end, int wt) {
            this.start = start;
            this.end = end;
            this.wt =  wt;
        }
    }

    private static class Node {
        int vertex;
        int wt;

        public Node(int target, int wt) {
            this.vertex = target;
            this.wt = wt;
        }
    }

    public static void dijkstra(List<Node>[] graph, int src) {

        PriorityQueue<Node> pq = new PriorityQueue<>((e1, e2) -> Integer.compare(e1.wt, e2.wt));

        // Dummy node
        pq.add(new Node(src, 0));
        boolean[] visited = new boolean[graph.length];

        while(!pq.isEmpty()) {

            Node rem = pq.remove();

            if (visited[rem.vertex]) {
                continue;
            }

            visited[rem.vertex] = true;

            System.out.println("Distance of " + rem.vertex + " from  source : "  + rem.wt);

            for(Node neigh : graph[rem.vertex]) {
                if (!visited[neigh.vertex]) {
                    pq.add(new Node(neigh.vertex, neigh.wt +  rem.wt));
                }
            }

        }

    }

    public static void bellmanFord(List<Node>[] graph, int src) {

        int V = graph.length;

        int[] dist = new int[V];

        // Initialize vertex with infinity
        for(int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        dist[src] = 0;


        // Relax vertex V-1 times
        for(int k = 1; k < V; k++) {
            // for each edge
            for(int u = 0; u < V; u++) {
                for(Node node : graph[u])  {
                    int v = node.vertex;
                    int edgeWt = node.wt;

                    if (dist[u] != Integer.MAX_VALUE && dist[v] > dist[u] + edgeWt) {
                        dist[v] = dist[u] + edgeWt;
                    }
                }
            }
        }


        // Check for negative edge cycle
        for(int u = 0; u < V; u++) {
            for(Node node : graph[u])  {
                int v = node.vertex;
                int edgeWt = node.wt;

                if (dist[u] != Integer.MAX_VALUE && dist[v] > dist[u] + edgeWt) {
                    System.out.println("System contains negative edge cycle");
                    return;
                }
            }
        }

        System.out.print(Arrays.toString(dist));
    }

    public static void floydWarshall(List<Node>[] graph) {
        int V = graph.length;

        int[][] d = new int[V][V];

        // Traverse edges
        for(int i = 0; i < V; i++) {
            for(Node node : graph[i]) {
                int u = i;
                int v = node.vertex;
                int edgeWt = node.wt;


                d[u][v] = edgeWt;
            }
        }

        for(int k = 0; k < V; k++) {
            for(int u = 0; u < V; u++) {
                for (int v = 0; v < V; v++) {
                    if (d[u][v] > d[u][k] + d[k][v]) {
                        d[u][v] = d[u][k] + d[k][v];
                    }
                }
            }
        }

        // Print matrix d
    }

}
