/* Construct binary tree from array, print the tree
*/
class Solution {

	public static <T> Node<T> fromArray(T[] array) {
		return fromArrayHelper(array, 0, array.length - 1);

	}

	// O(n), avoid copying array
	private static <T> Node<T> fromArrayHelper(T[] array, int start, int end) {
		if (start > end)
			return null;
		// find mid point
		int midIndex = (start + end) / 2;
		Node<T> leftNode = fromArrayHelper(array, start, midIndex - 1);
		Node<T> rightNode = fromArrayHelper(array, midIndex + 1, end);
		Node<T> midNode = new Node<>(array[midIndex], leftNode, rightNode);
		return midNode;
	}

	public static void main(String[] args) {
		Node<Integer> root = new Node<Integer>(0);
		System.out.println(root);
		// "(0)"

		Node<Integer> root2 = new Node<Integer>(1, new Node<Integer>(0), new Node<Integer>(2));
		System.out.println(root2);
		// "((0)1(2))"

		Integer[] v = { 1, 2, 3, 4, 5, 6, 7, };
		System.out.println(fromArray(v));
		// (((1)2(3))4((5)6(7)))
	}
}

class Node<T> {
	T item;
	Node<T> left;
	Node<T> right;

	public Node(T item) {
		this.item = item;
	}

	public Node(T item, Node<T> left, Node<T> right) {
		this.item = item;
		this.left = left;
		this.right = right;
	}

	@Override
	// more efficient
	public String toString() {
		StringBuilder sb = new StringBuilder();
		helper(sb, this);
		return sb.toString();
	}

	private void helper(StringBuilder sb, Node<T> curNode) {
		if (curNode == null)
			return;
		sb.append("(");
		helper(sb, curNode.left);
		sb.append(curNode.item.toString());
		helper(sb, curNode.right);
		sb.append(")");
	}

	public String toString2() {
		String leftStr = left == null ? "" : left.toString();
		String rightStr = right == null ? "" : right.toString();
		return "(" + leftStr + item.toString() + rightStr + ")";
	}

}


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
