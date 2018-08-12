/** Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

Example:
Input: n = 4, k = 2
Output:
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
*/
class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, new ArrayList<>(), 1, n, k);
        return res;
    }
    
    private void dfs(List<List<Integer>> res, List<Integer> tempList, int start, int n, int k) {
        if (k == 0) {
            res.add(new ArrayList<>(tempList));
            return;
        }
        for (int i = start; i <= n; i++) {
            tempList.add(i);
            dfs(res, tempList, i+1, n, k-1);
            // backtrack
            tempList.remove(tempList.size() - 1);
        }   
    }
}