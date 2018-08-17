/* Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

Example:
Input:
[
  1->4->5,
  1->3->4,
  2->6
]
Output: 1->1->2->3->4->4->5->6
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

// method 1: divide and conquer
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0)
            return null;
        return merge(lists, 0, lists.length-1);
    }
    
    private ListNode merge(ListNode[] lists, int start, int end) {
        if (start == end)
            return lists[start];
        int mid = start + (end - start)/2;
        ListNode left = merge(lists, start, mid);
        ListNode right = merge(lists, mid+1, end);
        return mergeTwoList(left, right);
    }
            
    private ListNode mergeTwoList(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode head = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                head.next = l1; 
                head = head.next;
                l1 = l1.next;
            } else {
                head.next = l2;
                head = head.next;
                l2 = l2.next;
            }
        }
        if (l1 == null)
            head.next = l2;
        if (l2 == null)
            head.next = l1;
        return dummy.next;
    }          
}


// method 2: heap
class Solution {
    private Comparator comparator = new Comparator<ListNode>(){
        @Override
        public int compare(ListNode node1, ListNode node2) {
            if (node1 == null) 
                return -1;
            if (node2 == null)
                return 1;
            return node1.val - node2.val;
        }
    };
    
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0)
            return null;
        PriorityQueue<ListNode> heap = new PriorityQueue<ListNode>(lists.length, comparator);
        for(ListNode node : lists) {
            if (node != null)
                heap.add(node);
        }
        ListNode dummy = new ListNode(0);
        ListNode head = dummy;
        while (!heap.isEmpty()) {
            ListNode cur = heap.poll();
            head.next = cur;
            head = head.next;
            if (cur.next != null)
                heap.add(cur.next);
        }
        return dummy.next;
    }        
}


