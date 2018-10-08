/* 每个人都有一个preference的排序，在不违反每个人的preference的情况下得到总体的preference的排序

example:
input: [[3, 5, 7, 9], [2, 3, 8], [5, 8]]
output: [2, 3, 5, 8, 7, 9]
*/

// airbnb
// topological sorting
class Solution {

	public List<Integer> getPreference(List<List<Integer>> preferences) {
		Map<Integer, Integer> inDegree = new HashMap<>();
		Map<Integer, Set<Integer>> nodes = new HashMap<>();
		for (List<Integer> list : preferences) {
			for (int i = 0; i < list.size() - 1; i++) {
				int from = list.get(i);
				int to = list.get(i + 1);
				if (!nodes.containsKey(from)) {
					inDegree.put(from, 0);
					nodes.put(from, new HashSet<>());
				}
				if (!nodes.containsKey(to)) {
					inDegree.put(to, 0);
					nodes.put(to, new HashSet<>());
				}
				if (!nodes.get(from).contains(to)) {
					nodes.get(from).add(to);
					inDegree.put(to, inDegree.getOrDefault(to, 0) + 1);
				}
			}
		}

		Queue<Integer> queue = new LinkedList<>();
		for (int i : inDegree.keySet()) {
			if (inDegree.get(i) == 0) {
				queue.offer(i);
			}
		}
		List<Integer> res = new ArrayList<>();
		while (!queue.isEmpty()) {
			int i = queue.poll();
			res.add(i);
			Set<Integer> neighbors = nodes.get(i);
			for (int next : neighbors) {
				int degree = inDegree.get(next) - 1;
				inDegree.put(next, degree);
				if (degree == 0)
					queue.offer(next);
			}
		}
		return res;
	}

	public static void main(String[] args) {
		Solution s = new Solution();
		int[][] input = { { 3, 5, 7, 9 }, { 2, 3, 8 }, { 5, 8 } };
		List<List<Integer>> preferences = new ArrayList<>();
		for (int i = 0; i < input.length; i++) {
			int[] oneList = input[i];
			preferences.add(new ArrayList<>());
			for (int j : oneList) {
				preferences.get(i).add(j);
			}
		}
		List<Integer> test = s.getPreference(preferences);
		for (int i : test) {
			System.out.print(i + " ");
		}
	}
}