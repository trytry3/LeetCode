/*
Given an encoded string, return its decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; there are no extra white spaces, square brackets are well-formed, etc. Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there will not be input like 3a or 2[4].

The test cases are generated so that the length of the output will never exceed 105.

Example 1:
Input: s = "3[a]2[bc]"
Output: "aaabcbc"

Example 2:
Input: s = "3[a2[c]]"
Output: "accaccacc"

Example 3:
Input: s = "2[abc]3[cd]ef"
Output: "abcabccdcdcdef"
 
Constraints:
1 <= s.length <= 30
s consists of lowercase English letters, digits, and square brackets '[]'.
s is guaranteed to be a valid input.
All the integers in s are in the range [1, 300].
*/


class Solution {
    public String decodeString(String s) {
        Stack<String> stack = new Stack<>();
        int curNum = 0;
        String curNumStr = "0";
        String curStr = "";
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                int curDigit = Character.getNumericValue(c);
                curNum = curNum * 10 + curDigit;
                curNumStr = String.valueOf(curNum);
            } else if (c == '[') {
                stack.push(curStr);
                stack.push(curNumStr);
                curStr = "";
                curNum = 0;
                curNumStr = "0";
            } else if (c == ']') {
                String numStr = stack.pop();
                int num = Integer.parseInt(numStr);
                String prevStr = stack.pop();
                curStr = prevStr + curStr.repeat(num);
            } else {
                curStr += c;
            }
        }
        return curStr;
    }
}
