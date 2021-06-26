package challenges;

import java.util.ArrayList;
import java.util.List;

public class MayChallenge {

    public int countComponents(int n, int[][] edges) {
        List<Integer>[] graph = buildGraph(n, edges);

        boolean[] visited = new boolean[n+1];

        int count = 0;

        for (int u = 1 ; u <= n; u++) {
            if (!visited[u]) {
                count++;
                markAConnectedComponent(graph, u, visited);
            }
        }

        return count;
    }

    private List<Integer>[] buildGraph(int n, int[][] edges) {
        List<Integer>[] graph = new List[n+1];

        for(int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            if (graph[u] == null) {
                graph[u] = new ArrayList<>();
            }

            graph[u].add(v);
        }

        return graph;
    }

    private void markAConnectedComponent(List<Integer>[] graph, int u, boolean[] visited) {

        if (visited[u]) {
            return;
        }

        visited[u] = true;

        for(int neigh : graph[u]) {
            markAConnectedComponent(graph, neigh, visited);
        }
    }

    class TicTacToe {

        int[][] game;

        public TicTacToe(int n) {
            game = new int[n][n];
        }

        public int move(int row, int col, int player) {
            game[row][col] = player;

            return isPlayerWin(row, col, game.length, player) ? 1 : 0;
        }

        private boolean isPlayerWin(int row, int col, int n,  int player) {
            boolean isWin = false;

            // check current row
            for(int j = 0; j < n; j++) {
                isWin =  isWin && (game[row][j] == player);
            }

            if (isWin) {
                System.out.println("Row match");
                return isWin;
            }

            isWin = true;
            // check for current col
            for(int i = 0; i < n; i++) {
                isWin = isWin && (game[i][col] == player);
            }

            if (isWin) {
                System.out.println("Col match");
                return isWin;
            }

            isWin = true;
            for(int i = 0, j = 0; i <n && j <n; i++, j++) {
                isWin = isWin && (game[i][j] == player);
            }

            if (isWin) {
                System.out.println("Diag match");
                return isWin;
            }



            isWin = true;
            for(int i = 0, j = n; i <n && j >= 0; i++, j--) {
                isWin = isWin && (game[i][j] == player);
            }

            System.out.println("Rev diag match");
            return isWin;
        }

    }

}
