/*
Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.

Example:
Input: 
1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0

Output: 4
*/
class Solution {
    public int maximalSquare(char[][] matrix) {
        int rows = matrix.length;
        // dp(i,j) represents the side length of the maximum square
        // whose bottom right corner is the cell with index (i-1,j-1)
        int cols = rows > 0 ? matrix[0].length : 0;
        int[][] dp = new int[rows+1][cols+1];
        int maxSideLen = 0;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                if (matrix[i-1][j-1] == '1') {
                  dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i-1][j-1]), dp[i][j-1]) + 1;
                  maxSideLen = Math.max(maxSideLen, dp[i][j]);
                }
            }
        }
        return maxSideLen * maxSideLen;
    }
}
