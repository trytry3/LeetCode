/* Given an unsorted array of integers, find the length of longest increasing subsequence.

Example:
Input: [10,9,2,5,3,7,101,18]
Output: 4 
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4. 

Note:
There may be more than one LIS combination, it is only necessary for you to return the length.
Your algorithm should run in O(n2) complexity.
Follow up: Could you improve it to O(n log n) time complexity?
*/

// dynamic programming O(n^2)
class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int n = nums.length;
        int max = 1;
        // f[i] stores longest subsequence length till nums[i] (nums[i] is picked)
        int[] f = new int[n];
        Arrays.fill(f, 1);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    f[i] = Math.max(f[i], f[j] + 1);
                    max = Math.max(max, f[i]);
                }
            }
        }
        return max;
    }
}
