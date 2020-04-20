/* Given an array of ints and target value, output the numbers that add up to target 
Note: numbers and target can be <= 0, numbers has no duplicates
example: [1,2,3,4,5] and 6 => [[1,2,3],[2,4],[5,1]] */

// O(2^n)
class Solution {
  
  public static List<List<Integer>> find(int[] array, int k) {
    List<List<Integer>> res = new ArrayList<>();
    dfs(res, new ArrayList<>(), 0, array, k);
    return res;   
  }
  
  private static void dfs(List<List<Integer>> res, List<Integer> tempList, int start, int[] array, int remainder) {
    if (remainder == 0) {
      res.add(new ArrayList<>(tempList));
      // no return, since numbers can contain 0, and want 0 to be included in result
    } 
    
    for (int i = start; i < array.length; i++) {
      int cur = array[i];
      int newRemainder = remainder - cur;
      tempList.add(cur);
      dfs(res, tempList, i+1, array, newRemainder);
      tempList.remove(tempList.get(tempList.size()-1));
    } 
  }
  
  public static void main(String[] args) {
    int[]array = {-1,-2,3,0,4,5};
    int k = -3;
    List<List<Integer>> res = find(array, k);
    System.out.println(res);  
    // [[-1, -2], [-1, -2, 0]]  
  }
}
