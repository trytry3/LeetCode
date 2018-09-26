/* Given a binary tree, return the zigzag level order traversal of its nodes' values. 
(ie, from left to right, then right to left for the next level and alternate between).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
  [15,7]
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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        traverse(root, res, 0);
        return res;
    }
    
    private void traverse(TreeNode cur, List<List<Integer>> res, int level) {
        if (cur == null) 
            return;
        // that level's list is not created yet
        // e.g. when at level 1, before traversing, res has size 1
        // need to add a new list to start traversing
        if (res.size() == level) {
            List<Integer> newLevel = new ArrayList<>();
            res.add(newLevel);
        }   
        List<Integer> oneLevelList  = res.get(level);
        if (level % 2 == 0) 
            oneLevelList.add(cur.val);
        else 
            // right to left
            oneLevelList.add(0, cur.val);
        
        traverse(cur.left, res, level + 1);
        traverse(cur.right, res, level + 1);
    }
}
