package maths;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MathsHelper {

    public String intToRoman(int num) {
        Map<Integer, String> valByCharMap = new TreeMap<>();

        valByCharMap.put(1000, "M");
        valByCharMap.put(900, "XM");
        valByCharMap.put(500, "D");
        valByCharMap.put(400, "CD");
        valByCharMap.put(100, "C");
        valByCharMap.put(90, "XC");
        valByCharMap.put(50, "L");
        valByCharMap.put(40, "XL");
        valByCharMap.put(10, "X");
        valByCharMap.put(9, "IX");
        valByCharMap.put(5, "V");
        valByCharMap.put(4, "IV");
        valByCharMap.put(1, "I");


        String result = "";

        for(Map.Entry<Integer, String> entry : valByCharMap.entrySet()) {
            int count = num / entry.getKey();

            while(count-- > 0) {
                result += entry.getValue();
            }

            num %= entry.getKey();
        }

        return result;
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

}
