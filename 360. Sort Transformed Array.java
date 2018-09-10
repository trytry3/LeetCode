/* Given a sorted array of integers nums and integer values a, b and c. 
Apply a quadratic function of the form f(x) = ax^2 + bx + c to each element x in the array.
The returned array must be in sorted order.
Expected time complexity: O(n)

Example 1:
Input: nums = [-4,-2,2,4], a = 1, b = 3, c = 5
Output: [3,9,15,33]

Example 2:
Input: nums = [-4,-2,2,4], a = -1, b = 3, c = 5
Output: [-23,-5,1,7]
*/

// two pointers
class Solution {
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        int n = nums.length;
        int[] res = new int[n];
        int start = 0, end = n-1;
        int index = a > 0 ? n-1 : 0;
        while (start <= end) {
            int fstart = f(nums[start], a, b, c);
            int fend = f(nums[end], a, b, c);
            if (a > 0) {
                if (fstart > fend) {
                    res[index] = fstart;
                    start++;
                } else {
                    res[index] = fend;
                    end--;
                }
                index--;
            } else {
                if (fstart > fend) {
                    res[index] = fend;
                    end--;
                } else {
                    res[index] = fstart;
                    start++;
                }
                index++;
            }
        }
        
        return res;    
    }
    
    private int f(int x, int a, int b, int c) {
        return a*x*x + b*x + c;
    }
}