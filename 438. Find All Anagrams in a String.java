/* Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.
The order of output does not matter.

Example 1:
Input:
s: "cbaebabacd" p: "abc"
Output:
[0, 6]
Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".
*/

// two pointers
// template for LeetCode substring search problem
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (s.length() < p.length())
            return res;
        // store leftover char frequency when matching `p` in `s`
        Map<Character, Integer> map = new HashMap<>();
        for (char c: p.toCharArray()) {
            map.put(c, map.getOrDefault(c,0)+1);
        }
        // store unmatched char counts
        int unMatchedCharCount = map.size();
        // two pointers marking the matching interval
        int start = 0, end = 0;    
        while (end < s.length()) {
            char e = s.charAt(end);
            if (map.containsKey(e)) {
                map.put(e, map.get(e)-1);
                if (map.get(e) == 0)
                    unMatchedCharCount--;
            }
            end++;
            // trying to shrink interval
            while (unMatchedCharCount == 0) {
                char c = s.charAt(start);
                if (map.containsKey(c)) {
                    map.put(c, map.get(c)+1);
                    // due to duplicate chars, map can have negative value
                    if (map.get(c) > 0)
                        unMatchedCharCount++;
                } 
                if (end - start == p.length()) {
                    res.add(start);
                }
                start++;
            }
        }
        return res;
    }
    
}