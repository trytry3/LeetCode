/* Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.
You have the following 3 operations permitted on a word:
Insert a character
Delete a character
Replace a character

Example 1:
Input: word1 = "horse", word2 = "ros"
Output: 3
Explanation: 
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')
*/

class Solution {
    public int minDistance(String word1, String word2) {
        if (word1 == null || word2 == null)
            return 0;
        int len1 = word1.length();
        int len2 = word2.length();
        // store min steps to convert first i chars in word1 to first j chars in word2
        int[][] minSteps = new int[len1+1][len2+1];
        for (int i = 0; i <= len1; i++)
            minSteps[i][0] = i;
        for (int j = 0; j <= len2; j++)
            minSteps[0][j] = j;
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    minSteps[i][j] = minSteps[i-1][j-1]; 
                } else {
                    minSteps[i][j] = 1 + Math.min(minSteps[i-1][j-1], Math.min(minSteps[i-1][j], minSteps[i][j-1]));
                } 
            }
        }
        return minSteps[len1][len2]; 
    }
}