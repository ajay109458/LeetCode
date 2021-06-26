package pepcoding;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Assertions.*;
import prepcoding.Recursion;
import prepcoding.RecursionBasics;
import prepcoding.RecursionBasics1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RecursionBasicTests {

    @Test
    public void printDecreasing() {
        RecursionBasics1.printDecreasing(5);
    }


    @Test
    public void printIncreasing() {
        RecursionBasics1.printIncreasing(5);
    }

    @Test
    public void printDecreasingIncreasing() {
        RecursionBasics1.printDecreasingIncreasing(5);
    }

    @Test
    public void testFactorial() {
        int actualFactorial = RecursionBasics1.factorial(5);
        Assertions.assertEquals(120, actualFactorial);
    }

    @Test
    public void testPowerLinear() {
        int actualPowerValue = RecursionBasics1.powerLinear(2, 4);
        Assertions.assertEquals(16, actualPowerValue);
    }

    @Test
    public void testPowerLog() {
        int actualPowerValue1 = RecursionBasics1.powerLog(2, 4);
        int actualPowerValue2 = RecursionBasics1.powerLog(2, 5);
        Assertions.assertEquals(16, actualPowerValue1);
        Assertions.assertEquals(32, actualPowerValue2);
    }

    @Test
    public void testPrintZigZag() {
        RecursionBasics1.printZigZag(2);
    }

    @Test
    public void printArray() {
        int[] arr = {10, 20, 30, 40, 50};
        RecursionBasics1.printArray(arr, 0);
    }

    @Test
    public void printArrayReverse() {
        int[] arr = {10, 20, 30, 40, 50};
        RecursionBasics1.printArrayReverse(arr, 0);
    }

    @Test
    public void testLastIndex() {
        int[] arr = {2, 8, 4, 5, 8, 7};
        System.out.println(RecursionBasics.findLastIndex(arr, 0, 8));
    }

    @Test
    public void testFindMax() {
        int[] arr = {2, 8, 4, 5, 8, 7};
        Assertions.assertEquals(8, RecursionBasics1.findMax(arr, 0));
    }

    @Test
    public void findFirstIndex() {
        int[] arr = {2, 8, 4, 5, 8, 7};
        int actualIndex = RecursionBasics1.findFirstOccurance(arr, 0, 8);
        Assertions.assertEquals(1, actualIndex);
    }

    @Test
    public void findLastIndex() {
        int[] arr = {2, 8, 4, 5, 8, 7};
        int actualIndex = RecursionBasics1.findLastOccurance(arr, 0, 8);
        Assertions.assertEquals(4, actualIndex);
    }

    @Test
    public void findAllIndex() {
        int[] arr = {2, 8, 4, 5, 8, 7};
        List<Integer> indexes = RecursionBasics1.findAllIndex(arr, 8);
        Assertions.assertIterableEquals(indexes, Arrays.asList(1,4));
    }

    @Test
    public void testGenerateSubstrings() {
        List<String> actualSubstrings = RecursionBasics1.generateSubSequences("abc");
        Assertions.assertIterableEquals(Arrays.asList("", "a", "b", "ab", "c", "ac", "bc", "abc"), actualSubstrings);
    }

    @Test
    public void testDialpadProblem() {
        List<String> actualSubstrings = RecursionBasics1.letterCombination("2");
        Collections.sort(actualSubstrings);
        Assertions.assertIterableEquals(Arrays.asList("a", "b", "c"), actualSubstrings);
    }

    @Test
    public void testAllWords() {
        List<String> result = RecursionBasics.allWords("573");
        System.out.println(result);
    }

    @Test
    public void testAllPath() {
        List<String> allPaths = RecursionBasics.getAllStairPaths(4);
        System.out.println(allPaths);
    }

    @Test
    public void mazePathTest() {
        List<String> allPaths = RecursionBasics.printMazePaths(new int[4][5]);
        System.out.print(allPaths);
    }

    @Test
    public void printPermutation() {
        RecursionBasics.printPermutation("ab");
    }

    @Test
    public void printEncodings() {
        RecursionBasics.printEncoding("123");
    }


    @Nested
    @DisplayName("Check stair path")
    class TestStairPaths {

        @Test
        public void testPathsForZeroStairs() {
            List<String> paths = RecursionBasics1.getStairPaths(0);
            Assertions.assertEquals(0, paths.size());
        }

        @Test
        public void testPathsForOneStair() {
            List<String> paths = RecursionBasics1.getStairPaths(1);
            Assertions.assertEquals(1, paths.size());
        }

        @Test
        public void testPathForMultipleStairs() {
            List<String> paths = RecursionBasics1.getStairPaths(3);
            Collections.sort(paths);
            Assertions.assertIterableEquals(Arrays.asList("123", "13", "23"), paths);
        }
    }

    @Nested
    @DisplayName("Maze path")
    class MazePathProblemTests {

        @Test
        public void testMazePathWithZeroSize() {
            List<String> result = RecursionBasics1.getMazePaths(0, 0);
            Assertions.assertEquals(0, result.size());
        }

        @Test
        public void testMazePathWithWithZeroRowSize() {
            List<String> result = RecursionBasics1.getMazePaths(0, 5);
            Assertions.assertEquals(0, result.size());
        }

        @Test
        public void testMazePathWithWithZeroColumnSize() {
            List<String> result = RecursionBasics1.getMazePaths(10, 0);
            Assertions.assertEquals(0, result.size());
        }

        @Test
        public void testMazePathWithWithOneSize() {
            List<String> result = RecursionBasics1.getMazePaths(1, 1);
            Assertions.assertEquals(1, result.size());
        }

        @Test
        public void testMazePath() {
            List<String> result = RecursionBasics1.getMazePaths(2, 3);
            Assertions.assertEquals(Arrays.asList("vhh", "hvh", "hhv"), result);
            Assertions.assertEquals(3, result.size());
        }
    }

    @Nested
    @DisplayName("Generate permuation for a string")
    class GeneratePermutationTest {

        @Test
        void generatePermutationForNull() {
            List<String> result = RecursionBasics1.generateAllPermutations(null);
            Assertions.assertEquals(0, result.size());
        }

        @Test
        void generatePermutationForEmptyString() {
            List<String> result = RecursionBasics1.generateAllPermutations("");
            Assertions.assertEquals(0, result.size());
        }

        @Test
        void generatePermutationForInput() {
            List<String> result = RecursionBasics1.generateAllPermutations("a");
            Assertions.assertEquals(1, result.size());
        }

        @Test
        void generatePermutationForInput2() {
            List<String> result = RecursionBasics1.generateAllPermutations("abc");
            Assertions.assertEquals(6, result.size());
        }
    }

    @Nested
    @DisplayName("Test generate encodings")
    class TestEncodings {

        @Test
        void generateTestEncodings() {
            List<String> encodings = RecursionBasics1.computeEncodings("123");
            Assertions.assertEquals(Arrays.asList("abc", "aw", "lc"), encodings);
        }
    }

    @Nested
    @DisplayName("Flood fill test")
    class TestFloodFill {

        @Test
        public void testFloodFill() {
            int[][] matrix = {
                    {0, 1, 0, 0, 0, 0, 0},
                    {0, 1, 0, 1, 1, 1, 0},
                    {0, 0, 0, 0, 0, 0, 0},
                    {1, 0, 1, 1, 0, 1, 1},
                    {1, 0, 1, 1, 0, 1, 1},
                    {1, 0, 0, 0, 0, 0, 0}
            };

            List<String> paths = RecursionBasics1.floodFillPaths(matrix);
            Assertions.assertIterableEquals(Arrays.asList("vvhvvvhhhhh", "vvhhhhvvvhh"), paths);
        }

    }


    @Test
    public void subsetSum() {
        int[] arr = {10, 20, 30, 40, 50};
        List<List<Integer>>  result = RecursionBasics1.findSubsets(arr, 70);
        System.out.println(result);
    }
}
