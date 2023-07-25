/*There are a total of n courses you have to take, labeled from 0 to n-1.
Some courses may have prerequisites, for example to take course 0 you have to first take course 1, 
which is expressed as a pair: [0,1]
Given the total number of courses and a list of prerequisite pairs, 
return the ordering of courses you should take to finish all courses.
There may be multiple correct orders, you just need to return one of them. 
If it is impossible to finish all courses, return an empty array.

Example 1:
Input: 2, [[1,0]] 
Output: [0,1]
Explanation: There are a total of 2 courses to take. To take course 1 you should have finished   
             course 0. So the correct course order is [0,1] .

Example 2:
Input: 4, [[1,0],[2,0],[3,1],[3,2]]
Output: [0,1,2,3] or [0,2,1,3]
Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both     
             courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0. 
             So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3] .

Note:
The input prerequisites is a graph represented by a list of edges, not adjacency matrices.
You may assume that there are no duplicate edges in the input prerequisites.
*/

// topological sorting
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] res = new int[numCourses];
        int count = 0;
        // graph to store prerequisites
        Map<Integer, List<Integer>> graph = new HashMap<>();
        // indegree map including all courses
        Map<Integer, Integer> indegrees = new HashMap<>();

        // build directed graph
        for (int[] prerequisite : prerequisites) {
            int course = prerequisite[0];
            int prereq = prerequisite[1];
            graph.putIfAbsent(prereq, new ArrayList<>());
            graph.get(prereq).add(course);
        }

        // build indegree map
        // note: use numbers till numCourses as keys instead of nodes in graph
        // because some courses may not be in prerequisites
        for (int i = 0; i < numCourses; i++) {
            // add the course itself
            indegrees.putIfAbsent(i, 0);
            if (!graph.keySet().contains(i))
                continue;
            // increment the course's neighbors' indegree by 1
            List<Integer> neighborCourses = graph.get(i);
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
                res[count] = course;
                count++;
            }
        }

        while (!queue.isEmpty()) {
            int course = queue.poll();
            // courses not listed in prerequisites
            // i.e. standalone nodes with indegree 0, already added to res
            if (!graph.keySet().contains(course)) {
                continue;
            }
            for (Integer neighbor : graph.get(course)) {
                int newIndegree = indegrees.get(neighbor) - 1;
                if (newIndegree == 0) {
                    queue.add(neighbor);
                    res[count] = neighbor;
                    count++;
                }
                indegrees.put(neighbor, newIndegree);
            }
        }

        if (count != numCourses)
            return new int[]{};
        return res;
    }
}


class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] res = new int[numCourses];
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
                res[count++] = i;
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
                    res[count++] = element;
                }
            }
        }
        if (count != numCourses)
            return new int[0];
        else 
            return res;
    }
    
}
