/*
You are given the root of a binary tree with n nodes. Each node is uniquely assigned a value from 1 to n. You are also given an integer startValue representing the value of the start node s, and a different integer destValue representing the value of the destination node t.

Find the shortest path starting from node s and ending at node t. Generate step-by-step directions of such path as a string consisting of only the uppercase letters 'L', 'R', and 'U'. Each letter indicates a specific direction:

'L' means to go from a node to its left child node.
'R' means to go from a node to its right child node.
'U' means to go from a node to its parent node.
Return the step-by-step directions of the shortest path from node s to node t.

Example 1:
Input: root = [5,1,2,3,null,6,4], startValue = 3, destValue = 6
Output: "UURL"
Explanation: The shortest path is: 3 → 1 → 5 → 2 → 6.

Example 2:
Input: root = [2,1], startValue = 2, destValue = 1
Output: "L"
Explanation: The shortest path is: 2 → 1.
 
Constraints:
The number of nodes in the tree is n.
2 <= n <= 10^5
1 <= Node.val <= n
All the values in the tree are unique.
1 <= startValue, destValue <= n
startValue != destValue
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

// dfs
class Solution {
    public String getDirections(TreeNode root, int startValue, int destValue) {
        StringBuilder s = new StringBuilder(), d = new StringBuilder();
        StringBuilder res = new StringBuilder();
        // build directions for both start and destination from the root
        find(root, startValue, s);
        find(root, destValue, d);
        s.reverse();
        d.reverse();
        int minLen = Math.min(s.length(), d.length());
        // remove common prefix
        int i = 0;
        while (i < minLen) {
            if (s.charAt(i) == d.charAt(i))
                i++;
            else
                break;
        }
        // replace all steps in the start direction to "U" and add destination direction
        for (int j = i; j < s.length(); j++) {
            res.append("U");
        }
        for (int k = i; k < d.length(); k++) {
            res.append(d.charAt(k));
        }
        return res.toString();
    }

    // the built string needs to be reversed to be the path
    private boolean find(TreeNode node, int val, StringBuilder sb) {
        if (node == null || node.val == val)
            return true;
        else if (node.left != null && find(node.left, val, sb)) {
            sb.append("L");
            return true;
        } else if (node.right != null && find(node.right, val, sb)) {
            sb.append("R");
            return true;
        }
        return false;
    }
}
