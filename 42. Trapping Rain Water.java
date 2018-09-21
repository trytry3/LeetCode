/* Given n non-negative integers representing an elevation map where the width of each bar is 1, 
compute how much water it is able to trap after raining.

Example:
Input: [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
*/

// method 1:
class Solution {
    public int trap(int[] height) {
        int n = height.length;
        int sum = 0;
        if (n == 0)
            return sum;
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];
        leftMax[0] = height[0];
        rightMax[n-1] = height[n-1];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i-1], height[i]); 
        }
        for (int i = n-2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i+1], height[i]);
        }
        for (int i = 0; i < n; i++) {
            sum += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return sum;
    }
}

// method 2: two pointers
class Solution {
    public int trap(int[] height) {
        int left = 0, right = height.length-1;
        int trapped = 0;
        int leftMax = 0, rightMax = 0;
        while (left < right) {
            leftMax = Math.max(height[left], leftMax);
            rightMax = Math.max(height[right], rightMax);
            if (leftMax < rightMax) {
                trapped += leftMax - height[left];
                left++;
            } else {
                trapped += rightMax - height[right];
                right--;
            }
        }
        return trapped;
    }
}