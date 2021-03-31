package arraytests;

import hash.HashHelper;
import org.junit.jupiter.api.Test;

public class HashTests {

    @Test
    public void testMaxSubArrayWithZeroSum() {
        int[] arr = {15, -2, 2, -8, 1, 7, 10, 23};
        int len = HashHelper.findLargestSubArrayWith0Sum(arr);
        System.out.println(len);
    }
}
