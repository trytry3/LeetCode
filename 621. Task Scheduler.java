/** Given a char array representing tasks CPU need to do. 
It contains capital letters A to Z where different letters represent different tasks.
Tasks could be done without original order. Each task could be done in one interval. 
For each interval, CPU could finish one task or just be idle.
However, there is a non-negative cooling interval n that means between two same tasks, 
there must be at least n intervals that CPU are doing different tasks or just be idle.
You need to return the least number of intervals the CPU will take to finish all the given tasks.

Example 1:
Input: tasks = ["A","A","A","B","B","B"], n = 2
Output: 8
Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
*/

/** Thinking:
the most frequent task should need more idles than any other tasks (more empty slots for other tasks to fill in), 
so always arrange task with most frequency first.

example (1 most freq task ): 3 A, 2 B, 1 C, n = 2
arrange like this first:
A ? ? A ? ? A

example (2 most freq task): 3 A 3 B 2 C 1 D, n = 3
arrange like this first:
A B ? ? A B ? ? A B

example: 3A, 3B, 3C, 3D, 3E, n = 2
arrange like this and no idle needed
A B C D | A B C D | A B C D
*/


class Solution {
    public int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> counter = new HashMap();
        // store the max task frequency
        int maxFreq = 0;
        // store how many most frequent tasks
        int maxFreqCount = 0;
        for(char task : tasks) {
            counter.put(task, counter.getOrDefault(task, 0) + 1);
            if(counter.get(task) == maxFreq) {
                maxFreqCount++;
            } else if (counter.get(task) > maxFreq) {
                maxFreq = counter.get(task);
                maxFreqCount = 1;
            }
        }
        
        int gapsNum = maxFreq - 1;
        int gapLen = n - (maxFreqCount - 1);
        int emptySlots = gapsNum * gapLen;
        int restTasks = tasks.length - maxFreq * maxFreqCount;
        int idles = Math.max(0, emptySlots - restTasks);
        
        return tasks.length + idles;
    }
}