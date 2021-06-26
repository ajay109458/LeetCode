package companies;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class ArrayProblemTests {

    @Test
    public void validateTransposeMatrix() {

        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

        ArrayProblems.rotateAMatrix(matrix);

        for(int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }

    @Test
    public void validateMatrixSpiralPrint() {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        ArrayProblems.printSpiralMatrix(matrix);
    }

    @Test
    public void validateIfJumpPossible() {
        int[] arr = {3,2,1,0,4};

        System.out.println(ArrayProblems.isJumpPossible(arr));
    }

}
