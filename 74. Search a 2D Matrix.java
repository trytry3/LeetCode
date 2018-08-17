/* Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.

Example:
Input:
matrix = [
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
target = 3
Output: true
*/

// binary search
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0)
            return false;
        int rows = matrix.length;
        int cols = matrix[0].length;
        int start = 0, end = rows*cols - 1;
        while (start + 1 < end) {
            int mid = start + (end - start)/2;
            int row = mid / cols;
            int col = mid % cols;
            if (matrix[row][col] > target) {
                end = mid;
            } else if (matrix[row][col] == target) {
                return true;
            } else {
                start = mid;
            }
        }
        if (matrix[start/cols][start%cols] == target) {
            return true;
        }
        if (matrix[end/cols][end%cols] == target) {
            return true;
        }
        return false;       
    }
}