/* A linked list is given such that each node contains an additional random pointer 
which could point to any node in the list or null.
Return a deep copy of the list.
*/

/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */
public class Solution {
	public RandomListNode copyRandomList(RandomListNode head) {
		if (head == null)
			return null;
		Map<RandomListNode, RandomListNode> map = new HashMap<>();
		// copy all the nodes
		RandomListNode node = head;
		while (node != null) {
			map.put(node, new RandomListNode(node.label));
			node = node.next;
		}

		// assign next and random pointers to the copied new node
		node = head;
		while (node != null) {
            RandomListNode newNode = map.get(node);
			newNode.next = map.get(node.next);
			newNode.random = map.get(node.random);
			node = node.next;
		}

		return map.get(head);
	}
}