/*
longestSequence(values, maxSum)

values is a list of integers
maxSum is an integer

Returns the length of the longest sequence of adjacent numbers whose sum does not exceed maxSum

longestSequence([3, 7, 1, 2, 6, 3, 3, 2, 9], 8)
Return 3

longestSequence([7, 2, 1, 7, 2, 2, 2, 2, 11, 8], 10)
Return 4
*/

class Solution {
	public static int longestSequence(int[] values, int maxSum) {
		int n = values.length;

		int start = 0, end = 0, currentSum = 0, output = 0;
		while (end < n) {
			currentSum += values[end];
			end++;
			while (currentSum > maxSum) {
				currentSum -= values[start];
				start++;
			}
			if (end - start > output) {
				output = end - start;
			}
		}

		return output;
	}

	public static void main(String[] args) {
		// expect output = 3
		int[] values = { 3, 7, 1, 2, 6, 3, 3, 2, 9 };
		int maxSum = 8;
		// expect output 4
		// int[] values = {7, 2, 1, 7, 2, 2, 2, 2, 11, 8};
		// int maxSum = 10;

		// expect output 0
		// int[] values = { 9, 10 };
		// int maxSum = 8;
		int res = longestSequence(values, maxSum);
		System.out.println(res);
	}

}
