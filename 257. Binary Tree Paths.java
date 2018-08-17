/* Given a binary tree, return all root-to-leaf paths.
Note: A leaf is a node with no children.

Example:
Input:

   1
 /   \
2     3
 \
  5
Output: ["1->2->5", "1->3"]
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
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root != null)
            dfs(res, "", root);
        return res;
    }
    
    private void dfs(List<String> res, String path, TreeNode node) {
        if (node.left == null && node.right == null) {
            res.add(path + node.val);
            return;
        }
        if (node.left != null)
            dfs(res, path+node.val+"->", node.left);
        if (node.right != null)
            dfs(res, path+node.val+"->", node.right);
    }
}