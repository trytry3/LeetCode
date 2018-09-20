/* Given a string, find the length of the longest substring without repeating characters.

Examples:
Given "abcabcbb", the answer is "abc", which the length is 3.
Given "bbbbb", the answer is "b", with the length of 1.
Given "pwwkew", the answer is "wke", with the length of 3. 
Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
*/

// two pointers
// method 1: template for LeetCode substring search problem
class Solution {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int start = 0, end = 0, duplicateCharCount = 0, maxLen = 0;
        
        while (end < s.length()) {
            char e = s.charAt(end);
            map.put(e, map.getOrDefault(e, 0) + 1);
            // repeating character
            if (map.get(e) > 1) 
                duplicateCharCount++;
            
            // duplicate chars exist
            // remove duplicates from the window as soon as they appear
            while (duplicateCharCount > 0) {
                char c = s.charAt(start);
                if (map.get(c) > 1) 
                    duplicateCharCount--;
                map.put(c, map.get(c)-1);
                start++;
            }
            maxLen = Math.max(maxLen, end - start + 1);
            end++;
        }
        return maxLen;
    }
    
}

// method 2: two pointers
class Solution {
    public int lengthOfLongestSubstring(String s) {
        // key is char in s, value is char's position
        Map<Character, Integer> map = new HashMap<>();
        int max = 0;
        for (int right = 0, left = 0; right < s.length(); right++){
            // If the character is already in the hashmap, 
            // then move the left pointer to the right of the same character last found
            if (map.containsKey(s.charAt(right))) {
                left = Math.max(left, map.get(s.charAt(right)) + 1);
            }
            map.put(s.charAt(right), right);
            max = Math.max(max, right - left + 1);
        }
        return max;
    }
}