/** Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

Example 1:
Input: n = 12
Output: 3 
Explanation: 12 = 4 + 4 + 4.

Example 2:
Input: n = 13
Output: 2
Explanation: 13 = 4 + 9.
*/

// dynamic programming
class Solution {
    public int numSquares(int n) {
        // f[i] stores least perfect square numbers that sum to i
        int f[] = new int[n+1]; 
        Arrays.fill(f, Integer.MAX_VALUE); 
        // 0 not included
        for (int i = 1; i*i <= n; i++) {
            f[i*i] = 1; 
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; i+j*j <= n; j++) {
                // i+j*j may be a perfect square, then f[i+j*j] is 1 instead of f[i]+1
                f[i+j*j] = Math.min(f[i]+1, f[i+j*j]); 
            }
        }
        return f[n]; 
    }
}