/* Given a binary tree, return the vertical order traversal of its nodes' values. 
(ie, from top to bottom, column by column).
If two nodes are in the same row and column, the order should be from left to right.

Examples 1:
Input: [3,9,20,null,null,15,7]
   3
  /\
 /  \
 9  20
    /\
   /  \
  15   7 
Output:
[
  [9],
  [3,15],
  [20],
  [7]
]

Examples 2:
Input: [3,9,8,4,0,1,7]
     3
    /\
   /  \
   9   8
  /\  /\
 /  \/  \
 4  01   7 
Output:
[
  [4],
  [9],
  [3,0,1],
  [8],
  [7]
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
	public List<List<Integer>> verticalOrder(TreeNode root) {
		if (root == null) {
			return new ArrayList<>();
		}

        // TreeMap preserves key's natural ordering
        // key is col, value is the list of nodes in that col
		Map<Integer, List<Integer>> map = new TreeMap<>();
		Queue<TreeNode> nodes = new LinkedList<>();
        // track each node's col
		Queue<Integer> cols = new LinkedList<>();

		nodes.add(root);
		cols.add(0);

		while (!nodes.isEmpty()) {
			TreeNode node = nodes.poll();
			int col = cols.poll();

			if (!map.containsKey(col)) {
				map.put(col, new ArrayList<>());
			}
			map.get(col).add(node.val);

			if (node.left != null) {
				nodes.add(node.left);
				cols.add(col - 1);
			}

			if (node.right != null) {
				nodes.add(node.right);
				cols.add(col + 1);
			}
		}

		return new ArrayList<>(map.values());
	}
}