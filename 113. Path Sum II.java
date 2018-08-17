/* Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
Note: A leaf is a node with no children.

Example:
Given the below binary tree and sum = 22,

      5
     / \
    4   8
   /   / \
  11  13  4
 /  \    / \
7    2  5   1

Return:
[
   [5,4,11,2],
   [5,8,4,5]
]
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
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, new ArrayList<Integer>(), root, sum);
        return res;
    }
    
    private void dfs(List<List<Integer>> res, List<Integer> tempList, TreeNode node, int target) {
        if (node == null)
            return;
        tempList.add(node.val);
        if (node.left == null && node.right == null && target == node.val) {
            res.add(new ArrayList<Integer>(tempList));
            // note: still need backtracking here
            tempList.remove(tempList.size() - 1);
            return;
        }
        dfs(res, tempList, node.left, target - node.val);
        dfs(res, tempList, node.right, target - node.val);
        tempList.remove(tempList.size() - 1);
    }
}