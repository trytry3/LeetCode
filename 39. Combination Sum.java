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
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(candidates == null || candidates.length == 0) 
            return res;
        Arrays.sort(candidates); // important
        dfs(res, new ArrayList<>(), candidates, target, 0);
        return res;
    }
    
    private void dfs(List<List<Integer>> res, List<Integer> tempList, int[] candidates, 
                     int target, int start) {
        if (target < 0)
            return;
        if (target == 0) {
            res.add(new ArrayList<Integer>(tempList));
            return;
        }
        int prev = -1;
        for(int i = start; i < candidates.length; i++) {
            // if the input contains duplicates, this step is to remove duplicates in result
            if (prev != -1 && candidates[i] == prev) 
                continue;
            tempList.add(candidates[i]);
            // not from i+1 because we can reuse same elements
            dfs(res, tempList, candidates, target-candidates[i], i); 
            tempList.remove(tempList.size() - 1);
            prev = candidates[i];
        }
    }
}