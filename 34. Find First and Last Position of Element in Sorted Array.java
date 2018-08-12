class Solution {
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return new int[]{-1, -1};
        return new int[]{findFirst(nums, target), findLast(nums, target)};
    }
    
    private int findFirst(int[] nums, int target) {
        int start = 0, end = nums.length - 1;   
        while (start + 1 < end) {
            int mid = start + (end - start)/2;
            if (nums[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (nums[start] == target)
            return start;
        if (nums[end] == target)
            return end;
        return -1;
    }

    private int findLast(int[] nums, int target) {
        int start = 0, end = nums.length - 1;  
        while (start + 1 < end) {
            int mid = start + (end - start)/2;
            if (nums[mid] <= target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        // note: check end first
        if (nums[end] == target)
            return end;
        if (nums[start] == target)
            return start; 
        return -1;
    }
}