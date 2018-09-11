/* Given an m x n matrix of positive integers representing the height of each unit cell in a 2D elevation map, 
compute the volume of water it is able to trap after raining.

Note:
Both m and n are less than 110. The height of each unit cell is greater than 0 and is less than 20,000.

Example:
Given the following 3x6 height map:
[
  [1,4,3,1,3,2],
  [3,2,1,3,2,4],
  [2,3,3,2,3,1]
]
Return 4.
*/

// heap
class Solution {
	private class Cell {
		int row;
		int col;
		int height;
		public Cell(int row, int col, int height) {
			this.row = row;
			this.col = col;
			this.height = height;
		}
	}

	public int trapRainWater(int[][] heights) {
		if (heights == null || heights.length == 0 || heights[0].length == 0)
			return 0;
        // note: explore lowest from borders first
		PriorityQueue<Cell> heap = new PriorityQueue<>((a, b) -> a.height - b.height);
		int m = heights.length;
		int n = heights[0].length;
		boolean[][] visited = new boolean[m][n];

		// initially, add all cells on borders to the queue
		for (int i = 0; i < m; i++) {
			visited[i][0] = true;
			visited[i][n - 1] = true;
			heap.offer(new Cell(i, 0, heights[i][0]));
			heap.offer(new Cell(i, n - 1, heights[i][n - 1]));
		}
		for (int i = 0; i < n; i++) {
			visited[0][i] = true;
			visited[m - 1][i] = true;
			heap.offer(new Cell(0, i, heights[0][i]));
			heap.offer(new Cell(m - 1, i, heights[m - 1][i]));
		}

		// from the borders, pick the shortest cell visited and check its neighbors:
		// if the neighbor is shorter, collect the water it can trap and 
        // update its height as its height plus the water trapped
		// add all its neighbors to the queue
		int[][] dirs = new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
		int res = 0;
		while (!heap.isEmpty()) {
			Cell cell = heap.poll();
			for (int[] dir : dirs) {
                // current neighbor
				int row = cell.row + dir[0];
				int col = cell.col + dir[1];
				if (row >= 0 && row < m && col >= 0 && col < n && !visited[row][col]) {
					visited[row][col] = true;
					res += Math.max(0, cell.height - heights[row][col]);
                    // update height as its height plus trapped water if any
					heap.offer(new Cell(row, col, Math.max(heights[row][col], cell.height)));
				}
			}
		}
		return res;
	}
}