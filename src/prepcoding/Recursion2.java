package prepcoding;

import java.util.*;

public class Recursion2 {

    public static void combinations(int n, int k) {
        combinationInternals(n, k, 1, "");
    }

    private static void combinationInternals(int n, int k, int index, String result) {
        if (result.length() == k) {
            System.out.println(result);
            return;
        }

        if (index > n) {
            return;
        }

        combinationInternals(n, k, index + 1, result);
        combinationInternals(n, k, index + 1, result + index);
    }

    public static List<String> generateAllPermutations(String input) {

        Map<Character, Integer> freqMap = new HashMap<>();

        for(char ch : input.toCharArray()) {
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }

        List<String> result = new ArrayList<>();
        populateAllPermutations(input.length(), freqMap, "", result);
        return result;
    }

    private static void populateAllPermutations(int len, Map<Character, Integer> freqMap, String result, List<String> list) {

        if (result.length() == len) {
            list.add(result);
            return;
        }

        for(Character ch : freqMap.keySet()) {
            int count = freqMap.get(ch);
            if (count > 0) {
                freqMap.put(ch, freqMap.get(ch) - 1);
                populateAllPermutations(len, freqMap, result + ch, list);
                freqMap.put(ch, freqMap.get(ch) + 1);
            }
        }
    }

    public static List<String> generateUniqueCombinations(String input, int k) {
        List<String> result = new ArrayList<>();

        String uniqueCharString = getUniqueCharacters(input);

        populateUniqueCombinations("", result, uniqueCharString, 0, k);

        return result;
    }

    private static String getUniqueCharacters(String input) {
        HashSet<Character> set = new HashSet<>();

        for(char ch : input.toCharArray()) {
            set.add(ch);
        }

        StringBuilder builder = new StringBuilder();

        for(Character ch : set) {
            builder.append(ch);
        }

        return builder.toString();
    }

    private static void populateUniqueCombinations(String word, List<String> results, String uniqueChars, int index, int k) {

        if (word.length() == k) {
            results.add(word);
            return;
        }

        if (index == uniqueChars.length()) {
            return;
        }

        populateUniqueCombinations(word, results, uniqueChars, index + 1, k);
        populateUniqueCombinations(word + uniqueChars.charAt(index), results, uniqueChars, index + 1, k);
    }


    public static List<String> wordKSelectionII(String input, int k) {
        String uniqueString = getUniqueCharacters(input);

        List<String> result = new ArrayList<>();

        populateWorkSelectionII(uniqueString, 0, k, "", result);

        return result;
    }

    private static void populateWorkSelectionII(String uniqueString, int index, int k, String curr, List<String> results) {

        if (curr.length() == k) {
            results.add(curr);
            return;
        }

        if (index == uniqueString.length()) {
            return;
        }

        for(int i = index; i < uniqueString.length(); i++) {
            char ch = uniqueString.charAt(i);
            populateWorkSelectionII(uniqueString, i + 1, k, curr + ch, results);
        }

    }

    public static List<String> getWordKLength(String input, int k) {
        List<String> result = new ArrayList<>();



        return result;
    }

    private static void populateWordKLengthK(String uniqueSting, int index, int k, String curr, List<String> result) {

         if (curr.length() == k) {
             result.add(curr);
             return;
         }

         if (index == uniqueSting.length()) {
             return;
         }



    }
}
