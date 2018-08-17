/* Given a non-empty array of integers, return the k most frequent elements.s
For example,
Given [1,1,1,2,2,3] and k = 2, return [1,2]
*/

// method 1: bucket
class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n: nums) {
            map.put(n, map.getOrDefault(n,0) + 1);
        }
        
        // bucket index is frequency
        List<Integer>[] bucket = new List[nums.length + 1];
        for (int n: map.keySet()) {
            int freq = map.get(n);
            if (bucket[freq] == null)
                bucket[freq] = new LinkedList<>();
            bucket[freq].add(n);
        }
        
        List<Integer> res = new LinkedList<>();
        for (int i = bucket.length-1; i > 0 && k > 0; i--){
            if (bucket[i] != null) {
                List<Integer> list = bucket[i]; 
                res.addAll(list);
                k -= list.size();
            }
        }
        
        return res;
    }
}

// method 2: max heap
class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int n: nums){
            map.put(n, map.getOrDefault(n,0) + 1);
        }
        // map value is frequency, sort by that
        PriorityQueue<Map.Entry<Integer, Integer>> maxHeap = new PriorityQueue<>(k, 
            new Comparator<Map.Entry<Integer, Integer>>(){
                @Override
                public int compare(Map.Entry<Integer, Integer> a, Map.Entry<Integer, Integer> b) {
                    return b.getValue() - a.getValue();
                }
            });
        for(Map.Entry<Integer,Integer> entry: map.entrySet()){
            maxHeap.add(entry);
        }
        
        List<Integer> res = new ArrayList<>();
        while (res.size() < k){
            Map.Entry<Integer, Integer> entry = maxHeap.poll();
            res.add(entry.getKey());
        }
        return res;
    }
}

// method 3: TreeMap
class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int n: nums){
            map.put(n, map.getOrDefault(n,0) + 1);
        }
        
        // map key is frequency, TreeMap sort by key
        TreeMap<Integer, List<Integer>> freqMap = new TreeMap<>();
        for(int num : map.keySet()){
           int freq = map.get(num);
           if(!freqMap.containsKey(freq)){
               freqMap.put(freq, new LinkedList<>());
           }
           freqMap.get(freq).add(num);
        }
        
        List<Integer> res = new ArrayList<>();
        while (res.size() < k){
            Map.Entry<Integer, List<Integer>> entry = freqMap.pollLastEntry();
            res.addAll(entry.getValue());
        }
        return res;
    }
}