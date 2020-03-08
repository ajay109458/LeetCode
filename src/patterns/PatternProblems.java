package patterns;

public class PatternProblems {

	public static void printPascalTriangle(int n) {
	
		
		int[][] dp = new int[n][n];
		
		
		for (int i = 0; i < n; i++) {
			
			for (int j = n-i; j > 0; j-- ) {
				System.out.print(" ");
			}
			
			for (int j = 0; j <= i; j++) {
				
				if (j == 0 || j == i) {
					dp[i][j] = 1;
				} else {
					dp[i][j] = dp[i-1][j-1] + dp[i-1][j]; 
				}
				
				System.out.print(dp[i][j] + " ");
			}
			
			System.out.println();
			
		}
		
	}
	
	
	
}
