package stringtests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.sun.org.apache.xml.internal.utils.StringBufferPool;
import org.junit.jupiter.api.Test;

import string.StringProblems;

public class StringTests {

	@Test
	public void validateDistinctStringCount() {
		
		int actualCount = StringProblems.countDistinctMatchingSubseq("rabbbit", "rabbit");
		int expectedCount = 3;
		
		assertEquals(expectedCount, actualCount);
		
	}
	
	@Test
	public void validatePalindrome() {
		
		boolean isValidPalindrome = StringProblems.isValidPalindrome("A man, a plan, a canal: Panama");
		
		
		assertEquals(true, isValidPalindrome);
		
	}

	@Test
	public void validateCloseString() {

		String one = "cabbba";
		String two = "aabbss";

		boolean isCloseString = StringProblems.closeStrings(one, two);

		System.out.println(isCloseString);

	}

	@Test
	public void validateArrayStringsEqual() {

		String[] word1 = {"a", "cb"}, word2 = {"ab", "c"};

		boolean isEqual = StringProblems.arrayStringsAreEqual(word1, word2);

		System.out.println(isEqual);

	}

	@Test
	public void getSmallestStringTest() {

		String  result = StringProblems.getSmallestString(80, 576);

		System.out.println(result);

	}

	@Test
	public void numOfSubstrings() {
		String val = "11100010101011110000011111111111111111111111";

		int res = StringProblems.numSub(val);
		System.out.println(res);
	}

	@Test
	public void generateParenthesisTest() {
		//StringProblems.generateParenthesis(3, 3, "");
	}


	@Test
	public void testRobinKarp() {
		boolean match = StringProblems.isPatternMatchRobinKarp("axtestxtext", "test");
		System.out.println(match);
	}

	@Test
	public void testExcelSheet() {
		System.out.print(StringProblems.titleToNumber("ZY"));
	}

}
