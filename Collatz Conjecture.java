// airbnb
class Solution {

	Map<Integer, Integer> map = new HashMap<>();

	private int findSteps(int num) {
		if (num <= 1)
			return 1;
		if (map.containsKey(num))
			return map.get(num);
		if (num % 2 == 0)
			return 1 + findSteps(num / 2);
		return 1 + findSteps(3 * num + 1);
	}

	public int findLongestSteps(int num) {
		if (num < 1)
			return 0;
		int res = 0;
		for (int i = 1; i <= num; i++) {
			int t = findSteps(i);
			if (!map.containsKey(i))
				map.put(i, t);
			res = Math.max(res, t);
		}
		return res;
	}

	public static void main(String[] args) {
		Solution s = new Solution();
		int test = 7;
		System.out.println(s.findSteps(test));
		System.out.println(s.findLongestSteps(test));
	}
}