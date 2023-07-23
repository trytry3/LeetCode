/*
Given a 0-indexed integer array nums of size n containing all numbers from 1 to n, return the number of increasing quadruplets.

A quadruplet (i, j, k, l) is increasing if:

0 <= i < j < k < l < n, and
nums[i] < nums[k] < nums[j] < nums[l].
 
Example 1:
Input: nums = [1,3,2,4,5]
Output: 2
Explanation: 
- When i = 0, j = 1, k = 2, and l = 3, nums[i] < nums[k] < nums[j] < nums[l].
- When i = 0, j = 1, k = 2, and l = 4, nums[i] < nums[k] < nums[j] < nums[l]. 
There are no other quadruplets, so we return 2.

Example 2:
Input: nums = [1,2,3,4]
Output: 0
Explanation: There exists only one quadruplet with i = 0, j = 1, k = 2, l = 3, but since nums[j] < nums[k], we return 0.
 
Constraints:
4 <= nums.length <= 4000
1 <= nums[i] <= nums.length
All the integers of nums are unique. nums is a permutation.
*/

// dynamic programming
class Solution {
    public long countQuadruplets(int[] nums) {
        int n = nums.length;
        // store the count of all valid 132 triplets
        long[] tripletCount = new long[n];
        long ans = 0;
        for (int j = 0; j < n; j++) {
            // amount of numbers that are smaller than the current number
            int prevSmall = 0;
            for (int i = 0; i < j; i++) {
                if (nums[j] > nums[i]) {
                    prevSmall++;
                    ans += tripletCount[i];
                } else if (nums[j] < nums[i]) {
                    // eg: 12342, nums[i] is 4, nums[j] is 2, prevSmall is 3, then there are 3 triplets
                    tripletCount[i] += prevSmall;
                }
            }
        }
        return ans;
    }
}
