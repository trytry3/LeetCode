/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};
*/
/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};
*/

// method 1:
class Solution {
    public Node treeToDoublyList(Node root) {
        if (root == null)
            return null;
        Node head = treeToDLLHelper(root);
        // make it circle
        Node cur = head;
        while (cur.right != null) {
            cur = cur.right;
        }
        cur.right = head;
        head.left = cur;
        return head;
    }
    
    // to non circular DLL
    private Node treeToDLLHelper(Node root) {
        if (root == null)
            return null;
        Node leftNode = treeToDLLHelper(root.left);
        Node rightNode = treeToDLLHelper(root.right);
        root.right = rightNode;
        if (rightNode != null)
            rightNode.left = root;
        if (leftNode == null)
            return root;
        // root's left node is the rightmost child of leftNode
        Node cur = leftNode;
        while (cur != null && cur.right != null) {
            cur = cur.right;
        }
        root.left = cur;
        cur.right = root;
        return leftNode;
    }
}

// method 2:
class Solution {
    Node prev = null;
    public Node treeToDoublyList(Node root) {
        if (root == null) 
            return null;
        Node dummy = new Node(0, null, null);
        prev = dummy;
        helper(root);
        // connect head and tail
        // prev now is the end of DLL
        prev.right = dummy.right;
        dummy.right.left = prev;
        return dummy.right;
    }

    // in order
    private void helper(Node cur) {
        if (cur == null) 
            return;
        helper(cur.left);
        // prev now is the end of left node's corresponding DLL
        prev.right = cur;
        cur.left = prev;
        prev = cur;
        helper(cur.right);
    }
}