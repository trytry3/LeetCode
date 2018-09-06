/* Given a string, we can "shift" each of its letter to its successive letter, 
for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:
"abc" -> "bcd" -> ... -> "xyz"
Given a list of strings which contains only lowercase alphabets, 
group all strings that belong to the same shifting sequence.

Example:
Input: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"],
Output: 
[
  ["abc","bcd","xyz"],
  ["az","ba"],
  ["acef"],
  ["a","z"]
]
*/

class Solution {
    public List<List<String>> groupStrings(String[] strs) {
        // key is the char distance pattern for a string, 
        // value is the list of strings that match that pattern
	    Map<String, List<String>> map = new HashMap<>(); 	
	    for (String str : strs) {
		    String key = "";
		    for (int i = 1; i < str.length(); i++) {
                // char distance 
			    key += (str.charAt(i) - str.charAt(i-1) + 26) % 26;
                key += " ";
            }
		    if (!map.containsKey(key)) 
                map.put(key, new ArrayList<>());
		    map.get(key).add(str);    		
	    } 
	    return new ArrayList<>(map.values());
    }
}