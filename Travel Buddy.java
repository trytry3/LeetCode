/* 每个人都有一些想去的city，如果你想去的city和另一个人想去的city的相似度高于50%的话你们就是travel buddy,
output一个list of travel buddy按相似度从高往低排序。

followup是给了一个max值，找出你的buddy的wishlist里不在你的wishlist里的最多max个城市，根据buddy和你的重合程度来排序。
例如 你的wishlist是 a,b,c,d
buddy1 的wishlist 是 a,b,e,f, 有两个和你的一样，所以是你的buddy
buddy2 的wishlist 是 a,c,d,g, 有三个和你的一样，也是你的budy
问题是输出一个size最多为max的推荐城市列表
当size为10时，buddy1和buddy2的wishlist中不在你的wishlist中的城市都可以加入推荐中，因为buddy2的重合度更高，
所以先输出buddy2中的，所以推荐为 g,e,f.
当size为2时，推荐是g,e 或 g,f
*/

// airbnb
class Solution {
	private List<Buddy> buddies;
	private Set<String> myWishList;

	public Solution(Set<String> myWishList, Map<String, Set<String>> friendsWishList) {
		this.buddies = new ArrayList<>();
		this.myWishList = myWishList;
		for (String name : friendsWishList.keySet()) {
			Set<String> wishList = friendsWishList.get(name);
			Set<String> intersection = new HashSet<>(wishList);
			intersection.retainAll(myWishList);
			int similarity = intersection.size();
			if (similarity >= wishList.size() / 2) {
				buddies.add(new Buddy(name, similarity, wishList));
			}
		}
	}

	public List<Buddy> getSortedBuddies() {
		// larger similarity comes first
		Collections.sort(buddies);
		List<Buddy> sortedBuddies = new ArrayList<>(buddies);
		return sortedBuddies;
	}

	public List<String> recommendCities(int k) {
		List<String> res = new ArrayList<>();
		List<Buddy> buddies = getSortedBuddies();
		int i = 0;
		while (k > 0 && i < buddies.size()) {
			Set<String> diff = new HashSet<>(buddies.get(i).wishList);
			diff.removeAll(myWishList);
			if (diff.size() <= k) {
				res.addAll(diff);
				k -= diff.size();
				i++;
			} else {
				Iterator<String> it = diff.iterator();
				while (k > 0) {
					res.add(it.next());
					k--;
				}
			}
		}
		return res;
	}

	class Buddy implements Comparable<Buddy> {
		String name;
		int similarity;
		Set<String> wishList;

		public Buddy(String name, int similarity, Set<String> wishList) {
			this.name = name;
			this.similarity = similarity;
			this.wishList = wishList;
		}

		@Override
		public int compareTo(Buddy b) {
			return b.similarity - this.similarity;
		}
	}
}
