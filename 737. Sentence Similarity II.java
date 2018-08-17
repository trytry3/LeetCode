/* Given two sentences words1, words2 (each represented as an array of strings), and a list of similar word pairs pairs, 
determine if two sentences are similar.
For example, words1 = ["great", "acting", "skills"] and words2 = ["fine", "drama", "talent"] are similar, 
if the similar word pairs are pairs = [["great", "good"], ["fine", "good"], ["acting","drama"], ["skills","talent"]].
Note that the similarity relation is transitive. 
For example, if "great" and "good" are similar, and "fine" and "good" are similar, then "great" and "fine" are similar.
Similarity is also symmetric. 
For example, "great" and "fine" being similar is the same as "fine" and "great" being similar.
Also, a word is always similar with itself. 
For example, the sentences words1 = ["great"], words2 = ["great"], pairs = [] are similar, even though there are no specified similar word pairs.
Finally, sentences can only be similar if they have the same number of words. 
So a sentence like words1 = ["great"] can never be similar to words2 = ["doubleplus","good"].
*/

// dfs
class Solution {
    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, String[][] pairs) {
        if (words1.length != words2.length)
            return false;
        Map<String, Set<String>> map = new HashMap<>();
        for (String[] pair: pairs) {
            if (!map.containsKey(pair[0])) {
                map.put(pair[0], new HashSet<>());
            }
            if (!map.containsKey(pair[1])) {
                map.put(pair[1], new HashSet<>());
            }            
            map.get(pair[0]).add(pair[1]);
            map.get(pair[1]).add(pair[0]);
        }
        for (int i = 0; i < words1.length; i++) {
            if (words1[i].equals(words2[i]))
                continue;
            else if (!map.containsKey(words1[i]))
                return false;
            else if (!dfsFind(words1[i], words2[i], map, new HashSet<String>()))
                return false;
        }
        return true;
    }
    
    private boolean dfsFind(String source, String target, Map<String, Set<String>> map, Set<String> visited) {
        if (map.get(source).contains(target))
            return true;
        visited.add(source);
        for (String neighbor: map.get(source)) {
            if (visited.contains(neighbor))
                continue;
            else if (dfsFind(neighbor, target, map, visited))
                return true;
        }    
        return false;
    }
}