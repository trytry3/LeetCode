/** Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real number (floating point number). 
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

// Thinking: Imagine a/b = k as a link between node a and b, the weight from a to b is k, the reverse link is 1/k. Query is to find a path between two nodes.
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