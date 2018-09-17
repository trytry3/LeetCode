/* Given a string, determine if a permutation of the string could form a palindrome.

Example 1:
Input: "code"
Output: false

Example 2:
Input: "aab"
Output: true

Example 3:
Input: "carerac"
Output: true
*/

class Solution {
    public boolean canPermutePalindrome(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c: s.toCharArray()) {
            map.put(c, map.containsKey(c) ? map.get(c)+1 : 1);
        }
        int count = 0;
        for (char c: map.keySet()) {
            count += map.get(c) % 2;
        }
        if (count > 1)
            return false;
        return true;
    }
}