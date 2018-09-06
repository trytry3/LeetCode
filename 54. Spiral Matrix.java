/* Given a matrix of m x n elements (m rows, n columns), 
return all elements of the matrix in spiral order.

Example 1:
Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
Output: [1,2,3,6,9,8,7,4,5]

Example 2:
Input:
[
  [1, 2, 3, 4],
  [5, 6, 7, 8],
  [9,10,11,12]
]
Output: [1,2,3,4,8,12,11,10,9,5,6,7]
*/

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix.length == 0) 
            return res;
        int rows = matrix.length, cols = matrix[0].length;
        boolean[][] seen = new boolean[rows][cols];
        // (dirsR[i], dirsC[i]) represents a direction, e.g. (1, 0) is one row down
        int[] dirsR = {0, 1, 0, -1};
        int[] dirsC = {1, 0, -1, 0};
        int row = 0, col = 0, d = 0;
        for (int i = 0; i < rows * cols; i++) {
            res.add(matrix[row][col]);
            seen[row][col] = true;
            int nextRow = row + dirsR[d];
            int nextCol = col + dirsC[d];
            if (nextRow >=0 && nextRow < rows && nextCol >=0 && nextCol < cols && !seen[nextRow][nextCol]){
                row = nextRow;
                col = nextCol;
            } else {
                // hit boundary, and change direction clockwise
                d = (d + 1) % 4;
                row += dirsR[d];
                col += dirsC[d];
            }
        }
        return res;
    }
}