class Solution {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int start = 0, end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (target == nums[mid]) {
                return mid;
            } else if (target > nums[mid]) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (nums[start] == target) {
            return start;
        } 
        if (nums[end] == target) {
            return end;
        }
        return -1;
    }
}