/* Compare two version numbers version1 and version2.
If version1 > version2 return 1; if version1 < version2 return -1;otherwise return 0.

You may assume that the version strings are non-empty and contain only digits and the . character.
The . character does not represent a decimal point and is used to separate number sequences.
For instance, 2.5 is not "two and a half" or "half way to version three", 
it is the fifth second-level revision of the second first-level revision.

Example 1:
Input: version1 = "0.1", version2 = "1.1"
Output: -1

Example 2:
Input: version1 = "1.0.1", version2 = "1"
Output: 1

Example 3:
Input: version1 = "7.5.2.4", version2 = "7.5.3"
Output: -1
*/
class Solution {
    public int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        int len1 = v1.length;
        int len2 = v2.length;
        int[] v1Int = new int[len1];
        int[] v2Int = new int[len2];
        for (int i = 0; i < len1; i++) {
            v1Int[i] = Integer.valueOf(v1[i]);
        }
        for (int i = 0; i < len2; i++) {
            v2Int[i] = Integer.valueOf(v2[i]);
        }
        int minLen = Math.min(len1, len2);
        for (int i = 0; i < minLen; i++) {
            if (v1Int[i] < v2Int[i])
                return -1;
            else if (v1Int[i] > v2Int[i])
                return 1;
            else
                continue;
        }
        // previous are all the same
        if (len1 > len2) {
            for (int i = len2; i < len1; i++) {
                if (v1Int[i] != 0)
                    return 1;
            }
            // the longer part are all 0
            return 0;
        } else if (len1 < len2) {
            for (int i = len1; i < len2; i++) {
                if (v2Int[i] != 0)
                    return -1;
            }
            return 0;
        } else 
            return 0;  
    }
}