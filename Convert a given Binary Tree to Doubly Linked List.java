/*      10
      /    \
    12      15
   /  \    /  
  25  30  36

to 

25 <=> 12 <=> 30 <=> 10 <=> 36 <=> 15
*/

class Node {
	int data;
	Node left, right;

	public Node(int data) {
		this.data = data;
		left = right = null;
	}
}

class BinaryTree {
	Node root;
	// pointer to head node of created doubly linked list
	Node head;

	static Node prev = null;

	// convert a given Binary Tree to Doubly Linked List
	public void flatten(Node root) {
		if (root == null)
			return;

		flatten(root.left);
		// convert this node
		if (prev == null)
			head = root;
		else {
			root.left = prev;
			prev.right = root;
		}
		prev = root;
		flatten(root.right);
	}

	public void printList(Node node) {
		while (node != null) {
			System.out.print(node.data + " ");
			node = node.right;
		}
	}

	// driver program to test above functions
	public static void main(String[] args) {
		BinaryTree tree = new BinaryTree();
		tree.root = new Node(10);
		tree.root.left = new Node(12);
		tree.root.right = new Node(15);
		tree.root.left.left = new Node(25);
		tree.root.left.right = new Node(30);
		tree.root.right.left = new Node(36);

		// convert to DLL
		tree.flatten(tree.root);
		tree.printList(tree.head);

	}
}