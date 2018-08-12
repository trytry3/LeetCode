// Method 1: hash table + double linked list
// least recently used put in the front of the list, so that it can be removed first
// use double linked to make operation O(1)
class LRUCache {
    private int capacity;
    private Map<Integer, Node> map;
    // dummy nodes to mark head and tail
    Node head;
    Node tail;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new Node(0,0);
        tail = new Node(0,0);
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        // recently used, remove from current position and move to tail
        Node cur = map.get(key);
        cur.prev.next = cur.next;
        cur.next.prev = cur.prev;
        moveToTail(cur);
        return cur.value;
    }
    
    public void put(int key, int value) {
        // key already exists, insert won't affect map size
        // have to use `get` instead of `map.containsKey`, because need to update newest
        if (get(key) != -1) {
            map.get(key).value = value;
            return;
        }
        if (map.size() == capacity) {
            // remove lru node
            map.remove(head.next.key);
            head.next = head.next.next;
            head.next.prev = head;
        }
        Node insert = new Node(key, value);
        map.put(key, insert);
        moveToTail(insert);
    }
    
    private void moveToTail(Node cur) {
        cur.prev = tail.prev;
        cur.next = tail;  
        tail.prev = cur;
        cur.prev.next = cur;
    }
    
    class Node {
        Node prev;
        Node next;
        int key;
        int value;
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.prev = null;
            this.next = null;
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */


// Method 2: Java's LinkedHashMap
class LRUCache {
    private LinkedHashMap<Integer, Integer> map;
    
    public LRUCache(int capacity) {
        map = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true){
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > capacity;
            }
        };
    }
    
    public int get(int key) {
        return map.getOrDefault(key, -1);
    }
    
    public void put(int key, int value) {
        map.put(key, value);
    }
}