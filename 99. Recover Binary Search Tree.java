/* Two elements of a binary search tree (BST) are swapped by mistake.
Recover the tree without changing its structure.

Example 1:
Input: [1,3,null,null,2]
   1
  /
 3
  \
   2
Output: [3,1,null,null,2]
   3
  /
 1
  \
   2

Example 2:
Input: [3,1,4,null,null,2]
  3
 / \
1   4
   /
  2
Output: [2,1,4,null,null,3]
  2
 / \
1   4
   /
  3

Follow up:
A solution using O(n) space is pretty straight forward.
Could you devise a constant space solution?
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
    private TreeNode prev = new TreeNode(Integer.MIN_VALUE);
    private TreeNode first = null;
    private TreeNode second = null;
    
    public void recoverTree(TreeNode root) {
        traverse(root);
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }
    
    // e.g. in [6, 3, 4, 5, 2], 6 is the first wrong node, 2 is the second wrong node.
    private void traverse(TreeNode root) {
        if (root == null)
            return;
        
        traverse(root.left);
        
        if (first == null && prev.val > root.val)
            first = prev;
        if (first != null && prev.val > root.val)
            second = root;
        
        prev = root;
        
        traverse(root.right);
    }
}