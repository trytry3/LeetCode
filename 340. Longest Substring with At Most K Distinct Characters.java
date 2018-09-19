/* Given a string, find the length of the longest substring T 
that contains at most k distinct characters.

Example 1:
Input: s = "eceba", k = 2
Output: 3
Explanation: T is "ece" which its length is 3.

Example 2:
Input: s = "aa", k = 1
Output: 2
Explanation: T is "aa" which its length is 2.
*/

// two pointers
class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int left = 0;
        int longest = 0;
        Map<Character, Integer> map = new HashMap<>();
    
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            map.put(c, map.containsKey(c) ? map.get(c) + 1 : 1);
            
            while (map.size() > k && left <= right) {
                char leftChar = s.charAt(left);
                int count = map.get(leftChar);
                if (count - 1 <= 0)
                    map.remove(leftChar);
                else 
                    map.put(leftChar, count-1); 
                left++;
            }
            longest = Math.max(longest, right-left+1); 
        }
        return longest;
    }
}