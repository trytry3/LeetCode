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
        if (res.size() <= level) {
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