/* 给一个有向图，每选中一个点，则可从该点到达的所有点都算作被遍历了。求最少选中多少个点可以遍历全图。
*/

// airbnb
class Solution {

	public List<Integer> getMin(int[][] graph, int n) {
		Map<Integer, Set<Integer>> nodes = new HashMap<>();
		for (int i = 0; i < n; i++) {
			nodes.put(i, new HashSet<>());
		}
		for (int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if (graph[i][j] == 1) {
					nodes.get(i).add(j);
				}
			}
		}
		System.out.println(nodes);
		
		Set<Integer> visited = new HashSet<>();
		Set<Integer> res = new HashSet<>();
		for (int i = 0; i < n; i++) {
			if (!visited.contains(i)) {
				res.add(i);
				visited.add(i);
				search(res, nodes, i, i, visited, new HashSet<>());
			}
		}
		return new ArrayList<>(res);
	}

	private void search(Set<Integer> res, Map<Integer, Set<Integer>> nodes, int cur, int start, 
			Set<Integer> visited, Set<Integer> currVisited) {
		// to deal with loop like [0,3] and [3,0]; cur is 0, next is 3
		currVisited.add(cur);
		visited.add(cur);
		for (int next : nodes.get(cur)) {
			// this node can be reached from other nodes
			if (res.contains(next) && next != start) {
				res.remove(next);
			}
			// when currVisited contains next, it's case like [3,0], skip
			if (!currVisited.contains(next)) {
				search(res, nodes, next, start, visited, currVisited);
			}
		}
	}

	public static void main(String[] args) {
		Solution s = new Solution();
		int[][] graph = { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
						  { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				          { 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 }, 
				          { 0, 0, 0, 1, 0, 1, 0, 1, 0, 0 }, 
				          { 0, 0, 0, 0, 0, 0, 0, 0, 1, 0 },
				          { 0, 0, 0, 0, 0, 0, 0, 0, 1, 0 }, 
				          { 0, 0, 0, 0, 0, 0, 1, 0, 0, 0 }, 
				          { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
				          { 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 },
				          { 0, 0, 0, 1, 0, 0, 1, 0, 0, 0 } };
		
		List<Integer> test = s.getMin(graph, 10);
		System.out.println(test);
	}
}
