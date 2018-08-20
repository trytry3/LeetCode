/* Given a set of distinct integers, nums, return all possible subsets (the power set).
Note: The solution set must not contain duplicate subsets.

Example:
Input: nums = [1,2,3]
Output:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
*/

// method 1: dfs, backtracking
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        // no need to sort
        dfs(res, new ArrayList<>(), nums, 0);
        return res;     
    }
    
    private void dfs(List<List<Integer>> res, List<Integer> tempList, int[] nums, int start) {
        res.add(new ArrayList<>(tempList));
        for (int i = start; i < nums.length; i++) {
            tempList.add(nums[i]);
            // note: start from i+1 since it's Set
            dfs(res, tempList, nums, i+1);
            tempList.remove(tempList.size()-1);
        }   
    }
}

// method 2: bit manipulation
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    int n = nums.length;
    Arrays.sort(nums);
    // 1 << n is 2^n
    // each subset equals to an binary integer between 0 .. 2^n - 1 
    // 0 -> 000 -> []
    // 1 -> 001 -> [1]
    // 2 -> 010 -> [2]
    // ..
    // 7 -> 111 -> [1,2,3]
    for (int i = 0; i < (1 << n); i++) {
      List<Integer> subset = new ArrayList<>();
      for (int j = 0; j < n; j++) {
        // check whether the jth digit of i's binary representation is 1
        // e.g. 110 & 10 != 0, so 2nd digit of 110 is 1
        if ((i & (1 << j)) != 0) {
          subset.add(nums[j]);
        }
      }
      result.add(subset);
    }
    return result;
  }
}