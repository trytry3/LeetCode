/*
Given an directed graph, a topological order of the graph nodes is defined as follow:
- For each directed edge A -> B in graph, A must before B in the order list.
- The first node in the order can be any node in the graph with no nodes direct to it.
Find any topological order for the given graph.
You can assume that there is at least one topological order in the graph.
*/
/**
 * Definition for Directed graph.
 * class DirectedGraphNode {
 *     int label;
 *     ArrayList<DirectedGraphNode> neighbors;
 *     DirectedGraphNode(int x) { label = x; neighbors = new ArrayList<DirectedGraphNode>(); }
 * };
 */
public class Solution {
    /**
     * @param graph: A list of Directed graph node
     * @return: Any topological order for the given graph.
     */    
    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        ArrayList<DirectedGraphNode> result = new ArrayList<DirectedGraphNode>();
        HashMap<DirectedGraphNode, Integer> map = new HashMap();
        // store each node's in-degree in a map
        for (DirectedGraphNode node : graph) {
            for (DirectedGraphNode neighbor : node.neighbors) {
                if (map.containsKey(neighbor)) {
                    map.put(neighbor, map.get(neighbor) + 1);
                } else {
                    map.put(neighbor, 1); 
                }
            }
        }
        // add nodes that with 0 in-degree to a queue
        Queue<DirectedGraphNode> q = new LinkedList<DirectedGraphNode>();
        for (DirectedGraphNode node : graph) {
            if (!map.containsKey(node)) {
                q.add(node);
                result.add(node);
            }
        }
        // poll a node in queue, reduce all its neighbor's in-degree by 1,
        // add all neighbors whose in-degree is 0 to the queue
        while (!q.isEmpty()) {
            DirectedGraphNode node = q.poll();
            for (DirectedGraphNode n : node.neighbors) {
                map.put(n, map.get(n) - 1);
                if (map.get(n) == 0) {
                    q.add(n);
                    result.add(n);
                }
            }
        }
        return result;
    }
}
