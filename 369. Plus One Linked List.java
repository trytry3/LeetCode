/** Given a non-negative integer represented as non-empty a singly linked list of digits, plus one to the integer.
You may assume the integer do not contain any leading zero, except the number 0 itself.
The digits are stored such that the most significant digit is at the head of the list.

Example:
Input:
1->2->3
Output:
1->2->4
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
    public ListNode plusOne(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode i = dummy; 
        ListNode j = dummy; 
        while (j.next != null) {
            j = j.next;
            // 2999: i is 2, j is last 9
            // 2998: i and j are both 8
            if (j.val != 9)
                i = j;
        }
        if (j.val != 9)
            j.val++;
        else {
            // 2999 + 1, i is 2
            i.val++;
            while (i.next != null) {
                i = i.next;
                i.val = 0;
            }
        }
        if (dummy.val == 0)
            return dummy.next;
        else 
            return dummy;
    }
}