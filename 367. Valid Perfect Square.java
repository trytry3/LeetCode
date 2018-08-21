/* Given a positive integer num, write a function which returns True if num is a perfect square else False.
Note: Do not use any built-in library function such as sqrt.

Example 1:
Input: 16
Returns: True

Example 2:
Input: 14
Returns: False
*/

// binary search
class Solution {
    public static boolean isPerfectSquare(int num) {
		long start = 0, end = num;
		while (start + 1 < end) {
			long mid = start + (end - start) / 2;
            if (mid * mid == num)
                return true;
			else if (mid * mid < num)
				start = mid;
			else
				end = mid;
		}
		return end * end == num;
	}
}