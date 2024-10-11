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
}
