/* Given: An array of strings where L indicates land and W indicates water, 
and a coordinate marking a starting point in the middle of the ocean.

Challenge: Find and mark the ocean in the map by changing appropriate Ws to Os. 
An ocean coordinate is defined to be the initial coordinate if a W, and
any coordinate directly adjacent to any other ocean coordinate.
*/

// airbnb, see 200. Number of Islands
class Solution {
	public void findOcean(String[] map, int row, int col) {
		char[][] grid = new char[map.length][];
		for (int i = 0; i < map.length; i++) {
			grid[i] = map[i].toCharArray();
		}
		dfsMarking(grid, row, col);
		for (int i = 0; i < map.length; i++) {
			map[i] = new String(grid[i]);
		}
	}

	private void dfsMarking(char[][] grid, int i, int j) {
		if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
			return;
		}
		if (grid[i][j] == 'W') {
			grid[i][j] = 'O';
			dfsMarking(grid, i - 1, j);
			dfsMarking(grid, i + 1, j);
			dfsMarking(grid, i, j - 1);
			dfsMarking(grid, i, j + 1);
		}
	}

	public static void main(String[] args) {
		Solution s = new Solution();
		String[] map = { "WWWLLLW", "WWLLLWW", "WLLLLWW" };
		s.findOcean(map, 0, 1);
		for (String row : map)
			System.out.println(row);
	}
}