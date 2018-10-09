/* We have a list of bus routes. Each routes[i] is a bus route that the i-th bus repeats forever. 
For example if routes[0] = [1, 5, 7], this means that the first bus (0-th indexed) travels 
in the sequence 1->5->7->1->5->7->1->... forever.

We start at bus stop S (initially not on a bus), and we want to go to bus stop T. 
Travelling by buses only, what is the least number of buses we must take to reach our destination? 
Return -1 if it is not possible.

Example:
Input: 
routes = [[1, 2, 7], [3, 6, 7]]
S = 1
T = 6
Output: 2
Explanation: 
The best strategy is take the first bus to the bus stop 7, then take the second bus to the bus stop 6.
*/

// bfs
class Solution {
	public int numBusesToDestination(int[][] routes, int S, int T) {
		Set<Integer> visited = new HashSet<>();
		Queue<Integer> queue = new LinkedList<>();
		// key is bus stop, value is bus
		Map<Integer, List<Integer>> map = new HashMap<>();

		int count = 0;
		if (S == T)
			return 0;

		// i is buses, j is bus tops
		for (int i = 0; i < routes.length; i++) {
			for (int j = 0; j < routes[i].length; j++) {
				List<Integer> buses = map.getOrDefault(routes[i][j], new ArrayList<>());
				buses.add(i);
				map.put(routes[i][j], buses);
			}
		}

		queue.offer(S);
		while (!queue.isEmpty()) {
			int len = queue.size();
			count++;
			for (int i = 0; i < len; i++) {
				int cur = queue.poll();
				List<Integer> buses = map.get(cur);
                // find next reachable bus stops
				for (int bus : buses) {
					if (visited.contains(bus))
						continue;
					visited.add(bus);
					for (int j = 0; j < routes[bus].length; j++) {
						if (routes[bus][j] == T)
							return count;
						queue.offer(routes[bus][j]);
					}
				}
			}
		}
		return -1;
	}
}