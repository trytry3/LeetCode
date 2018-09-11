// bfs
class Solution {
	public static void main(String[] args) {
		String rootDir = "/Users";
		Queue<File> queue = new LinkedList<>();
		queue.add(new File(rootDir));
		while (!queue.isEmpty()) {
			File current = queue.poll();
			File[] listOfFilesAndDir = current.listFiles();
			if (listOfFilesAndDir != null) {
				for (File file : listOfFilesAndDir) {
					if (file.isDirectory())
						queue.offer(file);
					else
						System.out.println(file);
				}
			}
		}
	}
}