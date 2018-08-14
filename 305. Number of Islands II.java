/** A 2d grid map of m rows and n columns is initially filled with water. 
We may perform an addLand operation which turns the water at position (row, col) into a land. 
Given a list of positions to operate, count the number of islands after each addLand operation. 
An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. 
You may assume all four edges of the grid are all surrounded by water.

Example:
Input: m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]]
Output: [1,1,2,3]

Explanation:
Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).
0 0 0
0 0 0
0 0 0

Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.
1 0 0
0 0 0   Number of islands = 1
0 0 0

Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.
1 1 0
0 0 0   Number of islands = 1
0 0 0

Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.
1 1 0
0 0 1   Number of islands = 2
0 0 0

Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.
1 1 0
0 0 1   Number of islands = 3
0 1 0
*/

// union find
class Solution {
    int[][] dirs = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> result = new ArrayList<>();
        if (m <= 0 || n <= 0) 
            return result;
        // number of islands
        int count = 0;  
        // store parent of node, not necessarily the highest root of the node
        int[] roots = new int[m*n];       
        Arrays.fill(roots, -1);            

        for (int[] p: positions) {
            // assume new point is isolated island
            // at first, parent is itself
            int curRoot = n * p[0] + p[1];       
            roots[curRoot] = curRoot;             
            count++;

            // explore neighbors
            for (int[] dir: dirs) {
                int x = p[0] + dir[0]; 
                int y = p[1] + dir[1];
                int neighbor = n * x + y;
                // roots[neighbor] == -1 means the neighbor is not an island
                if(x < 0 || x >= m || y < 0 || y >= n || roots[neighbor] == -1) 
                    continue;
                // neighbor is an island, but not the same island
                int rootNb = findIslandRoot(roots, neighbor);
                if(curRoot != rootNb) { 
                    // union two islands 
                    roots[curRoot] = rootNb;   
                    // current tree root = joined tree root
                    curRoot = rootNb;          
                    count--;               
                }
            }
            result.add(count);
        }
        return result;
    }

    public int findIslandRoot(int[] roots, int id) {
        while(id != roots[id]) {
            roots[id] = roots[roots[id]]; // shorten search path
            id = roots[id];
        }
        return id;
    }
    
}