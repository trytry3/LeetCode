/* There are a total of n courses you have to take, labeled from 0 to n-1.
Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

Example 1:
Input: 2, [[1,0]] 
Output: true
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0. So it is possible.
             
Example 2:
Input: 2, [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0, and to take course 0 you should
             also have finished course 1. So it is impossible.

Example 3:
Input: 1, [] 
Output: true
*/

// topological sorting
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int count = 0;
        // graph
        Map<Integer, List<Integer>> graph = new HashMap<>();
        // indegree map
        Map<Integer, Integer> indegrees = new HashMap<>();

        // build directed graph
        for (int[] prerequisite : prerequisites) {
            int course = prerequisite[0];
            int prereq = prerequisite[1];
            graph.putIfAbsent(prereq, new ArrayList<>());
            graph.get(prereq).add(course);
            // make sure the nodes with no neighbors are also included in the graph
            graph.putIfAbsent(course, new ArrayList<>());
        }

        // build indegree map
        for (Integer course : graph.keySet()) {
            // add the course itself
            indegrees.putIfAbsent(course, 0);
            // increment the course's neighbors' indegree by 1
            List<Integer> neighborCourses = graph.get(course);
            for (Integer neighbor : neighborCourses) {
                indegrees.putIfAbsent(neighbor, 0);
                indegrees.put(neighbor, indegrees.get(neighbor) + 1);
            }
        }

        // add nodes with 0 indegree to a queue
        Queue<Integer> queue = new LinkedList<>();
        for (Integer course : indegrees.keySet()) {
            if (indegrees.get(course) == 0) {
                queue.add(course);
                count++;
            }
        }

        while (!queue.isEmpty()) {
            int course = queue.poll();
            for (Integer neighbor : graph.get(course)) {
                int newIndegree = indegrees.get(neighbor) - 1;
                if (newIndegree == 0) {
                    queue.add(neighbor);
                    count++;
                }
                indegrees.put(neighbor, newIndegree);
            }
        }

        // not check count == numCourses, because numCourses can be random
        // as long as the graph does not contain cycles, it is valid
        return count == graph.keySet().size();
    }
}


class Solution {
    // numCourses is actually not useful
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer> sortedCourses = new ArrayList<>();
        
        // store the prerequisites as a directed graph
        // course -> list of next courses
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] prerequisite: prerequisites) {
            int course = prerequisite[0];
            int req = prerequisite[1];
            graph.putIfAbsent(req, new ArrayList<>()); 
            graph.get(req).add(course);
            // make sure the nodes with no neighbors are also included in the graph
            graph.putIfAbsent(course, new ArrayList<>()); 
        }
        
        // store indegrees in a map
        // course -> indegree
        Map<Integer, Integer> indegrees = new HashMap<>();
        for (Integer course: graph.keySet()) {
            // increase neighbors' indegree
            List<Integer> neighbors = graph.get(course);
            for (Integer neighbor: neighbors) {
                indegrees.putIfAbsent(neighbor, 0);
                indegrees.put(neighbor, indegrees.get(neighbor) + 1); 
            }
        }
        
        // add nodes originally with 0 indegree to a queue
        Queue<Integer> queue = new LinkedList<Integer>();
        for (Integer course: graph.keySet()) {
            if (!indegrees.containsKey(course)) {
                queue.add(course);
                sortedCourses.add(course);
            }
        }
        
        // poll a node in queue, reduce all its neighbor's indegree by 1,
        // add all neighbors whose indegree is 0 to the queue
        while (!queue.isEmpty()) {
            Integer course = queue.poll();
            for (Integer neighbor: graph.get(course)) {
                indegrees.put(neighbor, indegrees.get(neighbor) - 1);
                if (indegrees.get(neighbor) == 0) {
                    queue.add(neighbor);
                    sortedCourses.add(neighbor);
                }
            }
        }
        
        return sortedCourses.size() == graph.keySet().size();
    }
}

class Solution2 {
  public boolean canFinish(int numCourses, int[][] prerequisites) {
      int count = 0;
      Queue<Integer> queue = new LinkedList<Integer>();
      List<List<Integer>> neighborList = new ArrayList<>();
      int[] indegree = new int[numCourses];
      // get indegrees of all nodes (i.e. courses)
      for (int i = 0;  i < prerequisites.length; i++) {
          indegree[prerequisites[i][0]]++;
      }
      // add indegree 0 nodes to queue first
      for (int i = 0; i < numCourses; i++) {
          if (indegree[i] == 0) {
              queue.offer(i);
              count++;
          }
      }
      // get each node's neighborList
      for (int i = 0; i < numCourses; i++) {
          neighborList.add(new ArrayList<>());
      }
      for (int i = 0; i < prerequisites.length; i++) {
          neighborList.get(prerequisites[i][1]).add(prerequisites[i][0]);
      }
      
      // remove neighbor indegree by 1 when checking nodes-in-queue's neighbors
      while (!queue.isEmpty()) {
          int cur = queue.poll();
          for (int element : neighborList.get(cur)) {
              indegree[element]--;
              if (indegree[element] == 0) {
                  queue.offer(element);
                  count++;
              }
          }
      }
      
      return count == numCourses;     
  }
}
