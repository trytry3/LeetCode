/* Given two integers representing the numerator and denominator of a fraction, 
return the fraction in string format.
If the fractional part is repeating, enclose the repeating part in parentheses.

Example 1:
Input: numerator = 1, denominator = 2
Output: "0.5"

Example 2:
Input: numerator = 2, denominator = 1
Output: "2"

Example 3:
Input: numerator = 2, denominator = 3
Output: "0.(6)"
*/

class Solution {
	public String fractionToDecimal(int numerator, int denominator) {
		if (numerator == 0) {
			return "0";
		}
		StringBuilder fraction = new StringBuilder();
		// either one is negative (not both)
		if (numerator < 0 ^ denominator < 0) {
			fraction.append("-");
		}
		// convert to long, otherwise abs(-2147483648) overflows
		long dividend = Math.abs(Long.valueOf(numerator));
		long divisor = Math.abs(Long.valueOf(denominator));
		fraction.append(String.valueOf(dividend / divisor));
		long remainder = dividend % divisor;
		if (remainder == 0) {
			return fraction.toString();
		}
		fraction.append(".");
		Map<Long, Integer> map = new HashMap<>();
		while (remainder != 0) {
			if (map.containsKey(remainder)) {
				fraction.insert(map.get(remainder), "(");
				fraction.append(")");
				break;
			}
            // key is the remainder, value is the position of starting repeating if any
			map.put(remainder, fraction.length());
			remainder *= 10;
			fraction.append(String.valueOf(remainder / divisor));
			remainder %= divisor;
		}
		return fraction.toString();
	}
}