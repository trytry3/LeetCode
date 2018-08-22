/* Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

Example:
Input: [-2,1,-3,4,-1,2,1,-5,4],
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.
*/

// method 1
class Solution {
    public int maxSubArray(int[] A) {
        int minPreSum = 0;
        int sum = 0;
        int maxSubSum = Integer.MIN_VALUE;
        for (int i = 0; i < A.length; i++) {
            sum += A[i];
            maxSubSum = Math.max(maxSubSum, sum - minPreSum);
            minPreSum = Math.min(sum, minPreSum);
        }
        return maxSubSum;
    }
}

// method 2: dynamic programming
class Solution {
    public int maxSubArray(int[] A) {
        int n = A.length;
        // dp[i] stores the maximum subarray ending with A[i];
        int[] dp = new int[n];
        dp[0] = A[0];
        int max = dp[0]; 
        for(int i = 1; i < n; i++){
            // note: check dp[i-1] > 0, not A[i-1]
            dp[i] = dp[i-1] > 0 ? (A[i] + dp[i-1]) : A[i];
            max = Math.max(max, dp[i]);
        }  
        return max;
    }
}