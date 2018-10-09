/* Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....

Example 1:
Input: nums = [1, 5, 1, 1, 6, 4]
Output: One possible answer is [1, 4, 1, 5, 1, 6].

Example 2:
Input: nums = [1, 3, 2, 2, 3, 1]
Output: One possible answer is [2, 3, 1, 3, 1, 2].

Note:
You may assume all input has valid answer.

Follow Up:
Can you do it in O(n) time and/or in-place with O(1) extra space?
*/

// Index :       0   1   2   3   4   5
// Small half:   M       S       S    
// Large half:       L       L       M
class Solution {
    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        int[] temp = new int[len];
        int mid = len%2 == 0 ? len/2-1 : len/2;
        
        int index = 0;
        for (int i = 0; i <= mid; i++) {
            temp[index] = nums[mid-i];
            if (index+1 < nums.length)
                temp[index+1] = nums[nums.length-i-1];
            index = index+2;
        }
        
        for (int i = 0; i < nums.length; i++) {
		    nums[i] = temp[i];
	    }
    }
}