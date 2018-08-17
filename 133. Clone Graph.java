/* Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors. */

/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */

// bfs
class Solution {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null)
            return node;
        // find all nodes using bfs
        List<UndirectedGraphNode> allNodes = getAllNodes(node);
        // construct old -> new nodes mapping
        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
        for (UndirectedGraphNode oldNode: allNodes) {
            map.put(oldNode, new UndirectedGraphNode(oldNode.label));
        }
        // connect the new nodes as the old graph
        for (UndirectedGraphNode oldNode: allNodes) {
            UndirectedGraphNode newNode = map.get(oldNode);
            for (UndirectedGraphNode neighborOfOld: oldNode.neighbors) {
                newNode.neighbors.add(map.get(neighborOfOld));
            }
        }
        return map.get(node);
    }
    
    // bfs
    private List<UndirectedGraphNode> getAllNodes(UndirectedGraphNode node) {
        // not necessary to use a queue, just to keep track
        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        Set<UndirectedGraphNode> set = new HashSet<>();
        queue.offer(node);
        set.add(node);
        while (!queue.isEmpty()) {
            UndirectedGraphNode head = queue.poll();
            for (UndirectedGraphNode neighbor: head.neighbors) {
                if (!set.contains(neighbor)) {
                    queue.offer(neighbor);
                    set.add(neighbor);
                }
            }
        }
        return new ArrayList<UndirectedGraphNode>(set);
    }
}