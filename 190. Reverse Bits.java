/* Reverse bits of a given 32 bits unsigned integer.

Example:
Input: 43261596
Output: 964176192
Explanation: 43261596 represented in binary as 00000010100101000001111010011100, 
             return 964176192 represented in binary as 00111001011110000010100101000000.
Follow up:
If this function is called many times, how would you optimize it?
*/

public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            result += n & 1;
            n >>>= 1;   // unsigned shift
            if (i < 31) // shift left except for last digit
                result <<= 1;
        }
        return result;
    }
}

// follow up idea: 
// We can divide the int into 4 bytes, reverse each byte then combine them into one int. 
// For each byte, we can use cache to store and get the reversed value.