package grokking;

public class IslandProblems {

    public int countIslands(int[][] matrix) {
        int totalIslands = 0;
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 1 && !visited[i][j]) {
                    totalIslands++;
                    dfs(matrix, i, j, visited);
                }
            }
        }

        return totalIslands;
    }

    private void dfs(int[][] matrix, int row, int col, boolean[][] visited) {
        if (row < 0 || row >= matrix.length) {
            return;
        }

        if (col < 0 || col >= matrix[0].length) {
            return;
        }

        if (visited[row][col]) {
            return;
        }

        visited[row][col] = true;

        dfs(matrix, row + 1, col, visited);
        dfs(matrix, row - 1, col, visited);
        dfs(matrix, row, col + 1, visited);
        dfs(matrix, row, col - 1, visited);
    }

    public int maxAreaOfIsland(int[][] matrix) {
        int biggestIslandArea = 0;

        boolean[][] visited = new boolean[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 1 && !visited[i][j]) {
                    biggestIslandArea = Math.max(biggestIslandArea, dfsArea(matrix, i, j, visited));
                }
            }
        }

        return biggestIslandArea;
    }

    private int dfsArea(int[][] matrix, int row, int col, boolean[][] visited) {
        if (row < 0 || row >= matrix.length) {
            return 0;
        }

        if (col < 0 || col >= matrix[0].length) {
            return 0;
        }

        if (visited[row][col]) {
            return 0;
        }

        visited[row][col] = true;

        return 1 + dfsArea(matrix, row + 1, col, visited)
        + dfsArea(matrix, row - 1, col, visited)
        + dfsArea(matrix, row, col + 1, visited)
        + dfsArea(matrix, row, col - 1, visited);
    }

        public int[][] floodFill(int[][] image, int sr, int sc, int color) {
            if (image[sr][sc] == color) {
                return image;
            }

            floodFill(image, sr, sc, image[sr][sc], color);
            return image;
        }

        public void floodFill(int[][] image, int sr, int sc, int srcColor, int color) {
            if (sr < 0 || sc < 0 || sr >= image.length || sc >= image[0].length || color < 0) {
                return;
            }

            if (image[sr][sc] == srcColor) {
                image[sr][sc] = color;
            } else {
                return;
            }

            floodFill(image, sr+1, sc, srcColor, color);
            floodFill(image, sr-1, sc, srcColor, color);
            floodFill(image, sr, sc+1, srcColor, color);
            floodFill(image, sr, sc-1, srcColor, color);
        }

    public int closedIsland(int[][] grid) {

        int count = 0;

        boolean[][] visited = new boolean[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 0 && !visited[i][j]) {

                    if (dfsClosedIsland(grid, i, j, visited)) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    private boolean dfsClosedIsland(int[][] matrix, int row, int col, boolean[][] visited) {
        if (row < 0 || row >= matrix.length || col < 0 || col >= matrix[0].length) {
            return false;
        }

        if (matrix[row][col] == 1 || visited[row][col]) {
            return true;
        }

        visited[row][col] = true;

        return dfsClosedIsland(matrix, row + 1, col, visited) &&
        dfsClosedIsland(matrix, row - 1, col, visited) &&
        dfsClosedIsland(matrix, row, col + 1, visited) &&
        dfsClosedIsland(matrix, row, col - 1, visited);
    }

    public int islandPerimeter(int[][] grid) {
        int perimeter = 0;

        boolean[][] visited = new boolean[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    perimeter += dfsPerimeter(grid, i, j, visited);
                }
            }
        }

        return perimeter;
    }

    private int dfsPerimeter(int[][] grid, int row, int col, boolean[][] visited) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length) {
            return 1;
        }

        if (grid[row][col] == 0) {
            return 1;
        }

        if (visited[row][col]) {
            return 0;
        }

        visited[row][col] = true;

        return dfsPerimeter(grid, row + 1, col, visited) +
                dfsPerimeter(grid, row, col + 1, visited) +
                dfsPerimeter(grid, row, col - 1, visited) +
                dfsPerimeter(grid, row-1, col, visited);
    }



}
