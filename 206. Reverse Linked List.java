/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
// Method 1: iteratively
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

// Method 2: recursively
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