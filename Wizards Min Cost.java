
// dijkstra
// airbnb
class Solution {
	private class Wizard implements Comparable<Wizard> {
		int distFrom0;
		int id;

		public Wizard(int d, int id) {
			this.distFrom0 = d;
			this.id = id;
		}

		public int compareTo(Wizard w) {
			return this.distFrom0 - w.distFrom0;
		}
	}

	public int getMinCost(List<List<Integer>> list) {
		HashSet<Integer> visited = new HashSet<>();
		PriorityQueue<Wizard> pq = new PriorityQueue<>();

		pq.offer(new Wizard(0, 0));
		// because of double direction, avoid going backwards
		visited.add(0);
		while (!pq.isEmpty()) {
			Wizard wizard = pq.poll();
			visited.add(wizard.id);
			if (wizard.id == 9) {
				return wizard.distFrom0;
			}
			for (int i : list.get(wizard.id)) {
				if (!visited.contains(i)) {
					pq.add(new Wizard(wizard.distFrom0 + (i - wizard.id) * (i - wizard.id), i));
				}
			}
		}

		return Integer.MAX_VALUE;
	}

	public static void main(String[] args) {
		Solution s = new Solution();
		List<List<Integer>> list = new ArrayList<>();
		// list[i] is wizard i's neighbors
		list.add(Arrays.asList(1, 4, 9));
		list.add(Arrays.asList(2));
		list.add(Arrays.asList(3));
		list.add(Arrays.asList(2));
		list.add(Arrays.asList(5));
		list.add(Arrays.asList(6));
		list.add(Arrays.asList(7));
		list.add(Arrays.asList(8));
		list.add(Arrays.asList(9));
		System.out.println(list);
		System.out.println(s.getMinCost(list));
	}
}
