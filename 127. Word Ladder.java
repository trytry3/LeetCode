/* Given two words (beginWord and endWord), and a dictionary's word list, 
find the length of shortest transformation sequence from beginWord to endWord, such that:
Only one letter can be changed at a time.
Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
Note:
Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.

Example 1:
Input:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]
Output: 5
Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.

Example 2:
Input:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]
Output: 0
Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
*/

// bfs
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
		Set<String> dict = new HashSet<>(wordList);
		Queue<String> queue = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();
		queue.offer(beginWord);
		visited.add(beginWord);
		int len = 1;
		while (!queue.isEmpty()) {
            len++;
            // have to fix size here, since queue size is changing in for loop
            int size = queue.size();
            // one queue is one level, len update for each level
			for (int i = 0; i < size; i++) {
				String word = queue.poll();
				for (String nextWord : getNextWords(word, dict)) {
					if (visited.contains(nextWord)) {
						continue;
					}
					if (nextWord.equals(endWord)) {
						return len;
					}
					visited.add(nextWord);
					queue.offer(nextWord);
				}
			}
		}
        // no possible transformation
		return 0;
	}

	private List<String> getNextWords(String word, Set<String> dict) {
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
		return new String(chars);
	}
}