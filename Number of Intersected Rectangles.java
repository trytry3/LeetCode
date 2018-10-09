/* You have a plane with lots of rectangles on it, find out how many of them intersect.
*/

// see 323. Number of Connected Components in an Undirected Graph and 836. Rectangle Overlap
// airbnb
class Solution {

	public int countIntersection(int[][][] rectangles) {
		int len = rectangles.length;
		int count = len;
		int[] roots = new int[len];
		for (int i = 0; i < len; i++) {
			roots[i] = i;
		}
		for (int i = 0; i < rectangles.length; i++) {
			for (int j = i + 1; j < rectangles.length; j++) {
				if (intersect(rectangles[i], rectangles[j])) {
					int root1 = find(i, roots);
					int root2 = find(j, roots);
					if (root1 != root2) {
						roots[root1] = root2;
						count--;
					}
				}
			}
		}
		return count;
	}

	// see 836. Rectangle Overlap
	private boolean intersect(int[][] r1, int[][] r2) {
		return r1[0][0] < r2[0][0] && r1[0][1] < r2[0][1] && r1[1][0] > r2[0][0] && r1[1][1] > r2[0][1]
				|| r1[0][0] < r2[1][0] && r1[0][1] < r2[1][1] && r1[1][0] > r2[1][0] && r1[1][1] > r2[1][1];
	}

	private int find(int val, int[] parents) {
		while (parents[val] != val) {
			val = parents[val];
		}
		return val;
	}

}
