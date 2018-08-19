/* Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.

Example:
Input: 38
Output: 2 
Explanation: The process is like: 3 + 8 = 11, 1 + 1 = 2. 
             Since 2 has only one digit, return it.
*/

// method 1
class Solution {
    public int addDigits(int num) {
        int sum = addHelper(num);
        while(sum >= 10) {
            sum = addHelper(sum);
        }
        return sum;
    }
    
    private int addHelper(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num = num / 10;
        }
        return sum;
    }
}


// method 2
class Solution {
    // result is periodic: 0 1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9 1 2 3 ...
    public int addDigits(int num) {
        return 1 + (num - 1) % 9;
    }
}