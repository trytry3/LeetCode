/* Median is the middle value in an ordered integer list. 
If the size of the list is even, there is no middle value. 
So the median is the mean of the two middle value.

For example,
[2,3,4], the median is 3
[2,3], the median is (2 + 3) / 2 = 2.5

Design a data structure that supports the following two operations:
void addNum(int num) - Add a integer number from the data stream to the data structure.
double findMedian() - Return the median of all elements so far.

Example:
addNum(1)
addNum(2)
findMedian() -> 1.5
addNum(3) 
findMedian() -> 2
 
Follow up:
If all integer numbers from the stream are between 0 and 100, how would you optimize it?
If 99% of all integer numbers from the stream are between 0 and 100, how would you optimize it?
*/

// heap
class MedianFinder {
    // the length of smaller half is kept to be n / 2 at all time,
    // and the length of the larger half is either n / 2 or n / 2 + 1 depending on n's parity
    private PriorityQueue<Integer> small; // max heap
    private PriorityQueue<Integer> large; // min heap
    private boolean even = true;

    /** initialize your data structure here. */
    public MedianFinder() {
        small = new PriorityQueue<>((a, b) -> b - a);
        large = new PriorityQueue<>();
        even = true;
    }
    
    public double findMedian() {
        if (even)
            return (small.peek() + large.peek()) / 2.0;
        else
            return large.peek();
    }

    public void addNum(int num) {
        if (even) {
            small.offer(num);
            large.offer(small.poll());
        } else {
            large.offer(num);
            small.offer(large.poll());
        }
        even = !even;
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */