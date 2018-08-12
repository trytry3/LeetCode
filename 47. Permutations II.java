class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        dfs(res, new ArrayList<Integer>(), nums, new boolean[nums.length]);
        return res;
    }
    
    private void dfs(List<List<Integer>> res, List<Integer> tempList, int[] nums, boolean[] used) {
        if (tempList.size() == nums.length) {
            res.add(new ArrayList<Integer>(tempList));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i] || i > 0 && nums[i] == nums[i-1] && !used[i-1]) 
                // when used[i-1]==false and nums[i]==nums[i-1], it's in backtrack step, don't add the ith element again
                // because the duplicate (ith element) already added once in the non-backtrack step
                continue;
            tempList.add(nums[i]);
            used[i] = true;
            dfs(res, tempList, nums, used);
            tempList.remove(tempList.size() - 1);
            used[i] = false;
        }
    }
}