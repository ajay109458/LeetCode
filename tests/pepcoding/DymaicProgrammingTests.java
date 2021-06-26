package pepcoding;

import org.junit.jupiter.api.Test;
import prepcoding.DynamicProgramming;
import prepcoding.RecursionBasics;

import java.util.List;

public class DymaicProgrammingTests {

    @Test
    public void fibTest() {
        int res = DynamicProgramming.fib(10);
        System.out.println(res);

        res = DynamicProgramming.fibMemoization(10, new int[11]);
        System.out.println(res);

        res = DynamicProgramming.fibTabulation(10);
        System.out.print(res);
    }

    @Test
    public void climbDownPathTest() {
        int n = 4;
        List<String> paths = RecursionBasics.getAllStairPaths(n);

        int pathCounts = DynamicProgramming.countAllStairPaths(n);

        System.out.println(paths.size() + " : " + pathCounts);
    }

}
