/* Given a string s, return all the palindromic permutations (without duplicates) of it. 
Return an empty list if no palindromic permutation could be form.

Example 1:
Input: "aabb"
Output: ["abba", "baab"]

Example 2:
Input: "abc"
Output: []
*/

class Solution {
    public List<String> generatePalindromes(String s) {
        // sort
		char[] chars = stringToSortedArray(s);
		List<String> res = new ArrayList<>();
        // dfs only if s can form palindrome after permutation
        if (canPermutePalindrome(s))
		    dfs(res, new StringBuilder(), chars, new boolean[chars.length]);
		return res;
    }
    
    private void dfs(List<String> res, StringBuilder tempString, char[] chars, boolean[] visited) {
		if (tempString.length() == chars.length) {
            if (isPalindrome(tempString.toString()))
			    res.add(tempString.toString());
			return;
		}
		for (int i = 0; i < chars.length; i++) {
			if (visited[i] == true || (i > 0 && chars[i] == chars[i - 1] && !visited[i - 1]))
				continue;
			visited[i] = true;
			tempString.append(chars[i]);
			dfs(res, tempString, chars, visited);
			// backtrack
			tempString = tempString.deleteCharAt(tempString.length() - 1);
			visited[i] = false;
		}
	}

	private char[] stringToSortedArray(String str) {
		char[] array = str.toCharArray();
		Arrays.sort(array);
		return array;
	}
    
    private boolean isPalindrome(String s) {
        int start = 0, end = s.length()-1;
        while (start < end) {
            if (s.charAt(start) != s.charAt(end))
                return false;
            start++;
            end--;
        }
        return true;
    }
    
    private boolean canPermutePalindrome(String s) {
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