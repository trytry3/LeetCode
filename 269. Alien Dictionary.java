/* There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. 
You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules 
of this new language. Derive the order of letters in this language.

Example 1:
Input:
[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]
Output: "wertf"

Example 2:
Input:
[
  "z",
  "x"
]
Output: "zx"

Example 3:
Input:
[
  "z",
  "x",
  "z"
] 
Output: "" 
Explanation: The order is invalid, so return "".

Note:
You may assume all letters are in lowercase.
You may assume that if a is a prefix of b, then a must appear before b in the given dictionary.
If the order is invalid, return an empty string.
There may be multiple valid order of letters, return any one of them is fine.
*/

// topological sorting
class Solution {
	public String alienOrder(String[] words) {
		// key is char, value is char's neighbors
		Map<Character, Set<Character>> graph = new HashMap<Character, Set<Character>>();
		// stores indegree of each char
		Map<Character, Integer> indegree = new HashMap<Character, Integer>();
		String result = "";

		if (words == null || words.length == 0)
			return result;

		for (String s : words) {
			for (char c : s.toCharArray()) {
				if (!indegree.containsKey(c)) {
					indegree.put(c, 0);
					graph.put(c, new HashSet<>());
				}
			}
		}

		// build graph
		// note: it follows the dictionary rules
		for (int i = 0; i < words.length - 1; i++) {
			String cur = words[i];
			String next = words[i + 1];
			int curLen = cur.length();
			int nextLen = next.length();
			int l = Math.min(curLen, nextLen);
			for (int j = 0; j < l; j++) {
				char c1 = cur.charAt(j);
				char c2 = next.charAt(j);
				if (c1 != c2) {
					// avoid adding duplicate edge
					if (!graph.get(c1).contains(c2)) {
						graph.get(c1).add(c2);
						indegree.put(c2, indegree.get(c2) + 1);
					}
					// no need to check the rest chars
					break;
				} else if (j == nextLen-1 && j < curLen-1){
					// "abc", "ab" is invalid
					return "";
				}
			}
		}
		
		// store nodes with indegree 0
		Queue<Character> queue = new LinkedList<Character>();
		for (char c : indegree.keySet()) {
			if (indegree.get(c) == 0)
				queue.add(c);
		}

		while (!queue.isEmpty()) {
			char c = queue.poll();
			result += c;
			for (char neighbor : graph.get(c)) {
				indegree.put(neighbor, indegree.get(neighbor) - 1);
				if (indegree.get(neighbor) == 0)
					queue.add(neighbor);
			}
		}

		// if there is circle in the graph (i.e. invalid order), the sizes won't be equal
		if (result.length() != indegree.size())
			return "";
		return result;
	}
}
