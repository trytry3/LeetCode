/* Given an unsorted integer array, find the smallest missing positive integer.

Example 1:
Input: [1,2,0]
Output: 3

Example 2:
Input: [3,4,-1,1]
Output: 2

Example 3:
Input: [7,8,9,11,12]
Output: 1

Note:
Your algorithm should run in O(n) time and uses constant extra space.
*/

class Solution {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        // put each number in its right place, like [1,2,3,4,5...]
        // e.g. when we find 5, and it's not at position 4, swap it with nums[4].
        for (int i = 0; i < n; i++) {
            while (nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1] != nums[i])
                swap(nums, i, nums[i]-1);
        }
        // the first place where its number is not right
        for (int i = 0; i < n; i++)
            if (nums[i] != i + 1)
                return i + 1;   
        return n+1;
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}