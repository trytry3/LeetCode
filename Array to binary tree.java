/* Construct binary tree from a given array in level order fashion 
*/

class TreeNode {
	Integer data;
	TreeNode left, right;

	TreeNode(Integer data) {
		this.data = data;
		this.left = null;
		this.right = null;
	}
}

class Tree {
	TreeNode root;

	// insert nodes in level order
	public TreeNode insertLevelOrder(Integer[] arr, TreeNode root, int i) {
		if (i >= arr.length)
			return root;

		TreeNode newNode = null;
		if (arr[i] != null)
			newNode = new TreeNode(arr[i]);
		root = newNode;
		if (root == null)
			return root;
		// insert left child
		root.left = insertLevelOrder(arr, root.left, 2 * i + 1);
		// insert right child
		root.right = insertLevelOrder(arr, root.right, 2 * i + 2);
		return root;
	}

	// print tree nodes in InOrder fashion
	public void inOrder(TreeNode root) {
		if (root != null) {
			inOrder(root.left);
			System.out.print(root.data + " ");
			inOrder(root.right);
		}
	}

	public static void main(String[] args) {
		Tree t = new Tree();
		Integer[] test = { 1, 2, 2, 3, 3, null, null, 4, 4 };
		t.root = t.insertLevelOrder(test, t.root, 0);
	}

}
