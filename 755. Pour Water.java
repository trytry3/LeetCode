/* We are given an elevation map, heights[i] representing the height of the terrain at that index. 
The width at each index is 1. After V units of water fall at index K, how much water is at each index?
Water first drops at index K and rests on top of the highest terrain or water at that index. 
Then, it flows according to the following rules:
If the droplet would eventually fall by moving left, then move left.
Otherwise, if the droplet would eventually fall by moving right, then move right.
Otherwise, rise at it's current position.
Here, "eventually fall" means that the droplet will eventually be at a lower level if it moves in that direction. 
Also, "level" means the height of the terrain plus any water in that column.
We can assume there's infinitely high terrain on the two sides out of bounds of the array. 
Also, there could not be partial water being spread out evenly on more than 1 grid block 
- each unit of water has to be in exactly one block.

Example 1:
Input: heights = [1,2,3,4], V = 2, K = 2
Output: [2,3,3,4]
Explanation:
The last droplet settles at index 1, since moving further left would not cause it to eventually fall to a lower height.

Example 2:
Input: heights = [3,1,3], V = 5, K = 1
Output: [4,4,4]
*/

class Solution {
    public int[] pourWater(int[] heights, int V, int K) {
        if (heights == null || heights.length == 0 || V == 0) 
            return heights;
        
        int index;
        // handle drop after drop
        while (V > 0) {
            index = K;
            // flow left
            for (int i = K - 1; i >= 0; i--) {
                // cannot flow any further
                if (heights[i] > heights[index]) {
                    break;
                } else if (heights[i] < heights[index]) {
                    index = i;
                }
            }
            // if can flow left, flow left first
            if (index != K) {
                heights[index]++;
                V--;
                continue;
            }
            // flow right
            for (int i = K + 1; i < heights.length; i++) {
                if (heights[i] > heights[index]) {
                    break;
                } else if (heights[i] < heights[index]) {
                    index = i;
                }
            }
            // index is > K or K
            heights[index]++;
            V--;
        }
        return heights;
    }
}