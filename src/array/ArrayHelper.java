package array;

import linkedlist.ListNode;
import utils.Pair;
import utils.UtilFunctions;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ArrayHelper {

	public ArrayHelper() {

	}

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

	public static int getMinimumLengthUnsortedArray(int[] arr) {

		int s = 0;

		int i = 0;
		for (i = 0; i < arr.length - 1; i++) {
			if (arr[i] > arr[i + 1]) {
				s = i;
				break;
			}
		}

		if (i == arr.length - 1)
			return 0;

		int t = arr.length - 1;

		for (i = arr.length - 1; i > 0; i--) {
			if (arr[i] < arr[i - 1]) {
				t = i;
				break;
			}
		}

		int min = arr[s];
		int max = arr[s];
		for (i = s; i <= t; i++) {
			if (arr[i] < min) {
				min = arr[i];
			}

			if (arr[i] > max) {
				max = arr[i];
			}
		}

		while (true) {
			if (s > 0 && arr[s - 1] > min)
				s--;
			else
				break;
		}

		while (true) {
			if (t < arr.length - 1 && arr[t + 1] < max)
				t++;
			else
				break;
		}


		return t - s + 1;
	}

	public static int[][] mergePairs(int[][] intervals) {
		Stack<Pair> stack = new Stack<>();
		List<Pair> list = new ArrayList<>();

		for (int i = 0; i < intervals.length; i++) {
			Pair pair = new Pair(intervals[i][0], intervals[i][1]);
			list.add(pair);
		}

		Collections.sort(list, (o1, o2) -> {
			Integer y1 = o1.y;
			Integer y2 = o2.y;

			return y1.compareTo(y2);
		});

		for (int i = 0; i < list.size(); i++) {
			Pair pair = list.get(i);
			stack.add(pair);

			while (!stack.isEmpty() && stack.size() >= 2) {
				Pair newPair = stack.pop();
				Pair oldPair = stack.pop();

				if (newPair.x <= oldPair.y) {
					newPair.x = oldPair.x;
					stack.push(newPair);
				} else {
					stack.push(oldPair);
					stack.push(newPair);
					break;
				}
			}
		}

		int[][] result = new int[stack.size()][2];

		for (int i = stack.size() - 1; i >= 0; i--) {
			Pair top = stack.pop();
			result[i][0] = top.x;
			result[i][1] = top.y;
		}

		return result;
	}


	public static List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		Map<Integer, Set<Integer>> minMaxMap = new HashMap();

		Arrays.sort(nums);

		for (int i = 0; i < nums.length; i++) {
			int rSum = -1 * nums[i];

			int left = 0;
			int right = nums.length - 1;

			while (left < right) {
				int cSum = nums[left] + nums[right];

				if (left == i) {
					left++;
					continue;
				}

				if (right == i) {
					right--;
					continue;
				}

				if (cSum == rSum) {

					int min = Math.min(nums[i], Math.min(nums[left], nums[right]));
					int max = Math.max(nums[i], Math.max(nums[left], nums[right]));


					Set<Integer> vals = minMaxMap.get(min);

					if (vals == null || !vals.contains(max)) {
						List<Integer> list = new ArrayList<Integer>();
						list.add(nums[i]);
						list.add(nums[left]);
						list.add(nums[right]);

						if (vals == null) {
							vals = new HashSet<>();
							minMaxMap.put(min, vals);
						}

						vals.add(max);
						result.add(list);
					}


					left++;
					right--;
				} else if (cSum < rSum) {
					left++;
				} else {
					right--;
				}
			}
		}

		return result;
	}


	public boolean isValidSudoku(char[][] board) {

		for (int i = 0; i < 9; i++) {

			Set<Integer> set = new HashSet<>();
			for (int j = 0; j < 9; j++) {

				if (board[i][j] == '.') {
					continue;
				}

				Integer digit = board[i][j] - '0';
				if (digit < 0 || digit > 9) {
					return false;
				}

				if (set.contains(digit)) {
					return false;
				}

				set.add(digit);
			}
		}

		// Sum of each col
		for (int j = 0; j < 9; j++) {
			Set<Integer> set = new HashSet<>();

			for (int i = 0; i < 9; i++) {

				if (board[i][j] == '.') {
					continue;
				}

				Integer digit = board[i][j] - '0';
				if (digit < 0 || digit > 9) {
					return false;
				}

				if (set.contains(digit)) {
					return false;
				}

				set.add(digit);
			}
		}

		// validate sum in each box
		for (int m = 3; m < 9; m = m + 3) {
			for (int n = 3; n <= 9; n = n + 3) {
				Set<Integer> set = new HashSet<>();
				for (int i = m - 3; i < m; i++) {
					for (int j = n - 3; j < n; j++) {
						if (board[i][j] == '.') {
							continue;
						}

						Integer digit = board[i][j] - '0';
						if (digit < 0 || digit > 9) {
							return false;
						}

						if (set.contains(digit)) {
							return false;
						}

						set.add(digit);
					}
				}
			}
		}


		return true;
	}

	public static int trap(int[] height) {

		int[] leftMax = new int[height.length];
		int[] rightMax = new int[height.length];

		for (int i = 1; i < height.length; i++) {
			leftMax[i] = Math.max(leftMax[i - 1], height[i - 1]);
		}

		for (int i = height.length - 2; i >= 0; i--) {
			rightMax[i] = Math.max(rightMax[i + 1], height[i + 1]);
		}

		int result = 0;

		for (int i = 0; i < height.length; i++) {
			int curr = (Math.min(leftMax[i], rightMax[i]) - height[i]);

			if (curr > 0) {
				result += curr;
			}
		}

		return result;
	}

	public static List<List<Integer>> combinationSum(int[] candidates, int target) {

		List<Integer> list = new ArrayList<>();

		List<List<Integer>> result = new ArrayList<>();

		populateList1(candidates, target, list, result, 0);

		return result;
	}

	private static void populateList1(int[] candidates, int target, List<Integer> list, List<List<Integer>> result, int index) {
		if (target == 0) {
			result.add(list);
			return;
		}


		if (index >= candidates.length || target < 0) {
			return;
		}

		if (candidates[index] < target) {
			List temp1 = new ArrayList<>(list);
			temp1.add(candidates[index]);
			populateList1(candidates, target - candidates[index], temp1, result, index + 1);

			List temp2 = new ArrayList<>(list);
			temp2.add(candidates[index]);
			populateList1(candidates, target - candidates[index], temp2, result, index);
		}

		populateList1(candidates, target, list, result, index + 1);
	}

	public static int[] runningSum(int[] nums) {
		int n = nums.length;
		int[] result = new int[n];

		if (n == 0)
			return result;

		result[0] = nums[0];

		for (int i = 0; i < n; i++) {
			result[i] = nums[i] + result[i - 1];
		}

		return result;
	}

	public static List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
		List<Boolean> result = new ArrayList<>();

		int max = Arrays.stream(candies).max().getAsInt();

		for (int candi : candies) {
			result.add(candi + extraCandies >= max);
		}

		return result;
	}

	public int numIdenticalPairs(int[] nums) {
		Arrays.sort(nums);

		int sum = 0;

		int count = 0;
		for(int i = 1; i < nums.length; i++) {

			if (nums[i] == nums[i-1]) {
				count++;
			} else {
				sum += count;
				count = 0;
			}

		}

		return sum;
	}

	public int factorial(int n) {
		if (n == 1)
			return 1;

		return n * factorial(n-1);
	}

	public int[] decompressRLElist(int[] nums) {
		int len = 0;

		for(int i = 0; i < nums.length; i+= 2) {
			len += nums[i];
		}

		int[] result = new int[len];

		int k = 0;
		int i = 1;
		while(k < len) {
			int j = nums[i-1];
			while(j-- > 0) {
				result[k++] = nums[i];
			}

			i += 2;
		}

		return result;
	}

	public int[] createTargetArray(int[] nums, int[] index) {
		List<Integer> list = new ArrayList<>();

		for (int i = 0; i < nums.length; i++) {
			list.add(index[i], nums[i]);
		}

		int[] result = new int[nums.length];

		for (int i = 0; i < nums.length; i++) {
			result[i] = list.get(i);
		}

		return result;
	}

	public int xorOperation(int n, int start) {
		int result = 0;

		for (int i = 0; i < n; i++) {
			result ^= (start + 2*i);
		}

		return result;
	}

	public int sumOddLengthSubarrays(int[] arr) {
		int result = 0;

		int k = 1;
		int n = arr.length;

		int sum = 0;
		for (int val : arr) {
			sum += val;
		}

		while (k < arr.length) {

			result += k * sum;

			for (int i = 1; i < k; i++) {
				result -= (k * (arr[i-1] + arr[n-i]));
			}
		}

		return result;
	}

	public int[][] highFive(int[][] items) {
		Map<Integer, Queue<Integer>> map = new HashMap<>();

		for (int i = 0; i < items.length; i++) {

			Queue q = map.get(items[i][0]);

			if (q == null) {
				q = new PriorityQueue<Integer>(5,Collections.reverseOrder());
				map.put(items[i][0], q);
			}

			q.add(items[i][1]);
		}

		int[][] result = new int[map.size()][2];

		int index = 0;
		for(Map.Entry<Integer, Queue<Integer>> entry : map.entrySet()) {
			Queue q = entry.getValue();

			int sum  =0;

			for (int i = 0; i < 5; i++) {
				sum += (Integer)q.poll();
			}

			result[index][0] = entry.getKey();
			result[index++][1] = (int) Math.ceil(sum/5.0);
		}

		return result;
	}

	public static int countGoodTriplets(int[] arr, int a, int b, int c) {
		int n = arr.length;
		int count = 0;
		for(int i = 0; i < n; i++) {
			for (int j = i+1; j < n; j++) {
				if (Math.abs(arr[i] - arr[j]) > a)
					break;

				for (int k = j+1; k < n; k++) {
					System.out.println(arr[i] + " " + arr[j] + " " + arr[k]);
					if (Math.abs(arr[j] - arr[k]) <= b && Math.abs(arr[i] - arr[k]) <= c ) {
						count++;


					}
				}
			}
		}

		return  count;
	}

	public int minTimeToVisitAllPoints(int[][] points) {

		int sum = 0;

		for (int i = 0; i < points.length-1; i++) {
			sum += (int)Math.sqrt(Math.pow(Math.abs(points[i][0] - points[i+1][0]), 2) + Math.pow(Math.abs(points[i][1] - points[i+1][1]), 2));
		}

		return sum;
	}

	public int[] sumZero(int n) {

		int[] result = new int[n];

		int index = 0;
		for(int i = 0; i <= n/2; i++) {

			if (i == 0) {
				if (n % 2 != 0) {
					result[index++] = 0;
				}
			} else {
				result[index++] = i;
				result[index++] = -1 * i;
			}
		}

		return result;
	}

	public int countNegatives(int[][] grid) {
		int count = 0;

		if (grid.length == 0) {
			return count;
		}

		int i = grid.length - 1;
		int j = 0;

		while (i >= 0 && j < grid[0].length) {
			if (grid[i][j] < 0) {
				count += (grid[j].length - j);
				i--;
			} else {
				j++;
			}
		}

		return count;
	}


	public int[] finalPrices(int[] prices) {
		int[] salePrice = new int[prices.length];

		for(int i = 0; i < prices.length; i++) {

			salePrice[i] = prices[i];
			for(int j = i + 1; j < prices.length; j++) {
				if (prices[j] <= prices[i]) {
					salePrice[i] = prices[i] - prices[j];
					break;
				}
			}
		}

		return salePrice;
	}

	public int sumOfDigits(int[] A) {
		if (A.length == 0) {
			return 1;
		}

		int min = Arrays.stream(A).min().getAsInt();
		int sum = digitSum(min);

		return sum % 2 == 0 ? 1 : 0;
	}

	public int digitSum(int n) {

		int sum = 0;
		while( n != 0) {
			sum += (n%10);
			n /= 10;
		}

		return sum;
	}

	public int[] replaceElements(int[] arr) {
		int[] result = new int[arr.length];

		int maxTillNow = -1;

		for (int i = arr.length-1; i >= 0; i--) {
			result[i] = maxTillNow;

			maxTillNow = Math.max(maxTillNow, arr[i]);
		}


		return result;
	}

	public boolean canBeEqual(int[] target, int[] arr) {

		Map<Integer, Integer> map = new HashMap<>();

		for (int val : target) {
			Integer count = map.get(val);
			if (count == null) {
				map.put(val, 1);
			} else {
				map.put(val, count + 1);
			}
		}


		for(int val: arr) {
			Integer count = map.get(val);
			if (count == 0) {
				return false;
			}

			map.put(val, count - 1);
		}

		for(Map.Entry<Integer, Integer> entry: map.entrySet()) {
			if (entry.getValue() > 0) {
				return false;
			}
		}

		return true;
	}


	public int arrayPairSum(int[] nums) {
		Arrays.sort(nums);

		int sum = 0;
		for (int i = 0; i < nums.length; i+= 2) {
			sum += nums[i];
		}

		return sum;
	}

	public boolean canMakeArithmeticProgression(int[] arr) {
		// Array can be empty
		if (arr.length < 2) {
			return false;
		}

		Arrays.sort(arr);

		int diff = arr[1] - arr[0];

		for (int i = 1; i < arr.length; i++) {
			if (diff != arr[i] - arr[i-1]) {
				return false;
			}
		}

		return true;
	}

	public int[] sortedSquares(int[] A) {
		int left = 0;
		int right = A.length - 1;
		int[] result = new int[A.length];
		int index = right;

		while (left <= right) {

			int valLeft = Math.abs(A[left]);
			int valRight = Math.abs(A[right]);

			if (valLeft <= valRight) {
				result[index--]= valRight * valRight;
				right--;
			} else {
				result[index--] = valLeft *  valLeft;
				left++;
			}

		}

		return result;
	}

	public int[] kWeakestRows(int[][] mat, int k) {
		List<Pair> list  = new ArrayList<>();

		for (int i = 0; i < mat.length; i++) {
			int count = 0;

			for (int j = 0; j < mat[i].length; i++) {
				if (mat[i][j] == 0) {
					break;
				}
				count++;
			}
			list.add(new Pair(i, count));
		}

		Collections.sort(list, new Comparator<Pair>() {
			@Override
			public int compare(Pair o1, Pair o2) {
				return Integer.compare(o1.y, o2.y);
			}
		});

		int[] result = new int[k];
		for(int i = 0; i < k; i++) {
			result[i]  = list.get(list.size() - 1 - i).x;
		}

		return result;
	}

	public int countCharacters(String[] words, String chars) {
		int[] cache = new int[26];

		for (char ch : chars.toCharArray()) {
			cache[ch - 'a']++;
		}

		int result = 0;

		for(String word : words) {
			int[] tempCache = new int[26];

			for (char ch : word.toCharArray()) {
				tempCache[ch - 'a']++;
			}

			boolean isGoodWord = true;
			for(int i = 0; i < 26; i++) {
				if (tempCache[i] > cache[i]) {
					isGoodWord = false;
				}
			}

			if (isGoodWord) {
				result += word.length();
			}
		}

		return result;
	}

	public int numRookCaptures(char[][] board) {

		int i = 0;
		int j = 0;
		for (i = 0; i < 8; i++) {
			for (j = 0; j < 8; j++) {
				if (board[i][j] == 'R') {
					break;
				}
			}
		}

		int result = 0;
		// Upward
		for (int x = i-1; x >= 0; x--) {
			if (board[x][j] == 'p') {
				result++;
				break;
			}
		}

		for (int x = i+1; x < 8; x++) {
			if (board[x][j] == 'p') {
				result++;
				break;
			}
		}

		for (int x = j+1; x < 8; x++) {
			if (board[i][x] == 'p') {
				result++;
				break;
			}
		}

		for (int x = j-1; x >= 0; x--) {
			if (board[i][x] == 'p') {
				result++;
				break;
			}
		}


		return result;
	}

	public double trimMean(int[] arr) {
		Arrays.sort(arr);

		int n = arr.length;

		int count = (n * 5)/100;

		int sum = 0;
		for (int i = count; i < n - count; i++) {
			sum += (arr[i]);
		}

		return sum/(n - 2 * count);

	}

	public List<List<Integer>> minimumAbsDifference(int[] arr) {
		Arrays.sort(arr);

		int minDiff = Integer.MAX_VALUE;

		for(int i = 1; i < arr.length; i++) {
			minDiff = Math.min( minDiff, Math.abs(arr[i] - arr[i-1]));
		}

		List<List<Integer>> result = new ArrayList<>();
		for (int i = 1; i < arr.length; i++) {
			if (Math.abs(arr[i] - arr[i-1]) == minDiff) {
				List<Integer> list = Arrays.asList(arr[i-1], arr[i]);
				result.add(list);
			}
		}

		return result;
	}


	public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
		int result = 0;
		for (int val1 :  arr1) {
			boolean isPossible = true;
			for (int val2 : arr2) {
				if (Math.abs(val2 - val1) <= d) {
					isPossible = false;
					break;
				}
			}

			if (isPossible) {
				result++;
			}
		}

		return result;
	}

	public int countLargestGroup(int n) {
		Map<Integer, Integer> countByDigitSumMap = new HashMap<>();

		int maxSum = 0;

		for (int i = 1; i <=n; i++) {
			int sum = digitSum(i);

			maxSum = Math.max(maxSum, sum);

			Integer count = countByDigitSumMap.get(sum);

			if (count == null) {
				count = 0;
			}

			countByDigitSumMap.put(sum, count + 1);
		}


		return countByDigitSumMap.get(maxSum);
	}

	public int numSpecial(int[][] mat) {
		int rows = mat.length;

		if (rows == 0) {
			return 0;
		}

		int  cols = mat[0].length;

		int[] rowArr = new int[rows];
		int[] colArr = new int[cols];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (mat[i][j] == 1) {
					rowArr[i] += 1;
					colArr[i] += 1;
				}
			}
		}

		int result = 0;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (rowArr[i] == 1 && colArr[i] == 1) {
					result++;
				}
			}
		}
		return result;
	}

	public int findLucky(int[] arr) {
		Map<Integer, Integer> map = new HashMap<>();

		int maxFreq = 0;

		int lucky = -1;

		for (int val: arr) {

			Integer count = map.get(val);

			if (count == null) {
				count = 0;
			}

			map.put(val, count + 1);
			maxFreq = Math.max(maxFreq, count + 1);
		}


		for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
			if (entry.getValue() == maxFreq && entry.getKey() == entry.getValue()) {
				lucky = Math.max(lucky, entry.getKey());
			}
		}

		return lucky;
	}

	public int specialArray(int[] nums) {
		Arrays.sort(nums);

		int count = 0;

		for (int i = nums.length -1; i >= 0; i--) {

			if (nums[i] > count) {
				count++;
			} else {
				break;
			}

		}

		return count == 0 ? -1 : count;
	}

	public char slowestKey(int[] releaseTimes, String keysPressed) {
		int[] cache = new int[26];

		int max = 0;
		for(int i = 0; i < keysPressed.length(); i++) {
			int index = keysPressed.charAt(i)-'a';
			cache[index] = Math.max(cache[index], i == 0 ? releaseTimes[i] : releaseTimes[i] - releaseTimes[i-1]);
			max = Math.max(max, cache[index]);
		}

		Character ch = null;
		for (int i = 0; i < 26; i++) {
			if (cache[i] == max) {
				ch = (char)(i + 'a');
			}
		}

		return ch;
	}

	public int twoSumLessThanK(int[] A, int K) {
		Arrays.sort(A);

		int left = 0;
		int right = A.length;
		int max = 0;
		while (left < right) {

			int cSum = A[left] + A[right];

			if (cSum >= K) {
				right--;
			} else {
				max = Math.max(cSum, max);
				left++;
			}
		}

		return  (max == 0) ? -1 : max;
	}

	public int[] numSmallerByFrequency(String[] queries, String[] words) {
		int[] freqQueries = new int[queries.length];

		for (int i = 0; i < freqQueries.length; i++) {
			freqQueries[i] = getFrequency(queries[i]);
		}

		int[] wordsFreq = new int[words.length];

		for (int i = 0; i < words.length; i++) {
			wordsFreq[i] = getFrequency(words[i]);
		}

		int[] result = new int[freqQueries.length];

		for (int i = 0; i < freqQueries.length; i++) {

			int count = 0;

			for (int j = 0; j < words.length; j++) {
				if (freqQueries[i] < wordsFreq[j]) {
					count++;
				}
			}

			result[i] = count;
		}

		return result;

	}

	private int getFrequency(String word) {
		int[] cache = new int[26];

		for(char ch : word.toCharArray()) {
			cache[ch-'a']++;
		}

		for(int i = 0; i < 26; i++) {
			if (cache[i] != 0) {
				return cache[i];
			}
		}

		return 0;
	}

	public int[] fairCandySwap(int[] A, int[] B) {
		int sum1 = Arrays.stream(A).sum();
		int sum2 = Arrays.stream(B).sum();

		int diff = (sum1 - sum2)/2;


		int[] result = new int[2];
		for (int val1 : A) {
			for (int val2 : B) {
				if ((val1 - val2) == diff) {
					result[0] = val1;
					result[1] = val2;
					break;
				}
			}
		}

		return result;


	}

	public boolean isMajorityElement(int[] nums, int target) {

		int left = 0;
		int right = nums.length - 1;

		int startIndex = -1;

		while (left <= right) {
			int mid = (left + right) / 2;

			if (nums[mid] == target && (mid == 0 || nums[mid - 1] != target)) {
				startIndex = mid;
				break;
			} else if (nums[mid] == target) {
				right = mid - 1;
			} else if (nums[mid] > target) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}

		if (startIndex == -1) {
			return false;
		}

		if (startIndex + nums.length / 2 < nums.length && nums[startIndex + nums.length / 2] == target) {
			return true;
		}

		return false;

	}

	public int dietPlanPerformance(int[] calories, int k, int lower, int upper) {

		int currCal = 0;
		int i ;
		for (i = 0; i < k-1; i++) {
			currCal += calories[i];
		}

		int points = 0;

		while( i  < calories.length) {

			currCal += calories[i];

			if (currCal < lower) {
				points -= 1;
			}

			if (currCal > upper) {
				points += 1;
			}

			i++;
		}

		return points;
	}

	public int findKthPositive(int[] arr, int k) {
		int i = 0;
		int cnum = 1;
		int count = 0;


		while (count != k) {
			if (cnum == arr[i] && i < arr.length) {
				i++;
			} else {
				count++;
			}

			if (count == k) {
				return cnum;
			}

			cnum++;
		}

		return 0;
	}

	public void duplicateZeros(int[] arr) {

		int countZeros = 0;

		int n = arr.length -1 ;
		for (int i = 0; i <= n - countZeros; i++) {
			if (arr[i] == 0) {


				if (i == n - countZeros) {
					arr[n] = 0;
					n--;
					break;
				}
				countZeros++;
			}
		}

		int j = n;
		int i = n-countZeros;

		while (i >= 0 && j >=0) {
			arr[j--] = arr[i];
			if (arr[i] == 0) {
				arr[j--] = arr[i];
			}
			i--;
		}
	}

	public List<Integer> getRow(int rowIndex) {
		int n = rowIndex;

		List<Integer> result = new ArrayList<>();

		for (int  i = 0; i <= n; i++) {
			int max = Math.max(i, n - i);
			int min = Math.min(i, n -i);

			long value = 1;
			int k = 0;
			while(k < min) {
				value *= (n-k)/(k+1 == 0 ? 1 : k+1);
				k++;
			}

			result.add((int)value);
		}

		return result;
	}


	public List<Integer> transformArray(int[] arr) {


		while(true) {

			int i = 1;
			boolean isChange = false;
			for (; i < arr.length-1; i++) {

				if (arr[i] < arr[i+1] && arr[i] < arr[i-1]) {
					isChange = true;
					arr[i]++;
				}

				if (arr[i] > arr[i+1] && arr[i] > arr[i-1]) {
					isChange = true;
					arr[i]--;
				}

			}
			if (isChange == false) {
				break;
			}
		}

		return Arrays.stream(arr).boxed().collect(Collectors.toList());
	}

	public boolean canThreePartsEqualSum(int[] A) {
		int sum = Arrays.stream(A).sum();

		if (sum % 3 != 0) {
			return false;
		}

		int partitionSum = sum / 3;

		int cSum = 0;
		for (int val : A) {
			cSum += val;

			if (cSum == partitionSum) {
				cSum = 0;
			} else if (cSum > partitionSum) {
				return false;
			}
		}

		if (cSum == 0) {
			return true;
		}

		return false;
	}

	public int numPairsDivisibleBy60(int[] time) {
		Map<Integer, Integer> countByReminder = new HashMap<>();

		for (int t : time) {
			Integer count = countByReminder.get(t%60);
			if (count == null) {
				count = 0;
			}

			countByReminder.put(t%60, count + 1);
		}

		int pairsCount = 0;

		for(Map.Entry<Integer, Integer> entry : countByReminder.entrySet()) {
			if(entry.getValue() != null && countByReminder.get(60 - entry.getKey()) != null) {
				if (entry.getKey() == 60 -entry.getKey()) {
					pairsCount = entry.getValue()/2;
				} else {
					pairsCount += Math.max(entry.getValue(), countByReminder.get(60 - entry.getKey()));
				}
			}
		}

		return pairsCount;
	}

	public int findLengthOfLCIS(int[] nums) {
		int n = nums.length;
		int[] lis = new int[n];

		int result = 0;

		for (int i = 0; i < n; i++) {
			lis[i] = 1;

			for (int j = i - 1; j >=0; j--) {
				if (nums[i] > nums[j]) {
					lis[i] = Math.max(lis[i], lis[j] + 1);
					result = Math.max(result, lis[i]);
				}
			}
		}

		return result;
	}

	public int findLengthOfLCIS1(int[] nums) {
		int n = nums.length;

		int result = 1;

		int cLen = 1;
		for (int i = 1; i < n; i++) {
			if (nums[i] > nums[i+1]) {
				cLen++;
			} else {
				result = Math.max(result, cLen);
				cLen = 1;
			}
		}

		return result;
	}

	public int numEquivDominoPairs(int[][] dominoes) {
		Map<Integer, Set<Integer>> map = new HashMap<>();

		int count = 0;
		for (int[] domino : dominoes) {
			boolean isPresent = false;

			Set<Integer>  values = map.get(domino[0]);
			if (values != null) {
				if (values.contains(domino[1])) {
					count++;
				} else {
					values.add(domino[1]);
				}
			} else {
				values = new HashSet<>();
				values.add((domino[1]));
				map.put(domino[0], values);
			}

			values = map.get(domino[1]);
			if (values == null) {
				values = new HashSet<>();
				values.add(domino[0]);
				map.put(domino[1], values);
			} else {
				if (values.contains(domino[0])) {
					count++;
				} else {
					values.add(domino[0]);
				}
			}
		}

		return count;
	}

	public int pivotIndex(int[] nums) {
		int sum = Arrays.stream(nums).sum();

		int cSum = 0;
		for(int i = 0; i < nums.length; i++) {
			sum -= nums[i];
			if (cSum == sum) {
				return i;
			}

			sum += nums[i];
		}

		return  -1;
	}

	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	public static void swap(char[] arr, int i, int j) {
		char temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	public void sortColors(int[] nums) {
		int i = -1;
		int k = nums.length;

		int j = 0;

		while(j < nums.length) {
			if (nums[j] == 0) {
				swap(nums, ++i, j++);
			} else if (nums[j] == 2) {
				swap(nums, --k, j);
			} else {
				j++;
			}
		}
	}

	public String removeKdigits(String num, int k) {
		Stack<Integer> stack = new Stack<>();

		for(char ch : num.toCharArray()) {
			int digit = ch - '0';

			while (!stack.isEmpty() && stack.peek() > digit && k > 0) {
				stack.pop();
				k--;
			}

			if(!stack.isEmpty() || digit !=0 )
				stack.push(digit);
		}

		while(k-- > 0 && !stack.isEmpty()) {
			stack.pop();
		}

		if (stack.isEmpty())
			return "0";

		StringBuilder result = new StringBuilder();

		while(!stack.isEmpty()) {
			result.insert(0, stack.pop());
		}

		return result.toString();
 	}

	public int findCircleNum(int[][] M) {
		int rows = M.length;

		if (rows == 0) {
			return 0;
		}

		int cols = M[0].length;

		int count = 0;
		for(int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (M[i][j] == 1) {
					dfs(M, i, j, rows, cols);
					count += 1;
				}
			}
		}

		return count;
	}

	public void dfs(int[][] M, int i, int j, int rows, int cols) {

		if (i < 0 || i >= rows) {
			return;
		}

		if (j < 0 || j >= cols) {
			return;
		}

		if (M[i][j] == 0 || M[i][j] == 2) {
			return;
		}

		if(M[i][j] == 1) {
			M[i][j] = 2;
			return;
		}
	}

	public int romanToInt(String s) {
		Map<Character, Integer> valByCharMap = new HashMap<>();

		valByCharMap.put('I', 1);
		valByCharMap.put('V', 50);
		valByCharMap.put('X', 10);
		valByCharMap.put('L', 50);
		valByCharMap.put('C', 100);
		valByCharMap.put('D', 500);
		valByCharMap.put('M', 1000);

		int result = 0;
		Character prev = null;
		for (char ch : s.toCharArray()) {

			if (prev == null) {
				result += valByCharMap.get(ch);
			} else {
				int prevValue = valByCharMap.get(prev);
				int currValue = valByCharMap.get(ch);

				if (prevValue >= currValue) {
					result += currValue;
				} else {
					result = (currValue - prevValue - prevValue);
				}
			}
		}

		return result;
	}


	public static int minOperations(int[] nums, int x) {
		int[][] dp = new int[nums.length][nums.length];
		return minOperations(nums,x, 0, nums.length -1, 0, dp);
	}

	private static int minOperations(int[] nums, int x, int left, int right, int count, int[][] dp) {
		if ( x == 0) {
			return count;
		}

		if (x < -1) {
			return -1;
		}

		if (left > right) {
			return -1;
		}

		if (dp[left][right] != 0) {
			return dp[left][right];
		}

		int op1 = minOperations(nums, x - nums[left], left+1, right, count + 1, dp);
		int op2 = minOperations(nums, x - nums[right], left, right-1, count + 1, dp);

		int result = 0;
		if (op1 != -1 && op2 != -1) {
			result =  Math.min(op1, op2);
		} else if (op1 != -1) {
			result =  op1;
		} else if (op2 != -1) {
			result = op2;
		} else {
			result =  -1;
		}

		dp[left][right] = result;

		return result;
	}

	public static  int waysToMakeFair(int[] nums) {
		boolean isOdd = true;
		int count = 0;

		for (int i = 0; i < nums.length; i++) {
			int oddSum = 0;
			int evenSum = 0;

			for (int j = 0; j < nums.length; j++) {
				if (i != j) {
					if (isOdd) {
						oddSum += nums[j];
					} else {
						evenSum += nums[j];
					}
					isOdd = !isOdd;
				}
			}

			if (oddSum == evenSum) {
				count++;
			}
		}

		return count;
	}

	public static  int waysToMakeFair1(int[] nums) {
		int n = nums.length;

		int[] sumBeforeMe = new int[n];
		int[] sumAfterMe = new int[n];


		for (int i = 1; i < nums.length; i++) {
			if (i%2 != 0) {
				sumBeforeMe[i] = sumBeforeMe[i-1] + nums[i-1];
			} else {
				sumBeforeMe[i] = sumBeforeMe[i-1] - nums[i-1];
			}
		}

		for (int i = n-2; i >= 0 ; i--) {
			if (i%2 != 0) {
				sumAfterMe[i] = sumAfterMe[i+1] + nums[i+1];
			} else {
				sumAfterMe[i] = sumAfterMe[i+1] - nums[i+1];
			}
		}

		int count = 0;

		for(int i = 0; i < n; i++) {
			if (sumBeforeMe[i] == sumAfterMe[i]) {
				count++;
			}
		}

		return count;
	}

	public int minimumEffort(int[][] tasks) {
		int enegryToPerform = 0;
		Integer minValue = null;

		for (int i = 0; i < tasks.length; i++) {
			enegryToPerform += tasks[i][0];

			int eDiff = tasks[i][1] - tasks[i][0];
			if (minValue == null || (eDiff) <  minValue) {
				minValue = eDiff;
			}
		}

		enegryToPerform =  enegryToPerform + minValue;

		for (int i = 0; i < tasks.length; i++) {
			if (enegryToPerform < tasks[i][1]) {
				enegryToPerform = tasks[i][1];
			}
		}

		return  enegryToPerform;
	}

	public int maximumWealth(int[][] accounts) {
		AtomicInteger max = new AtomicInteger();
		Arrays.stream(accounts).forEach(arr -> {
			int sum = Arrays.stream(arr).sum();
			max.set(Math.max(sum, max.get()));
		});

		return max.get();
	}




	public static int minMoves(int[] nums, int limit) {
		Map<Integer, List<MovePair>> map = new HashMap<>();

		int maxCount = 0;
		int sumForMaxCount = 0;
		int n = nums.length;
		for(int i = 0; i < nums.length; i++) {
			int sum = nums[i] + nums[n-1-i];

			List<MovePair> pairs = map.get(sum);
			if (pairs == null) {
				pairs = new ArrayList<>();
				map.put(sum, pairs);
			}

			pairs.add(new MovePair(nums[i], nums[n-1-i]));

			if (maxCount < pairs.size()) {
				maxCount = pairs.size();
				sumForMaxCount = sum;
			}
		}

		int ops = 0;
		for(Map.Entry<Integer, List<MovePair>> entry : map.entrySet()) {
			int sum = entry.getKey();
			List<MovePair> pairs = entry.getValue();

			int diff = Math.abs(sumForMaxCount - sum);
			if (diff > 0) {
				int count = 0;
				for (MovePair pair : pairs) {
					if (Math.min(pair.x, pair.y) + diff <= limit) {
						count++;
					} else {
						count += 2;
					}
				}

				ops += (count / 2);
			}
		}
		return ops;
	}

	private static class MovePair {
		public int x;
		public int y;

		public MovePair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

 	public static int numUniqueEmails(String[] emails) {
		return (int)Arrays.stream(emails)
				.map(email -> {
					int index = email.indexOf('@');
					String emailPrefix = email.substring(0, index).replaceAll("\\.", "");
					int plusIndex =  emailPrefix.indexOf("+");
					emailPrefix = (plusIndex == -1) ? emailPrefix : emailPrefix.substring(0, plusIndex);

					return emailPrefix + email.substring(index);
				})
				.distinct()
				.count();
	}

	public static String licenseKeyFormatting(String S, int K) {

		if (S == null || S.isEmpty()) {
			return S;
		}

		int firstHypenIndex = S.indexOf("-");
		if (firstHypenIndex == -1) {
			return S;
		}

		String prefix = S.substring(0, firstHypenIndex);
		String suffix = S.substring(firstHypenIndex+1);

		String temp = prefix;
		List<String> results = new ArrayList<>();


		for(Character ch : suffix.toCharArray()) {
			if (temp.length() == K) {
				results.add(temp);
				temp = "";
			}

			if (ch != '-') {
				temp += ch;
			}
		}

		if (!temp.isEmpty())
			results.add(temp);

		return String.join("-", results).toUpperCase();
	}

	public int[] twoSum(int[] nums, int target) {


		for(int i = 0; i < nums.length; i++) {
			for(int j = i+1; j < nums.length; j++) {
				if (nums[i] + nums[j] == target) {
					return new int[]{i, j};
				}
			}
		}

		return new int[]{};
	}


	public static  boolean isPalindrome(String s) {
		String s1 = s.toLowerCase().chars()
				.mapToObj(ch -> (char)ch)
				.filter(ch -> ((ch >= 'a' && ch <= 'z') || (ch >= '0' && ch <= '9')))
				.collect(Collector.of(StringBuilder::new, StringBuilder::append, StringBuilder::append, StringBuilder::toString));

		String s2 = new StringBuilder(s1).reverse().toString();

		return s1.equals(s2);
	}

	public String reverseWords(String s) {
		String[] arr = s.trim().split(" ");

		List<String> list = Arrays.stream(arr)
				.filter(val -> !val.trim().isEmpty())
				.collect(Collectors.toList());
		Collections.reverse(list);

		return String.join(" ", list);
	}


	/**
	 * Sort
	 *
	 * Two pointer approach
	 */

	public int maxOperations(int[] nums, int k) {
		Map<Integer, Integer> countByNum = new HashMap<>();
		int resultCount = 0;

		for(int val: nums) {

			if (k - val < 0)
				continue;

			Integer secondNumberCount = countByNum.get(k - val);

			if (secondNumberCount == null || secondNumberCount == 0) {
				Integer currentNumCount = countByNum.get(val);
				if (currentNumCount == null) {
					currentNumCount = 0;
				}
				countByNum.put(val, currentNumCount + 1);
			} else {
				countByNum.put(k-val, secondNumberCount -1);
				resultCount++;
			}
		}

		return resultCount;
	}

	public boolean canFormArray(int[] arr, int[][] pieces) {
		Map<Integer, int[]> map = new HashMap<>();

		for(int[] piece:  pieces) {
			map.put(piece[0], piece);
		}

		int i = 0;

		while (i < arr.length) {
			int[] tempArr = map.get(arr[i]);

			if (tempArr == null) {
				return false;
			}

			for(int j = 0; i < tempArr.length; j++) {
				if (tempArr[j] != arr[i++]) {
					return false;
				}
			}
		}

		return true;
	}


	public int numTriplets(int[] nums1, int[] nums2) {
		return tripletCheck(nums1, nums2) + tripletCheck(nums2, nums1);
	}

	private int tripletCheck(int[] nums1, int[] nums2) {
		Map<Integer, Long> countByValMap =  Arrays.stream(nums1)
				.boxed()
				.map(val -> val * val)
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

		int res = 0;
		for (int i = 0; i < nums2.length; i++) {
			for (int j = i+ 1; j < nums2.length; j++) {
				Long count = countByValMap.get(nums2[i] * nums2[j]);

				if (count != null) {
					res += count;
				}
			}
		}

		return res;
	}

	public static ListNode mergeKLists(ListNode[] lists) {

		if (lists.length == 0) {
			return null;
		}

		ListNode result =  lists[0];

		for(int i = 1 ; i < lists.length; i++) {
			result = mergeList(result, lists[i]);
		}

		return result;
	}

	public static ListNode mergeList(ListNode head1, ListNode head2) {

		ListNode rHead = null;
		ListNode r = null;

		while(head1 != null && head2 != null) {
			ListNode temp = null;
			if (head1.val < head2.val) {
				temp = head1;
				head1 = head1.next;
			} else {
				temp = head2;
				head2 = head2.next;
			}

			temp.next = null;
			if(rHead == null) {
				rHead = temp;
				r = temp;
			} else{
				r.next = temp;
				r = r.next;
			}
		}

		if (rHead == null) {
			return (head1 == null) ? head2 : head1;
		} else {
			r.next = (head1 == null) ? head2 : head1;
		}

		return rHead;
	}

	public static int jump(int[] nums) {
		return makeJump(nums, 0, 0);
	}

	private static int makeJump(int[] nums, int index, int count) {
		if (index >= nums.length-1) {
			return count;
		}

		Integer min = null;
		for(int i = 1; i <= nums[index]; i++) {
			int jumpCount = makeJump(nums, index + i, count +1);
			if (min == null) {
				min = jumpCount;
			} else {
				min = Math.min(min, jumpCount);
			}
		}

		return min != null ? min: Integer.MAX_VALUE;
	}

	public static int makeJump(int[] nums) {
		int n = nums.length;
		int[] jumps = new int[n];

		if (n == 0) {
			return 0;
		}

		jumps[0] = 0;

		for(int i = 1; i < n; i++) {
			jumps[i] = Integer.MAX_VALUE;

			for(int j = 0; j < i; j++) {
				if (j + nums[j] >= i && jumps[j] != Integer.MIN_VALUE) {
					jumps[i] = Math.min(jumps[i], 1 + jumps[j]);
					break;
				}
			}

			System.out.println(Arrays.toString(jumps));
		}


		return jumps[n-1];
	}


	public List<String> letterCombinations(String digits) {
		Map<Integer, String> map = new HashMap<>();
		map.put(2, "abc");
		map.put(3, "def");
		map.put(4, "ghi");
		map.put(5, "jkl");
		map.put(6, "mno");
		map.put(7, "pqrs");
		map.put(8, "tuv");
		map.put(9, "wxyz");
		StringBuilder builder = new StringBuilder(digits);

		int digitNumber = Integer.parseInt(builder.reverse().toString());

		List<String> values = new ArrayList<>();
		generateValues(map, "", digitNumber, values);
		return values;
	}

	public void  generateValues(Map<Integer, String> map, String result, int digits, List<String> values) {

		if (digits == 0) {
			values.add(result);
			return ;
		}

		int digit = digits % 10;

		char[] chars = map.get(digit).toCharArray();
		for(char ch : chars) {
			generateValues(map, result + ch, digits/10, values);
		}
	}

	public static int threeSumClosest(int[] nums, int target) {

		Arrays.sort(nums);

		int diff = Integer.MAX_VALUE;

		int selectedSum = 0;

		for (int i = 0; i < nums.length; i++) {

			int remainingSum = target - nums[i];

			int leftIndex = 0;
			int rightIndex = nums.length -1;

			while(leftIndex < rightIndex) {
				int currSubSum = nums[leftIndex] + nums[rightIndex] ;
				int currDiff = Math.abs(remainingSum - currSubSum);
				if( leftIndex != i && rightIndex != i) {
					if (currDiff < diff) {
						selectedSum = currSubSum + nums[i];
						diff = currDiff;
					}

					if (currSubSum == remainingSum) {
						return target;
					}
				}

				if (currSubSum < remainingSum) {
					leftIndex++;
				} else{
					rightIndex--;
				}
			}
		}

		return selectedSum;
	}


	public int maxAreaOfIsland(int[][] grid) {

		int maxArea = 0;
		for(int i = 0; i < grid.length; i++) {
			for (int j = 0; i < grid[0].length; j++) {
				if (grid[i][j] == 1) {
					maxArea = Math.max(maxArea, dfsGetArea(grid, i, j));
				}
			}
		}

		return maxArea;
	}

	private int dfsGetArea(int[][] grid, int i, int j){

		if (i < 0 || i >= grid.length) {
			return 0;
		}

		if (j < 0 || j > grid[0].length) {
			return 0;
		}

		if (grid[i][j] == 2 || grid[i][j] == 0) {
			return 0;
		}

		grid[i][j] = 2;

		return 1 + dfsGetArea(grid, i+1, j)
				+ dfsGetArea(grid, i - 1, j)
				+ dfsGetArea(grid, i, j+1)
				+ dfsGetArea(grid, i, j-1);
	}

	public static String getHint(String secret, String guess) {
		Map<Character, Set<Integer>> indiciesByDigitMap = new HashMap<>();
		Map<Character, Set<Integer>> guessIndiciesByDigitMap = new HashMap<>();

		char[] secArr = secret.toCharArray();
		for(int i = 0; i < secArr.length; i++) {
			Set<Integer> indicies = indiciesByDigitMap.get(secArr[i]);
			if (indicies == null) {
				indicies = new HashSet<>();
				indiciesByDigitMap.put(secArr[i], indicies);
			}
			indicies.add(i);
		}

		int bulls = 0;
		int cows = 0;

		char[] guessArr = guess.toCharArray();
		for(int i = 0; i < guessArr.length; i++) {
			Set<Integer> indicies = guessIndiciesByDigitMap.get(guessArr[i]);
			if (indicies == null) {
				indicies = new HashSet<>();
				guessIndiciesByDigitMap.put(guessArr[i], indicies);
			}
			indicies.add(i);
		}

		for(char ch = '0'; ch <= '9'; ch++) {
			Set<Integer> secIndicies = indiciesByDigitMap.get(ch);
			Set<Integer> guessIndicies = guessIndiciesByDigitMap.get(ch);
			if (secIndicies != null && guessIndicies != null ) {
				int common = 0;
				for(Integer val : secIndicies) {
					if (guessIndicies.contains(val)) {
						common++;
					}
				}

				bulls += common;
				cows += Math.min(secIndicies.size(), guessIndicies.size()) - common;
			}
		}

		return bulls + "A" + cows + "B";
	}


	public static int countBalls(int lowLimit, int highLimit) {
		Map<Integer, Integer> countBySum  = new HashMap<>();
		int max = 0;
		for(int i = lowLimit; i <= highLimit; i++) {
			int sum = UtilFunctions.sumOfDigits(i);

			Integer count = countBySum.get(sum);
			if (count == null) {
				count = 0;
			}
			count++;
			countBySum.put(sum, count);
			max = Math.max(max, count);
		}

		return max;
	}

	public void restoreArray(int[][] adjacentPairs) {
		Map<Integer, Integer> fwdMap = new HashMap<>();
		Map<Integer, Integer> bwdMap = new HashMap<>();

		for (int i = 0; i < adjacentPairs.length; i++) {
			fwdMap.put(adjacentPairs[i][0], adjacentPairs[i][1]);
			bwdMap.put(adjacentPairs[i][1], adjacentPairs[i][0]);
		}
	}

	public static List<Integer> findClosestElements(int[] arr, int k, int x) {
		int left = 0;
		int right = arr.length - 1;

		while (left <= right) {
			int mid = (left + right)/2;

			if (arr[mid] == x) {
				left = mid-1;
				right = mid;
				break;
			} else if (x < arr[left]) {
				right = left;
				left--;
				break;
			} else if (x > arr[right]) {
				left = right;
				right++;
				break;
			} else if (arr[left] < x && x < arr[right] && right - left == 1) {
				break;
			} else if (x > arr[mid]) {
				left = mid + 1;
			} else if (x < arr[mid]) {
				right = mid - 1;
			}
		}

		List<Integer> result = new ArrayList<>();
		while (left >= 0 && right < arr.length) {

			if (Math.abs(arr[left] - x) < Math.abs(arr[right] -x)) {
				result.add(arr[left--]);
			} else if (Math.abs(arr[left] - x) > Math.abs(arr[right] -x)) {
				result.add(arr[right++]);
			} else if (arr[left] < arr[right]) {
				result.add(arr[left--]);
			} else if (arr[right] < arr[left]) {
				result.add(arr[right++]);
			} else {
				result.add(arr[left--]);
				result.add(arr[right++]);
			}

			if (result.size() == k) {
				break;
			}
		}

		while(result.size() < k) {
			if (left >= 0) {
				result.add(arr[left--]);
			}

			if (right < arr.length) {
				result.add(arr[right++]);
			}
		}

		Collections.sort(result);

		return result;
	}

	public static int compareVersion(String version1, String version2) {
		String[] ver1Arr = version1.split("\\.");
		String[] ver2Arr = version2.split("\\.");

		int i = 0;

		while (i  < Math.min(ver1Arr.length, ver2Arr.length)) {
			int par1 = Integer.parseInt(ver1Arr[i]);
			int par2 = Integer.parseInt(ver2Arr[i]);

			if (par1 < par2) {
				return -1;
			} else  if (par1 > par2) {
				return 1;
			}

			i++;
		}

		while(i < ver1Arr.length) {
			int par1 = Integer.parseInt(ver1Arr[i]);
			if (par1 > 0) {
				return 1;
			}
			i++;
		}

		while(i < ver2Arr.length) {
			int par2 = Integer.parseInt(ver2Arr[i]);
			if (par2 > 0) {
				return -1;
			}
			i++;
		}

		return 0;
	}

	public int largestRectangleArea(int[] heights) {
		int area = 0;

		for (int i = 0; i < heights.length; i++) {
			int localArea = heights[i];

			for(int j = i + 1; j < heights.length; j++) {
				if (heights[j] < heights[j-1]) {
					area = Math.max(area, localArea);
					break;
				}
				localArea += heights[j];
			}
		}

		return area;
	}

	public int[] dailyTemperatures(int[] T) {
		Stack<Pair> stack = new Stack<>();

		int[] result = new int[T.length];

		if (T.length == 0) {
			return  result;
		}

		for (int i = 0; i < T.length; i++) {
			Pair peekPair = stack.peek();

			if (!stack.isEmpty()) {
				while(!stack.isEmpty() && peekPair.x < T[i]) {
					result[peekPair.y] = i-peekPair.y;
				}
			}

			stack.push(new Pair(T[i], i));
		}

		return result;
	}

	public double myPow(double x, int n) {
		if (n < 0) {
			n = -n;
			x = 1.0/x;
		}

		double ans = 1;

		for(int i = 0; i < n; i++) {
			ans *= x;
		}

		return ans;
	}

	public boolean searchMatrix(int[][] matrix, int target) {
		int rowStart = 0;
		int rowEnd = matrix.length -1;

		while(rowStart <= rowEnd) {
			int mid = (rowStart + rowEnd) /2;

			if (mid == 0 && target < matrix[mid][0]) {
				return false;
			} else if (mid == matrix.length -1 && target > matrix[mid][matrix[0].length -1]) {
				return false;
			} else if (mid == matrix.length - 1) {
				int index =  Arrays.binarySearch(matrix[matrix.length-1], target);
				return index != -1;
			} else if (matrix[mid][0] <= target && target < matrix[mid+1][0]) {
				int index =  Arrays.binarySearch(matrix[mid], target);
				return index != -1;
			} else if (matrix[mid][0] > target) {
				rowEnd = mid -1;
			} else {
				rowStart = mid + 1;
			}
		}

		return false;
	}


	public boolean searchInRotatedArray(int[] arr, int val) {
		int pivotIndex = findPivotIndex(arr);
		if (Arrays.binarySearch(arr, 0, pivotIndex, val) >= 0) {
			return true;
		}

		return Arrays.binarySearch(arr, pivotIndex+1, arr.length - 1, val) >= 0;
	}

	public  int binarySearch(int[] arr, int val) {
		int left = 0;
		int right = arr.length - 1;

		return binarySearch(arr, left, right, val);
	}

	public  int binarySearch(int[] arr, int left, int right, int val) {
		while (left <= right) {

			int mid = (left + right) / 2;

			if (arr[mid] == val) {
				return mid;
			} else if (val > arr[mid]) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}

		return -1;
	}

	/**
	 * Return rotation point of the array
	 *
	 * @param arr
	 * @return
	 */
	public  int findPivotIndex(int[] arr) {

		int len = 0;

		if (len == 0)
			return -1;

		if (len == 1)
			return 0;

		int startIndex = 0;

		int left = 0;
		int right = len - 1;

		while (left <= right) {
			int mid = (left + right) / 2;

			if (mid == right || arr[mid] > arr[mid+1]) {
				return mid;
			}if (arr[mid] >= arr[startIndex]){
				left = mid + 1;
			} else if (arr[mid] < arr[startIndex]) {
				right = mid - 1;
			}
		}

		return -1;

	}

	/**
	 *  001011
	 *  11 8 5 3 1 0
	 *  0  0 0 1 2 4
	 *
	 */

	public int[] minOperations(String boxes) {
		int  n = boxes.length();
		int[] preSum = new int[n];
		int[] postSum = new int[n];

		int count1 = 0;
		int total = 0;
		for(int i = 0; i < n; i++) {
			total += count1;
			preSum[i] = total;

			if (boxes.charAt(i) == '1') {
				count1++;
			}
		}

		count1 = 0;
		total = 0;
		for(int i = n-1; i >= 0; i--) {
			total += count1;
			postSum[i] = total;

			if (boxes.charAt(i) == '1') {
				count1++;
			}
		}

		int[] result = new int[n];
		for (int i = 0; i < n; i++) {
			result[i] = preSum[i] + postSum[i];
		}
		return  result;
	}

	public int[][] diagonalSort(int[][] mat) {

		int m = mat.length;
		int n = mat[0].length;

		for(int k = 0; k < m; k++) {
			int i = k;
			int j = 0;

			List<Integer> list = new ArrayList<>();

			while(i < m && j < n) {
				list.add(mat[i++][j++]);
			}

			Collections.sort(list);

			i = k;
			j = 0;
			for(int val : list){
				mat[i++][j++] = val;
			}
		}

		for(int k = 1; k < n; k++) {
			int i = 0;
			int j = k;

			List<Integer> list = new ArrayList<>();

			while(i < m && j < n) {
				list.add(mat[i++][j++]);
			}

			Collections.sort(list);

			i = k;
			j = 0;
			for(int val : list){
				mat[i++][j++] = val;
			}
		}

		return mat;
	}

	public int numTeams(int[] rating) {
		int n = rating.length;
		int res = 0;
		for(int i = 1; i < n-1; i++ ) {
			int maxB = 0;
			int maxA = 0;
			int minB = 0;
			int minA = 0;
			for (int j = 0; j < n; j++) {
				if (j < i) {
					if (rating[j] > rating[i]) {
						maxB++;
					} else {
						minB++;
					}
				}
				if (j > i) {
					if (rating[j] > rating[i]) {
						maxA++;
					} else {
						minA++;
					}
				}
			}

			res += minB * maxA + minA * maxB;
		}

		return res;
	}

	class MRUQueue {

		List<Integer> list;

		public MRUQueue(int n) {
			list = new ArrayList<>();

			for(int i = 1; i <= n ; i++) {
				list.add(i);
			}
		}

		public int fetch(int k) {
			Integer val = list.remove(k);
			list.add(val);
			return val;
		}
	}

	/**
	 * 2 3 5 7 11 13 17  = 7
	 * 0 1 2 3 4 5 6
	 *
	 */

	public int[] deckRevealedIncreasing(int[] deck) {
		Arrays.sort(deck);

		int[] result = new int[deck.length];

		boolean isSkipped = true;
		for(int i = 0 ; i < deck.length; i++) {
			result[i] = -1;
		}

		int k = 0;
		for(int i = 0; i < deck.length; i++) {
			if (isSkipped) {
				result[k++] = deck[i];
				isSkipped = false;
			}

			if (!isSkipped) {
				while (i != deck.length - 1 && result[k++] != -1){
					k = k % deck.length;
				}
				isSkipped = true;
				while (result[k++] != -1){
					k = k % deck.length;
				}
			}
		}

		return result;
	}

	/**
	 * [0,0,0]
	 * [0,1,0]
	 * [0,1,0]
	 * [1,1,1]
	 * [1,2,0]
	 */

	public int countSquares(int[][] matrix) {
		for(int i = 1; i < matrix.length; i++) {
			for (int j = 1; j < matrix[0].length; j++) {
				if (matrix[i][j] == 1)
					matrix[i][j] = Math.min(Math.min(matrix[i-1][j], matrix[i][j-1]), matrix[i-1][j-1]);
			}
		}

		int count = 0;

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				count += matrix[i][j];
			}
		}

		return count;
	}

	public int countTriplets(int[] arr) {
		int count = 0;

		for(int i = 0; i < arr.length; i++) {
			int val = arr[i];

			for (int k = i +1; k < arr.length; k++) {
				val ^= arr[k];

				if (val == 0) {
					count += (k-i);
				}
			}
		}

		return count;
	}

	public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
		return populateQueens(queens, king);
	}

	private void checkAndUpdateQueen(int[] queen, int c, int d, boolean positive, Map<Integer, List<Integer>> selectedQueens ) {
		List<Integer> existing = selectedQueens.get(d);
		if (existing != null) {
			if (positive && queen[c] < existing.get(c)) {
				existing = Arrays.asList(queen[0], queen[1]);
			}

			if (!positive && queen[c] > existing.get(c)) {
				existing = Arrays.asList(queen[0], queen[1]);
			}
		}
		selectedQueens.put(d, existing);
	}

	private List<List<Integer>> populateQueens(int[][] queens, int[] king) {
		Map<Integer, List<Integer>> selectedQueens = new HashMap<>();
		for(int[] queen : queens) {

			int xDiff = queen[0] - king[0];
			int yDiff = queen[1] - king[1];

			if (xDiff == 0) {
				if (queen[1] - king[1] > 0) {
					checkAndUpdateQueen(queen, 1, 0, true, selectedQueens);
				} else if (queen[1] - king[1] < 0) {
					checkAndUpdateQueen(queen, 1, 1, false, selectedQueens);
				}
			} else if (yDiff == 0) {
				if (queen[0] - king[0] > 0) {
					checkAndUpdateQueen(queen, 0, 2, true, selectedQueens);
				} else if (queen[0] - king[0] < 0) {
					checkAndUpdateQueen(queen, 0, 3, false, selectedQueens);
				}
			} else if (xDiff / yDiff > 0) {
				if (xDiff > 0) {
					checkAndUpdateQueen(queen, 0, 4, true, selectedQueens);
				} else {
					checkAndUpdateQueen(queen, 0, 5, false, selectedQueens);
				}
			} else if (xDiff / yDiff < 0) {
				if (xDiff > 0) {
					checkAndUpdateQueen(queen, 0, 4, true, selectedQueens);
				} else {
					checkAndUpdateQueen(queen, 0, 5, false, selectedQueens);
				}
			}
		}

		return new ArrayList<>(selectedQueens.values());
	}

	public List<Integer> findDuplicates(int[] nums) {
		int k = nums.length + 1;
		for(int i = 0; i < nums.length; i++) {
			int index = Math.abs(nums[i])%k-1;
			nums[index] = nums[index] + k;
		}

		List<Integer> result = new ArrayList<>();
		for(int i = 0; i < nums.length; i++) {
			if (nums[i] % k == 2 ) {
				result.add(i+1);
			}
		}

		return result;
	}

	public int numOfSubarrays(int[] arr, int k, int threshold) {
		int sum = 0;
		int count = 0;
		int i = 0;
		for(i = 0; i < k && i < arr.length; i++) {
			sum += arr[i];
		}

		if (sum/k > threshold) {
			count++;
		}

		for (; i < arr.length; i++) {
			sum = (arr[i] + arr[i-k]);

			if (sum/k > threshold) {
				count++;
			}
		}

		return count;

	}

	public void wiggleSort(int[] nums) {
		for( int i = 1; i < nums.length; i+= 2) {
			if (nums[i] < nums[i-1]) {
				int temp = nums[i-1];
				nums[i-1] = nums[i];
				nums[i] = temp;
			}

			if (i+1 < nums.length && nums[i] < nums[i+1]) {
				int temp = nums[i+1];
				nums[i+1] = nums[i];
				nums[i] = temp;
			}
		}
	}

	public int numTimesAllBlue(int[] light) {
		int sum = 0;

		int n = light.length;
		int count = 0;
		for(int i =0 ; i < n; i++) {
			sum += light[i];
			int expected = (i * (i+1))/2;

			System.out.println(sum + " : " + expected);

			if (sum == expected) {
				count++;
			}
		}

		return count;
	}

	public boolean splitArray(int[] nums) {
		int sum = 0;
		for(int val : nums) {
			sum += val;
		}

		if (sum %4 != 0) {
			return false;
		}

		int paritionSum = sum / 4;

		int i = 0;
		sum = 0;
		for(int k = 0; k < 4; k++) {
			while(sum < paritionSum && i < nums.length) {
				sum += nums[i];
			}

			if (sum != paritionSum) {
				return false;
			}

			sum = 0;
		}

		return true;
	}

	public static int largestSubArraySum(int[] arr) {
		int sumSorFar = 0;
		int maxSumSoFar = 0;

		for(int val : arr) {
			sumSorFar += val;
			if (sumSorFar < 0) {
				sumSorFar = 0;
			}

			maxSumSoFar = Math.max(sumSorFar, maxSumSoFar);
		}

		return maxSumSoFar;
	}

	public int findMaxConsecutiveOnes(int[] nums) {
		int count = 0;
		int maxCount = 0;

		for (int val : nums) {
			if (val == 0) {
				count = 0;
			} else {
				count++;
				maxCount = Math.max(count, maxCount);
			}
		}

		return maxCount;
	}

	public int findMaxConsecutiveOnes1(int[] nums) {
		int count = 0;
		int maxCount = 0;

		int s = 0; int e = 0;

		int zeroCounts = 0;

		while(e < nums.length) {
			while(e < nums.length && zeroCounts <= 1) {
				maxCount = Math.max(maxCount, count);

				if (nums[e] == 0) {
					zeroCounts++;
				}

				e++;
				count++;
			}

			while(s < nums.length && zeroCounts > 1) {
				if (nums[e] == 0) {
					zeroCounts--;
				}
				s--;
			}
		}

		return maxCount;
	}

	public void merge(int[] nums1, int m, int[] nums2, int n) {
		int i = m - 1;
		int j = n -1;
		int k = m + n -1;

		while (i >= 0 && j >= 0) {
			if (nums1[i] >= nums2[j]) {
				nums1[k--] = nums1[i--];
			} else {
				nums1[k--] = nums2[j--];
			}
		}

		while(i >= 0) {
			nums1[k--] = nums1[i--];
		}

		while (j >=0) {
			nums1[k--] = nums2[j--];
		}
	}

	public boolean checkIfExist(int[] arr) {

		Map<Integer, Integer> set = new HashMap<>();
		for (int val : arr) {
			Integer count = set.get(val);
			if (count == null) {
				count = 0;
			}

			set.put(val, count + 1);
		}

		for(int val : arr) {
			if (val != 0 && set.containsKey(val * 2)) {
				return true;
			}

			if (val == 0) {
				Integer count = set.get(0);
				if (count > 1) {
					return true;
				}
			}
		}

		return false;
	}

	public boolean validMountainArray(int[] arr) {

		boolean isIncreasing = true;

		for(int i = 1; i < arr.length ; i++) {
			if (isIncreasing && arr[i] <= arr[i-1]) {
				return false;
			}

			if (arr[i] > arr[i-1] && i != arr.length -1 && arr[i] > arr[i+1]) {
				isIncreasing = false;
			}

			if (!isIncreasing && arr[i] >= arr[i-1]) {
				return false;
			}
		}

		return true;
	}


	public int[] getModifiedArray(int length, int[][] updates) {
		int[] arr = new int[length];

		for(int[] update : updates) {
			int startIndex = update[0];
			int endIndex = update[1];
			int inc = update[2];

			for(int i = startIndex; i <= endIndex; i++) {
				arr[i] += inc;
			}
		}

		return arr;
	}

	public int findMinFibonacciNumbers(int k) {
		int a = 1;
		int b = 1;
		List<Integer> list = new ArrayList<>();

		while(a <= k) {
			list.add(a);
			int sum = a + b;
			a = b;
			b = sum;
		}

		int i = list.size() - 1;

		int count = 0;
		while(i >= 0) {
			if (list.get(i) <= k) {
				k = k - list.get(i);
				count++;

				if (k <= 0) {
					break;
				}
			}

			i--;
		}

		return count;
	}

	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int n = nums1.length + nums2.length;

		int[] temp = new int[n];

		int i = 0; int j = 0; int k = 0;

		while( i < nums1.length && j < nums2.length){
			if (nums1[i] < nums2[j]) {
				temp[k++] = nums1[i++];
			} else {
				temp[k++] = nums2[j++];
			}
		}

		while (i < nums1.length) {
			temp[k++] = nums1[i++];
		}

		while(j < nums2.length) {
			temp[k++] = nums2[j++];
		}

		if ( n % 2 == 0) {
			return temp[n/2-1] + temp[n/2];
		}

		return temp[n/2];
	}

	public List<List<Integer>> fourSum(int[] nums, int target) {
		Arrays.sort(nums);

		Set<List<Integer>> result = new HashSet<>();
		for(int i = 0; i < nums.length; i++) {
			for (int j = i+1; j < nums.length; j++) {
				int k = j+1;
				int l = nums.length -1;

				int rSum = target -  (nums[i] + nums[j]);

				while(k < l) {
					int s = nums[k] + nums[l];
					if (rSum == s) {
						result.add(Arrays.asList(nums[i], nums[j], nums[k], nums[l]));
						k++;
						l--;
					} else if (rSum < s) {
						l--;
					} else {
						k++;
					}
				}
			}
		}

		return new ArrayList<>(result);
	}


	public List<List<Integer>> combinationSum1(int[] candidates, int target)     {

		List<List<Integer>> res = new ArrayList<>();
		populateList(candidates, target, res, new ArrayList<>(), 0);
		return res;
	}

	private void populateList(int[] candidates,int target, List<List<Integer>> result, List<Integer> selected, int index) {

		if (target == 0) {
			List<Integer> list = new ArrayList<>();
			for(int val : selected ) {
				list.add(val);
			}

			result.add(list);
			return;
		}

		if (target < 0) {
			return;
		}

		if (index == candidates.length) {
			return;
		}



		populateList(candidates, target, result, selected, index+1);
		selected.add(candidates[index]);
		populateList(candidates, target - candidates[index], result, selected, index);
		selected.remove((Integer)candidates[index]);
	}

	public int firstMissingPositive(int[] nums) {

		Integer smallest = null;
		Integer largest = null;

		Set<Integer> set = new HashSet<>();

		for (int num : nums) {
			if (num > 0) {
				set.add(num);
				if (smallest == null) {
					smallest = num;
				} else {
					smallest = Math.min(smallest, num);
				}

				if (largest == null) {
					largest = num;
				} else {
					largest = Math.max(largest, num);
				}
			}
		}

		if (smallest == null || smallest > 1) {
			return 1;
		}

		for(int i = smallest; i <=  nums.length; i++) {
			if(!set.contains(i)) {
				return i;
			}
		}

		return 1;
	}

	public void rotate(int[][] matrix) {
		int m = matrix.length;
		for(int k = m - 1; k >= 0; k-- ) {

			for(int i = 0; i < m - 1; i++) {

				for(int j = 0; j < 4; j++) {
				}

			}
		}

	}

	public boolean canJump(int[] nums) {
		Integer[] dp = new Integer[nums.length];
		dp[0] = 0;

		for(int i = 1; i < nums.length; i++) {
			for(int j = i-1; i >= 0; i++) {
				if (nums[j] + j >= i) {
					if (dp[i] == null || dp[i] > dp[j] + 1) {
						dp[i] = dp[j] + 1;
					}
				}
			}
		}

		if (dp[nums.length] != null)
			return true;

		return false;
	}

	public static  int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
		int count = 0;

		for(List<String> item : items) {
			switch (ruleKey) {
				case "type":
					if (ruleValue.equals(item.get(0))) {
						count++;
					}
					break;
				case "color":
					if (ruleValue.equals(item.get(1))) {
						count++;
					}
					break;
				case "name":
					if (ruleValue.equals(item.get(2))) {
						count++;
					}
					break;
			}
		}

		return count;
	}

	public static int closestCost(int[] baseCosts, int[] toppingCosts, int target) {
		Arrays.sort(toppingCosts);

		int maxClosest = 0;

		int minBase = Integer.MAX_VALUE;

		for(int baseCost: baseCosts) {
			int closestCost = 0;

			minBase= Math.min(minBase, baseCost);

			if (closestCost + baseCost > target) {
				continue;
			}

			closestCost += baseCost;

			int rCost = target - closestCost;
			Map<Integer, Integer> map = new HashMap<>();
			if (rCost > 0) {
				int cost = getMaxClosestSum(toppingCosts, rCost, 0, 0, map);
				closestCost += cost;
			}

			maxClosest = Math.max(maxClosest, closestCost);
		}

		if( maxClosest > 0 ) {
			return maxClosest;
		};

		return minBase;
	}

	static int getMaxClosestSum(int[] vals, int sum, int cSum, int index, Map<Integer, Integer> map) {
		if (index >= vals.length)
			return cSum;

		int x = 0;
		int k = 0;
		Integer count = map.get(vals[index]);
		if (count == null) {
			count = 0;
		}

		if (count < 2) {
			if (cSum + vals[index] <= sum) {
				map.put(vals[index], count+1);
				x = getMaxClosestSum(vals, sum, cSum + vals[index], index, map);
				map.put(vals[index], count);
			}
		}


		int y = getMaxClosestSum(vals, sum, cSum, index + 1, map);
		return  Math.max(x, y);

	}

	public List<List<Integer>> generate(int numRows) {
		int[][] mat = new int[numRows+1][numRows+1];

		List<List<Integer>> result = new ArrayList<>();

		for(int row = 0; row <= numRows; row++) {
			List<Integer> list = new ArrayList<>();
			for(int col = 0; col <= row; col++) {
				if (col == 0) {
					mat[row][col] = 1;
				} else if (col == row) {
					mat[row][col] = 1;
				} else {
					mat[row][col] = mat[row-1][col-1] + mat[row-1][col];
				}

				list.add(mat[row][col]);
			}

			result.add(list);
		}

		return result;
	}

	public int[] twoSum1(int[] numbers, int target) {
		int left = 0;
		int right  = numbers.length -1;

		int[] result = new int[2];

		while(left < right) {
			int sum = numbers[left] + numbers[right];

			if (sum == target) {
				result[0] = left;
				result[1] = right;
				return result;
			} else if (sum < target) {
				left++;
			} else {
				right--;
			}
		}

		return result;
	}

	public void sort(int[] nums) {
		int i = 0;

		while(i < nums.length) {
			int j = nums[i] - 1;
			if (nums[j] != nums[i]) {
				swap(nums, i, j);
			} else
				i++;
		}
	}

	public List<Integer> spiralOrder(int[][] matrix) {

		List<Integer> result = new ArrayList<>();
		if (matrix.length == 0)
			return result;

		int rowStart = 0;
		int rowEnd = matrix[0].length - 1;

		int columnStart = 0;
		int columnEnd = matrix[0].length - 1;

		while(rowStart <= rowEnd && columnStart <= columnEnd) {
			// First row
			for(int i = columnStart; i <= columnEnd; i++) {
				result.add(matrix[rowStart][i]);
			}
			rowStart++;

			// Last column
			for(int i = rowStart; i <= rowEnd; i++) {
				result.add(matrix[i][columnEnd]);
			}
			columnEnd--;



			if (rowStart <= rowEnd) {
				// last row
				for(int i = columnEnd; i >= columnStart; i--) {
					result.add(matrix[rowEnd][i]);
				}
				rowEnd--;
			}

			if (columnStart <= columnEnd) {
				// first column
				for (int i = rowEnd; i >= rowStart; i--) {
					result.add(matrix[i][columnStart]);
				}
				columnStart++;
			}
		}

		return result;
	}

	public int[][] generateMatrix(int n) {
		int[][] matrix = new int[n][n];

		int count = 1;

		int rowStart = 0;
		int rowEnd = n - 1;

		int columnStart = 0;
		int columnEnd = n - 1;

		while(rowStart <= rowEnd && columnStart <= columnEnd) {
			// First row
			for(int i = columnStart; i <= columnEnd; i++) {
				matrix[rowStart][i] = count++;
			}
			rowStart++;

			// Last column
			for(int i = rowStart; i <= rowEnd; i++) {
				matrix[i][columnEnd] = count++;;
			}
			columnEnd--;



			if (rowStart <= rowEnd) {
				// last row
				for(int i = columnEnd; i >= columnStart; i--) {
					matrix[rowEnd][i]= count++;
				}
				rowEnd--;
			}

			if (columnStart <= columnEnd) {
				// first column
				for (int i = rowEnd; i >= rowStart; i--) {
					matrix[i][columnStart] = count++;
				}
				columnStart++;
			}
		}

		return matrix;
	}

	public int[] plusOne(int[] digits) {
		List<Integer> result = new ArrayList<>();

		int carry = 1;

		for(int i = digits.length - 1; i >= 0; i--) {
			int sum = digits[i] + carry;

			result.add(0, sum % 10);
			carry = sum / 10;
		}

		if (carry != 0) {
			result.add(0, carry);
		}

		int[] res = new int[result.size()];

		for(int i = 0; i < result.size(); i++) {
			res[i] = result.get(i);
		}

		return res;
	}

	public List<String> findMissingRanges(int[] nums, int lower, int upper) {
		int x = lower;

		int index = 0;

		List<String> result = new ArrayList<>();

		while(x <= upper) {

			if (nums[index] == x) {
				// case if character at x matches with the character at the index
				x++;
				index++;
			} else {
				// case when the char at x don't match with the char at index

				if (index  >= nums.length) {
					// if index of num exceed the limit
					if (nums[index-1] == x) {
						result.add(Integer.toString(x));
					} else {
						result.add(x + "->" + (nums[index] - 1));
					}
					x = nums[index];
				} else {
					// if index of nums is in limit
					result.add(x + "->" + upper);
					x = upper + 1;
				}
			}
		}

		return result;
	}

	public String nextClosestTime(String time) {
		int minutes = Integer.parseInt(time.substring(0, 2)) * 60;
		minutes += Integer.parseInt(time.substring(3));

		HashSet<Integer> digits = new HashSet<>();

		for(char ch : time.toCharArray()) {
			digits.add(ch - '0');
		}

		while(true) {
			minutes = (minutes + 1) % (24 * 60);

			int[] nextTime = {minutes/60 /10, minutes/60%10, minutes % 60 /10, minutes % 60 % 10};

			boolean isValid = true;
			for(int d : nextTime) {
				if (!digits.contains(d)) {
					isValid = false;
				}
			}

			if (isValid) {
				return String.format("%02d:%02d", minutes/60, minutes%60);
			}
		}
	}

	public int findKthLargest(int[] nums, int k) {
		PriorityQueue<Integer>  pq = new PriorityQueue<>();

		for(int i = 0; i < pq.size(); i++) {
			if (i < k) {
				pq.add(nums[i]);
			} else {
				if(nums[i] > pq.peek()) {
					pq.remove();
					pq.add(nums[i]);
				}
			}
		}

		while(pq.size() > 1) {
			pq.remove();
		}

		if (pq.peek() == null)
			return -1;

		return pq.peek();
	}

	public int minMeetingRooms(int[][] intervals) {
		if (intervals == null || intervals.length == 0)
			return 0;

		Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));

		pq.add(intervals[0]);

		for(int i = 1; i < intervals.length; i++) {
			int[] curr = intervals[i];
			int[] prev = pq.poll();

			if (curr[0] < prev[1]) {
				// intersection
				pq.add(prev);
				pq.add(curr);
			} else {
				prev[1] = curr[1];
				pq.add(prev);
			}
		}

		return pq.size();
	}

	public int maxIncreaseKeepingSkyline(int[][] grid) {
		int result = 0;

		int n = grid.length;

		int[] maxRowVals = new int[n];
		int[] maxColVals = new int[n];

		for(int i = 0 ; i < grid.length; i++) {
			for(int j = 0; j < grid.length; j++) {
				maxRowVals[i] = Math.max(maxRowVals[i], grid[i][j]);

				maxColVals[j] = Math.max(maxColVals[j], grid[i][j]);
			}
		}

		for(int i = 0 ; i < grid.length; i++) {
			for(int j = 0; j < grid.length; j++) {
				result += Math.min(maxRowVals[i], maxColVals[j]) - grid[i][j];
			}
		}

		return result;
	}

	public int distanceBetweenBusStops(int[] distance, int start, int destination) {
		int clockWiseDistance  = 0;

		int x = start;

		while(x !=  destination) {
			clockWiseDistance += distance[x];

			x = (x + 1) % distance.length;
		}

		int totalDistance = 0;

		for(int val : distance) {
			totalDistance += val;
		}


		return Math.min(clockWiseDistance, totalDistance-clockWiseDistance);
	}

	public static void bubbleSort(int[] arr) {
		for (int itr = 1; itr <= arr.length -1 ; itr++) {
			for(int i = 0; i < arr.length - itr; i++) {
				if (arr[i+1] < arr[i]) {
					swap(arr, i, i+1);
				}
			}
		}
	}

	public static void selectionSort(int[] arr) {
		for(int i = 0; i < arr.length; i++) {
			int min = i;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] < arr[min]) {
					min = j;
				}
			}

			swap(arr, i, min);
		}
	}

	public int splitArray(int[] nums, int m) {
		int totalSum  = 0;

		for(int num : nums) {
			totalSum += num;
		}

		int result  = 0;
		int currSetSum = 0;
		for(int i = 0; i < nums.length; i++) {
			if (i > 0) {
				currSetSum += nums[i-1];
			}

			result = Math.min(result, Math.max(currSetSum, totalSum - currSetSum));
		}

		return result;
	}

	public int[] shortestToChar(String s, char c) {
		int n = s.length();

		int[] outputArr = new int[n];

		int c_position = -n;

		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == c) {
				c_position = i;
			}

			outputArr[i] = i - c_position;
		}

		for(int i = n - 1; i >=0 ; i--) {

			if (s.charAt(i) == c) {
				c_position = i;
			}

			outputArr[i] = Math.min(outputArr[i], c_position - i);
		}

		return outputArr;
	}

	public String pushDominoes(String dominoes) {
		char[] charArray = dominoes.toCharArray();

		int N = dominoes.length();
		int[] forces = new int[N];

		int force = 0;
		for(int i = 0; i < N; i++) {
			if (charArray[i] == 'R') {
				force = N;
			} else if (charArray[i] == 'L') {
				force = 0;
			} else {
				force = Math.max(force - 1, 0);
			}

			forces[i] += force;
		}

		for(int i = N  -1 ; i >= 0; i--) {
			if (charArray[i] == 'L') {
				force = N;
			} else if (charArray[i] == 'R') {
				force = 0;
			} else {
				force = Math.max(force - 1, 0);
			}

			forces[i] -= force;
		}

		StringBuilder builder = new StringBuilder();

		for(int i = 0 ; i < N; i++) {
			if (forces[i] > 0) {
				builder.append('R');
			} else if (forces[i] == 0) {
				builder.append('.');
			} else {
				builder.append('L');
			}
		}

		return builder.toString();
	}

	public boolean lemonadeChange(int[] bills) {
		int count5 = 0;
		int count10 = 0;
		int count20 = 0;

		int lemonadeCharge = 10;

		for (int i = 0; i < bills.length; i++) {

			switch (bills[i]) {
				case 20:
					count20++;
					break;
				case 10:
					count10++;
					break;
				case 5:
					count5++;
					break;
			}

			int remaining = bills[i] - lemonadeCharge;

			while(remaining >= 20 && count20 > 0) {
				remaining = remaining - 20;
				count20--;
			}

			while(remaining >= 10 && count10 > 0) {
				remaining = remaining - 10;
				count10--;
			}

			while(remaining >= 5 && count5 > 0) {
				remaining = remaining - 5;
				count5--;
			}

			if (remaining > 0)
				return false;

		}

		return true;
	}

	public void moveZeroes(int[] nums) {

		// Represents the non-zero numbers in the system
		int i = -1;
		// current index
		int j = 0;

		while(j < nums.length) {
			if (nums[j] != 0) {
				swap(nums, ++i, j);
			}
			j++;
		}

	}

	public int[] productExceptSelf(int[] nums) {

		if (nums.length == 0) {
			return new int[0];
		}

		int n = nums.length;

		int[] leftProduct = new int[n];
		int[] rightProduct = new int[n];

		leftProduct[0] = 1;
		for(int i= 1; i < n; i++) {
			leftProduct[i] = nums[i-1] * leftProduct[i-1];
		}

		System.out.println(Arrays.toString(leftProduct));

		rightProduct[n-1] = 1;
		for(int i = n - 2; i >= 0; i--) {
			rightProduct[i] = rightProduct[i+1] * nums[i+1];
		}

		System.out.println(Arrays.toString(rightProduct));

		int[] result = new int[n];

		for(int i = 0; i < n; i++) {
			result[i] = leftProduct[i] * rightProduct[i];
		}

		return result;
	}
	public List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		populatePermutation(nums, 0, result, new ArrayList<>());
		return result;
	}

	private void populatePermutation(int[] num, int index, List<List<Integer>> result, List<Integer> curr) {

		if (index == num.length) {
			List<Integer> clone = new ArrayList<>();
			for(int val : curr) {
				clone.add(val);
			}
			result.add(clone);
			return;
		}

		for(int j = index; j < num.length; j++) {
			swap(num, index, j);
			curr.add(num[index]);
			populatePermutation(num, index + 1, result, curr);
			curr.remove(new Integer(num[index]));
			swap(num, index, j);
		}

	}

	public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
		int n = wage.length;

		double minCost = Double.MAX_VALUE;

		for(int captain = 0; captain < n; captain++) {
			double captainRatio = wage[captain]/quality[captain];

			List<Double> acceptedOffers = new ArrayList<>();
			for(int worker = 0; worker < n; worker++) {
				double offer = quality[worker] * captainRatio;
				if (offer >= wage[worker]) {
					acceptedOffers.add(offer);
				}

				if (acceptedOffers.size() < K) {
					continue;
				}
			}

			// find sum of k smallest elements in accepted offers
			PriorityQueue<Double> pq = new PriorityQueue<>((a, b) -> Double.compare(b, a));

			double heapSum = 0;
			for(int i = 0; i < K; i++) {
				pq.add(acceptedOffers.get(i));
				heapSum += acceptedOffers.get(i);
			}

			for(int i = K; i < acceptedOffers.size(); i++) {
				if (acceptedOffers.get(i) < pq.peek()) {
					heapSum += (acceptedOffers.get(i) - pq.peek());
					pq.remove();
					pq.add(acceptedOffers.get(i));
				}
			}

			minCost = Math.min(minCost, heapSum);
		}

		return minCost;
	}

	public static void reverse(int[] arr) {
		int left = 0;
		int right = arr.length -1;
		reverse(arr, left, right);
	}

	public static void reverse(int[] arr, int left, int right) {
		while(left <= right) {
			swap(arr, left, right);
			left++;
			right--;
		}
	}

	public static void reverse(char[] arr, int left, int right) {
		while(left <= right) {
			swap(arr, left, right);
			left++;
			right--;
		}
	}

	public int maximalSquare(char[][] matrix) {

		int n = matrix.length;

		int[][] sol = new int[n][n];

		int maxSize = 0;

		for(int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i == 0 || j == 0) {
					sol[i][j] = matrix[i][j] - '0';
				} else {
					if (matrix[i][j] == '1') {
						sol[i][j] = Math.min(sol[i-1][j-1], Math.min(sol[i][j-1], sol[i-1][j])) + 1;
					}
				}

				maxSize = Math.max(maxSize, sol[i][j]);
			}


		}

		return maxSize * maxSize;
	}

	public int evalRPN(String[] tokens) {
		Stack<Integer> stack = new Stack<>();

		HashSet<String> operators = new HashSet<>(Arrays.asList("+", "-", "/", "*"));

		for(String str : tokens) {
			if (operators.contains(str)) {

				Integer val1 = stack.pop();
				Integer val2 = stack.pop();

				Integer result = null;
				switch (str) {
					case "+":
						result = val1 + val2;
						break;
					case "-":
						result = val2 - val1;
						break;
					case "/":
						result = val2 / val1;
						break;
					case "*":
						result = val2 * val1;
						break;
				}

				stack.push(result);
			} else {
				stack.push(Integer.parseInt(str));
			}
		}

		return stack.peek();
	}

	public int[] sortArrayByParity(int[] A) {
		int i = -1;
		int j = 0;

		while (j < A.length) {
			if (A[j] % 2 == 0) {
				swap(A, ++i, j);
			}
			j++;
		}

		return A;
	}

	public int minPartitions(String n) {
		int max = 0;

		for(char ch : n.toCharArray()) {
			max = Math.max(max, ch - '0');
		}

		return max;
	}

	public int maxProduct(String[] words) {
		if (words == null || words.length == 0)
			return 0;

		int max = 0;

		for(int i = 0; i < words.length; i++) {
			HashSet<Character> set = getSet(words[i]);
			for(int j = i + 1; j < words.length; j++) {
				if (!isSetContainsAnyChar(set, words[j])) {
					max = Math.max(words[i].length(), words[j].length());
				}
			}
		}

		return max;
	}

	private HashSet getSet(String word) {
		HashSet<Character> set = new HashSet<>();
		for(char ch : word.toCharArray()) {
			set.add(ch);
		}

		return set;
	}

	public boolean isSetContainsAnyChar(HashSet<Character> set, String word) {
		for(char ch : word.toCharArray()) {
			if (set.contains(ch)) {
				return true;
			}
		}

		return false;
	}

	public int[] sortArray(int[] nums) {
		mergeSort(nums, 0, nums.length - 1);
		return nums;
	}

	public void mergeSort(int[] nums, int left, int right) {
		if (left >= right) {
			return;
		}

		int mid = (left + right) / 2;

		mergeSort(nums, left, mid);
		mergeSort(nums, mid+1, right);
		merge(nums, left, mid, right);

	}

	public void merge(int[] nums, int left, int mid, int right) {
		int size = right - left + 1;
		int[] temp = new int[size];

		int k = 0;

		int i = left;
		int j = mid + 1;

		while(i <= mid && j <= right) {
			if (nums[i] <= nums[j]) {
				temp[k++] = nums[i++];
			} else {
				temp[k++] = nums[j++];
			}
		}

		while(i <= mid) {
			temp[k++] = nums[i++];
		}

		while(j <= mid) {
			temp[k++] = nums[j++];
		}

		for ( k = 0; k < size; k++) {
			nums[left + k] = temp[k];
		}
	}

	public boolean searchMatrix1(int[][] matrix, int target) {
		int rows = matrix.length;
		int cols = matrix[0].length;

		int i = 0;
		int j = cols - 1;

		while (i < rows && j >= 0) {
			int val = matrix[i][j];
			if (target == val) {
				return true;
			} else if (target < val) {
				j--;
			} else {
				i++;
			}
		}

		return false;
	}

	public int[] frequencySort(int[] nums) {
		Map<Integer, Long> freqMap =  Arrays.stream(nums)
				.boxed()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

		List<Map.Entry<Integer, Long>> entryList = new ArrayList<>(freqMap.entrySet());

		Collections.sort(entryList, (a, b) -> {

			if (a.getValue() == b.getValue()) {
				return a.getValue().compareTo(b.getValue());
			}

			return b.getKey().compareTo(a.getKey());
		});

		int[] result = new int[nums.length];

		int index = 0;

		for (int i = 0;  i < entryList.size(); i++) {
			long k = entryList.get(i).getValue();

			while(k-- != 0) {
				result[index++] = entryList.get(i).getKey();
			}

		}

		return result;
	}

	public String reverseVowels(String s) {
		StringBuilder builder = new StringBuilder();
		HashSet<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
		for(char ch : s.toCharArray())  {
			if (vowels.contains(ch)){
				builder.append(ch);
			}
		}

		String vowelString = builder.reverse().toString();

		int vowelIndex = 0;
		StringBuilder result = new StringBuilder();

		for(int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (vowels.contains(ch)) {
				char vowelCh = vowelString.charAt(vowelIndex++);
				result.append(vowelCh);
			} else {
				result.append(ch);
			}
		}

		return result.toString();
	}

	public int arraySign(int[] nums) {
		int negativeCount = 0;

		for(int num : nums) {
			if (num == 0) {
				return 0;
			} else if (num < 0) {
				negativeCount++;
			}
		}

		return (negativeCount % 2 == 0) ? 1 : -1;
	}

	public boolean isMonotonic(int[] nums) {

		Boolean isIncreasing = false;
		Boolean isDecreasing = false;

		for (int i = 1; i < nums.length-1; i++) {
			if (!isIncreasing && !isDecreasing) {
				if (nums[i] > nums[i-1]) {
					isIncreasing = true;
				}

				if (nums[i] < nums[i-1]) {
					isDecreasing = true;
				}
			}

			if (isIncreasing) {
				if (nums[i] - nums[i-1] < 0) {
					return false;
				}
			}

			if (isDecreasing) {
				if (nums[i] - nums[i-1] > 0) {
					return false;
				}
			}
		}

		return true;

	}

	public int maximumGap(int[] nums) {
		if (nums.length < 2) {
			return 0;
		}

		Arrays.sort(nums);

		int result = 0;

		for(int i = 1; i < nums.length; i++) {
			result = Math.max(result, nums[i] - nums[i-1]);
		}

		return result;
	}

	public int maxAreaOfIsland1(int[][] grid) {

		int rows = grid.length;
		int cols = grid[0].length;

		boolean[][] visited = new boolean[rows][cols];

		int maxArea = 0;

		for (int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				if (visited[i][j] != true) {
					maxArea = Math.max( maxArea, computeAreaOfLand(grid, i, j, visited));
				}
			}
		}

		return maxArea;

	}

	public static int[][] DIRECTIONS = {
			{0, 1},
			{1, 0},
			{-1, 0},
			{0, -1}
	};

	public int computeAreaOfLand(int[][] grid, int i, int j, boolean[][] visited) {

		if (i < 0 || i >= grid.length)
			return 0;

		if (j < 0 || j >= grid[0].length)
			return 0;


		if (visited[i][j]) {
			return 0;
		}

		visited[i][j] = true;

		return 1
				+ computeAreaOfLand(grid, i + 1, j, visited)
				+ computeAreaOfLand(grid, i -1 , j, visited)
				+ computeAreaOfLand(grid, i , j + 1, visited)
				+ computeAreaOfLand(grid, i  , j - 1, visited);

	}

	public int maxNumberOfApples(int[] arr) {
		Arrays.sort(arr);

		int sum = 0;
		int count = 0;

		for(int num : arr) {
			if (sum + num <= 5000) {
				count++;
				sum += num;
			} else {
				break;
			}
		}

		return count;
	}

	public int[] nextGreaterElement(int[] nums1, int[] nums2) {
		int[] result = new int[nums1.length];

		for(int i = 0; i < nums1.length; i++) {

			int val = Integer.MAX_VALUE;

			for(int j = i+1; j < nums2.length; j++) {
				if (nums2[j] < val && nums2[j] > nums1[i]) {
					val = nums2[j];
				}
			}

			result[i] = (val == Integer.MAX_VALUE) ? -1 : val;
		}

		return result;
	}

}
