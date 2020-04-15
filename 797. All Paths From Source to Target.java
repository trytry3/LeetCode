/* Given a directed, acyclic graph of N nodes.  Find all possible paths from node 0 to node N-1, and return them in any order.
The graph is given as follows:  the nodes are 0, 1, ..., graph.length - 1.  graph[i] is a list of all nodes j for which the edge (i, j) exists.

Example:
Input: [[1,2], [3], [3], []] 
Output: [[0,1,3],[0,2,3]] 
Explanation: The graph looks like this:
0--->1
|    |
v    v
2--->3
There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
*/

// Method 1:
class Solution {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> res = new ArrayList<>();
        if (graph == null || graph.length == 0) 
            return res;
        List<Integer> tempList = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        // note: add node 0 to tempList and visited
        tempList.add(0);
        visited.add(0);
        dfs(res, tempList, graph, 0, visited);
        return res;    
    }
    
    private void dfs(List<List<Integer>> res, List<Integer> tempList, int[][] graph, int curNode, Set<Integer> visited) {
        // tempList reaches target
        if (tempList.get(tempList.size()-1) == graph.length-1) {
            res.add(new ArrayList<>(tempList));
            return;
        }
        for (int next: graph[curNode]) {
            if (visited.contains(next)) 
                continue;
            tempList.add(next);
            visited.add(next);
            dfs(res, tempList, graph, next, visited);
            tempList.remove(tempList.size()-1);
            visited.remove(next);
        } 
    }
}

// Method 2:
class Solution {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        return solve(graph, 0);
    }

    public List<List<Integer>> solve(int[][] graph, int node) {
        int N = graph.length;
        List<List<Integer>> res = new ArrayList();
        if (node == N - 1) {
            List<Integer> path = new ArrayList();
            path.add(N-1);
            res.add(path);
            return res;
        }

        for (int neighbor: graph[node]) {
            for (List<Integer> path: solve(graph, neighbor)) {
                path.add(0, node);
                res.add(path);
            }
        }
        return res;
    }
}
