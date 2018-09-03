/* Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, 
add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.
Note:
The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.

Example 1:
Input:
s = "catsanddog"
wordDict = ["cat", "cats", "and", "sand", "dog"]
Output:
[
  "cats and dog",
  "cat sand dog"
]

Example 2:
Input:
s = "pineapplepenapple"
wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
Output:
[
  "pine apple pen apple",
  "pineapple pen apple",
  "pine applepen apple"
]
Explanation: Note that you are allowed to reuse a dictionary word.

Example 3:
Input:
s = "catsandog"
wordDict = ["cats", "dog", "sand", "and", "cat"]
Output:
[]
*/

// dfs, memoization
class Solution { 
    // key is the starting index in s, value is ways of word break
    Map<Integer, List<String>> cache = new HashMap<>();
   
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet<>(wordDict);
        return dfs(s, wordDictSet, 0);   
    }
    
    private List<String> dfs(String s, Set<String> wordDictSet, int start) {
        if (cache.containsKey(start))
            return cache.get(start);
        List<String> res = new ArrayList<>();
        if (start == s.length()) {
            res.add("");
            return res;
        }
            
        for (int cut = start + 1; cut <= s.length(); cut++) {
            String prefix = s.substring(start, cut);
            if (wordDictSet.contains(prefix)) {
                List<String> remainderList = dfs(s, wordDictSet, cut);
                for (String remainder: remainderList) {
                    String sep = remainder.equals("") ? "" : " ";
                    res.add(prefix + sep + remainder);
                }
            }   
        }
        cache.put(start, res);
        return res;
    }   
}

// note: better than using dynamic programming for this problem. 
// when using dp, some partial results that won't result in a final word break solution 
// will be stored in the process and may have memory limit exceeded error