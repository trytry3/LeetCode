/* Given a list of strings, return a list of strings that formed by selecting one char from each input string.
*/

// my partial solution to airbnb's onsite interview 
public static List<String> comb(List<String> items, int start) {
	if (start == items.size() - 1) {
		List<String> singleCharList = new ArrayList<>();
		for (Character c : items.get(start).toCharArray()) {
			singleCharList.add(c.toString());
		}
		return singleCharList;
	}
	List<String> next = comb(items, start + 1);
	List<String> res = new ArrayList<>();
	for (char c : items.get(start).toCharArray()) {
		for (String combined : next) {
			String newCombined = c + combined;
			res.add(newCombined);
		}
	}
	return res;
}

public static void main(String[] args) {
	String[] test = { "AB", "CDG", "EF" };
	List<String> items = Arrays.asList(test);
	List<String> res = comb(items, 0);
	System.out.println(res);
}