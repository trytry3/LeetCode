/* Given a collection of intervals, merge all overlapping intervals.

Example:
Input: [[1,4],[4,5]]
Output: [[1,5]]
*/

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */

// method 1
class Solution {
    public List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.size() == 0)
            return intervals;
        List<Interval> res = new ArrayList<>();
        // sort according to interval's start
        intervals.sort((i1, i2) -> i1.start - i2.start);
        int curStart = intervals.get(0).start;
        int curEnd = intervals.get(0).end;
        for (int i = 1; i < intervals.size(); i++) {
            // overlap
            if (intervals.get(i).start <= curEnd) {
                curEnd = Math.max(curEnd, intervals.get(i).end);
            } else {
                res.add(new Interval(curStart, curEnd));
                curStart = intervals.get(i).start;
                curEnd = intervals.get(i).end;
            }
        }
        res.add(new Interval(curStart, curEnd));  
        return res;
    }
}


// method 2
class Solution {
    public List<Interval> merge(List<Interval> intervals) {
        int n = intervals.size();
        int[] start = new int[n];
        int[] end = new int[n];
        for (int i = 0; i < n; i++) {
            start[i] = intervals.get(i).start;
            end[i] = intervals.get(i).end;
        }
        Arrays.sort(start);
        Arrays.sort(end);
        
        List<Interval> res = new ArrayList<>();
        for (int i = 0, j = 0; i < n; i++) {
            // j is to mark interval start
            if (i == n-1 || start[i+1] > end[i]) {
                res.add(new Interval(start[j], end[i]));
                j = i+1;
            }
        }
        return res;
    }
}