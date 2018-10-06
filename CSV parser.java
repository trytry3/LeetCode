/* Input: csvformat
John,Smith,john.smith@gmail.com,Los Angeles,1
Jane,Roberts,janer@msn.com,"San Francisco, CA",0
"Alexandra ""Alex""",Menendez,alex.menendez@gmail.com,Miami,1 """Alexandra Alex"""

Output: escaped string
John|Smith|john.smith@gmail.com|Los Angeles|1 
Jane|Roberts|janer@msn.com|San Francisco, CA|0
Alexandra "Alex"|Menendez|alex.menendez@gmail.com|Miami|1 "Alexandra Alex"
*/

// airbnb
class Solution {

	public String parseCSV(String str) {
		List<String> res = new ArrayList<>();
		boolean inQuote = false;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			if (!inQuote) {
				if (str.charAt(i) == '\"') {
					inQuote = true;
				} else if (str.charAt(i) == ',') {
					res.add(sb.toString());
					sb.setLength(0);
				} else {
					sb.append(str.charAt(i));
				}
			} else {
				if (str.charAt(i) == '\"') {
					// quotes in quotes, it must have two quotation marks
					if (i < str.length() - 1 && str.charAt(i + 1) == '\"') {
						sb.append("\"");
						i++;
					} else {
						inQuote = false;
					}
				} else {
					sb.append(str.charAt(i));
				}
			}
		}
		// add last word
		if (sb.length() > 0) {
			res.add(sb.toString());
		}
		return String.join("|", res);
	}

	public static void main(String[] args) {
		Solution s = new Solution();
		String test1 = "John,Smith,john.smith@gmail.com,Los Angeles,1";
		System.out.println(s.parseCSV(test1));
		String test2 = "Jane,Roberts,janer@msn.com,\"San Francisco, CA\",0";
		System.out.println(s.parseCSV(test2));
		String test3 = "\"Alexandra \"\"Alex, test\"\"\",Menendez,alex.menendez@gmail.com,Miami,1 \"\"\"Alexandra Alex\"\"\"";
		System.out.println(s.parseCSV(test3));
	}
}
