/** You are given a binary tree in which each node contains an integer value.
Find the number of paths that sum to a given value.
The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).

Example:
root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

      10
     /  \
    5   -3
   / \    \
  3   2   11
 / \   \
3  -2   1

Return 3. The paths that sum to 8 are:
1.  5 -> 3
2.  5 -> 2 -> 1
3. -3 -> 11
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public int pathSum(TreeNode root, int sum) {
        if (root == null) 
            return 0;
        return sumFrom(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }
    
    private int sumFrom(TreeNode node, int target) {
        if (node == null) 
            return 0;
        return (node.val == target ? 1 : 0) + sumFrom(node.left, target - node.val) 
                + sumFrom(node.right, target - node.val);
    }
   
}