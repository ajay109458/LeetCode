import graph.GraphProblem;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphProblemTests {

    @Test
    public void testTopologicalSort() {
        Map<Integer, List<Integer>> graph = new HashMap<>();

        graph.put(0, null);
        graph.put(1, null);
        graph.put(2, Arrays.asList(3));
        graph.put(3, Arrays.asList(1));
        graph.put(4, Arrays.asList(0, 1));
        graph.put(5, Arrays.asList(0, 2));

        GraphProblem.topologicalSort(graph);
    }
}
