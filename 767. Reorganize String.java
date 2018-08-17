/* Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.
If possible, output any possible result.  If not possible, return the empty string.

Example 1:
Input: S = "aab"
Output: "aba"

Example 2:
Input: S = "aaab"
Output: ""
*/

// see 358. Rearrange String k Distance Apart

class Solution {
    public String reorganizeString(String s) {
        return rearrangeStringK(s, 2);
    }
    
    // each char k distance apart
    private String rearrangeStringK(String str, int k) {
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