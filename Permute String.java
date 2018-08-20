class Solution {
	public static List<String> permuteUnique(String word) {
		// sort
		char[] chars = stringToSortedArray(word);
		List<String> result = new ArrayList<>();
		dfs(result, new StringBuilder(), chars, new boolean[chars.length]);
		return result;
	}

	public static void dfs(List<String> result, StringBuilder tempString, char[] chars, boolean[] visited) {
		if (tempString.length() == chars.length) {
			result.add(tempString.toString());
			return;
		}
		for (int i = 0; i < chars.length; i++) {
			if (visited[i] == true || (i > 0 && chars[i] == chars[i - 1] && !visited[i - 1]))
				continue;
			visited[i] = true;
			tempString.append(chars[i]);
			dfs(result, tempString, chars, visited);
			// backtrack
			tempString = tempString.deleteCharAt(tempString.length() - 1);
			visited[i] = false;
		}
	}

	private static char[] stringToSortedArray(String str) {
		char[] array = str.toCharArray();
		Arrays.sort(array);
		return array;
	}

	public static void main(String[] args) {
		List<String> test = permuteUnique("abcc");
		for (String str : test) {
			System.out.println(str);
		}
	}
}