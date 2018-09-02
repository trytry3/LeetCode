/* Given a function rand7 which generates a uniform random integer in the range 1 to 7, 
write a function rand10 which generates a uniform random integer in the range 1 to 10.
Do NOT use system's Math.random().

Example 1:
Input: 1
Output: [7]

Example 2:
Input: 2
Output: [8,4]

Example 3:
Input: 3
Output: [8,1,10]
*/

/**
 * The rand7() API is already defined in the parent class SolBase.
 * public int rand7();
 * @return a random integer in the range 1 to 7
 */

// method 1:
class Solution extends SolBase {
    public int rand10() {
        int row, col, idx;
        do {
            row = rand7();
            col = rand7();
            // ensure every number is equal prob 
            // e.g. idx 3 can only be produced by 3 + 0
            idx = col + (row - 1) * 7; 
        } while (idx > 40); // if > 10 rejection, will be too slow 
        // (1,2, ...9, 0) + 1
        return idx % 10 + 1;
    }
}


// method 2:
class Solution extends SolBase {
    public int rand10() {
        int n1 = rand5();
        int n2 = rand2();
        return n1 + (n2-1)*5;
    }
    
    private int rand5() {
        int n;
        do {
            n = rand7();
        } while (n > 5);
        return n;
    }
    
    private int rand2() {
        int n;
        do {
            n = rand7();
        } while (n > 2);
        return n;
    }
}