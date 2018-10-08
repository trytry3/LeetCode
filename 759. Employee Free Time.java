/* We are given a list schedule of employees, which represents the working time for each employee.
Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.
Return the list of finite intervals representing common, positive-length free time for all employees, also in sorted order.

Example 1:
Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
Output: [[3,4]]
Explanation:
There are a total of three employees, and all common
free time intervals would be [-inf, 1], [3, 4], [10, inf].
We discard any intervals that contain inf as they aren't finite.

Example 2:
Input: schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
Output: [[5,6],[7,9]]

Also, we wouldn't include intervals like [5, 5] in our answer, as they have zero length.

Note:
schedule and schedule[i] are lists with lengths in range [1, 50].
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

// compare 253. Meeting Rooms II
class Solution {
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> res = new ArrayList<>();
        PriorityQueue<Interval> minHeap = new PriorityQueue<>((a, b) -> a.start - b.start);
        schedule.forEach(list -> minHeap.addAll(list));
        int prevEnd = minHeap.poll().end;
        while (!minHeap.isEmpty()) {
            Interval cur = minHeap.poll();
            if (cur.start > prevEnd) 
                res.add(new Interval(prevEnd, cur.start));
            if (cur.end > prevEnd)
                prevEnd = cur.end;
        }
        return res;
    }
}