package prepcoding;

public class Recursion {

    private static boolean coinChange(int[] coins, int remAmount, int index) {
        if (remAmount == 0) {
            return true;
        }

        if (index >= coins.length) {
            return false;
        }

        if (remAmount < 0) {
            return false;
        }

        return coinChange(coins, remAmount, index+1) || coinChange(coins, remAmount - coins[index], index+1);
    }

    public int change(int amount, int[] coins) {
        return change(amount, coins, 0);
    }

    private int change(int amount, int[] coins, int index) {
        if (amount == 0) {
            return 1;
        }

        if (amount < 0 || index > coins.length) {
            return 0;
        }

        return change(amount, coins, index + 1) + change(amount - coins[index], coins, index);
    }

    public static int queenNumberOfWays(int[][] board, int queen, int row, int col) {
        if (queen == 0) {
            return 1;
        }

        if (row >= board.length && col >= board.length) {
            return 0;
        }

        int nr;
        int nc;

        if (col == board.length - 1) {
            nc = 0;
            nr = row + 1;
        }  else {
            nr = row;
            nc = col + 1;
        }

        return queenNumberOfWays(board, queen, nr, nc) + queenNumberOfWays(board, queen-1, nr, nc);
    }

    public static int nQueenPermutation(int[][] board, int queens) {
        if (queens == 0) {
            return 1;
        }

        int count = 0;

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board.length; j++) {
                board[i][j] = 1;
                count += nQueenPermutation(board, queens - 1);
                board[i][j] = 0;
            }
        }

        return count;
    }

    public static void nQueenWithCellFormula(boolean[][] board, int queens, int index) {
        int n = board.length;
        if (index >= n * n) {
            // Print the board
            return;
        }

        for(int cellPos = index; cellPos < n * n; cellPos++) {
            int row = cellPos / n;
            int col = cellPos % n;

            board[row][col] = true;
            nQueenWithCellFormula(board, queens - 1, cellPos + 1);
            board[row][col] = false;
        }

    }

    private static boolean isQueenSafe(boolean[][] board, int row, int col) {

        // vertical check
        for(int i = row-1, j = col; i >= 0; i--) {
            if (board[i][j]) {
                return false;
            }
        }

        // horizontal check
        for(int i = row, j = col; j >= 0; j--) {
            if (board[i][j]) {
                return false;
            }
        }

        // left diagonal check
        for(int i = row-1, j = col-1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j]) {
                return false;
            }
        }

        // right diagonal check
        for(int i = row -1, j = col + 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j]) {
                return false;
            }
        }

        return true;
    }

    public static void nQueenWithCellFormulaWithProtection(boolean[][] board, int queens, int index) {
        int n = board.length;
        if (index >= n * n) {
            // Print the board
            return;
        }

        for(int cellPos = index; cellPos < n * n; cellPos++) {
            int row = cellPos / n;
            int col = cellPos % n;

            if (board[row][col] == false && isQueenSafe(board, row, col)) {
                board[row][col] = true;
                nQueenWithCellFormula(board, queens - 1, cellPos + 1);
                board[row][col] = false;
            }
        }
    }

}
