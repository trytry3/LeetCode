/* Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), 
find all unique combinations in candidates where the candidate numbers sums to target.
The same repeated number may be chosen from candidates unlimited number of times.
Note:
All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.

Example 1:
Input: candidates = [2,3,6,7], target = 7,
A solution set is:
[
  [7],
  [2,2,3]
]

Example 2:
Input: candidates = [2,3,5], target = 8,
A solution set is:
[
  [2,2,2,2],
  [2,3,3],
  [3,5]
]
*/

// dfs, backtracking
// same as 40. Combination Sum II.java, just change `start` in dfs step from i+1 to i
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        dfs(res, new ArrayList<>(), candidates, target, 0);
        return res;    
    }
    
    // when Set is wanted, need to pass `start` to avoid duplication
    private void dfs(List<List<Integer>> res, List<Integer> tempList, 
                     int[] candidates, int target, int start) {
        if (target == 0) {
            res.add(new ArrayList<>(tempList));
            return;
        } else if (target < 0)
            return;
        for (int i = start; i < candidates.length; i++) {
            // if the input contains duplicates, this step is to remove duplicates in result
            // if (i != start && candidates[i] == candidates[i-1])
            //     continue;
            tempList.add(candidates[i]);
            // not from i+1 because we can reuse same elements
            dfs(res, tempList, candidates, target-candidates[i], i);
            tempList.remove(tempList.size()-1);
        }
    }
}