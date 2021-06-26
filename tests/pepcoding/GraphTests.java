package pepcoding;

import graph.GraphProblem;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import prepcoding.GraphProblems;

import java.util.List;

public class GraphTests {

    private static List<Integer>[] graph;

    @BeforeAll
    public static void init() {
        int vertex = 7;
        int[][] edges = {{0, 3}, {0, 1}, {1, 2}, {2, 3}, {3, 4}, {4, 5}, {5, 6}};

        graph = GraphProblems.buildGraph(vertex, edges);
    }

    @Test
    public void testPrintAllPaths() {
        GraphProblems.printAllPaths(graph, 0, 6);
    }

    @Test
    public void testHamiltonianPaths() {
        GraphProblems.printAllHamiltonianPaths(graph,0 );
    }

    @Test
    public void knightTourTests() {
        GraphProblems.printKnightTourPaths(new int[4][4], 0, 0);
    }

    @Test
    public void printBFS() {
        GraphProblems.printBFS(graph, 0);
    }

    @Test
    public void cycleTest() {
        System.out.println(GraphProblems.isGraphCycle(graph));
    }

    @Test
    public void infectionTest() {
        int count = GraphProblems.countInfectedPeoples(graph, 0, 2);
        System.out.print(count);
    }

}
