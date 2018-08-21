/* Given a string s, partition s such that every substring of the partition is a palindrome.
Return all possible palindrome partitioning of s.

Example:
Input: "aab"
Output:
[
  ["aa","b"],
  ["a","a","b"]
]
*/


// dfs, backtracking
class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        dfs(res, new ArrayList<>(), s, 0);
        return res;
    }
    
    private void dfs(List<List<String>> res, List<String> tempList, String s, int start) {
        if (start == s.length())
            res.add(new ArrayList<>(tempList));
        for (int i = start; i < s.length(); i++) {
            // substring from start to i
            if (isPalindrome(s, start, i)) {
                tempList.add(s.substring(start, i+1));
                // explore from i+1 to end
                dfs(res, tempList, s, i+1);
                tempList.remove(tempList.size()-1);
            }
        }
    }
    
    private boolean isPalindrome(String str, int start, int end) {
        while (start < end) {
            if (str.charAt(start) != str.charAt(end))
                return false;
            start++;
            end--;
        }
        return true;
    }
}