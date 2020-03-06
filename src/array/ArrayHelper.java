package array;

public class ArrayHelper {
	
	public static int search(int[] arr, int val) {
		
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == val)
				return i;
		}
		
		return -1;
	}

}
