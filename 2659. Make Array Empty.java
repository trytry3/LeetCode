/*
You are given an integer array nums containing distinct numbers, and you can perform the following operations until the array is empty:

If the first element has the smallest value, remove it
Otherwise, put the first element at the end of the array.
Return an integer denoting the number of operations it takes to make nums empty.

Example 1:
Input: nums = [3,4,-1]
Output: 5
Operation	Array
1	[4, -1, 3]
2	[-1, 3, 4]
3	[3, 4]
4	[4]
5	[]

Example 2:
Input: nums = [1,2,4,3]
Output: 5
Operation	Array
1	[2, 4, 3]
2	[4, 3]
3	[3, 4]
4	[4]
5	[]

Example 3:
Input: nums = [1,2,3]
Output: 3
Operation	Array
1	[2, 3]
2	[3]
3	[]
 
Constraints:
1 <= nums.length <= 10^5
-10^9 <= nums[i] <= 10^9
All values in nums are distinct.
*/


class Solution {
 /*
example [1,4,5,2,8,7,6,3], where n = size of array = 8
we split the process of removing smallest elements into 4 passes:
remove [1,2,3]
remove [4,5,6]
remove [7]
remove [8]

To remove [1,2,3] from the array, we need to remove 1, move 4 to the back, move 5 to the back,
remove 2, move 8 to the back, move 7 to the back, move 6 to the back, remove 3.
The first pass uses n = size of array = 8 operations, regardless what elements are in the first pass.
This is why we initialize res to n.

Now, the remaining of the array is [4,5,8,7,6] after removing [1,2,3]
We want to remove [4,5,6] from the remaining array, we need to remove 4, remove 5, move 8 to the back,
move 7 to the back, remove 6. And this pass takes 5 operations, which is equal to the size of the remaining array.
This is why res += n - i. (since n - i is the size of the remaining array)
*/
    public long countOperationsToEmptyArray(int[] nums) {
        long n = nums.length;
        Map<Integer, Integer> pos = new HashMap<>();
        long res = n;
        for (int i = 0; i < n; i++)
            pos.put(nums[i], i);
        Arrays.sort(nums);
        for (int i = 1; i < n; i++) {
            if (pos.get(nums[i]) < pos.get(nums[i - 1]))
                res += n - i;
        }
        return res;
    }
}


// brute force, time limit exceeded
class Solution {
    public long countOperationsToEmptyArray(int[] nums) {
        int count = 0;
        List<Integer> copy = new ArrayList<>();
        for (int num : nums)
            copy.add(num);
        Collections.sort(copy);
        // put the array into a list, manipulate the list
        List<Integer> list = new ArrayList<>();
        for (int num : nums)
            list.add(num);
        while (!list.isEmpty()) {
            if (list.get(0) == copy.get(0)) {
                copy.remove(0);
                list.remove(0);
            } else {
                int first = list.get(0);
                list.remove(0);
                list.add(first);
            }
            count++;
        }
        return count;
    }
}
