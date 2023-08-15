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
                // add the previous merged interval to res
                res.add(new Interval(curStart, curEnd));
                curStart = intervals.get(i).start;
                curEnd = intervals.get(i).end;
            }
        }
        // add the last merged interval
        res.add(new Interval(curStart, curEnd));  
        return res;
    }
}


class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0)
            return intervals;
        List<int[]> res = new ArrayList<>();
        // sort according to interval's start
        Arrays.sort(intervals, (i1, i2) -> Integer.compare(i1[0], i2[0]));
        int curStart = intervals[0][0];
        int curEnd = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            // overlap
            if (intervals[i][0] <= curEnd) {
                curEnd = Math.max(curEnd, intervals[i][1]);
            } else {
                // add the previous merged interval to res
                res.add(new int[]{curStart, curEnd});
                curStart = intervals[i][0];
                curEnd = intervals[i][1];
            }
        }
        // add the last merged interval
        res.add(new int[]{curStart, curEnd});
        return res.toArray(new int[res.size()][]);
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
