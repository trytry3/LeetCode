/* Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
(i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
Find the minimum element.
You may assume no duplicate exists in the array.

Example 1:
Input: [3,4,5,1,2] 
Output: 1

Example 2:
Input: [-1,1,2]
Output: -1
*/

// binary search
class Solution {
    public int findMin(int[] nums) {
        int start = 0, end = nums.length - 1;
        while (start + 1 < end) {
            if (nums[start] < nums[end])
                return nums[start];
            int mid = start + (end - start)/2;
            if (nums[mid] > nums[start])
                start = mid + 1;
            else
                end = mid;
        }
        return Math.min(nums[start], nums[end]);
    }
}