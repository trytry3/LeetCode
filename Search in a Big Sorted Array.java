/* Given a big sorted array with positive integers sorted by ascending order. 
The array is so big so that you can not get the length of the whole array directly, 
and you can only access the kth number by ArrayReader.get(k)​ (or ArrayReader­->get(k) for C++). 
Find the first index of a target number. Your algorithm should be in O(log k), 
where k is the first index of the target number.
Return -1, if the number doesn't exist in the array.
*/

class Solution {
	// 1. get the index that ArrayReader.get(index) >= target in O(logk)
	// 2. binary search the target between 0 and index
	public int searchBigSortedArray(ArrayReader reader, int target) {
		int index = 1;
		while (reader.get(index) < target) {
			index *= 2;
		}
		int start = 0;
		int end = index;
		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			if (reader.get(mid) == target) {
				return mid;
			} else if (reader.get(mid) > target) {
				end = mid;
			} else {
				start = mid;
			}
		}
		if (reader.get(start) == target) {
			return start;
		}
		if (reader.get(end) == target) {
			return end;
		}
		return 1;
	}
}