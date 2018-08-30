/* A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
The robot can only move either down or right at any point in time. 
The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
Now consider if some obstacles are added to the grids. How many unique paths would there be?
An obstacle and empty space is marked as 1 and 0 respectively in the grid.
Note: m and n will be at most 100.

Example 1:
Input:
[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
Output: 2
Explanation:
There is one obstacle in the middle of the 3x3 grid above.
There are two ways to reach the bottom-right corner:
1. Right -> Right -> Down -> Down
2. Down -> Down -> Right -> Right
*/

// method 1: dynamic programming
class Solution {
    public int uniquePathsWithObstacles(int[][] a) {
        if (a == null || a.length == 0 || a[0].length == 0)
            return 0;
        int rows = a.length, cols = a[0].length;
        int[][] f = new int[rows][cols];
        // start from top left
        f[0][0] = a[0][0] == 0 ? 1 : 0;
        // init first row;
        for (int j = 1; j < cols; j++) {
            if (a[0][j] == 1)
                f[0][j] = 0;
            else 
                f[0][j] = f[0][j-1];
        }
        // init first col
        for (int i = 1; i < rows; i++) {
            if (a[i][0] == 1)
                f[i][0] = 0;
            else 
                f[i][0] = f[i-1][0];
        }
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (a[i][j] == 1)
                    f[i][j] = 0;
                else 
                    f[i][j] = (f[i-1][j] + f[i][j-1]); // from up and left      
            }
        }
        return f[rows-1][cols-1];
    }
}

// method 2: memoization, dfs (actually same as dp)
class Solution {
    private static int[][] cached;    

    static int uniquePathsWithObstacles(int[][] a) {
        if (a == null || a.length == 0)
            return 0;
        cached = new int[a.length][a[0].length];
        for (int[] row: cached) {
            Arrays.fill(row, -1);
        }
        return numOfPathsHelper(a, 0, 0);
    }

    private static int numOfPathsHelper(int[][] a, int row, int col) {
        // visited, return cached value
        if (cached[row][col] != -1)
            return cached[row][col];
        // start from bottom right
        if (row==a.length-1 && col==a[0].length-1) {
            if (a[row][col] != 1) {
                cached[row][col] = 1;
                return 1;
            } else {
                cached[row][col] = 0;
                return 0;
            }     
        }  
        if (a[row][col] == 1) {
            cached[row][col] = 0;
            return 0;
        } else {
            int numRight = 0;
            int numDown = 0;
            if (col+1 < a[0].length && a[row][col+1] != 1) 
                numRight = numOfPathsHelper(a, row, col+1);
            if (row+1 < a.length && a[row+1][col] != 1) 
                numDown = numOfPathsHelper(a, row+1, col);
            cached[row][col] = numRight + numDown;
            return cached[row][col];
        }
    }
}