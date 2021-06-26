package grokking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;

public class TopologicalSortTests {

    @Test
    public void testTopologicalSort() {
        Map<Integer, List<Integer>> graph = new HashMap<>();

        graph.put(0, new ArrayList<>());
        graph.put(1, new ArrayList<>());
        graph.put(2, Arrays.asList(0, 1));
        graph.put(3, Arrays.asList(2, 1));

        System.out.println(TopologicalSort.topologicalOrder(graph));
    }

    @Test
    public void testTopologicalSort2() {
        Map<Integer, List<Integer>> graph = new HashMap<>();

        graph.put(0, new ArrayList<>());
        graph.put(1, new ArrayList<>());
        graph.put(2, Arrays.asList(0, 1));
        graph.put(3, Arrays.asList(2, 1));

        System.out.println(TopologicalSort.getTopologicalSort2(graph));
    }

    @Test
    public void isTaskSchedulingPossible() {
        Map<Integer, List<Integer>> graph = new HashMap<>();

        graph.put(0, Arrays.asList(1));
        graph.put(1, Arrays.asList(2));
        graph.put(2, new ArrayList<>());
        System.out.println(TopologicalSort.isTaskSchedulingPossible(graph));
    }

    @Test
    public void isTaskSchedulingPossible2() {
        Map<Integer, List<Integer>> graph = new HashMap<>();

        graph.put(0, Arrays.asList(1));
        graph.put(1, Arrays.asList(2));
        graph.put(2, Arrays.asList(0));
        System.out.println(TopologicalSort.isTaskSchedulingPossible(graph));
    }

    @Nested
    @DisplayName("Task scheduling")
    class TaskSchedulingTests {
        @Test
        public void test1() {
            int tasks = 3;
            int[][] edges = {{0,1}, {1, 2}};

            boolean result = TopologicalSort.isTaskSchedulePossible(tasks, edges);
            Assertions.assertEquals(true, result);
        }

        @Test
        public void test2() {
            int tasks = 3;
            int[][] edges = {{0,1}, {1, 2}, {2,0}};

            boolean result = TopologicalSort.isTaskSchedulePossible(tasks, edges);
            Assertions.assertEquals(false, result);
        }
    }

}
