/* Given an input string, reverse the string word by word.

Example:  
Input: "the sky is blue",
Output: "blue is sky the".
Note:
A word is defined as a sequence of non-space characters.
Input string may contain leading or trailing spaces. However, your reversed string should not contain leading or trailing spaces.
You need to reduce multiple spaces between two words to a single space in the reversed string.
*/

class Solution {
    public String reverseWords(String s) {
        String[] words = s.split("\\s");
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= words.length; i++){
            if(words[words.length - i].equals("")){
                continue;
            }
            sb.append(words[words.length - i]);
            sb.append(" ");
        }
        return sb.toString().trim();
    }
}