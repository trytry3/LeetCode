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
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        traverse(root, res);
        return res;
    }
    
    private void traverse(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        traverse(node.left, list);
        traverse(node.right, list);
        list.add(node.val);
    }
}