package grokking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class TwoPointerTests {

    @Nested
    @DisplayName("Sum of two numbers equals target")
    class SumTwoNumber {
        @Test
        public void test1() {
            int[] arr = {1, 2, 3, 4, 6};
            int target = 6;

            int[] result = TwoPointer.searchInArray(arr, target);

            Assertions.assertArrayEquals(new int[]{1, 3}, result);
        }

        @Test
        public void test2() {
            int[] arr = {2, 5, 9, 11};
            int target = 11;

            int[] result = TwoPointer.searchInArray(arr, target);

            Assertions.assertArrayEquals(new int[]{0, 2}, result);
        }
    }

    @Nested
    @DisplayName("Test duplicate elements")
    class UniqueElements {

        @Test
        public void test1() {
            int[] arr = {2, 3, 3, 3, 6, 9, 9};
            int count = TwoPointer.moveElements(arr);
            Assertions.assertEquals(4, count);
        }

        @Test
        public void test2() {
            int[] arr = {2, 2, 2, 11};
            int count = TwoPointer.moveElements(arr);
            Assertions.assertEquals(2, count);
        }

    }

    @Nested
    @DisplayName("Sorted array squares")
    class SortedSquareArrayTests {
        @Test
        public void test1() {
            int[] arr = {-2, -1, 0, 2, 3};
            int[] expected = {0, 1, 4, 4, 9};

            int[] result = TwoPointer.makeSquaresArray(arr);

            System.out.println(Arrays.toString(result));

            Assertions.assertArrayEquals(expected, result);
        }


        @Test
        public void test2() {
            int[] arr = {-3, -1, 0, 1, 2};
            int[] expected = {0, 1, 1 ,4, 9};

            int[] result = TwoPointer.makeSquaresArray(arr);

            System.out.println(Arrays.toString(result));

            Assertions.assertArrayEquals(expected, result);
        }
    }

    @Nested
    @DisplayName("Triplet tests")
    class TripletSumZero {
        @Test
        public void test1() {
            int[] arr = {-3, 0, 1, 2, -1, 1, -2};
            List<List<Integer>> result = TwoPointer.searchTriplets(arr);
            Assertions.assertEquals(4, result.size());
        }

        @Test
        public void test2() {
            int[] arr = {-5, 2, -1, -2, 3};
            List<List<Integer>> result = TwoPointer.searchTriplets(arr);
            Assertions.assertEquals(2, result.size());
        }
    }

    @Nested
    @DisplayName("Triplet sum closes to zero")
    class TripletSumClosest {
        @Test
        public void test1() {
            int[] arr = {-2, 0, 1, 2};
            int result = TwoPointer.searchClosestTriplet(arr, 2);
            Assertions.assertEquals(1, result);
        }

        @Test
        public void test2() {
            int[] arr = {-3, -1, 1, 2};
            int result = TwoPointer.searchClosestTriplet(arr, 1);
            Assertions.assertEquals(0, result);
        }
    }

}
