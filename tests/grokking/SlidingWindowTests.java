package grokking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class SlidingWindowTests {

    @Nested
    @DisplayName("Maximum Sum Subarray of Size K")
    class MaximumSubArraySumTests {

        @Test
        void testInput1() {
            int[] arr = {2, 1, 5, 1, 3, 2};
            int k = 3;

            int sum = SlidingWindow.computeMaxSumOfWindow(arr, k);
            Assertions.assertEquals(9, sum);
        }

        @Test
        void testInput2() {
            int[] arr = {2, 3, 4, 1, 5};
            int k = 2;

            int sum = SlidingWindow.computeMaxSumOfWindow(arr, k);
            Assertions.assertEquals(7, sum);
        }
    }

    @Nested
    @DisplayName("Smallest subarray with a given sum")
    class SmallestSubArraySumTests {

        @Test
        public void inputTest1() {
            int[] arr = {2, 1, 5, 2, 3, 2};
            int sum = 7;

            int len = SlidingWindow.smallestSubarray(arr, sum);
            Assertions.assertEquals(2, len);
        }

        @Test
        public void inputTest2() {
            int[] arr = {2, 1, 5, 2, 8};
            int sum = 7;

            int len = SlidingWindow.smallestSubarray(arr, sum);
            Assertions.assertEquals(1, len);
        }

        @Test
        public void inputTest3() {
            int[] arr = {3, 4, 1, 1, 6};
            int sum = 8;

            int len = SlidingWindow.smallestSubarray(arr, sum);
            Assertions.assertEquals(3, len);
        }

    }

    @Nested
    @DisplayName("Longest substring with K distinct characters")
    class LongestSubstringKDistinctCharTests {

        @Test
        public void testInput1() {
            int len = SlidingWindow.longestSubstringKDistinctChar("araaci", 2);
            Assertions.assertEquals(4, len);
        }

        @Test
        public void testInput2() {
            int len = SlidingWindow.longestSubstringKDistinctChar("araaci", 1);
            Assertions.assertEquals(2, len);
        }

        @Test
        public void testInput3() {
            int len = SlidingWindow.longestSubstringKDistinctChar("cbbebi", 3);
            Assertions.assertEquals(5, len);
        }
    }

    @Nested
    @DisplayName("Number of the fruits in the basket")
    class FruitInBasketTest {

        @Test
        public void testInput1() {
            char[] arr = {'A', 'B', 'C', 'A', 'C'};
            int counts = SlidingWindow.numberOfFruitsInBasket(arr, 2);
            Assertions.assertEquals(3, counts);
        }

        @Test
        public void testInput2() {
            char[] arr = {'A', 'B', 'C', 'B', 'B', 'C'};
            int counts = SlidingWindow.numberOfFruitsInBasket(arr, 2);
            Assertions.assertEquals(5, counts);
        }
    }

    @Nested
    @DisplayName("No repeat substring")
    class NoRepeatSubstringTests {

        @Test
        public void testInput1() {
            int len = SlidingWindow.getMaxLengthNoRepeatChar("aabccbb");
            Assertions.assertEquals(3, len);
        }

        @Test
        public void testInput2() {
            int len = SlidingWindow.getMaxLengthNoRepeatChar("abbbb");
            Assertions.assertEquals(2, len);
        }

        @Test
        public void testInput3() {
            int len = SlidingWindow.getMaxLengthNoRepeatChar("abccde");
            Assertions.assertEquals(3, len);
        }

    }

    @Nested
    @DisplayName("Longest substring with replacement")
    class LongestSubstringWithReplacement {

        @Test
        public void testInput1() {
            int len = SlidingWindow.longestSubStringWithReplacement("aabccbb", 2);
            Assertions.assertEquals(5, len);
        }

        @Test
        public void testInput2() {
            int len = SlidingWindow.longestSubStringWithReplacement("abbcb", 1);
            Assertions.assertEquals(4, len);
        }

        @Test
        public void testInput3() {
            int len = SlidingWindow.longestSubStringWithReplacement("abccde", 1);
            Assertions.assertEquals(3, len);
        }

    }

}
