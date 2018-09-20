/* Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, 
so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.

Example 1:
Input: ["abcd","dcba","lls","s","sssll"]
Output: [[0,1],[1,0],[3,2],[2,4]] 
Explanation: The palindromes are ["dcbaabcd","abcddcba","slls","llssssll"]

Example 2:
Input: ["bat","tab","cat"]
Output: [[0,1],[1,0]] 
Explanation: The palindromes are ["battab","tabbat"]
*/

// O(n*k*k), k is length of word, n is length of word array
class Solution {
    public List<List<Integer>> palindromePairs(String[] words) {
        Set<List<Integer>> res = new HashSet<>();
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) 
            map.put(words[i], i);
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            // divide a word to two halves, 
            // if first half is palindrome, add second half's reverse to the beginning can form palindrome
            // if second half is palindrome, add first half's reverse to the end can form palindrome
            for (int j = 0; j <= word.length(); j++) { // note: j <= word.length()
                String str1 = word.substring(0, j);
                String str2 = word.substring(j); // to include ""
                if (isPalindrome(str1)) {
                    String str2rev = new StringBuilder(str2).reverse().toString();
                    if (map.containsKey(str2rev) && map.get(str2rev) != i) {
                        List<Integer> pair = new ArrayList<>();
                        pair.add(map.get(str2rev));
                        pair.add(i);
                        res.add(pair);
                    }
                }
                if (isPalindrome(str2)) {
                    String str1rev = new StringBuilder(str1).reverse().toString();
                    if (map.containsKey(str1rev) && map.get(str1rev) != i) {
                        List<Integer> pair = new ArrayList<>();
                        pair.add(i);
                        pair.add(map.get(str1rev));
                        res.add(pair);
                    }
                }
            }
        }
        return new ArrayList<>(res);
    }
    
    private boolean isPalindrome(String word) {
        int i = 0, j = word.length() - 1;
        while (i < j) {
            if (word.charAt(i) != word.charAt(j))
                return false;
            i++;
            j--;
        }
        return true;
    }
}