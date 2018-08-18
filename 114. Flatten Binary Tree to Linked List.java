/* Given a binary tree, flatten it to a linked list in-place.
For example, given the following tree:
    1
   / \
  2   5
 / \   \
3   4   6
The flattened tree should look like:
1
 \
  2
   \
    3
     \
      4
       \
        5
         \
          6
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
    public void flatten(TreeNode root) {
        if (root == null) 
            return; 
        // make a copy of root's left and right for flatten
        TreeNode left = root.left;
        TreeNode right = root.right;   
        flatten(left);
        flatten(right);     
        // update current root's left and right
        root.left = null;
        root.right = left;   
        // append the flattened right to the end of list
        TreeNode cur = root;
        while (cur.right != null) 
            cur = cur.right;
        cur.right = right;
    }
}