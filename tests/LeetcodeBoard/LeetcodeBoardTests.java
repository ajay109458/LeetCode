package LeetcodeBoard;

import org.junit.jupiter.api.Test;


public class LeetcodeBoardTests {

	@Test
	public void DecodeStringTest() {
		String s = "2[abc]3[cd]ef";
		String output = LeetcodeBoard.decodeString(s);
		System.out.println(output);
	}
}
