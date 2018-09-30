/* Given two strings s and t , write a function to determine if t is an anagram of s.

Example 1:
Input: s = "anagram", t = "nagaram"
Output: true

Example 2:
Input: s = "rat", t = "car"
Output: false

Note:
You may assume the string contains only lowercase alphabets.
*/

class Solution {
    public boolean isAnagram(String s, String t) {
        return order(s).equals(order(t));
    }
    
    private String order(String str) {
        char[] array = str.toCharArray();
        Arrays.sort(array);
        return new String(array);
    }
}