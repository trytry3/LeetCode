/* Reverse a singly linked list.

Example:
Input: 1->2->3->4->5->NULL
Output: 5->4->3->2->1->NULL
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

// method 1: iteratively
class Solution {
    public ListNode reverseList(ListNode head) {
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

// method 2: recursively
class Solution {
    public ListNode reverseList(ListNode head) {
        return reverse(head, null);
    }
    
    private ListNode reverse(ListNode cur, ListNode prev) {
        if (cur == null)
            return prev;
        ListNode next = cur.next;
        cur.next = prev;
        return reverse(next, cur);
    }
}