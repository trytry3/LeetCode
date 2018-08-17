/* Given a non-empty binary tree, find the maximum path sum.
For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. 
The path must contain at least one node and does not need to go through the root.

Example 1:
Input: [-3]
Output: -3

Example 2:
Input: [-10,9,20,null,null,15,7]

   -10
   / \
  9  20
    /  \
   15   7

Output: 42
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
    int max = Integer.MIN_VALUE;
    
    public int maxPathSum(TreeNode root) {
        maxDepthSum(root);
        return max;
    }
    
    private int maxDepthSum(TreeNode node) {
        if (node == null) 
            return 0;
        int leftMax = maxDepthSum(node.left);
        int rightMax = maxDepthSum(node.right);
        // update max path sum
        max = Math.max(max, leftMax + rightMax + node.val);
        // note: return the max on the depth, not the whole path
        return Math.max(0, node.val + Math.max(leftMax, rightMax));
    }
}