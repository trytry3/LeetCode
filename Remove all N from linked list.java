/* Remove all nodes that have value n in the singly linked list.
*/

/*
For your reference:
LinkedListNode {
    int val;
    LinkedListNode *next;
};
*/

// my solution to Pure Storage OA
public LinkedListNode removeAll(int n, LinkedListNode head) {
    LinkedListNode dummy = new LinkedListNode(0);
    dummy.next = head;
    LinkedListNode cur = dummy;
    while (cur != null && cur.next != null) {
        while (cur.next != null && cur.next.val == n)
            cur.next = cur.next.next;
        cur = cur.next;       
    }
    return dummy.next;
}