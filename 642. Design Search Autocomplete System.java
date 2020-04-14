/*
Design a search autocomplete system for a search engine. Users may input a sentence (at least one word and end with 
a special character '#'). For each character they type except '#', you need to return the top 3 historical hot sentences 
that have prefix the same as the part of sentence already typed. Here are the specific rules:
1. The hot degree for a sentence is defined as the number of times a user typed the exactly same sentence before.
2. The returned top 3 hot sentences should be sorted by hot degree (The first is the hottest one). 
   If several sentences have the same degree of hot, you need to use ASCII-code order (smaller one appears first).
3. If less than 3 hot sentences exist, then just return as many as you can.
4. When the input is a special character, it means the sentence ends, and in this case, you need to return an empty list.
Your job is to implement the following functions:

The constructor function:
AutocompleteSystem(String[] sentences, int[] times): 
This is the constructor. The input is historical data. Sentences is a string array consists of previously typed sentences. 
Times is the corresponding times a sentence has been typed. Your system should record these historical data.

Now, the user wants to input a new sentence. The following function will provide the next character the user types:
List<String> input(char c): 
The input c is the next character typed by the user. The character will only be lower-case letters ('a' to 'z'), 
blank space (' ') or a special character ('#'). Also, the previously typed sentence should be recorded in your system. 
The output will be the top 3 historical hot sentences that have prefix the same as the part of sentence already typed.

Example:
Operation: AutocompleteSystem(["i love you", "island","ironman", "i love leetcode"], [5,3,2,2])
The system have already tracked down the following sentences and their corresponding times:
"i love you" : 5 times
"island" : 3 times
"ironman" : 2 times
"i love leetcode" : 2 times

Now, the user begins another search:

Operation: input('i')
Output: ["i love you", "island","i love leetcode"]
Explanation:
There are four sentences that have prefix "i". Among them, "ironman" and "i love leetcode" have same hot degree. 
Since ' ' has ASCII code 32 and 'r' has ASCII code 114, "i love leetcode" should be in front of "ironman". 
Also we only need to output top 3 hot sentences, so "ironman" will be ignored.

Operation: input(' ')
Output: ["i love you","i love leetcode"]
Explanation:
There are only two sentences that have prefix "i ".

Operation: input('a')
Output: []
Explanation:
There are no sentences that have prefix "i a".

Operation: input('#')
Output: []
Explanation:
The user finished the input, the sentence "i a" should be saved as a historical sentence in system. 
And the following input will be counted as a new search.
*/

// using one level indexing
class AutocompleteSystem {
    // arr[0] is a map that stores the sentences starting with an 'a'
    // map's key is sentence, value is times
    private HashMap<String, Integer>[] arr;
    private String curSent = "";

    public AutocompleteSystem(String[] sentences, int[] times) {
        arr = new HashMap[26];
        for (int i = 0; i < 26; i++) 
            arr[i] = new HashMap<String, Integer>();
        for (int i = 0; i < sentences.length; i++)
            arr[sentences[i].charAt(0) - 'a'].put(sentences[i], times[i]);
    }
    
    public List<String> input(char c) {
        List<String> res = new ArrayList<>();
        if (c == '#') {
            // you can type after typing #
            // increase history by 1 since searched a complete sentence
            arr[curSent.charAt(0) - 'a'].put(
                curSent, arr[curSent.charAt(0) - 'a'].getOrDefault(curSent, 0) + 1);
            curSent = "";
        } else {
            List<Node> list = new ArrayList<>();
            curSent += c;
            for (String key : arr[curSent.charAt(0) - 'a'].keySet()) {
                if (key.indexOf(curSent) == 0) {
                    list.add(new Node(key, arr[curSent.charAt(0) - 'a'].get(key)));
                }
             }
            Collections.sort(
                list,
                (a, b) -> 
                    a.times == b.times ? a.sentence.compareTo(b.sentence) : b.times - a.times);
            for (int i = 0; i < Math.min(3, list.size()); i++) 
                res.add(list.get(i).sentence);
        }
        return res;
    }
}

class Node {
  String sentence;
  int times;

  Node(String st, int t) {
    sentence = st;
    times = t;
  }
}


/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */
