/* Given n pieces of wood with length ​L[i]​ (integer array). 
Cut them into small pieces to guarantee you could have equal or more than k pieces 
with the same length. What is the longest length you can get from the n pieces of wood? 
Given L and k, return the maximum length of the small pieces. 
You couldn't cut wood into float length. 
*/

// binary search
class Solution {
	public int woodCut(int[] L, int k) {
		int maxLen = 0;
		for (int len : L) {
			maxLen = Math.max(maxLen, len);
		}
		int start = 0;
		int end = maxLen;
		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			if (count(L, mid) >= k)
				start = mid;
			else
				end = mid;

		}
		if (count(L, end) >= k)
			return end;
		return start;
	}

	private int count(int[] L, int maxLength) {
		int count = 0;
		for (int len : L) {
			count += len / maxLength;
		}
		return count;
	}
}