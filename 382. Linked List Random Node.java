/* Given a singly linked list, return a random node's value from the linked list. 
Each node must have the same probability of being chosen.
Follow up:
What if the linked list is extremely large and its length is unknown to you? 
Could you solve this efficiently without using extra space?

Example:
// Init a singly linked list [1,2,3].
ListNode head = new ListNode(1);
head.next = new ListNode(2);
head.next.next = new ListNode(3);
Solution solution = new Solution(head);
// getRandom() should return either 1, 2, or 3 randomly. Each element should have equal probability of returning.
solution.getRandom();
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

// reservoir sampling
class Solution {
    ListNode head;
    Random random;
    
    /** @param head The linked list's head.
        Note that the head is guaranteed to be not null, so it contains at least one node. */
    public Solution(ListNode head) {
        this.head = head;       
        random = new Random();  
    }
    
    /** Returns a random node's value. */
    public int getRandom() {
        ListNode node = head;
        int count = 0;
        int r = node.val;
        // at nth node, make the probabilty of returning its value 1/n
        // e.g. 1->2->3->4->5->null, prob of return 3 is (1/3)*(3/4)*(4/5)=1/5  
        while (node != null) {
            if (random.nextInt(count+1) == count) // `nextInt(i+1)` generates random int from 0 to i
                r = node.val;
            node = node.next;
            count++;
        }
        return r;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(head);
 * int param_1 = obj.getRandom();
 */