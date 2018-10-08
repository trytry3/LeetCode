/* Find all the combinations of a string in lowercase and uppercase. 
For example, string "ab" -> "ab", "Ab", "aB", "AB". So, you will have 2^n (n = length of string) output strings. 
The goal is for you to test each of these strings and see if it matchs a hidden string.
*/

// airbnb
class Solution {
	private boolean isBitSet(int n, int offset) {
		return (n >> offset & 1) != 0;
	}

	public List<String> enumerateString(String text) {
		List<String> res = new ArrayList<>();
		if (text == null || text.length() == 0) {
			return res;
		}
		char[] chars = text.toCharArray();
		int len = chars.length;
		int n = (int) Math.pow(2, len);
		for (int i = 0; i < n; i++) {
			char[] curr = new char[len];
			for (int j = 0; j < len; j++) {
				curr[j] = (isBitSet(i, j)) ? Character.toUpperCase(chars[j]) : chars[j];
			}
			res.add(new String(curr));
		}
		return res;
	}

	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println(s.enumerateString("abcd"));
	}
}
