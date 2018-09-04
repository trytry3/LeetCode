import java.io.*;
import java.util.*;

/*
 *Build a queue class with the enqueue and dequeue methods. The queue can store an *UNLIMITED* number of elements 
 but you are limited to using arrays that can store up to 5 elements max
 */

// my solution to airbnb phone interview
class Solution {
  public static void main(String[] args) throws Exception {
    Queue test = new Queue();
    test.enqueue(1);
    test.enqueue(2);
    test.enqueue(3);
    test.enqueue(4);
    test.enqueue(5);
    test.enqueue(6);
    test.enqueue(7);
    System.out.println(test.dequeue());
    System.out.println(test.dequeue());
    System.out.println(test.dequeue());
    System.out.println("-----");
    test.enqueue(8);
    test.enqueue(9);
    test.enqueue(10);
    System.out.println(test.dequeue());
    System.out.println(test.dequeue());
    System.out.println(test.dequeue());
    System.out.println(test.dequeue());
    System.out.println(test.dequeue());
    System.out.println(test.dequeue());
    System.out.println(test.dequeue());
    System.out.println(test.dequeue());
  }
}

class Queue {
  private int indexEnq;
  private int indexDeq;
  private ListNode head;
  private ListNode dummy;
  private ListNode cur;

  public Queue() {
    head = new ListNode(new int[5]);
    dummy = new ListNode(new int[5]);
    dummy.next = head;
    cur = head;
  }

  public void enqueue(int ele) {
    if (indexEnq >= 5) {
      indexEnq = indexEnq % 5;
      // add a list node
      ListNode node = new ListNode(new int[5]);
      cur.next = node;
      cur = cur.next;
    }
    cur.val[indexEnq] = ele;
    indexEnq++;
  }

  public int dequeue() throws Exception {
    if (indexDeq >= 5) {
      indexDeq = indexDeq % 5;
      // remove head of linked list
      dummy.next = dummy.next.next;
      head = dummy.next;
      if (head == null) {
        throw new Exception("not enough elements for deq");
      }
    }
    int res = head.val[indexDeq];
    indexDeq++;
    return res;
  }

}

class ListNode {
  public int[] val;
  public ListNode next;

  public ListNode(int[] arrayVal) {
    val = arrayVal;
  }

}
