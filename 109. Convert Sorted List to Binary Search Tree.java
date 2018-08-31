/* Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
For this problem, a height-balanced binary tree is defined as a binary tree 
in which the depth of the two subtrees of every node never differ by more than 1.

Example:
Given the sorted linked list: [-10,-3,0,5,9],
One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
      0
     / \
   -3   9
   /   /
 -10  5
*/

class Solution {
	public TreeNode sortedListToBST(ListNode head) {
        return toBST(head, null);
    }
    
    private TreeNode toBST(ListNode head, ListNode tail) {
        ListNode slow = head;
        ListNode fast = head;
        if (head == tail) 
            return null; 
        // find the mid point, slow points to the mid
        while (fast != tail && fast.next != tail) {
            fast = fast.next.next;
            slow = slow.next;
        }
        TreeNode midNode = new TreeNode(slow.val);
        midNode.left = toBST(head, slow);
        midNode.right = toBST(slow.next, tail);
        return midNode;
    }
}