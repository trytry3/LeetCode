/* Given a sorted linked list, delete all nodes that have duplicate numbers, 
leaving only distinct numbers from the original list.

Example 1:
Input: 1->2->3->3->4->4->5
Output: 1->2->5

Example 2:
Input: 1->1->1->2->3
Output: 2->3
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
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy  = new ListNode(0);
        dummy.next = head;
        ListNode cur = dummy;
        ListNode prev = dummy;
        while (cur != null) {
            while (cur.next != null && cur.next.val == cur.val) {
                cur =cur.next;
            }
            // cur has moved, there are duplicates
            if (prev.next != cur) {
                prev.next = cur.next;
                cur = prev.next;
            } else {
                prev = prev.next;
                cur = cur.next;
            }    
        }
        return dummy.next;
    }
}