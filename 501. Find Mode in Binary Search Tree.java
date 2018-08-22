/* Given a binary search tree (BST) with duplicates, find all the mode(s) (the most frequently occurred element) in the given BST.
Assume a BST is defined as follows:
The left subtree of a node contains only nodes with keys less than or equal to the node's key.
The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
Both the left and right subtrees must also be binary search trees.
 
For example:
Given BST [1,null,2,2],
   1
    \
     2
    /
   2
return [2].
Note: If a tree has more than one mode, you can return them in any order.
Follow up: Could you do that without using any extra space? (Assume that the implicit stack space incurred due to recursion does not count).
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

// dfs
class Solution {
    TreeNode prev;
    int count = 0;
    int max = 0;
    public int[] findMode(TreeNode root) {
        List<Integer> resList = new ArrayList<>();
        if (root == null) 
            return listToArray(resList);      
        traverse(root, resList);
        return listToArray(resList);     
    }
    
    // in order
    private void traverse(TreeNode root, List<Integer> res) {
        if (root == null) 
            return;
        traverse(root.left, res);
        if (prev != null && root.val == prev.val)
            count++;
        else
            count = 1;
        if (count > max) {
            max = count;
            res.clear();
            res.add(root.val);
        } else if (count == max) {
            res.add(root.val);
        }
        prev = root;
        traverse(root.right, res);
    }
    
    private int[] listToArray(List<Integer> list) {
        int[] array = new int[list.size()];
        for (int i = 0; i < list.size(); i++) 
            array[i] = list.get(i);
        return array;
    }
}