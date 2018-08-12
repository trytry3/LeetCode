/** Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.
[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
*/

public class Solution {
    /**
     * @param triangle: a list of lists of integers
     * @return: An integer, minimum path sum
     */
    public int minimumTotal(int[][] triangle) {
        if (triangle == null || triangle.length == 0)
            return -1;
        int n = triangle.length;
        // store dynamic sum
        int[][] f = new int[n][n];
        // initialize the bottom row
        for (int i = 0; i < n; i++) {
            f[n-1][i] = triangle[n-1][i];
        }
        // if n is 1, it won't enter this for loop
        for (int i = n-2; i >= 0; i--) {
            // kth layer has k+1 elements
            for (int j = 0; j <= i; j++) {
                f[i][j] = triangle[i][j] + Math.min(f[i+1][j], f[i+1][j+1]);
            }
        }
        return f[0][0];
    }
}