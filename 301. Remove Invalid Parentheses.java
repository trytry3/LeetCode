/* Remove the minimum number of invalid parentheses in order to make the input string valid. 
Return all possible results.
Note: The input string may contain letters other than the parentheses ( and ).

Example 1:
Input: "()())()"
Output: ["()()()", "(())()"]

Example 2:
Input: "(a)())()"
Output: ["(a)()()", "(a())()"]

Example 3:
Input: ")("
Output: [""]
*/

// dfs
class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<String>();
        remove(s, res, 0, 0, 0, new char[] {'(', ')'});
        return res;
    }
    
    // last_i is the removing start position
    // last_j is the last removal position
    private void remove(String s, List<String> res, int count, int last_i, int last_j, char[] par) {
        //count means the current number of par[0] - that of par[1]
        // par[0] == ')' means it's the removal of excessive (, thus reverse result
        if (last_i == s.length() && par[0] == ')') {
            res.add(new StringBuilder(s).reverse().toString());
            return;
        }
        // par[0] == '(' means it's the removal of excessive )
        if (last_i == s.length() && par[0] == '(') {
            // finished left to right and do reverse
            String reverse = new StringBuilder(s).reverse().toString();
            remove(reverse, res, 0, 0, 0, new char[] {')', '('});
            return;
        }
        char c = s.charAt(last_i);
        // cases like '())'
        if (c == par[1] && count == 0) {
            for (int j = last_j; j <= last_i; j++) {
                // remove first ')' in '())'
                if (s.charAt(j) == par[1] && (j == last_j || s.charAt(j - 1) != par[1])) {
                    // s length decreased 1, thus pass 'last_i' instead of 'last_i+1'
                    remove(s.substring(0, j) + s.substring(j + 1), res, 0, last_i, j, par);
                }
            }
        } else {
            if (c == par[0]) count++;
            if (c == par[1]) count--;
            remove(s, res, count, last_i + 1, last_j, par);
        }
    }
}
