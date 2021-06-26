package grokking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class BitwiseOperatorTests {

    @Nested
    @DisplayName("Unique Number Tests")
    class UniqueNumberTests {

        @Test
        public void test1() {
            int[] arr = {1, 4, 2, 1, 3, 2, 3};
            int val =  BitwiseXor.findUniqueNumber(arr);
            Assertions.assertEquals(4, val);
        }

        @Test
        public void test2() {
            int[] arr = {7, 9, 7};
            int val = BitwiseXor.findUniqueNumber(arr);
            Assertions.assertEquals(9, val);
        }
    }

    @Nested
    @DisplayName("Two Single Numbers")
    class TwoSingleNumbers {

        @Test
        public void test1() {
            int[] arr = {1, 4, 2, 1, 3, 5, 6, 2, 3, 5};
            int[] result = BitwiseXor.findTwoNumbers(arr);
            Arrays.sort(result);
            Assertions.assertArrayEquals(new int[]{4,6}, result);
        }

        @Test
        public void test2() {
            int[] arr = {2, 1, 3, 2};
            int[] result = BitwiseXor.findTwoNumbers(arr);
            Arrays.sort(result);
            Assertions.assertArrayEquals(new int[]{1, 3}, result);
        }

    }

    @Nested
    @DisplayName("Complement of a number")
    class ComplementOfNumber {
        @Test
        public void test1() {
            int complement = BitwiseXor.complement(8);
            Assertions.assertEquals(7, complement);
        }

        @Test
        public void test2() {
            int complement = BitwiseXor.complement(10);
            Assertions.assertEquals(5, complement);
        }
    }

    @Nested
    @DisplayName("Inverse Matrix")
    class InverseMatrixTests {
        @Test
        public void test1() {
            int[][] matrix = {{1,0,1}, {1,1,1}, {0,1,1}};
            BitwiseXor.flipAndInvertImage(matrix);
            for(int row = 0; row < matrix.length; row++) {
                System.out.println(Arrays.toString(matrix[row]));
            }
        }
    }

}
