package pepcoding;

import org.junit.jupiter.api.Test;
import prepcoding.StackQueue;

import java.util.Arrays;

public class StackAndQueueTests {

    @Test
    public void nextGreaterElement() {
        int[] arr = {4, 5, 2, 25};

        System.out.print(Arrays.toString(StackQueue.nextGreaterElementOnRight(arr)));
    }



}
