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
*/

// topological sorting
class Solution{
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