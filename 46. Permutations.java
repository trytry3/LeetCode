class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, new ArrayList<Integer>(), nums);
        return res;
    }
    
    private void dfs(List<List<Integer>> res, List<Integer> tempList, int[] nums) {
        if (tempList.size() == nums.length) {
            res.add(new ArrayList<Integer>(tempList));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (tempList.contains(nums[i]))
                continue;
            tempList.add(nums[i]);
            dfs(res, tempList, nums);
            // backtrack
            tempList.remove(tempList.size() - 1);
        }
    }
}