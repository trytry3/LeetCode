/* Write a function that takes a string as input and returns the string reversed.

Example 1:
Input: "hello"
Output: "olleh"

Example 2:
Input: "A man, a plan, a canal: Panama"
Output: "amanaP :lanac a ,nalp a ,nam A"
*/

class Solution {
    public String reverseString(String s) {
        char[] word = s.toCharArray();
        int i = 0, j = s.length() - 1;
        while (i < j) {
            char temp = word[i];
            word[i] = word[j];
            word[j] = temp;
            i++; 
            j--;
        }
        return new String(word);
    }
}