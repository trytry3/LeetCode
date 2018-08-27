/* Given an integer matrix, find the length of the longest increasing path.

From each cell, you can either move to four directions: left, right, up or down. 
You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).

Example 1:
Input: nums = 
[
  [9,9,4],
  [6,6,8],
  [2,1,1]
] 
Output: 4 
Explanation: The longest increasing path is [1, 2, 6, 9].

Example 2:
Input: nums = 
[
  [3,4,5],
  [3,2,6],
  [2,2,1]
] 
Output: 4 
Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
*/

// dfs, memoization
class Solution {
    private static final int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    private int rows, cols;

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix.length == 0) 
            return 0;
        rows = matrix.length;
        cols = matrix[0].length;
        int max = 0;
        // cache[i][j] stores the longest path length from i, j 
        int[][] cache = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                dfs(matrix, i, j, cache);
                max = Math.max(max, cache[i][j]);
            }
        }
        return max;
    }

    private void dfs(int[][] matrix, int i, int j, int[][] cache) {
        if (cache[i][j] != 0) 
            return;
        // cache[i][j] is not calculated yet and is 0
        for (int[] d : dirs) {
            int x = i + d[0], y = j + d[1];
            if (0 <= x && x < rows && 0 <= y && y < cols) {
                if (matrix[x][y] > matrix[i][j]) {
                    dfs(matrix, x, y, cache); 
                    cache[i][j] = Math.max(cache[i][j], cache[x][y]);
                } 
                // not increasing from (i, j) to (x, y)
            }      
        }
        // if not increasing from (i, j) to all directions, cache[i][j] is 0 after the for loop
        cache[i][j]++;
    }
}