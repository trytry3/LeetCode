/* Given two strings s and t, determine if they are both one edit distance apart.

Note: 
There are 3 possiblities to satisify one edit distance apart:
Insert a character into s to get t
Delete a character from s to get t
Replace a character of s to get t

Example 1:
Input: s = "ab", t = "acb"
Output: true
Explanation: We can insert 'c' into s to get t.

Example 2:
Input: s = "cab", t = "ad"
Output: false
Explanation: We cannot get t from s by only one step.

Example 3:
Input: s = "1203", t = "1213"
Output: true
Explanation: We can replace '0' with '1' to get t.
*/

class Solution {
    public boolean isOneEditDistance(String s, String t) {
        int lenS = s.length();
        int lenT = t.length();
        if (Math.abs(lenS - lenT) > 1) 
            return false;
        if (lenS == lenT)
            return isOneModify(s, t);
        else if (lenS > lenT)
            return isOneDel(s, t);
        else 
            return isOneDel(t, s);
    }
    
    private boolean isOneModify(String s, String t) {
        int diffCount = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != t.charAt(i))
                diffCount++;
        }
        return diffCount == 1;
    }
    
    private boolean isOneDel(String s, String t) {
        // s longer than t
        for (int i = 0; i < t.length(); i++) {
            if (s.charAt(i) != t.charAt(i)) {
                return s.substring(i+1).equals(t.substring(i));
            }
        }    
        return true;
    }
}