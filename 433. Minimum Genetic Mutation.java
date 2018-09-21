/* A gene string can be represented by an 8-character long string, with choices from "A", "C", "G", "T".
Suppose we need to investigate about a mutation (mutation from "start" to "end"), 
where ONE mutation is defined as ONE single character changed in the gene string.
For example, "AACCGGTT" -> "AACCGGTA" is 1 mutation.
Also, there is a given gene "bank", which records all the valid gene mutations. 
A gene must be in the bank to make it a valid gene string.
Now, given 3 things - start, end, bank, your task is to determine what is the minimum number of 
mutations needed to mutate from "start" to "end". If there is no such a mutation, return -1.

Note:
Starting point is assumed to be valid, so it might not be included in the bank.
If multiple mutations are needed, all mutations during in the sequence must be valid.
You may assume start and end string is not the same.

Example 1:
start: "AACCGGTT"
end:   "AACCGGTA"
bank: ["AACCGGTA"]
return: 1

Example 2:
start: "AACCGGTT"
end:   "AAACGGTA"
bank: ["AACCGGTA", "AACCGCTA", "AAACGGTA"]
return: 2

Example 3:
start: "AAAAACCC"
end:   "AACCCCCC"
bank: ["AAAACCCC", "AAACCCCC", "AACCCCCC"]
return: 3
*/

// bfs, see 127. Word Ladder
class Solution {
    char[] bases = {'A', 'C', 'G', 'T'};
    
    public int minMutation(String start, String end, String[] bank) {
        Set<String> dict = new HashSet<>();
        for (String s: bank)
            dict.add(s);
		Queue<String> queue = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();
		queue.offer(start);
		visited.add(start);
		int len = 0;
		while (!queue.isEmpty()) {
            len++;
            // have to fix size here, since queue size is changing in for loop
            int size = queue.size();
            // one queue is one level, len update for each level
			for (int i = 0; i < size; i++) {
				String gene = queue.poll();
				for (String next : getNext(gene, dict)) {
					if (visited.contains(next)) {
						continue;
					}
					if (next.equals(end)) {
						return len;
					}
					visited.add(next);
					queue.offer(next);
				}
			}
		}
        // no possible transformation
		return -1;
    }
    
    private List<String> getNext(String gene, Set<String> dict) {
		List<String> nextList = new ArrayList<>();
		for (int i = 0; i < gene.length(); i++) {
            for (char c: bases) {
				if (c == gene.charAt(i)) {
					continue;
				}
				String nextWord = replace(gene, i, c);
				if (dict.contains(nextWord)) {
					nextList.add(nextWord);
				}
			}
		}
		return nextList;
	}

	private String replace(String s, int index, char c) {
		char[] chars = s.toCharArray();
		chars[index] = c;
		return new String(chars);
	}
}