/* Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
For example, given n = 3, a solution set is:
[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]
*/

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<String>();
        helper(res, "", 0, 0, n);
        return res;
    }
    
    private void helper(List<String> res, String prefix, int left, int right, int n) {
        if (prefix.length() == 2*n) {
            res.add(prefix);
            return;
        }
        if (left < n) {
            helper(res, prefix+'(', left+1, right, n);
        }
        if (left > right) {
            helper(res, prefix+')', left, right+1, n);
        }
    }
}