package pepcoding;

import org.junit.jupiter.api.Test;
import prepcoding.Recursion2;

import java.util.List;

public class RecursionAndBacktrackingTests {

    @Test
    public void printCombinations() {
        Recursion2.combinations(4, 2);
    }

    @Test
    public void printPermutationWithRepitition() {
        List<String> result = Recursion2.generateAllPermutations("aabb");
        System.out.println(result);
    }

    @Test
    public void generateUniqueCombinations() {
        List<String> result = Recursion2.generateUniqueCombinations("abcabc", 2);
        System.out.println(result);
    }

    @Test
    public void wordSelectionII() {
        List<String> result = Recursion2.wordKSelectionII("abcabc", 2);
        System.out.println(result);
    }

}
