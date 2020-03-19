package string;

public class StringProblems {

	public static int countDistinctMatchingSubseq(String S, String target) {
		return _countDistinctMatchingSubseq(S, target, 0, 0);
	}
	
	private static int _countDistinctMatchingSubseq(String S, String target, int sIndex, int tIndex) {
		
		if (tIndex == target.length()) {
			return 1;
		}
		
		if (sIndex == S.length()) {
			return 0;
		}
		
		int count = 0;
		
		if (S.charAt(sIndex) == target.charAt(tIndex)) {
			count+= _countDistinctMatchingSubseq(S, target, sIndex+1, tIndex + 1);
		}
		
		count += _countDistinctMatchingSubseq(S, target, sIndex + 1, tIndex);
		
		return count;
	}
	
	public static boolean isValidPalindrome(String input) {
		
		if(null == input || "".equals(input)) {
			return true;
		}
		
		int start = 0;
		int end = input.length()-1;
		
		while(start <=  end) {
			
			char startCh = input.charAt(start);
			char endCh = input.charAt(end);
			
			
			if (Character.isAlphabetic(startCh) && Character.isAlphabetic(endCh)) {
				if (Character.toLowerCase(startCh) != Character.toLowerCase(endCh))
					return false;
				
				start++;
				end--;
				
			} else if (!Character.isAlphabetic(startCh) && !Character.isAlphabetic(endCh)) {
				start++;
				end--;
			} else if (!Character.isAlphabetic(startCh)) {
				start++;
			} else {
				end--;
			}
			
		}
		
		return true;
		
	}
	
}
