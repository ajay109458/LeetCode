package grokking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class TopKTests {

    @Nested
    @DisplayName("Find K largest elements")
    class KLargestTests {

        @Test
        public void test1() {
            int[] arr = {3, 1, 5, 12, 2, 11};

            List<Integer> list = TopKPattern.findKLargest(arr, 3);
            Assertions.assertIterableEquals(Arrays.asList(5, 11, 12), list);
        }

        @Test
        public void test2() {
            int[] arr = {5, 12, 11, -1, 12};

            List<Integer> list = TopKPattern.findKLargest(arr, 3);
            Assertions.assertIterableEquals(Arrays.asList( 11, 12, 12), list);
        }
    }

    @Nested
    @DisplayName("Kth smallest number")
    class KthSmallestTest {

        @Test
        public  void test1() {
            int[] arr = {1, 5, 12, 2, 11, 5};
            int K = 3;

            int smallest = TopKPattern.getKthSmallestElement(arr, K);
            Assertions.assertEquals(5, smallest);
        }

        @Test
        public  void test2() {
            int[] arr = {1, 5, 12, 2, 11, 5};
            int K = 4;

            int smallest = TopKPattern.getKthSmallestElement(arr, K);
            Assertions.assertEquals(5, smallest);
        }

        @Test
        public  void test3() {
            int[] arr = {5, 12, 11, -1, 12};
            int K = 3;

            int smallest = TopKPattern.getKthSmallestElement(arr, K);
            Assertions.assertEquals(11, smallest);
        }
    }

    @Nested
    @DisplayName("Closes K to origins")
    class ClosesKBikesToOrigin {

        @Test
        public  void test1() {
            List<TopKPattern.Bike> bikes = Arrays.asList(new TopKPattern.Bike(1, 2), new TopKPattern.Bike(1, 3));

            List<TopKPattern.Bike> results = TopKPattern.getKClosestBikesToOrigin(bikes, 1);
            Assertions.assertIterableEquals(Arrays.asList(new TopKPattern.Bike(1, 2)), results);
        }

    }

    @Nested
    @DisplayName("Connect rope cost")
    class ConnectRopeTests {
        @Test
        public void test1() {
            int[] arr = {1, 3, 11, 5};
            int ropeConnectingCost = TopKPattern.ropeConnectingCost(arr);
            Assertions.assertEquals(33, ropeConnectingCost);

            System.out.println();
        }

        @Test
        public void test2() {
            int[] arr = {3, 4, 5, 6};
            int ropeConnectingCost = TopKPattern.ropeConnectingCost(arr);
            Assertions.assertEquals(36, ropeConnectingCost);
        }

        @Test
        public void test3() {
            int[] arr = {1, 3, 11, 5, 2};
            int ropeConnectingCost = TopKPattern.ropeConnectingCost(arr);
            Assertions.assertEquals(42, ropeConnectingCost);
        }
    }

    @Nested
    @DisplayName("Get top k frequent elements")
    class TopKFrequentElementsTest {
        @Test
        public void test1() {
            int[] arr = {1, 3, 5, 12, 11, 12, 11};
            List<Integer>  list = TopKPattern.getTopKFreqElements(arr, 2);
            Assertions.assertIterableEquals(Arrays.asList(11, 12), list);
        }
    }

    @Nested
    @DisplayName("Sort string by frequncy")
    class SortStringByFreqMap {

        @Test
        public void test1() {
            String input = "Programming";
            String output = TopKPattern.sortByFreq(input);
            Assertions.assertEquals("rrggmmPaino", output);
        }

    }

    @Test
    public void KthLargestNumberInStream() {
        int[] arr = {3, 1, 5, 12, 2, 11};
        int k = 4;
        TopKPattern.KthLargestInStream kthLargestInStream = new TopKPattern.KthLargestInStream(arr, k);
        Assertions.assertEquals(5, kthLargestInStream.add(6));
    }

    @Nested
    @DisplayName("K closest element to X")
    class KClosestElementToXTests {

        @Test
        public void test1() {
            int[] arr = {5, 6, 7, 8, 9};
            List<Integer> list = TopKPattern.getKClosestNumbers(arr, 3, 7);
            Assertions.assertEquals(Arrays.asList(6,8, 7), list);
        }

    }

    @Nested
    @DisplayName("Maximum Distinct Elements")
    class MaximumDistinctElements {
        @Test
        public void test1() {
            int[] arr = {7, 3, 5, 8, 5, 3, 3};

            int count = TopKPattern.findMaxDistinctElements(arr, 2);

            Assertions.assertEquals(count, 3);
        }

        @Test
        public void test2() {
            int[] arr = {3, 5, 12, 11, 12};

            int count = TopKPattern.findMaxDistinctElements(arr, 3);

            Assertions.assertEquals(count, 2);
        }

        @Test
        public void test3() {
            int[] arr = {1, 2, 3, 3, 3, 3, 4, 4, 5, 5, 5};

            int count = TopKPattern.findMaxDistinctElements(arr, 2);

            Assertions.assertEquals(count, 3);
        }
    }

    @Nested
    @DisplayName("Find the sum of the elements")
    class SumOfTheKthElements {
        @Test
        public void test1() {
            int[] arr = {3, 5, 8, 7};
            int sum = TopKPattern.findSumOfElements(arr, 1, 4);
            Assertions.assertEquals(12, sum);
        }

        @Test
        public void test2() {
            int[] arr = {1, 3, 12, 5, 15, 11};
            int sum = TopKPattern.findSumOfElements(arr, 3, 6);
            Assertions.assertEquals(23, sum);
        }
    }

    @Test
    public void reorganizeStringTests() {

        String input = "mmpp";
        String output = TopKPattern.reorganizeString(input, 2);
        System.out.println(output);

    }

}
