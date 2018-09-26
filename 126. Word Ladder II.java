/* Given two words (beginWord and endWord), and a dictionary's word list, 
find all shortest transformation sequence(s) from beginWord to endWord, such that:
Only one letter can be changed at a time
Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
Note:
Return an empty list if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.

Example 1:
Input:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]
Output:
[
  ["hit","hot","dot","dog","cog"],
  ["hit","hot","lot","log","cog"]
]

Example 2:
Input:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]
Output: []
Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
*/

// bfs, dfs
class Solution {
    public List<List<String>> findLadders(String start, String end, List<String> wordList) {
		Set<String> dict = new HashSet<>(wordList);
		List<List<String>> res = new ArrayList<>();
		// neighbors for every node
		Map<String, List<String>> nodeNeighbors = new HashMap<>();
		// shortest distance of every node from the start node, also serve the purpose of checking visited
		Map<String, Integer> distances = new HashMap<String, Integer>();

		bfs(start, end, dict, nodeNeighbors, distances);
		dfs(start, end, nodeNeighbors, distances, new ArrayList<String>(), res);
		return res;
	}

	// record every node's distance from the start node
	private void bfs(String start, String end, Set<String> dict, 
                     Map<String, List<String>> nodeNeighbors, Map<String, Integer> distances) {
		for (String str : dict)
			nodeNeighbors.put(str, new ArrayList<String>());
        nodeNeighbors.put(start, new ArrayList<String>());

		Queue<String> queue = new LinkedList<String>();
		queue.offer(start);
		distances.put(start, 0);

		while (!queue.isEmpty()) {
			int size = queue.size();
			boolean foundEnd = false;
			for (int i = 0; i < size; i++) {
				String cur = queue.poll();
				int curDistance = distances.get(cur);
				List<String> neighbors = getNeighbors(cur, dict);

				for (String neighbor : neighbors) {
					nodeNeighbors.get(cur).add(neighbor);
					// since it's bfs, `distances` is recording the shortest distance
					if (!distances.containsKey(neighbor)) {
						distances.put(neighbor, curDistance + 1);
                    	// found the shortest path, other neighbors must have longer paths, so ignore
						if (end.equals(neighbor)) 
							foundEnd = true;
						else
							queue.offer(neighbor);
					}
				}
			}

			if (foundEnd)
				break;
		}
	}
    
	// output all paths with the shortest distance
	private void dfs(String cur, String end, Map<String, List<String>> nodeNeighbors, 
                     Map<String, Integer> distances, List<String> path, List<List<String>> res) {
		path.add(cur);
		if (end.equals(cur)) {
			res.add(new ArrayList<String>(path));
		} else {
			for (String next : nodeNeighbors.get(cur)) {
				if (distances.get(next) == distances.get(cur) + 1) {
					dfs(next, end, nodeNeighbors, distances, path, res);
				}
			}
		}
        path.remove(path.size() - 1);
    }

	private List<String> getNeighbors(String word, Set<String> dict) {
		List<String> nextWords = new ArrayList<>();
		for (int i = 0; i < word.length(); i++) {
            for (char c = 'a'; c <= 'z'; c++) {
				if (c == word.charAt(i)) {
					continue;
				}
				String nextWord = replace(word, i, c);
				if (dict.contains(nextWord)) {
					nextWords.add(nextWord);
				}
			}
		}
		return nextWords;
	}
    
    private String replace(String s, int index, char c) {
		char[] chars = s.toCharArray();
		chars[index] = c;
		return String.valueOf(chars);
	}
    
}