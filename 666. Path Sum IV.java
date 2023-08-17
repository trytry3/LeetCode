/*
If the depth of a tree is smaller than 5, then this tree can be represented by an array of three-digit integers. For each integer in this array:

The hundreds digit represents the depth d of this node where 1 <= d <= 4.
The tens digit represents the position p of this node in the level it belongs to where 1 <= p <= 8. The position is the same as that in a full binary tree.
The units digit represents the value v of this node where 0 <= v <= 9.
Given an array of ascending three-digit integers nums representing a binary tree with a depth smaller than 5, return the sum of all paths from the root towards the leaves.

It is guaranteed that the given array represents a valid connected binary tree.

Example 1:
Input: nums = [113,215,221]
Output: 12
Explanation: The tree that the list represents is shown.
The path sum is (3 + 5) + (3 + 1) = 12.

Example 2:
Input: nums = [113,221]
Output: 4
Explanation: The tree that the list represents is shown. 
The path sum is (3 + 1) = 4.
 
Constraints:
1 <= nums.length <= 15
110 <= nums[i] <= 489
nums represents a valid binary tree with depth less than 5.
*/

class Solution {
    int sum = 0;

    public int pathSum(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        // key is first two digits (level and position), value is last digit
        Map<Integer, Integer> tree = new HashMap<>();

        for (int num : nums) {
            int key = num / 10;
            int value = num % 10;
            tree.put(key, value);
        }

        traverse(tree, nums[0] / 10, 0);

        return sum;
    }

    private void traverse(Map<Integer, Integer> tree, int rootKey, int preSum) {
        int level = rootKey / 10;
        int pos = rootKey % 10;
        int leftKey = (level + 1) * 10 + (pos * 2 - 1);
        int rightKey = (level + 1) * 10 + pos * 2;

        int curSum = preSum + tree.get(rootKey);

        if (!tree.containsKey(leftKey) && !tree.containsKey(rightKey)) {
            sum += curSum;
            return;
        }
        if (tree.containsKey(leftKey))
            traverse(tree, leftKey, curSum);
        if (tree.containsKey(rightKey))
            traverse(tree, rightKey, curSum);
    }
}
