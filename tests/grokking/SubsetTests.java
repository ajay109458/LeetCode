package grokking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SubsetTests {

    @Nested
    @DisplayName("Print subsets")
    class PrintSubsets {
        @Test
        public void test1() {
            int[] arr = {1, 3};
            SubsetProblems.printAllSubSets(arr);
        }
    }

    @Nested
    @DisplayName("Generate all subsets")
    class GenerateAllSubsets {
        @Test
        public void test1() {
            int[] arr = {1, 3};
            List<List<Integer>> allSubsets = SubsetProblems.findSubsets(arr);
            System.out.println(allSubsets);
            Assertions.assertEquals(4, allSubsets.size());
        }

        @Test
        public void test2() {
            int[] arr = {1, 5, 3};
            List<List<Integer>> allSubsets = SubsetProblems.findSubsets(arr);
            System.out.println(allSubsets);
            Assertions.assertEquals(8, allSubsets.size());
        }
    }

}
