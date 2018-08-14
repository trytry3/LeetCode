/** Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

Example 1:
Input: haystack = "hello", needle = "ll"
Output: 2

Example 2:
Input: haystack = "aaaaa", needle = "bba"
Output: -1
*/

class Solution {
    public int strStr(String haystack, String needle) {
        int hayLen = haystack.length();
        int needleLen = needle.length();
        if (hayLen < needleLen)
            return -1;
        for (int i = 0; i <= hayLen - needleLen; i++) {
            if (haystack.substring(i, i+needleLen).equals(needle))
                return i;
        }
        return -1;
    }
}