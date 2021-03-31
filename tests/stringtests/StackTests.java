package stringtests;

import org.junit.jupiter.api.Test;
import stack.QueueHelper;
import stack.StackHelper;
import string.StringProblems;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StackTests {

	@Test
	public void validateSimplifyPath() {
		
		String simplifiedPath = StackHelper.simplifyPath("/home/");
		assertEquals("/home", simplifiedPath);

		simplifiedPath = StackHelper.simplifyPath("/../");
		assertEquals("/", simplifiedPath);

		simplifiedPath = StackHelper.simplifyPath("/a/./b/../../c/");
		assertEquals("/c", simplifiedPath);
	}

	@Test
	public void checkCompondFormula() {
		StackHelper.countOfAtoms("H2O");
	}

	@Test
	public void TaskSchedulerTest() {
		char[] arr = {'A','A','A','B','B','B'};

		QueueHelper.leastInterval(arr, 2);
	}

	@Test
	public void testShortestArray() {
		int[] arr = {-28,81,-20,28,-29};
		QueueHelper.shortestSubarray(arr, 89);
	}

}
