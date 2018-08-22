/* Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
Note: You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

Example 1:
Input: root = [3,1,4,null,2], k = 1
   3
  / \
 1   4
  \
   2
Output: 1

Example 2:
Input: root = [5,3,6,2,4,null,null,1], k = 3
       5
      / \
     3   6
    / \
   2   4
  /
 1
Output: 3
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

// method 1: binary search, dfs
class Solution {
    public int kthSmallest(TreeNode root, int k) {
        int leftNodeCount = countNodes(root.left);
        if (leftNodeCount + 1 == k)
            return root.val;
        else if (leftNodeCount + 1 > k)
            return kthSmallest(root.left, k);
        else // if (leftNodeCount + 1 < k )
            return kthSmallest(root.right, k - (leftNodeCount+1));
    }
    
    private int countNodes(TreeNode node) {
        if (node == null)
            return 0;
        return 1 + countNodes(node.left) + countNodes(node.right);
    }
}


// method 2: see iterative method of 94. Binary Tree Inorder Traversal.java
class Solution {
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>(); 
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            TreeNode cur = stack.pop();
            k--;
            if (k==0)
                return cur.val;
            root = cur.right;  
        }  
        // should never be reached if k is within range
        return -1;
    }
}