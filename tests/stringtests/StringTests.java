package stringtests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import string.StringProblems;

public class StringTests {

	@Test
	public void validateDistinctStringCount() {
		
		int actualCount = StringProblems.countDistinctMatchingSubseq("rabbbit", "rabbit");
		int expectedCount = 3;
		
		assertEquals(expectedCount, actualCount);
		
	}
	
}
