/* Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1 ... n.

Example:
Input: 3
Output:
[
  [1,null,3,2],
  [3,2,null,1],
  [3,1,null,null,2],
  [2,1,3],
  [1,null,2,null,3]
]
Explanation:
The above output corresponds to the 5 unique BST's shown below:

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
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

// see 96. Unique Binary Search Trees
class Solution {
    public List<TreeNode> generateTrees(int n) {
        if (n==0) 
            return new ArrayList();
	    return generateSubtrees(1, n);
    }

    private List<TreeNode> generateSubtrees(int s, int e) {
	    List<TreeNode> res = new ArrayList<TreeNode>();
	    if (s > e) {
		    res.add(null); // empty tree
		    return res;
	    }

	    for (int i = s; i <= e; i++) {
		    List<TreeNode> leftSubtrees = generateSubtrees(s, i - 1);
		    List<TreeNode> rightSubtrees = generateSubtrees(i + 1, e);

		    for (TreeNode left : leftSubtrees) {
			    for (TreeNode right : rightSubtrees) {
				    TreeNode root = new TreeNode(i);
				    root.left = left;
				    root.right = right;
				    res.add(root);
			    }
		    }
	    }
	    return res;
    }
}