/** Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.
You may assume that the array is non-empty and the majority element always exist in the array.

Example:
Input: [2,2,1,1,1,2,2]
Output: 2
*/

// Method 1
class Solution {
    public int majorityElement(int[] num) {
        int major = num[0], count = 1;
        for (int i = 1; i < num.length; i++) {
            if (num[i] == major) {
                count++;
            } else if (count == 0) {
                major = num[i];
                count = 1;
            } else {
                count--;
            }
        }
        return major;
    }
}


// Method 2, slow
class Solution {
    public int majorityElement(int[] num) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int i : num) {
            countMap.put(i, countMap.getOrDefault(i,0) + 1);    
        }
        for (int i : countMap.keySet()) {
            if (countMap.get(i) > num.length / 2)
                return i;
        }
        return -1;
    }
}
