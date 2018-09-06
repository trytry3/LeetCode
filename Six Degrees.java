/* Six degrees of separation is the theory that everyone and everything is six or fewer steps away, 
by way of introduction, from any other person in the world, so that a chain of "a friend of a friend" statements 
can be made to connect any two people in a maximum of six steps.
Given a friendship relations, find the degrees of two people, 
return ​-1​ if they can not been connected by friends of friends.
*/

/**
 * Definition for undirected graph. 
 * class UndirectedGraphNode { 
 * 	  int label;
 *    List<UndirectedGraphNode> neighbors;
 * 
 *    UndirectedGraphNode(int x) { 
 *        label = x; 
 *        neighbors = new ArrayList<UndirectedGraphNode>(); 
 *    } 
 *}
 */

// bfs
class Solution {
	public int sixDegrees(List<UndirectedGraphNode> graph, UndirectedGraphNode s, UndirectedGraphNode t) {
		if (s.equals(t))
			return 0;
		Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
		// stores how many steps (minimum since using bfs) needed to get to a node from source
		Map<UndirectedGraphNode, Integer> visited = new HashMap<UndirectedGraphNode, Integer>();
		queue.offer(s);
		visited.put(s, 0);
		while (!queue.isEmpty()) {
			UndirectedGraphNode node = queue.poll();
			int step = visited.get(node);
			for (UndirectedGraphNode neighbor : node.neighbors) {
				if (neighbor.equals(t))
					return step + 1;
				if (visited.containsKey(neighbor))
					continue;
				visited.put(neighbor, step + 1);
				queue.offer(neighbor);
			}
		}
		return -1;
	}
}
