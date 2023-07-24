/*
Implement a basic calculator to evaluate a simple expression string.

The expression string contains only non-negative integers, '+', '-', '*', '/' operators, and open '(' and closing parentheses ')'. The integer division should truncate toward zero.

You may assume that the given expression is always valid. All intermediate results will be in the range of [-231, 231 - 1].

Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().

Example 1:
Input: s = "1+1"
Output: 2

Example 2:
Input: s = "6-4/2"
Output: 4

Example 3:
Input: s = "2*(5+5*2)/3+(6/2+8)"
Output: 21
 
Constraints:
1 <= s <= 104
s consists of digits, '+', '-', '*', '/', '(', and ')'.
s is a valid expression.
*/

class Solution {
    public int calculate(String s) {
        if (s == null || s.length() == 0) return 0;
        Stack<Integer> nums = new Stack<>(); // store numbers
        Stack<Character> ops = new Stack<>(); // store operators (including parentheses)
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // if (c == ' ') continue;
            if (Character.isDigit(c)) {
                num = c - '0';
                // iteratively calculate each number
                while (i < s.length() - 1 && Character.isDigit(s.charAt(i + 1))) {
                    num = num * 10 + (s.charAt(i + 1) - '0');
                    i++;
                }
                nums.push(num);
            } else if (c == '(') {
                ops.push(c);
            } else if (c == ')') {
                // do the math when we encounter a ')' until the previous '('
                while (ops.peek() != '(')
                    nums.push(operation(ops.pop(), nums.pop(), nums.pop()));
                ops.pop(); // get rid of '(' in the ops stack
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                while (!ops.isEmpty() && precedence(ops.peek(), c))
                    nums.push(operation(ops.pop(), nums.pop(), nums.pop()));
                ops.push(c);
            }
        }
        while (!ops.isEmpty()) {
            nums.push(operation(ops.pop(), nums.pop(), nums.pop()));
        }
        return nums.pop();
    }

    // note: b is the newer one in stack and is passed before a
    private int operation(char op, int b, int a) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                return a / b; // assume b is not 0
        }
        return 0;
    }

    // check if op1 precedes op2
    private boolean precedence(char op1, char op2) {
        if (op1 == '(' || op1 == ')') // not operations
            return false;
        if ((op2 == '*' || op2 == '/') && (op1 == '+' || op1 == '-'))
            return false;
        return true;
    }
}
