/* Implement pow(x, n), which calculates x raised to the power n (xn).

Example 1:
Input: 2.00000, 10
Output: 1024.00000

Example 2:
Input: 2.00000, -2
Output: 0.25000
*/

class Solution {
    public double myPow(double x, int n) {
        if (n == 0)
            return 1;
        if (x == 0)
            return 0;
        if (n < 0) {
            // if n is long, no need to handle this case
            if (n == Integer.MIN_VALUE) {
                n += 2; // so that the sign won't change
            }
            n = -n;
            x = 1/x;
        }
        return n % 2 == 0 ? myPow(x*x, n/2) : x*myPow(x*x, n/2);
    }
}
