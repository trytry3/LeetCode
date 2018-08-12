/** Given a non-empty string s and an integer k, rearrange the string such that the same characters are at least distance k from each other.
All input strings are given in lowercase letters. If it is not possible to rearrange the string, return an empty string "".

Example 1:
s = "aabbcc", k = 3
Result: "abcabc"

Example 2:
s = "aaabc", k = 3 
Answer: ""
*/

// choose most frequent char first, so that more gaps can be utilized by other chars
class Solution {
    public String rearrangeString(String str, int k) {
        StringBuilder rearranged = new StringBuilder();
        // store frequency of each char
        Map<Character, Integer> map = new HashMap<>();
        for (char c : str.toCharArray()) {
            int count = 1;
            if (map.containsKey(c)) {
                count = map.get(c) + 1;
            }
            map.put(c, count);
        }
        
        // max to min char frequency, store all the chars
        Queue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<>(
            new Comparator<Map.Entry<Character, Integer>>() {
                @Override
                public int compare(Map.Entry<Character, Integer> e1, Map.Entry<Character, Integer> e2) {
                    return e2.getValue() - e1.getValue();
                }
            });
        
        // store one segment of chars (k apart) added to the rearranged str
        Queue<Map.Entry<Character, Integer>> queue = new LinkedList<>();
        maxHeap.addAll(map.entrySet());
        
        while (!maxHeap.isEmpty()) {   
            Map.Entry<Character, Integer> cur = maxHeap.poll();
            rearranged.append(cur.getKey());
            cur.setValue(cur.getValue() - 1);
            queue.offer(cur);
            
            if (queue.size() < k)
                continue;
            
            // release from queue if char is already k apart, that char can be reused again
            Map.Entry<Character, Integer> nextUse = queue.poll();
            // if that char is the last one of its kind, no need to add to maxHeap since it's used up
            if (nextUse.getValue() > 0) {
                maxHeap.offer(nextUse);
            }
        }
        
        return rearranged.length() == str.length() ? rearranged.toString() : "";
    }
    
}