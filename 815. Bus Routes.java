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
        // visited buses
        // why use visited for buses, but not for stops?
        // because we try to visit all buses to see if there is a solution,
        // not all bus stops need to be visited.
        // if use visited for stops, it will end in infinite loop
        Set<Integer> visited = new HashSet<>();  
        // bus stops
        Queue<Integer> queue = new LinkedList<>();
        // key is bus stop, value is a list of buses
        Map<Integer, List<Integer>> map = new HashMap<>();
        
        int count = 0;
        if (S == T)
            return count;
        
        // i is bus index, j is stop index
        for (int i = 0; i < routes.length; i++) {
            for (int j = 0; j < routes[i].length; j++) {
                map.putIfAbsent(routes[i][j], new ArrayList<>());
                List<Integer> buses = map.get(routes[i][j]);
                buses.add(i);       
            }
        }
        
        queue.add(S);
        while (!queue.isEmpty()) {
            // exhaust each level before exploring next level
            int len = queue.size();
            count++;
            for (int i = 0; i < len; i++) {
                Integer curStop = queue.poll();
                List<Integer> buses = map.get(curStop);
                for (int bus: buses) {
                    if (visited.contains(bus))
                        continue;
                    visited.add(bus);
                    int[] nextStops = routes[bus];
                    for (int nextStop: nextStops) {
                        if (nextStop == T)
                            return count;
                        else
                            queue.add(nextStop);
                    }
                }
            }     
        }
        return -1;
    }
}
