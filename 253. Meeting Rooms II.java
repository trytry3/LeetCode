/* Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.
Example 1:
Input: [[0, 30],[5, 10],[15, 20]]
Output: 2
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

// newest version
class Solution {
    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0)
            return 0;
        
        // sort interval starts
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        
        // store intervals with sorted ends
        // one item in heap needs one room
        PriorityQueue<int[]> heap = new PriorityQueue<int[]>((a, b) -> a[1] - b[1]);
        
        heap.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            int[] intervalWithEarliestEnd = heap.poll();
            if (intervals[i][0] < intervalWithEarliestEnd[1]) {// overlap, start a new room
                heap.add(intervals[i]);
                // add back the polled original room
                heap.add(intervalWithEarliestEnd);
            } else {
                // original room was polled, add the current interval
                // now the new earliest end is current interval's end
                heap.add(intervals[i]);
            }
        }
        return heap.size();
    } 
}


// method 1
class Solution {
    public int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length == 0)
            return 0;
        // sort by interval start
        Arrays.sort(intervals, new Comparator<Interval>(){
            @Override
            public int compare(Interval i1, Interval i2) {
                return i1.start - i2.start;
            }
        });
        // sort by interval end
        PriorityQueue<Interval> heap = new PriorityQueue(intervals.length, new Comparator<Interval>(){
            @Override
            public int compare(Interval i1, Interval i2) {
                return i1.end - i2.end;
            }
        });
        // one item in heap represent one non-conflict timeline
        heap.offer(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            // earliest finish time(room)
            Interval curEnd = heap.poll();
            if (intervals[i].start < curEnd.end) {
                heap.offer(intervals[i]);
            } else {
                // no overlap, merge into current interval
                curEnd.end = intervals[i].end;
            }
            heap.offer(curEnd);
        }
        return heap.size();
    }
}

// method 2
class Solution {
    public int minMeetingRooms(Interval[] intervals) {
        int n = intervals.length;
        int[] start = new int[n];
        int[] end = new int[n];
        for (int i = 0; i < n; i++) {
            start[i] = intervals[i].start;
            end[i] = intervals[i].end;
        }
        Arrays.sort(start);
        Arrays.sort(end);
        int count = 0;
        int j = 0; // track end
        for (int i = 0; i < n; i++) {
            if (start[i] < end[j]) {
                count++;
            } else {
                j++;
            }
        }
        return count;
    }
}
