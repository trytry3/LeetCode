/** Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

Example:
Input: S = "ADOBECODEBANC", T = "ABC"
Output: "BANC"

Note:
If there is no such window in S that covers all characters in T, return the empty string "".
If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
*/

// two pointers
class Solution {
	public String minWindow(String str, String pat) {
        if (str.length() < pat.length())
            return "";
        int minWindowLen = Integer.MAX_VALUE;
        // store leftover char frequency when matching `pat` in `str`
        Map<Character, Integer> map = new HashMap<>();
        for (char c: pat.toCharArray()) {
            map.put(c, map.getOrDefault(c,0)+1);
        }
        // store unmatched char counts
        int unMatchedCharCount = map.size();
        // two pointers marking the matching interval
        int start = 0, end = 0;
        // store the min matched window starting position 
        int head = 0;
        while (end < str.length()) {
            char e = str.charAt(end);
            if (map.containsKey(e)) {
                map.put(e, map.get(e)-1);
                if (map.get(e) == 0)
                    unMatchedCharCount--;
            }
            end++;
            // after finding all chars of `pat` in `str`, trying to shrink interval
            while (unMatchedCharCount == 0) {
                char s = str.charAt(start);
                if (map.containsKey(s)) {
                    map.put(s, map.get(s)+1);
                    // due to duplicate chars, map can have negative value
                    if (map.get(s) > 0)
                        unMatchedCharCount++;
                } 
                if (end - start < minWindowLen) {
                    minWindowLen = end - start;
                    head = start;
                }
                start++;
            }
        }
        return minWindowLen == Integer.MAX_VALUE ? "" : str.substring(head, head+minWindowLen);
	}
}
