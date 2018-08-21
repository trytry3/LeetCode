/* Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
Note: For the purpose of this problem, we define empty string as valid palindrome.

Example 1:
Input: "A man, a plan, a canal: Panama"
Output: true

Example 2:
Input: "race a car"
Output: false
*/

class Solution {
    public boolean isPalindrome(String s) {
        int start = 0, end = s.length()-1;
        while (start < end) {
            while (start < end && !Character.isLetterOrDigit(s.charAt(start)))
                start++;
            while (end > start && !Character.isLetterOrDigit(s.charAt(end)))
                end--;
            if (Character.toLowerCase(s.charAt(start)) != Character.toLowerCase(s.charAt(end))) 
                return false;
            start++;
            end--;
        }
        return true;
    }
}