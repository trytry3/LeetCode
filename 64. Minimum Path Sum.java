/* Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.
Note: You can only move either down or right at any point in time.

Example:
Input:
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
Output: 7
Explanation: Because the path 1→3→1→1→1 minimizes the sum.
*/

// dynamic programming
class Solution {
    public int minPathSum(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] res = new int[rows][cols];
        res[0][0] = grid[0][0];
        for (int j = 1; j < cols; j++) {
            res[0][j] = grid[0][j] + res[0][j-1];
        }
        for (int i = 1; i < rows; i++) {
            res[i][0] = grid[i][0] + res[i-1][0];
        }
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                res[i][j] = Math.min(res[i-1][j], res[i][j-1]) + grid[i][j];
            }
        }
        return res[rows-1][cols-1];
    }
}