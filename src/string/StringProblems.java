package string;
import tree.TrieDataStructure;

import java.util.*;


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

	public static boolean closeStrings(String word1, String word2) {

		int[] cache =  new int[26];
		int[] cache1 =  new int[26];

		Set<Character> chSet = new HashSet<>();

		for(char ch : word1.toCharArray()) {
			cache[ch-'a']++;
			chSet.add(ch);
		}

		for(char ch : word2.toCharArray()) {
			cache1[ch-'a']++;

			if (!chSet.contains(ch)) {
				return false;
			}
		}



		Map<Integer, Integer> map = new HashMap<>();
		for(int i = 0; i < 26; i++) {
			if (cache[i] > 0) {
				Integer count = map.get(cache[i]);
				if (count == null) {
					count = 0;
				}
				map.put(cache[i], count + 1);
			}
		}

		for(int i = 0; i < 26; i++) {

			int key = cache1[i];
			if (key != 0 ) {
				Integer count = map.get(key);
				if (count == null) {
					return false;
				}

				count = count - 1;
				map.put(cache1[i], count);
			}
		}


		for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
			if (entry.getValue() != 0) {
				return false;
			}
		}

		return true;

	}


	public static boolean arrayStringsAreEqual(String[] word1, String[] word2) {
		StringBuilder b1 = new StringBuilder();
		StringBuilder b2 = new StringBuilder();

		for(String word : word1) {
			b1.append(word);
		}

		for(String word : word2) {
			b2.append(word);
		}

		return b1.toString().equals(b2.toString());
	}

	public static String getSmallestString(int n, int k) {
		int zCount = k / 26;
		int reminder = k % 26;

		int rCharCount = n - zCount;
		StringBuilder builder = new StringBuilder();
		while (rCharCount > reminder) {
			zCount -= 1;
			rCharCount++;
			reminder += 26;
		}

		int i = 1;

		for(; i < rCharCount; i++) {
			builder.append('a');
			reminder--;
		}
		builder.append((char)('a' + (reminder-1)));
		for(int j = 0; j < zCount; j++) {
			builder.append('z');
		}
		return builder.toString();
	}

	public static int numSub(String s) {
		int result = 0;
		int MOD_NUM = 1000000007;
		int last1Index = -1;
		char[] arr = s.toCharArray();
		for(int i = 0; i < arr.length; i++) {

			if ((i == 0 || arr[i-1] == '0') && arr[i] == '1') {
				last1Index = i;
			}

			if ((i == arr.length -1 || arr[i+1] == '0') && arr[i] == '1') {
				int len = i - last1Index + 1;

				int curr = ((len * (len+1))/2)%MOD_NUM;
				result += curr;
				result %= MOD_NUM;
			}
		}
		return result;
	}

	public String nearestPalindromic(String n) {
		int i = 0;
		int j = n.length() - 1;

		char[] arr = n.toCharArray();

		if (isPalindrome(arr)){
			return nearestPalindrome(arr);
		}

		long num = Long.parseLong(n);
		long prev = num -1;
		long next = num + 1;

		return "";
	}

	public String nearestPalindrome(char[] arr) {
		int i = 0;
		int j = arr.length - 1;
		while(i < j) {
			if (arr[i] != arr[j]) {
				arr[j] = arr[i];
			}
			i++;
			j--;
		}

		return new String(arr);
	}

	public boolean isPalindrome(char[] arr) {
		int i = 0;
		int j = arr.length - 1;

		while( i < j) {
			if (arr[i] != arr[j]) {
				return false;
			}
			i++;
			j--;
		}

		return false;
	}

	public static int wordCounts(String text, String word) {
		Map<Character, Integer> countByCharMap = new HashMap<>();
		Map<Character, Integer> freqByCharMap = new HashMap<>();

		for(char ch :  word.toCharArray()) {
			Integer count = countByCharMap.get(ch);
			if (count == null) {
				count = 0;
			}

			countByCharMap.put(ch, count + 1);
		}


		for(char ch : text.toCharArray()) {
			Integer count = freqByCharMap.get(ch);

			if (count == null) {
				count = 0;
			}

			freqByCharMap.put(ch, count + 1);
		}


		int minCount = word.length();

		for(char ch : word.toCharArray()) {
			Integer freqCount = freqByCharMap.get(ch);
			Integer charCount = countByCharMap.get(ch);


			if (freqCount == null) {
				return 0;
			}


			minCount = Math.min(minCount, freqCount/charCount);
		}

		return minCount;
	}


	/**
	 * 01001
	 *
	 * @param s
	 * @return
	 */
	public int minOperations(String s) {
		int firstZeroCount = 0;
		int firstOneCount = 0;
		boolean firstZero = true;
		boolean firstOne = true;

		for(char ch : s.toCharArray()) {

			if (ch == '0') {
				if (!firstZero) {
					firstZeroCount++;
				}

				if (firstOne) {
					firstOneCount++;
				}
			} else if (ch == '1') {
				if (firstZero) {
					firstZeroCount++;
				}

				if (!firstOne) {
					firstOneCount++;
				}
			}

			firstOne = !firstOne;
			firstZero = !firstZero;
		}

		return Math.min(firstOneCount, firstZeroCount);
	}

	public String replaceWords(List<String> dictionary, String sentence) {
		Set<String> dic = new HashSet<>(dictionary);

		String[] words = sentence.split(" ");
		List<String> result = new ArrayList<>();

		for(String word : words) {
			String prefix = "";
			boolean isAdded = false;
			for(char ch : word.toCharArray()) {
				prefix += ch;
				if (dic.contains(prefix)) {
					isAdded= true;
					result.add(prefix);
				}
			}

			if (isAdded == false) {
				result.add(word);
			}
		}

		return String.join(" ", result);
	}

	public int myAtoi(String s) {
		s = s.trim();
		
		if (s.length() == 0) {
			return 0;
		}

		int index = 0;

		char ch = s.charAt(index);

		if (!Character.isDigit(ch) && ch != '-' && ch != '+') {
			return 0;
		}

		int sign = 1 ;

		if (ch == '-') {
			sign = -1;
			index++;
		}

		long result = 0;

		while(index < s.length()) {
			ch = s.charAt(index);

			if (!Character.isDigit(ch)) {
				break;
			}



			int digit = (ch - '0');
			result = result * 10 + digit;

			if (result > Integer.MAX_VALUE) {
				if (sign == 1) {
					return Integer.MAX_VALUE;
				} else {
					return Integer.MIN_VALUE;
				}
			}

			index++;
		}



		return (int)result * sign;
	}

	public String longestCommonPrefix(String[] strs) {
		if (strs.length == 0) {
			return "";
		}

		if (strs.length == 1) {
			return strs[0];
		}

		String firstString = strs[0];
		String secondString = strs[1];

		int j = 0;
		for(j = 0; j < Math.min(firstString.length(), secondString.length()); j++) {
			if (firstString.charAt(j) != secondString.charAt(j)) {
				break;
			}
		}

		j--;

		for(int i = 2; i < strs.length; i++) {

			if (j >= strs[i].length()) {
				j = strs[i].length() - 1;
			}

			while(j >= 0 &&  strs[i].charAt(j) != firstString.charAt(j)) {
				j--;
			}
		}

		if (j < 0) {
			return "";
		}

		return firstString.substring(0, j+1);
	}

	public List<String> generateParenthesis(int n) {
		List<String> result = new ArrayList<>();
		generateParenthesis(n, n, "", result);
		return result;
	}

	public static void generateParenthesis(int start, int end, String para, List<String> result) {
		if (end == 0) {
			result.add(para);
			return;
		}

		if (start >= end) {
			generateParenthesis(start-1, end, para + "(", result);
		} else if (start < end) {
			if (start == 0) {
				generateParenthesis(start, end-1, para + ")", result);
			} else {
				generateParenthesis(start-1, end, para + "(", result);
				generateParenthesis(start, end-1, para + ")", result);
			}
		}
	}


	public String multiply(String num1, String num2) {
		int m = num1.length();
		int n = num2.length();

		int[][] res = new int[n][m+n];
		int resIndex = 0;

		for(int i = n-1; i >= 0; i--) {
			int k = m + i;
			int a = num2.charAt(i) - '0';
			int carry = 0;

			for(int j = m-1; j >= 0; j--) {
				int b = num1.charAt(j) - '0';

				int prod = a * b;

				res[resIndex][k] = (prod + carry) / 10;
				carry = (prod + carry) % 10;
			}

			System.out.println(Arrays.toString(res[resIndex]));

			resIndex++;
		}


		String result = "";
		int carry = 0;
		for(int j = m + n -1; j >= 0; j--) {
			int sum = 0;

			for(int i = 0; i < num2.length(); i++) {
				sum += res[i][j];
			}

			sum = (sum + carry);
			carry = sum % 10;
			sum = sum / 10;

			result = sum + result;
		}

		int i = 0;

		while(i < result.length()) {
			if (result.charAt(i) == '0') {
				i++;
			}
		}

		return result.substring(i);
	}


	public boolean isMatch(String s, String p) {
		return isMatch(s, p, 0, 0, "");
	}

	private boolean isMatch(String s, String p, int i, int j, String str) {

		if (s.length() == i && p.length() == j) {
			System.out.println(str);
			return true;
		}

		if (p.charAt(j) == '*' && s.length() != i && p.length() == j+1) {
			System.out.println(str);
			return false;
		}

		if (s.charAt(i) == p.charAt(j)) {
			return  isMatch(s, p, i + 1, j+ 1, str + "2");
		}

		if (p.charAt(j) == '?') {
			return isMatch(s, p, i+1, j+1, str+ "2");
		}

		if (p.charAt(j) == '*') {
			return isMatch(s, p, i, j+1, str + "0") || isMatch(s, p, i + 1, j, str + "1") || isMatch(s, p, i + 1, j + 1, "");
		}

		return false;
	}

	public boolean isMatchDP(String s, String p) {
		int m  = p.length();
		int  n = s.length();

		int[][] dp = new int[m+1][n+1];

		dp[m][n] = 1;

		for(int i = m ; i >= 0; i--) {
			for(int  j = n  ; j >= 0; j--) {
				if (i == m ) {
					if (j == n)
						dp[i][j] = 1;
				} else if (j == n) {
					dp[i][j] = 0;
				} else if (s.charAt(j) == p.charAt(i) || p.charAt(i) == '?') {
					dp[i][j] = dp[i+1][j+1];
				} else if (p.charAt(i) == '*') {
					if (dp[i+1][j] == 1 || dp[i+1][j+1] == 1 || dp[i][j+1] == 1) {
						dp[i][j] = 1;
					}
				}
			}
		}

		return dp[0][0] == 1;
	}

	public String toLowerCase(String str) {
		StringBuilder builder = new StringBuilder();

		for(char ch : str.toCharArray()) {
			if (ch >= 'A' && ch <= 'Z') {
				ch = (char) (ch - 'A' + 'a');
			}

			builder.append(ch);
		}

		return builder.toString();
	}

	public boolean judgeCircle(String moves) {
		int x = 0;
		int y = 0;

		for(char ch : moves.toCharArray()) {
			switch (ch) {
				case 'U':
					y++;
					break;
				case 'D':
					y--;
					break;
				case 'L':
					x--;
					break;
				case 'R':
					x++;
					break;
			}
		}

		return  (x == 0 && y == 0);
	}

	public boolean canVisitAllRooms(List<List<Integer>> rooms) {
		boolean[] visited = new boolean[rooms.size()];

		Queue<Integer> queue = new LinkedList<>();

		List<Integer> keys = rooms.get(0);
		visited[0] = true;

		for(Integer key : keys) {
			queue.add(key);
		}

		while(!queue.isEmpty()) {
			Integer key = queue.poll();

			// open room and find keys
			visited[key] = true;
			for(Integer k : rooms.get(key)) {
				if (!visited[key]) {
					queue.add(k);
				}
			}
		}

		for(boolean visit : visited) {
			if (!visit)
				return false;
		}

		return true;
	}


	// Note string contains lower case letters only
	public static boolean isPatternMatchRobinKarp(String s, String p) {

		int base = 26;

		long patternHash = 0;

		for(int i = 0; i < p.length(); i++) {
			char ch = p.charAt(i);
			patternHash = patternHash * base + (ch - 'a');
		}

		int windowSize = p.length();

		if (s.length() < p.length())
			return false;

		long windowHash = 0;
		int sIndex = 0;
		int eIndex = 0;
		while(eIndex < windowSize) {
			char ch = s.charAt(eIndex++);
			windowHash = windowHash * base + (ch - 'a');
		}

		while(eIndex <= s.length()) {
			if (Long.compare(patternHash, windowHash) == 0) {
				return true;
			}

			char rch = s.charAt(sIndex++);
			char ch = s.charAt(eIndex++);
			windowHash = (long) ((windowHash - (rch - 'a')*Math.pow(base, windowSize-1)) * base + (ch - 'a'));
		}

		return false;
	}

	public String swap(String a, int i, int j)
	{
		char temp;
		char[] charArray = a.toCharArray();
		temp = charArray[i] ;
		charArray[i] = charArray[j];
		charArray[j] = temp;
		return String.valueOf(charArray);
	}

	public void printAllPermutation(String str, int left, int right) {
		if (left == right) {
			System.out.print(str);
		} else {
			for(int i = left; i <= right; i++) {
				swap(str, left, right);
				printAllPermutation(str, left + 1, right);
				swap(str, left, right);
			}
		}
	}

	public List<String> wordSubsets(String[] A, String[] B) {
        Map<Character, Integer> countMap = new HashMap<>();

        for(String b : B) {
        	Map<Character, Integer> tempMap = new HashMap<>();

        	for(char ch : b.toCharArray()) {
        		tempMap.put(ch, tempMap.getOrDefault(ch, 0) + 1);
			}

        	for(Map.Entry<Character, Integer> entry : tempMap.entrySet()) {
        		if (countMap.getOrDefault(entry.getKey(), 0) < entry.getValue()) {
        			countMap.put(entry.getKey(), entry.getValue());
				}
			}
		}

        List<String> result = new ArrayList<>();

        for(String a : A) {
			Map<Character, Integer> tempMap = new HashMap<>();

			for(char ch : a.toCharArray()) {
				tempMap.put(ch, tempMap.getOrDefault(ch, 0));
			}

			boolean shouldAdd = true;
			for(Map.Entry<Character, Integer> entry : tempMap.entrySet()) {
				if (countMap.getOrDefault(entry.getKey(), 0) < entry.getValue()) {
					shouldAdd = false;
					break;
				}
			}

			if (shouldAdd) {
				result.add(a);
			}
		}

        return result;
	}

	public boolean exist(char[][] board, String word) {
		return isWordExist(board, word, 0, new boolean[board.length][board.length], 0, 0);
	}

	public boolean isWordExist(char[][] board, String word, int index, boolean[][] visited, int x, int y) {
		if (index >= word.length()) {
			return true;
		}

		if (x < 0 || x > board.length)
			return false;

		if (y < 0 || y > board.length)
			return false;

		if (visited[x][y])
			return false;

		if (board[x][y] != word.charAt(index))
			return false;

		visited[x][y] = true;


		return isWordExist(board, word, index + 1, visited, x - 1, y)
				|| isWordExist(board, word, index + 1, visited, x + 1, y)
				|| isWordExist(board, word, index + 1, visited, x, y - 1)
				|| isWordExist(board, word, index + 1, visited, x, y + 1);
	}

    public String minWindow(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();

        for(char ch : t.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        int start = 0;
        int end = 0;


        int min = Integer.MAX_VALUE;
        int minStart = -1;
        int minEnd = -1;

        Map<Character, Integer> tempMap = new HashMap<>();

        //System.out.println(set);

        while(end < s.length()) {
            //System.out.println("1");
            while(end < s.length() ) {

                char ch =  s.charAt(end++);
                //System.out.println("Procesing : " + ch);
                if (map.containsKey(ch)) {
                    tempMap.put(ch, tempMap.getOrDefault(ch, 0) + 1);
                }

                //System.out.println(tempMap);

                if (tempMap.size() >= map.size()) {
                    boolean containsKey = true;
                    for(Map.Entry<Character, Integer> entry : map.entrySet()) {
                        if (map.get(entry.getKey()) > entry.getValue()) {
                            containsKey = false;
                        }
                    }

                    if (containsKey)
                        break;
                }
            }

            while (start < s.length()) {

                if (tempMap.size() < map.size()) {
                    break;
                } else {
                    boolean containsKey = true;
                    for(Map.Entry<Character, Integer> entry : map.entrySet()) {
                        if (map.get(entry.getKey()) > entry.getValue()) {
                            containsKey = false;
                        }
                    }

                    if (!containsKey)
                        break;
                }

                if (end - start < min) {
                    min = end - start;
                    minStart = start;
                    minEnd = end;
                }
                char ch = s.charAt(start++);

                //System.out.println("checking for removal : " + ch);

                if (map.containsKey(ch)) {
                    Integer count = tempMap.get(ch);
                    if (count != null) {
                        if (count == 1) {
                            tempMap.remove(ch);
                        } else {
                            tempMap.put(ch, count -1);
                        }
                    }
                }
            }
        }

        return s.substring(minStart, minEnd);
    }

    public List<String> restoreIpAddresses(String s) {
	    List<String> result = new ArrayList<>();

        buildIPAddresses(s, 0, new StringBuilder(), 0, result);

	    return result;
    }

    private void buildIPAddresses(String s, int rStringIndex, StringBuilder builder, int count, List<String> results) {
	    if (count == 4 && rStringIndex == s.length()) {
	        results.add(builder.toString());
        }

	    if (rStringIndex >= s.length())
	        return;

	    if (count > 4)
	        return;

	    for(int i = 1; i <= 3; i++) {
	        String subString = s.substring(rStringIndex, rStringIndex + i);
	        if (isValidPart(subString)) {
	            builder.append(subString);
	            if (count < 4) {
	                builder.append(".");
                }
	            buildIPAddresses(s, rStringIndex + i, builder, count + 1, results);
            }
        }
    }

    private boolean isValidPart(String s) {
	    if (s.length() > 3) {
	        return false;
        }

	    // 0 is a valid but 00, 01 are invalid
	    if (s.startsWith("0") && s.length() > 1)
	        return false;

	    int val = Integer.parseInt(s);
	    return val >= 0 && val <= 255;
    }

    public boolean isInterleave(String s1, String s2, String s3) {

	    if (s3.length() != (s1.length() + s2.length()))
	        return false;

	    return isInterleaveUtils(s1, s2, s3, 0, 0, 0, "");
    }

    public boolean isInterleaveUtils(String s1, String s2, String s3, int a, int b, int c, String s) {

	    System.out.println(s);

        if (a == s1.length() && b == s2.length() && c == s3.length())
            return true;

        if (a == s1.length() || b == s2.length() || c == s3.length())
            return false;

        if (s3.charAt(c) != s1.charAt(a) && s3.charAt(c) != s2.charAt(b))
            return false;

        boolean isInterleave = false;
        if (s3.charAt(c) == s1.charAt(a)) {
            isInterleave = isInterleave ||  isInterleaveUtils(s1, s2, s3, a + 1, b, c+1, s + s1.charAt(a));
        }

        if (s3.charAt(c) == s2.charAt(a)) {
            isInterleave = isInterleave ||  isInterleaveUtils(s1, s2, s3, a , b + 1, c+1, s + s2.charAt(b));
        }

        return isInterleave;
    }

    public boolean isInterleaveUtilsDP(String s1, String s2, String s3) {

        int M = s1.length();
        int N = s2.length();

        boolean[][] dp = new boolean[M+1][N+1];

        if (s3.length() != (s1.length() + s2.length()))
            return false;

        for(int i = 0; i <= M; i++) {
            for(int j = 0; j <= N; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = true;
                } else if (i == 0) {
                    if (s2.charAt(j-1) == s3.charAt(j-1)) {
                        dp[i][j] = dp[i][j-1];
                    }
                } else if (j == 0) {
                    if (s1.charAt(i-1) == s3.charAt(i-1)) {
                        dp[i][j] = dp[i-1][j];
                    }
                } else {
                    if (s1.charAt(i-1) == s3.charAt(i + j - 1) || s2.charAt(j-1) == s3.charAt(i + j - 1)) {
                        dp[i][j] = dp[i-1][j] || dp[i][j-1];
                    }
                }
            }
        }

        return dp[M][N];
    }

    public int numDistinct(String s, String t) {
        return countDistinct(s, t, 0, 0);
    }

    public int countDistinct(String s, String t, int a, int b) {
	    if (b >= t.length())
	        return 1;

	    if (a >= s.length())
	        return 0;

	    if (s.charAt(a) != t.charAt(b)) {
	        return countDistinct(s, t, a + 1, b);
        }

	    return countDistinct(s, t, a + 1, b) + countDistinct(s, t, a + 1, b + 1);
    }

    public int countDistinctDP(String s, String t) {
        int M = s.length();
        int N = t.length();

        int[][] dp = new int[M+1][N+1];

        for(int i = 0; i <= M; i++) {
            for(int j = 0; j <= N; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = 1;
                } else if (i == 0) {
                    dp[i][j] = 0;
                } else if (j == 0) {
                    dp[i][j] = 1;
                } else {
                    if (s.charAt(i-1) != t.charAt(j-1)) {
                        dp[i][j] = dp[i-1][j];
                    } else {
                        dp[i][j] = dp[i-1][j-1] + dp[i][j-1];
                    }
                }
            }
        }

        return dp[s.length()][s.length()];
    }

    public int countDistinctSeq(String s) {
	    int[] dp = new int[s.length() + 1];
	    dp[0] = 1;

	    Map<Character, Integer> map = new HashMap<>();
	    for(int i = 1; i < dp.length; i++) {
	        char ch = s.charAt(i-1);

	        dp[i] = 2 * dp[i-1];
	        if (map.containsKey(ch)) {
	            int j = map.get(ch);
	            dp[i] = dp[i] - dp[j-1];
            }

	        map.put(ch, i);
        }

	    return dp[s.length()];
    }

	public String defangIPaddr(String address) {
		return address.replaceAll("\\.", "[\\.]");
	}

	public int calculateTime(String keyboard, String word) {
		Map<Character, Integer> indexByCharMap = new HashMap<>();

		for(int i = 0; i < keyboard.length(); i++) {
			indexByCharMap.put(keyboard.charAt(i), i);
		}

		int lastIndex = 0;
		int timeTaken = 0;

		for(char ch : word.toCharArray()) {
			Integer index = indexByCharMap.get(ch);

			timeTaken += (index - lastIndex);
			lastIndex = index;
		}

		return timeTaken ;
	}

	public String interpret(String command) {
		StringBuilder builder = new StringBuilder();

		int lastBraceIndex = -1;
		for (int i = 0; i < command.length(); i++) {
			char ch = command.charAt(i);
			if (ch == ')') {
				if (i == lastBraceIndex + 1) {
					builder.append('o');
				} else {
					for(int j = lastBraceIndex + 1; j < i; j++ ) {
						builder.append(command.charAt(j));
					}
				}

				lastBraceIndex = -1;
			} else if (ch == '(') {
				lastBraceIndex = i;
			} else {
				if (lastBraceIndex == -1) {
					builder.append(ch);
				}
			}
		}

		return builder.toString();
	}

	public int balancedStringSplit(String s) {
		int sum = 0;

		int count = 0;

		for(char ch : s.toCharArray()) {
			if (ch == 'R') {
				sum += 1;
			} else {
				sum -= 1;
			}

			if (sum == 0) {
				count++;
			}
		}

		return count;

	}

	public int maxDepth(String s) {
		int count = 0;

		int max = 0;
		for(char ch : s.toCharArray())  {
			if (ch == '(') {
				count++;

				max = Math.max(count, max);
			} else if (ch == ')') {
				if (count > 0)
					count--;
			}
		}

		return max;
	}

	public int countConsistentStrings(String allowed, String[] words) {
		Set<Character> set = new HashSet<>();

		for(char ch : allowed.toCharArray()) {
			set.add(ch);
		}

		int count = 0;
		for(String word: words) {
			boolean isConsistent = true;
			for(char ch : word.toCharArray()) {
				if (!set.contains(ch)) {
					isConsistent = false;
					break;
				}
			}

			if (isConsistent)
				count++;

		}

		return count;
	}

	public int countLetters(String S) {

		int substrings[] = new int[S.length()];
		int total = 1;
		substrings[0] = 1;
		for (int i = 1; i < S.length(); i++) {
			if (S.charAt(i) == S.charAt(i - 1)) {
				substrings[i] = substrings[i - 1] + 1;
			} else {
				substrings[i] = 1;
			}
			total += substrings[i];
		}
		return total;
	}

	public String freqAlphabets(String s) {
		int n = s.length();

		StringBuilder builder = new StringBuilder();

		int i = 0;
		while(i < s.length()) {
			if (i < n - 2 && s.charAt(i+2) == '#') {
				int num = Integer.parseInt(s.substring(i, i+2));
				builder.append((char) (num + 96));
				i = i + 3;

			} else {
				builder.append((char)('a' + s.charAt(i) - '0'));
			}
		}

		return builder.toString();
	}

	public String destCity(List<List<String>> paths) {
		Set<String> sources = new HashSet<>();
		Set<String> dests = new HashSet<>();

		for(List<String> path : paths) {
			sources.add(path.get(0));
			dests.add(path.get(1));
		}

		for(String dest : dests) {
			if (!sources.contains(dest))
				return dest;
		}

		return "";
	}

	public boolean halvesAreAlike(String s) {
		Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));

		int sIndex = 0;
		int mIndex = s.length()/2;

		int firstCount = 0;
		int secondCount = 0;

		while(sIndex < mIndex) {
			if (vowels.contains(s.charAt(sIndex++))) {
				firstCount++;
			}
		}

		while(mIndex < s.length()) {

			if (vowels.contains(s.charAt(mIndex++))) {
				secondCount++;
			}
		}

		return firstCount == secondCount;
	}

	public String mergeAlternately(String word1, String word2) {
		StringBuilder builder = new StringBuilder();

		int i = 0;
		int j = 0;

		while(i < word1.length() && j < word2.length()) {
			if (i <= j) {
				builder.append(word1.charAt(i++));
			} else {
				builder.append(word2.charAt(j++));
			}
		}

		while (i < word1.length()) {
			builder.append(word1.charAt(i++));
		}

		while (j < word2.length()) {
			builder.append(word2.charAt(j++));
		}

		return builder.toString();
	}

	public String reverseWords(String s) {
		StringBuilder builder = new StringBuilder();
		StringBuilder temp = new StringBuilder();

		for(int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (ch != ' ') {
				temp.append(ch);
			} else {
				if (temp.length() > 0) {
					builder.append(temp.reverse());
					temp = new StringBuilder();
				}

				builder.append(ch);
			}
		}

		if (temp.length() > 0) {
			builder.append(temp.reverse().toString());
		}

		return builder.toString();
	}

	public int isPrefixOfWord(String sentence, String searchWord) {
		String[] words = sentence.split(" ");

		for(int i = 0; i < words.length; i++) {
			if (words[i].startsWith(searchWord))
				return i+1;
		}

		return -1;
	}

	public List<String> stringMatching(String[] words) {
		Arrays.sort(words, Comparator.comparingInt(String::length));

		List<String> result = new ArrayList<>();

		for(int i = 0; i < words.length - 1; i++) {
			for (int j = i + 1; j < words.length; j++) {
				if (words[j].contains(words[i])) {
					result.add(words[i]);
				}
			}
		}


		return result;
	}

	public boolean areAlmostEqual(String s1, String s2) {

		if (s1 == null && s2 == null)
			return true;

		if (s1 == null || s2 == null)
			return false;

		if (s1.length() != s2.length())
			return false;

		int firstIndex = -1;
		int secondIndex = -1;

		for (int i = 0; i < s1.length() ; i++) {
			if (s1.charAt(i) != s2.charAt(i)) {
				if (secondIndex != -1) {
					return false;
				} else {
					if (firstIndex != -1) {
						secondIndex = i;
					} else {
						firstIndex = i;
					}
				}
			}
		}

		if (firstIndex == - 1 && secondIndex == -1)
			return true;

		if (firstIndex == - 1 || secondIndex == -1) {
			return false;
		}

		return s1.charAt(firstIndex) == s2.charAt(secondIndex) && s1.charAt(secondIndex) == s2.charAt(firstIndex);
	}


	public int maxPower(String s) {
		int count = 1;

		int max = 0;

		for(int i = 0; i < s.length(); i++) {

			if (i > 0) {
				if (s.charAt(i) == s.charAt(i-1)) {
					count++;
				} else {
					max = Math.max(count, max);
					count = 1;
				}
			}

		}

		max = Math.max(count, max);

		return max;
	}

	public int[][] indexPairs(String text, String[] words) {
		List<List<Integer>> result = new ArrayList<>();

		for(int i = 0; i < words.length; i++) {

			List<Integer> startIndexes = getStartIndexMatch(text, words[i]);

			for(int startIndex : startIndexes) {
				result.add(Arrays.asList(startIndex, startIndex + words[i].length() - 1));
			}
		}

		result.sort((l1, l2) -> {
			int val = Integer.compare(l1.get(0), l2.get(0));
			if (val == 0)
				return Integer.compare(l1.get(1), l2.get(1));
			return val;
		});

		int[][] res = new int[result.size()][2];

		for(int i = 0; i < result.size(); i++) {
			List<Integer> list = result.get(i);
			int[] arr = {list.get(0), list.get(1)};
			res[i] = arr;
		}

		return res;
	}

	public static List<Integer> getStartIndexMatch(String s, String p) {

		int base = 26;

		long patternHash = 0;

		List<Integer> results = new ArrayList<>();

		for(int i = 0; i < p.length(); i++) {
			char ch = p.charAt(i);
			patternHash = patternHash * base + (ch - 'a');
		}

		int windowSize = p.length();

		if (s.length() < p.length())
			return results;

		long windowHash = 0;
		int sIndex = 0;
		int eIndex = 0;
		while(eIndex < windowSize) {
			char ch = s.charAt(eIndex++);
			windowHash = windowHash * base + (ch - 'a');
		}

		while(eIndex <= s.length()) {
			if (Long.compare(patternHash, windowHash) == 0) {
				results.add(sIndex);
			}

			char rch = s.charAt(sIndex++);
			char ch = s.charAt(eIndex++);
			windowHash = (long) ((windowHash - (rch - 'a')*Math.pow(base, windowSize-1)) * base + (ch - 'a'));
		}

		return results;
	}

	public int maxLengthBetweenEqualCharacters(String s) {
		Map<Character, Integer> map = new HashMap<>();

		int max = -1;
		for(int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			Integer index = map.get(ch);
			if (index != null) {
				max = Math.max(max, i - index - 1);
			}

			map.put(ch, i);
		}

		return max;
	}

	public int countBinarySubstrings(String s) {
		int sum = 0;

		Set<Integer> set = new HashSet<>();

		int count = 0;

		for(char ch : s.toCharArray()) {
			if (ch  == '0') {
				sum += -1;
			} else {
				sum += 1;
			}

			if (set.contains(sum)) {
				count++;
			}
			set.add(sum);
		}

		return count;
	}

	public String thousandSeparator(int n) {
		int count = 0;

		StringBuilder result = new StringBuilder();

		while(n != 0) {
			int digit = n % 10;
			count++;

			result.insert(0, (char)(digit + '0'));

			if (count % 3 == 0 && n != 0) {
				result.insert(0, '.');
			}

			n = n / 10;
		}

		return result.toString();
	}

	public int lengthOfLongestSubstring(String s) {
		Set<Character> set = new HashSet<>();

		int startIndex = 0;
		int endIndex = 0;

		int max = 0;

		while(endIndex < s.length()) {
			while(!set.contains(s.charAt(endIndex)) && endIndex < s.length()) {
				set.add(s.charAt(endIndex++));
			}

			max = Math.max(max, endIndex - startIndex);

			while (set.contains(s.charAt(startIndex))) {
				set.remove(s.charAt(startIndex++));
			}
		}

		return max;
	}

	public boolean backspaceCompare(String S, String T) {

		if (S == null && T ==  null)
			return true;

		if (S == null || T == null)
			return false;

		if (S.length() == 0 && T.length() == 0)
			return true;

		if (S.length() == 0 || T.length() == 0)
			return false;

		String sClean = getActualString(S);
		String tClean = getActualString(T);

		return sClean.equals(tClean);
	}

	public static String getActualString(String s) {
		Stack<Character> stack = new Stack<>();

		for(char ch : s.toCharArray()) {
			if (ch == '#') {
				if (!stack.isEmpty())
					stack.pop();
			} else {
				stack.add(ch);
			}
		}

		StringBuilder builder = new StringBuilder();

		while(!stack.isEmpty()) {
			builder.append(stack.pop());
		}

		return builder.reverse().toString();
	}

	public int[][] kClosest(int[][] points, int k) {
		PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingDouble(a -> distanceFormula(a[0], a[1])));

		for(int[] point : points) {
			pq.add(point);
		}

		int[][] result = new int[k][2];

		for(int i = 0; i < k; i++) {
			result[i] = pq.poll();
		}

		return result;
	}

	private double distanceFormula(int x, int y) {
		return Math.sqrt((x*x + y*y));
	}

	public String licenseKeyFormatting(String S, int K) {

		StringBuilder builder = new StringBuilder();

		int count = 0;

		int i = S.length() - 1;

		while(i >= 0) {
			char ch = Character.toUpperCase(S.charAt(i));
			if (ch == '-') {
				i--;
			} else if (count != 0 && count % K == 0) {
				builder.insert(0, '-');
				count = 0;
			} else {
				builder.insert(0, ch);
				i--;
				count++;
			}
		}

		return builder.toString();

	}

	public int totalFruit(int[] tree) {
		Map<Integer, Integer> countByFruitTypeMap = new HashMap<>();

		int totalFruits = 0;
		int maxFruits = 0;

		int start = 0;
		int end = 0;

		while(end < tree.length) {
			countByFruitTypeMap.put(tree[end], countByFruitTypeMap.getOrDefault(tree[end], 0) + 1);

			while(countByFruitTypeMap.size() > 2) {
				Integer count = countByFruitTypeMap.get(tree[start]);
				if (count == 1) {
					countByFruitTypeMap.remove(tree[start]);
				} else {
					countByFruitTypeMap.put(tree[start], count - 1);
				}

				start++;
			}

			totalFruits = 0;
			for(Map.Entry<Integer, Integer> entry : countByFruitTypeMap.entrySet()) {
				totalFruits += entry.getValue();
			}

			maxFruits = Math.max(maxFruits, totalFruits);

			end++;
		}

		return maxFruits;
	}

	public boolean isIsomorphic(String s, String t) {
		Map<Character, Character> map = new HashMap<>();

		Set<Character> used = new HashSet<>();

		if (s == null && t == null)
			return true;

		if (s == null || t == null)
			return false;

		if (s.length() == 0 && t.length() == 0)
			return true;

		if (s.length() == 0 || t.length() == 0)
			return false;

		for(int i = 0; i < s.length(); i++) {
			if (map.containsKey(s.charAt(i))) {
				if(map.get(s.charAt(i)) != t.charAt(i)) {
					return false;
				}
			} else {
				if (used.contains(t.charAt(i))) {
					return false;
				}
			}

			map.putIfAbsent(s.charAt(i), t.charAt(i));
			used.add(t.charAt(i));
		}

		return false;
	}

	public static int titleToNumber(String columnTitle) {
		int result = 0;

		for(char ch : columnTitle.toCharArray()) {
			result = result * 26 + (ch - 'A' + 1);
		}

		return result;
	}

	TrieDataStructure trieDS = new TrieDataStructure();
	public List<String> findWords(char[][] board, String[] words) {

		Set<String> set = new HashSet<>();
		for(String word:  words) {
			set.add(word);

		}

		boolean[][] visited = new boolean[board.length][board[0].length];

		List<String> result = new ArrayList<>();

		for(int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				dfsWords(board, i, j, set, visited, "", result);
			}
		}

		return result;
	}

	int[][] directions = {
			{0, 1},
			{1, 0},
			{0, -1},
			{-1, 0}
	};

	private void dfsWords(char[][] board, int i, int j, Set<String> words, boolean[][] visited, String wsf, List<String> result) {

		if (words.contains(wsf)) {
			result.add(wsf);
			return;
		}

		if (visited[i][j])
			return;

		visited[i][j] = false;

		for(int[] dir : directions) {
			int x = i + dir[0];
			int y = j + dir[1];

			if (x >= 0 && x < board.length && y >= 0 && y < board[0].length && !visited[x][y]) {
				dfsWords(board, x, y, words, visited, wsf + board[i][j], result);
			}
		}
	}


}


