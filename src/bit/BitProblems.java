package bit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BitProblems {

    public int singleNumber(int[] nums) {

        Map<Integer, Integer> map = new HashMap<>();

        for(int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for(int num: nums) {
            if( map.get(num) == 1) {
                return num;
            }
        }

        return -1;
    }

    public List<String> findRepeatedDnaSequences(String s) {
        Map<String, Integer> map = new HashMap<>();

        for(int i = 0; i < s.length() - 10; i++) {
            String pattern = s.substring(i, i + 10-1);
            map.put(pattern, map.getOrDefault(pattern, 0) + 1);
        }

        List<String> result = new ArrayList<>();
        for(Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 1) {
                result.add(entry.getKey());
            }
        }

        return result;
    }

    public int rangeBitwiseAnd(int m, int n) {
        int shift = 0;

        while(m < n) {
            m >>= 1;
            n >>= 1;

            shift++;
        }

        return m << shift;
    }

    public int[] singleNumber1(int[] nums) {
        int xor = 0;

        for(int num : nums) {
            xor ^= num;
        }

        int index = 0;

        while((xor & (1 << index)) == 0){
            index++;
        }

        int num1 = 0;
        int num2 = 0;

        for(int num: nums) {
            if ((num & (1 << index)) != 0) {
                num1 ^= num;
            } else {
                num2 ^= num;
            }
        }

        return new int[]{num1, num2};
    }

    public int maxProduct(String[] words) {
        int n = words.length;

        int maxLen = 0;
        for(int i = 0; i < n; i++) {
            for (int j = i +1; j < n; j++) {
                if (noCommonLetters(words[i], words[j])) {
                    maxLen = Math.max(maxLen, words[i].length() * words[j].length());
                }
            }
        }

        return maxLen;
    }

    private boolean noCommonLetters(String s1, String s2) {
        int bit1 = 0;
        int bit2 = 0;

        for(int i = 0; i < s1.length(); i++) {
            bit1 |= (1 << (s1.charAt(i) - 'a'));
        }

        for(int j = 0; j < s2.length(); j++) {
            bit2 |= (1 << (s2.charAt(j) - 'a'));
        }

        return (bit1 & bit2) == 0;
    }

    public int[] countBits(int num) {
        int[] res = new int[num+1];

        for(int i = 0; i <= num; i++) {
            int k = i;
            int count = 0;
            while(k!= 0) {
                k = k & (k-1);
                count++;
            }
            res[i]= count;
        }

        return res;
    }


    public int findMaximumXOR(int[] nums) {
        int res = 0;

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                res = Math.max(res, nums[i] ^ nums[j]);
            }
        }

        return res;
    }

    public int totalHammingDistance(int[] nums) {
        int distance = 0;

        int n = nums.length;
        for(int i = 0; i < 32; i++) {
            int k = 0;
            for (int num : nums) {
                if ((num & (1 << i)) == 0) {
                    k++;
                }
            }

            distance += (k * (n-k));
        }

        return distance;
    }
}
