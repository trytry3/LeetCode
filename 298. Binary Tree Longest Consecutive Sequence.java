/* Given a binary tree, find the length of the longest consecutive sequence path.
The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. 
The longest consecutive path need to be from parent to child (cannot be the reverse).

Example 1:
Input:

   1
    \
     3
    / \
   2   4
        \
         5

Output: 3
Explanation: Longest consecutive sequence path is 3-4-5, so return 3.
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
    int max = 0;
    
    public int longestConsecutive(TreeNode root) {
        if (root == null)
            return 0;
        dfs(root, 1, root.val);
        return max;
    }
    
    private void dfs(TreeNode node, int cur, int parentVal) {
        if (node == null) 
            return;
        if (node.val == parentVal + 1) 
            cur++; 
        else 
            cur = 1;
        max = Math.max(max, cur);
        dfs(node.left, cur, node.val);
        dfs(node.right, cur, node.val);
    } 
}