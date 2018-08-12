/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;
        for (int i = 1; i < m; i++) {
            head = head.next;
        }
        ListNode preMNnode = head;
        ListNode mNode = head.next;
        ListNode prev = mNode;
        ListNode cur = mNode.next;
        for (int i = 0; i < n - m; i++) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        ListNode postNnode = cur;
        preMNnode.next = prev;
        mNode.next = postNnode;
        return dummy.next;  
    }
}