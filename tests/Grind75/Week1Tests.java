package Grind75;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;


public class Week1Tests {

	@Test
	public void TwoSum() {

		// Arrange
		int[] arr1 = {2,7,11,15};
		int target1 = 9;
		int[] expected1 = {0, 1};

		int[] arr2 = {3,2,4};
		int target2 = 6;
		int[] expected2 = {1, 2};

		int[] arr3 = {3,3};
		int target3 = 6;
		int[] expected3 = {0, 1};

		int[] result1 = Week1.twoSum(arr1, target1);
		int[] result2 = Week1.twoSum(arr2, target2);
		int[] result3 = Week1.twoSum(arr3, target3);

		System.out.println(Arrays.toString(result1));
		System.out.println(Arrays.toString(result2));
		System.out.println(Arrays.toString(result3));

		Assertions.assertArrayEquals(expected1, result1, "Test1 failed");
		Assertions.assertArrayEquals(expected2, result2, "Test2 failed");
		Assertions.assertArrayEquals(expected3, result3, "Test3 failed");
	}

	@Test
	public void IsValidTest() {
		// Arrange
		String s1 = "()";
		boolean expected1 = true;

		String s2 = "()[]{}";
		boolean expected2 = true;

		String s3 = "(]";
		boolean expected3 = false;

		String s4 = "([])";
		boolean expected4 = true;

		// Act
		boolean result1 = Week1.isValid(s1);
		boolean result2 = Week1.isValid(s2);
		boolean result3 = Week1.isValid(s3);
		boolean result4 = Week1.isValid(s4);

		// Assert
		Assertions.assertEquals(expected1, result1);
		Assertions.assertEquals(expected2, result2);
		Assertions.assertEquals(expected3, result3);
		Assertions.assertEquals(expected4, result4);
	}

	public void maxProfitTest() {
		// Arrange
		int[] prices1 = {7,1,5,3,6,4};
		int expected1 = 5;

		int[] prices2 = {7,6,4,3,1};
		int expected2 = 0;

		// Act
		int actual1 = Week1.maxProfit(prices1);
		int actual2 = Week1.maxProfit(prices2);

		Assertions.assertEquals(expected1, actual1);
		Assertions.assertEquals(expected2, actual2);
	}
}
