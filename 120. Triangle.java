/* Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.
For example, given the following triangle
[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
*/

// dynamic programming
class Solution { 
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0)
            return -1;
        int n = triangle.size();
        // store dynamic sum
        int[][] f = new int[n][n];
        // initialize the bottom row
        for (int i = 0; i < n; i++) {
            f[n-1][i] = triangle.get(n-1).get(i);
        }
        // if n is 1, it won't enter this for loop
        for (int i = n-2; i >= 0; i--) {
            // kth layer has k+1 elements
            for (int j = 0; j <= i; j++) {
                f[i][j] = triangle.get(i).get(j) + Math.min(f[i+1][j], f[i+1][j+1]);
            }
        }
        return f[0][0];
    }
}