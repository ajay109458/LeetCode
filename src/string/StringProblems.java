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
			count+= _countDistinctMatchingSubseq(S, target, sIndex+1, tIndex+1);
		}
		
		count += _countDistinctMatchingSubseq(S, target, sIndex + 1, tIndex);
		
		return count;
	}
	
}
