/* Write a function to find the longest common prefix string amongst an array of strings.
If there is no common prefix, return an empty string "".

Example 1:
Input: ["flower","flow","flight"]
Output: "fl"

Example 2:
Input: ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.

Note:
All given inputs are in lowercase letters a-z.
*/

class Solution {
    // another way is to use Trie to store the strs
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0)
            return "";
        int len = strs[0].length();
        StringBuilder curLongest = new StringBuilder();
        for (int i = 0; i < len; i++) {
            Character curChar = strs[0].charAt(i);
            for (String s: strs) {
                if (i >= s.length())
                    return curLongest.toString();
                else {
                    if (s.charAt(i) != curChar)
                        return curLongest.toString();
                }
            }
            curLongest.append(curChar);
        }
        return curLongest.toString();
    }
    
    public String longestCommonPrefix2(String[] strs) {
        if (strs == null || strs.length == 0) 
            return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            // equal to 0 means is a prefix
            while (strs[i].indexOf(prefix) != 0) {
                // shrink prefix
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) 
                    return "";
            }  
        }      
        return prefix;
    }
}
