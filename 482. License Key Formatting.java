/** You are given a license key represented as a string S which consists only alphanumeric character and dashes. 
The string is separated into N+1 groups by N dashes.
Given a number K, we would want to reformat the strings such that each group contains exactly K characters, 
except for the first group which could be shorter than K, but still must contain at least one character. 
Furthermore, there must be a dash inserted between two groups and all lowercase letters should be converted to uppercase.
Given a non-empty string S and a number K, format the string according to the rules described above.

Example 1:
Input: S = "5F3Z-2e-9-w", K = 4
Output: "5F3Z-2E9W"

Example 2:
Input: S = "2-5g-3-J", K = 2
Output: "2-5G-3J"
*/

class Solution {
    public String licenseKeyFormatting(String s, int k) {
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--)
            if (s.charAt(i) != '-')
                // k+1 is to include "-", e.g. "5G-3J" % "-3J" = "5G"
                sb.append(sb.length() % (k + 1) == k ? '-' : "").append(s.charAt(i));
        return sb.reverse().toString().toUpperCase();
    } 
}