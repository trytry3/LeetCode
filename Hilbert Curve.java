/* Input is coordinates x, y and iteration `iter`, output how many steps needed to get to (x, y)
*/

// thinking: find which quadrant (x, y) is in first, since each quadrant is a transformation of 
// the previous iteration's curve.
// airbnb
class Solution {
	public int hilbertCurve(int x, int y, int iter) {
		if (iter == 0)
			return 1;

		// how many units on one side
		int len = 1 << (iter - 1);
		// how many points in total for `iter`
		int num = len * len;

		if (x >= len && y >= len) {
			// upper right quadrant, shape is identical as previous iteration
			return 2 * num + hilbertCurve(x - len, y - len, iter - 1);
		} else if (x < len && y >= len) {
			// upper left quadrant, shape is identical as previous iteration
			return num + hilbertCurve(x, y - len, iter - 1);
		} else if (x < len && y < len) {
			// bottom left quadrant, clock-wise rotate 90
			return hilbertCurve(y, x, iter - 1);
		} else {
			// bottom right quadrant, anti-clockwise rotate 90
			return 3 * num + hilbertCurve(len - y - 1, 2 * len - x - 1, iter - 1);
		}

	}

	public static void main(String[] args) {
		Solution s = new Solution();
		// should be 2
		System.out.println(s.hilbertCurve(0, 1, 1));
		// should be 3
		System.out.println(s.hilbertCurve(1, 1, 2));
		// should be 9
		System.out.println(s.hilbertCurve(2, 2, 2));
	}

}
