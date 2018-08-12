/** Given a binary tree, find the length of the longest path where each node in the path has the same value. 
This path may or may not pass through the root.
Note: The length of path between two nodes is represented by the number of edges between them.

Example:
Input:
              1
             / \
            4   5
           / \   \
          4   4   5
Output:
2
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
    int longest = 0;
    
    public int longestUnivaluePath(TreeNode root) {
        singleLen(root);
        return longest;
    }
    
    // note: return only the length in one direction, not the whole path
    private int singleLen(TreeNode node) {
        if (node == null)
            return 0;
        int leftLen = singleLen(node.left);
        int rightLen = singleLen(node.right);
        int curLeftLen = 0, curRightLen = 0;
        // same val as the left node
        if (node.left != null && node.left.val == node.val) {
            curLeftLen = leftLen + 1;
        }
        // same val as the right node
        if (node.right != null && node.right.val == node.val) {
            curRightLen = rightLen + 1;
        }
        longest = Math.max(longest, curLeftLen + curRightLen);
        return Math.max(curLeftLen, curRightLen);
    }
}