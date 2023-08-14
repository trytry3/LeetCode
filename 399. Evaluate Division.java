/* Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real number (floating point number). 
Given some queries, return the answers. If the answer does not exist, return -1.0.

Example:
Given a / b = 2.0, b / c = 3.0. 
queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? . 
return [6.0, 0.5, -1.0, 1.0, -1.0 ].

According to the example above:
equations = [ ["a", "b"], ["b", "c"] ],
values = [2.0, 3.0],
queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ]. 
The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction.
*/

// thinking: Imagine a/b = k as a link between node a and b, the weight from a to b is k, the reverse link is 1/k. Query is to find a path between two nodes.
class Solution {
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        // e.g. {a: {b: 2.0}}
        Map<String, Map<String, Double>> eqValGraph = new HashMap<>();
        for (int i = 0; i < equations.length; i++) {
            eqValGraph.putIfAbsent(equations[i][0], new HashMap<>());
            eqValGraph.putIfAbsent(equations[i][1], new HashMap<>());
            eqValGraph.get(equations[i][0]).put(equations[i][1], values[i]);
            eqValGraph.get(equations[i][1]).put(equations[i][0], 1 / values[i]);
        }
        
        double[] res = new double[queries.length];
        for (int i = 0; i < queries.length; i++) {
            String[] query = queries[i];
            res[i] = dfs(query[0], query[1], eqValGraph, new HashSet<String>(), 1.0);
        }
        return res;
    }
    
    private double dfs(String numerator, String denominator, Map<String, Map<String, Double>> eqValGraph, 
                       Set<String> seen, double curRes) {
        if (seen.contains(numerator))
            // if a->b->c is the path, avoid a->b->a->b->c. any a->b->a-> is the same as a->
            return -1.0;
        if (!eqValGraph.containsKey(numerator)) 
            return -1.0;
        if (numerator.equals(denominator)) 
            return curRes;
        // keep track of path
        seen.add(numerator);
        
        Map<String, Double> next = eqValGraph.get(numerator);
        for (String nextNumerator: next.keySet()) {
            // denominator not change, since it's the destination
            double nextRes = dfs(nextNumerator, denominator, eqValGraph, seen, curRes*next.get(nextNumerator));
            if (nextRes != -1.0) {
                // found a path that can get the result of query
                return nextRes;
            }
        }
        // not found
        return -1.0;
    } 
}


/*
You are given an array of variable pairs equations and an array of real numbers values, where equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i]. Each Ai or Bi is a string that represents a single variable.

You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where you must find the answer for Cj / Dj = ?.

Return the answers to all queries. If a single answer cannot be determined, return -1.0.

Note: The input is always valid. You may assume that evaluating the queries will not result in division by zero and that there is no contradiction.

Note: The variables that do not occur in the list of equations are undefined, so the answer cannot be determined for them.

Example 1:
Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
Explanation: 
Given: a / b = 2.0, b / c = 3.0
queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? 
return: [6.0, 0.5, -1.0, 1.0, -1.0 ]
note: x is undefined => -1.0

Example 2:
Input: equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
Output: [3.75000,0.40000,5.00000,0.20000]

Example 3:
Input: equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
Output: [0.50000,2.00000,-1.00000,-1.00000]

Constraints:
1 <= equations.length <= 20
equations[i].length == 2
1 <= Ai.length, Bi.length <= 5
values.length == equations.length
0.0 < values[i] <= 20.0
1 <= queries.length <= 20
queries[i].length == 2
1 <= Cj.length, Dj.length <= 5
Ai, Bi, Cj, Dj consist of lower case English letters and digits.
*/
class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> graph = new HashMap<>();
        for (int i = 0; i < equations.size(); i++) {
            String numerator = equations.get(i).get(0);
            String denominator = equations.get(i).get(1);
            graph.putIfAbsent(numerator, new HashMap<>());
            graph.putIfAbsent(denominator, new HashMap<>());
            graph.get(numerator).put(denominator, values[i]);
            graph.get(denominator).put(numerator, 1.0 / values[i]);
        }
        double[] res = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            List<String> query = queries.get(i);
            res[i] = dfs(query.get(0), query.get(1), graph, new HashSet<String>(), 1.0);
        }
        return res;
    }

    private double dfs(String numerator, String denominator, Map<String, Map<String, Double>> graph, Set<String> seen, double curRes) {
        if (seen.contains(numerator))
            return -1.0;
        if (!graph.containsKey(numerator))
            return -1.0;
        if (numerator.equals(denominator))
            return curRes;
        seen.add(numerator);

        Map<String, Double> next = graph.get(numerator);
        for (String nextNumerator : next.keySet()) {
            double nextRes = dfs(nextNumerator, denominator, graph, seen, curRes * next.get(nextNumerator));
            if (nextRes != -1.0)
                return nextRes;
        }
        return -1.0;
    }
}
