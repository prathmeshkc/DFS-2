import java.util.*;

// Time Complexity : O(m*n)
// Space Complexity : O(m*n) (queue size can be m*n in worst case) or O(1) in DFS
// Did this code successfully run on Leetcode: YES
// Any problem you faced while coding this: No


// Your code here along with comments explaining your approach

/**
 * In BFS, since we want to count the number of connected components,
 * we start from the first '1' we encounter and do a BFS traversal to mark
 * all the connected '1's as '0' so that we don't repeat the BFS (Mark it visited).
 * In DFS, we do the same thing recursively. Notice that in recursion, we write the base
 * condition opposite to that in BFS.
 */


public class Problem1 {

    //BFS
    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] dirs = new int[][]{
                {-1, 0},
                {0, 1},
                {1, 0},
                {0, -1},
        };

        int count = 0;

        Queue<int[]> q = new LinkedList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    q.add(new int[]{i, j});
                    grid[i][j] = '0';

                    while (!q.isEmpty()) {
                        int[] pos = q.poll();
                        int row = pos[0];
                        int col = pos[1];

                        for (int[] dir : dirs) {
                            int nr = row + dir[0];
                            int nc = col + dir[1];

                            //bounds check
                            if (nr >= 0 && nc >= 0 && nr < m && nc < n && grid[nr][nc] == '1') {
                                q.add(new int[]{nr, nc});
                                grid[nr][nc] = '0';
                            }
                        }
                    }
                }
            }
        }

        return count;
    }

    //DFS
    public int numIslandsDFS(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] dirs = new int[][]{
                {-1, 0},
                {0, 1},
                {1, 0},
                {0, -1},
        };

        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    dfs(grid, dirs, i, j, m, n);
                }
            }
        }

        return count;
    }

    private void dfs(char[][] grid, int[][] dirs, int row, int col, int m, int n) {
        //base
        if (row < 0 || col < 0 || row == m || col == n || grid[row][col] == '0') return;
        //logic
        grid[row][col] = '0';
        for (int[] dir : dirs) {
            int nr = row + dir[0];
            int nc = col + dir[1];
            dfs(grid, dirs, nr, nc, m, n);
        }
    }

}
