/* Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.

Example:
MovingAverage m = new MovingAverage(3);
m.next(1) = 1
m.next(10) = (1 + 10) / 2
m.next(3) = (1 + 10 + 3) / 3
m.next(5) = (10 + 3 + 5) / 3
*/

class MovingAverage {
    private int[] window;
    private double sum; // to make average double, not int
    private int n, insertIndex;

    public MovingAverage(int size) {
        window = new int[size];
        sum = 0; 
        n = 0; 
        insertIndex = 0;
    }
    
    public double next(int val) {
        if (n < window.length)
            n++;
        insertIndex = (insertIndex + 1) % window.length;    
        sum = sum + val - window[insertIndex];
        window[insertIndex] = val;
        return sum/n;
    }
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */