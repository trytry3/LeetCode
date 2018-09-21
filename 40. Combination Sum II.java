/* Given a collection of candidate numbers (candidates) and a target number (target), 
find all unique combinations in candidates where the candidate numbers sums to target.
Each number in candidates may only be used once in the combination.
Note:
All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.

Example 1:
Input: candidates = [10,1,2,7,6,1,5], target = 8,
A solution set is:
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]
*/

// dfs, backtracking
// see 90. Subsets II
class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
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
            if (i != start && candidates[i] == candidates[i-1])
                continue;
            tempList.add(candidates[i]);
            dfs(res, tempList, candidates, target-candidates[i], i+1);
            tempList.remove(tempList.size()-1);
        }
    }
}