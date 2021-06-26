package grokking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import utils.Pair;

public class BinarySearchTests {

    @Nested
    @DisplayName("Binary Search Tests")
    class BinarySearchTest {
        @Test
        public void test1() {
            int[] arr = {4, 6, 10};
            int index = BinarySearch.searchIndex(arr, 10);
            Assertions.assertEquals(2, index);
        }

        @Test
        public void test2() {
            int[] arr = {1, 2, 3, 4, 5, 6, 7};
            int index = BinarySearch.searchIndex(arr, 5);
            Assertions.assertEquals(4, index);
        }

        @Test
        public void test3() {
            int[] arr = {10, 6, 4};
            int index = BinarySearch.searchIndex(arr, 10);
            Assertions.assertEquals(0, index);
        }

        @Test
        public void test4() {
            int[] arr = {10, 6, 4};
            int index = BinarySearch.searchIndex(arr, 4);
            Assertions.assertEquals(2, index);
        }
    }

    @Nested
    @DisplayName("Ciel of a value")
    class FindCielValueSortedArray {
        @Test
        public void test1() {
            int[] arr = {4, 6, 10};
            int index = BinarySearch.ciel(arr, 6);
            Assertions.assertEquals(1, index);
        }

        @Test
        public void test2() {
            int[] arr = {1, 3, 8, 10, 15};
            int index = BinarySearch.ciel(arr, 12);
            Assertions.assertEquals(4, index);
        }

        @Test
        public void test3() {
            int[] arr = {4, 6, 10};
            int index = BinarySearch.ciel(arr, 17);
            Assertions.assertEquals(-1, index);
        }

        @Test
        public void test4() {
            int[] arr = {4, 6, 10};
            int index = BinarySearch.ciel(arr, -1);
            Assertions.assertEquals(0, index);
        }
    }

    @Nested
    @DisplayName("Find next character")
    class FindNextCharacter {
        @Test
        public void test1() {
            char[] arr = {'a', 'c', 'f', 'h'};
            int index = BinarySearch.findNextChar(arr, 'f');
            Assertions.assertEquals('h', index);
        }

        @Test
        public void test2() {
            char[] arr = {'a', 'c', 'f', 'h'};
            int index = BinarySearch.findNextChar(arr, 'b');
            Assertions.assertEquals('c', index);
        }

        @Test
        public void test3() {
            char[] arr = {'a', 'c', 'f', 'h'};
            int index = BinarySearch.findNextChar(arr, 'm');
            Assertions.assertEquals('a', index);
        }

        @Test
        public void test4() {
            char[] arr = {'a', 'c', 'f', 'h'};
            int index = BinarySearch.findNextChar(arr, 'h');
            Assertions.assertEquals('a', index);
        }
    }


    @Nested
    @DisplayName("Find range")
    class FindRange {
        @Test
        public void test1() {
            int[] arr = {4, 6, 6, 6, 9};
            Pair pair = BinarySearch.findRange(arr, 6);
            Assertions.assertEquals(1, pair.x);
            Assertions.assertEquals(3, pair.y);
        }

        @Test
        public void test2() {
            int[] arr = {1, 3, 8, 10, 15};
            Pair pair = BinarySearch.findRange(arr, 10);
            Assertions.assertEquals(3, pair.x);
            Assertions.assertEquals(3, pair.y);
        }

        @Test
        public void test3() {
            int[] arr = {1, 3, 8, 10, 15};
            Pair pair = BinarySearch.findRange(arr, 12);
            Assertions.assertEquals(-1, pair.x);
            Assertions.assertEquals(-1, pair.y);
        }
    }

    @Nested
    @DisplayName("Infinite Binary search ")
    class InfiniteBinarySearch {
        @Test
        public void test1() {
            int[] arr = {4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30};
            int index = BinarySearch.searchInInfiniteArray(arr, 16);
            Assertions.assertEquals(6, index);
        }

        @Test
        public void test2() {
            int[] arr = {4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30};
            int index = BinarySearch.searchInInfiniteArray(arr, 11);
            Assertions.assertEquals(-1, index);
        }

        @Test
        public void test3() {
            int[] arr = {1, 3, 8, 10, 15};
            int index = BinarySearch.searchInInfiniteArray(arr, 15);
            Assertions.assertEquals(4, index);
        }
    }

    @Nested
    @DisplayName("Minimum Value near to key ")
    class MinValueNearToKey {
        @Test
        public void test1() {
            int[] arr = {4, 6, 10};
            int value = BinarySearch.findMinDiffElement(arr, 7);
            Assertions.assertEquals(6, value);
        }

        @Test
        public void test2() {
            int[] arr = {4, 6, 10};
            int value = BinarySearch.findMinDiffElement(arr, 4);
            Assertions.assertEquals(4, value);
        }

        @Test
        public void test3() {
            int[] arr = {1, 3, 8, 10, 15};
            int value = BinarySearch.findMinDiffElement(arr, 12);
            Assertions.assertEquals(10, value);
        }

        @Test
        public void test4() {
            int[] arr = {4, 6, 10};
            int value = BinarySearch.findMinDiffElement(arr, 17);
            Assertions.assertEquals(10, value);
        }
    }


    @Nested
    @DisplayName("Max Value in Bitonic array")
    class MaxValueBitonicArray {
        @Test
        public void test1() {
            int[] arr = {1, 3, 8, 12, 4, 2};
            int value = BinarySearch.findMaximumInBitonicArray(arr);
            Assertions.assertEquals(12, value);
        }

        @Test
        public void test2() {
            int[] arr = {3, 8, 3, 1};
            int value = BinarySearch.findMaximumInBitonicArray(arr);
            Assertions.assertEquals(8, value);
        }

        @Test
        public void test3() {
            int[] arr = {1, 3, 8, 12};
            int value = BinarySearch.findMaximumInBitonicArray(arr);
            Assertions.assertEquals(12, value);
        }

        @Test
        public void test4() {
            int[] arr = {10, 9, 8};
            int value = BinarySearch.findMaximumInBitonicArray(arr);
            Assertions.assertEquals(10, value);
        }
    }
}
