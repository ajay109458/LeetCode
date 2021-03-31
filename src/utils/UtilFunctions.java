package utils;

public class UtilFunctions {

    public static int sumOfDigits(int num) {
        int sum = 0;

        while(num != 0) {
            int digit = num % 10;
            sum += digit;
            num /= 10;
        }

        return sum;
    }

}
