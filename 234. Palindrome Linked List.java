/* Given a singly linked list, determine if it is a palindrome.

Example 1:
Input: 1->2
Output: false

Example 2:
Input: 1->2->2->1
Output: true

Follow up:
Could you do it in O(n) time and O(1) space?
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
	public boolean isPalindrome(ListNode head) {
		ListNode fast = head, slow = head;
		while (fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}
    	// odd nodes, let right half smaller;
    	// because when even nodes, after reverse slow, right half is smaller 
    	// since the middle is not break
		if (fast != null) { 
			slow = slow.next;
		}
		ListNode right = reverse(slow);
		ListNode left = head;
    	// since right half is smaller, check right
		while (right != null) {
			if (left.val != right.val) {
				return false;
			}
			left = left.next;
			right = right.next;
		}
		return true;
	}
    
    public ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
        	// store head's next before reversing
            ListNode temp = head.next;
            head.next = prev;
            prev = head;
            head = temp;        
        } 
        return prev;
    }
}
