/* Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).
Note: The solution set must not contain duplicate subsets.
Example:
Input: [1,2,2]
Output:
[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
*/

// dfs, backtracking
class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        dfs(res, new ArrayList<>(), nums, 0);
        return res;
    }
    
    private void dfs(List<List<Integer>> res, List<Integer> tempList, 
                     int[] nums, int start) {
        res.add(new ArrayList<>(tempList));
        for (int i = start; i < nums.length; i++) {
            // if at the start position, no need to check the element before start
            if (i != start && nums[i] == nums[i-1])
                continue;
            tempList.add(nums[i]);
            dfs(res, tempList, nums, i+1);
            tempList.remove(tempList.size()-1);
        }
    }
}