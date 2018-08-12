class Solution{
  // topological sorting
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