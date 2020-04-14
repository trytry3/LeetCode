/* Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

Example 1:
Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.

Example 2:
Input: "cbbd"
Output: "bb"
*/

// method 1: dynamic programming
class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();
        if (n < 2)
            return s;
        int maxLen = 0, start = 0, end = 0;
        // P[j][i] is true when s[j] equals to s[i] and s(j+1 ... i-1) is a palindromic substring
        boolean[][] P = new boolean[n][n];
    
        for (int i = 0; i < n; i++) {
            // j is left pointer
            // j has to be left pointer because otherwise the previous case was not filled yet
            for (int j = 0; j < i; j++) {
                P[i][i] = true; 
                P[j][i] = (s.charAt(j) == s.charAt(i) && (i - j < 2 || P[j + 1][i - 1])); 
                if (P[j][i] &&  i - j + 1 > maxLen) {
                    maxLen = i - j + 1; 
                    start = j;
                    end = i;
                } 
            }
        }
        return s.substring(start, end+1);
    }
}


// method 2:
class Solution {
    private int lo, maxLen;
    
    public String longestPalindrome(String s) {
	    int len = s.length();
	    if (len < 2)
		    return s;
        for (int i = 0; i < len-1; i++) {
     	    extendPalindrome(s, i, i); //assume odd length, try to extend Palindrome as possible
     	    extendPalindrome(s, i, i+1); //assume even length.
        }
        return s.substring(lo, lo + maxLen);
    }

    private void extendPalindrome(String s, int start, int end) {
	    while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
		    start--;
		    end++;
	    }
	    if ((end-1) - (start+1) + 1 > maxLen) {
		    lo = start + 1;
		    maxLen = end - start - 1;
	    }
    }
}
