package array;

public class ArrayHelper {
	
	public static int search(int[] arr, int val) {
		
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == val)
				return i;
		}
		
		return -1;
	}
	
	public static int maxProfitBuyAndSellStock(int[] arr) {
		
		if (arr.length < 2) {
			return 0;
		}
		
		int min = arr[0];
		int maxProfit = 0;
		
		for (int i = 1; i < arr.length; i++) {
			
			int diff = arr[i] - min;
			
			if (diff > maxProfit) {
				maxProfit = diff;
			}
			
			min = Math.min(arr[i], min);
		}                             
		
		
		return maxProfit;
	}

}
