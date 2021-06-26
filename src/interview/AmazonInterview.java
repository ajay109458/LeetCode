package interview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AmazonInterview {

    private static Map<Integer, List<Integer>> buildNetwork(int n, List<String> edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for(int node = 1; node <= n; node++) {
            graph.put(node, new ArrayList<>());
        }

        for(String edge : edges) {
            String[] edgeSplit = edge.split(" ");
            Integer u = Integer.parseInt(edgeSplit[0]);
            Integer v = Integer.parseInt(edgeSplit[1]);
            if (graph.containsKey(u)) {
                graph.get(u).add(v);
            }
        }

        return graph;
    }

}
