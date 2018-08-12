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