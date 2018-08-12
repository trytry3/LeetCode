/** A matrix is Toeplitz if every diagonal from top-left to bottom-right has the same element.
Now given an M x N matrix, return True if and only if the matrix is Toeplitz.

Example:
Input:
matrix = [
  [1,2,3,4],
  [5,1,2,3],
  [9,5,1,2]
]
Output: True
*/

class Solution {
    public boolean isToeplitzMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0)
            return true;
        for (int i = 0; i < matrix.length-1; i++) {
            for (int j = 0; j < matrix[0].length-1; j++) {
                if (matrix[i][j] != matrix[i+1][j+1])
                    return false;
            }
        }
        return true;
    }
}