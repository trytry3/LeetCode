/*
Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.

Example:
Input: "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].

Note:
Although the above answer is in lexicographical order, your answer could be in any order you want.
*/

class Solution {
    Map<String, String> map = new HashMap<String, String>() {{
        put("2", "abc");
        put("3", "def");
        put("4", "ghi");
        put("5", "jkl");
        put("6", "mno");
        put("7", "pqrs");
        put("8", "tuv");
        put("9", "wxyz");
    }};
    
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        combine(res, "", digits);
        return res;
    }
    
    private void combine(List<String> res, String temp, String remainDigits) {
        if (remainDigits.length() == 0) {
            if (!temp.equals(""))
                res.add(temp);
            return;
        }
        String digit = remainDigits.substring(0, 1);
        String mappedLetters = map.get(digit);
        for (int i = 0; i < mappedLetters.length(); i++) {
            String newTemp = temp + mappedLetters.substring(i, i+1);
            combine(res, newTemp, remainDigits.substring(1));
        }
    }
}
