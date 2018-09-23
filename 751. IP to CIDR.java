/* Given a start IP address ip and a number of ips we need to cover n, 
return a representation of the range as a list (of smallest possible length) of CIDR blocks.

A CIDR block is a string consisting of an IP, followed by a slash, 
and then the prefix length. For example: "123.45.67.89/20". 
That prefix length "20" represents the number of common prefix bits in the specified range.

Example 1:
Input: ip = "255.0.0.7", n = 10
Output: ["255.0.0.7/32","255.0.0.8/29","255.0.0.16/32"]
Explanation:
The initial ip address, when converted to binary, looks like this (spaces added for clarity):
255.0.0.7 -> 11111111 00000000 00000000 00000111
The address "255.0.0.7/32" specifies all addresses with a common prefix of 32 bits to the given address,
ie. just this one address.
The address "255.0.0.8/29" specifies all addresses with a common prefix of 29 bits to the given address:
255.0.0.8 -> 11111111 00000000 00000000 00001000

Addresses with common prefix of 29 bits are:
11111111 00000000 00000000 00001000
11111111 00000000 00000000 00001001
11111111 00000000 00000000 00001010
11111111 00000000 00000000 00001011
11111111 00000000 00000000 00001100
11111111 00000000 00000000 00001101
11111111 00000000 00000000 00001110
11111111 00000000 00000000 00001111

The address "255.0.0.16/32" specifies all addresses with a common prefix of 32 bits to the given address,
ie. just 11111111 00000000 00000000 00010000.

In total, the answer specifies the range of 10 ips starting with the address 255.0.0.7 .

There were other representations, such as:
["255.0.0.7/32","255.0.0.8/30", "255.0.0.12/30", "255.0.0.16/32"],
but our answer was the shortest possible.

Also note that a representation beginning with say, "255.0.0.7/30" would be incorrect,
because it includes addresses like 255.0.0.4 = 11111111 00000000 00000000 00000100 
that are outside the specified range.

Note:
1. ip will be a valid IPv4 address.
2. Every implied address ip + x (for x < n) will be a valid IPv4 address.
3. n will be an integer in the range [1, 1000].
*/

// bit manipulation
class Solution {
    public List<String> ipToCIDR(String ip, int range) {
		long x = 0;
		String[] ips = ip.split("\\.");
        // ip string to long
		for (int i = 0; i < ips.length; ++i) {
			x += Integer.parseInt(ips[i]) * Math.pow(2, 24-8*i);
		}
		List<String> res = new ArrayList<>();
        // exhaust range by taking steps
		while (range > 0) {
            // geting the lowest bit of x, -x=~x+1
            // e.g. lowest bit is 2, then there are 2 ips covered: 10, 11
            // number of step is the number of ips covered
			long step = x & -x;
			while (step > range) 
                step /= 2;
			res.add(longToCIDR(x, (int)step));
            // since ip ranges from x to x+step-1 is covered now
			x += step;
			range -= step;
		}
		return res;
	}
	
	String longToCIDR(long x, int step) {
		int[] res = new int[4];
        for (int i = 0; i < res.length; i++) {
            res[i] = (int)((x >> (24-8*i)) & 255);
        }
        // find how many bits represent step
        // e.g. step 2 has 2 bits (10, 11), then prefix should be 32-2+1 = 31
		int k = 0;
		while (step > 0) {
			step /= 2;
            k++;
		}
		return res[0]+"."+res[1]+"."+res[2]+"."+res[3]+"/"+(32-k+1);
	}
}