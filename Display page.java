/* You’re given an array of CSV strings representing search results. Results are sorted by a score initially. 
A given host may have several listings that show up in these results. 
Suppose we want to show 12 results per page, but we don’t want the same host to dominate the results. 
Write a function that will reorder the list so that a host shows up at most once on a page if possible, 
but otherwise preserves the ordering. 
Your program should return the new array and print out the results in blocks representing the pages.

Input: An array of csv strings, with sort score number of results per page. 
example: 
"host_id,listing_id,score,city"
"1,28,300.1,San Francisco"
*/

// airbnb
class Solution {
	public List<String> displayPages(List<String> input, int pageSize) {
		List<String> res = new ArrayList<>();
		if (input == null || input.size() == 0) {
			return res;
		}
		Set<String> itemsOnOnePage = new HashSet<>();
		Iterator<String> iter = input.iterator();
		boolean reachEnd = false;
		int countItemsOnPage = 0;
		while (iter.hasNext()) {
			String cur = iter.next();
			String hostId = cur.split(",")[0];
			// add non-duplicate host id or duplicate if exhausted input
			if (!itemsOnOnePage.contains(hostId) || reachEnd) {
				res.add(cur);
				itemsOnOnePage.add(hostId);
				// remove an item from input after being added
				iter.remove();
				countItemsOnPage++;
			}
			// start a new page
			if (countItemsOnPage == pageSize) {
				itemsOnOnePage.clear();
				reachEnd = false;
				// separate pages
				if (!input.isEmpty()) {
					res.add(" ");
				}
				// for a new page, restart searching in input
				iter = input.iterator();
				countItemsOnPage = 0;
			}
			// reach end of input
			if (!iter.hasNext()) {
				// restart searching from items left in input
				iter = input.iterator();
				reachEnd = true;
			}
		}
		return res;
	}

	public static void main(String[] args) {
		Solution s = new Solution();

		String[] input = { "1,28,310.6,SF", "4,5,204.1,SF", "20,7,203.2,Oakland", "6,8,202.2,SF", "6,10,199.1,SF",
				"1,16,190.4,SF", "6,29,185.2,SF", "7,20,180.1,SF", "6,21,162.1,SF", "2,18,161.2,SF", "2,30,149.1,SF",
				"3,76,146.2,SF", "2,14,141.1,San Jose" };
		List<String> inputList = new ArrayList<>();
		for (String item : input) {
			inputList.add(item);
		}
		List<String> res = s.displayPages(inputList, 5);
		for (String item : res)
			System.out.println(item);
	}
}