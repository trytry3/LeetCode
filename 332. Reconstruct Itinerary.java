/* Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. 
All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.

Note:
If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. 
For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
All airports are represented by three capital letters (IATA code).
You may assume all tickets form at least one valid itinerary.

Example 1:
Input: tickets = [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]

Example 2:
Input: tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
*/

// compare 797. All Paths From Source to Target.java
// dfs
class Solution {
    
    public List<String> findItinerary(String[][] tickets) {
		Map<String, PriorityQueue<String>> flights = new HashMap<>();
		List<String> path = new ArrayList<>();
		for (String[] ticket: tickets) {
			flights.putIfAbsent(ticket[0], new PriorityQueue<>());
			flights.get(ticket[0]).add(ticket[1]);
		}
		dfs("JFK", flights, path);
		return path;
	}

	private void dfs(String departure, Map<String, PriorityQueue<String>> flights, List<String> path) {
		PriorityQueue<String> arrivals = flights.get(departure);
		while (arrivals != null && !arrivals.isEmpty()) {
			dfs(arrivals.poll(), flights, path);
		}
		path.add(0, departure);
	}

}
