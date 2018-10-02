/* Implement a basic calculator to evaluate a simple expression string.
The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . 
The integer division should truncate toward zero.

Example 1:
Input: "3+2*2"
Output: 7

Example 2:
Input: " 3/2 "
Output: 1

Example 3:
Input: " 3+5 / 2 "
Output: 5

Note:
You may assume that the given expression is always valid.
Do not use the eval built-in library function.
*/

// stack
class Solution {
	public int calculate(String s) {
        if (s == null)
			return 0;
        // remove spaces
        s = s.trim().replaceAll(" +", "");
		int len = s.length();
		Stack<Integer> stack = new Stack<>();
		int num = 0;
		char sign = '+';
		for (int i = 0; i < len; i++) {
			if (Character.isDigit(s.charAt(i))) {
				num = num * 10 + s.charAt(i) - '0';
			}
            // when it's not a number, add the current number to stack
            // i == len-1 to make last number added to stack
			if (!Character.isDigit(s.charAt(i)) || i == len - 1) {
				if (sign == '-') {
					stack.push(-num);
				}
				if (sign == '+') {
					stack.push(num);
				}
				if (sign == '*') {
					stack.push(stack.pop() * num);
				}
				if (sign == '/') {
					stack.push(stack.pop() / num);
				}
				sign = s.charAt(i);
                // current number finished
				num = 0;
			}
		}

		int res = 0;
		for (int i : stack) {
			res += i;
		}
		return res;
	}
}