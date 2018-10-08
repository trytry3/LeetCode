/* 给个input list of floating points, 要求output list of integers, 满足以下两个constraint， 
就是和跟Round(x1+x2+... +xn)的结果一样，但是minimize output 和input的绝对值差之和
Input: A = [x1, x2, ..., xn]
Sum T = Round(x1+x2+... +xn)  
Output: B = [y1, y2, ...., yn]
Constraint #1: y1+y2+...+yn = T
Constraint #2: minimize sum(abs(diff(xi - yi)))

举例
A = [1.2, 2.3, 3.4]
Round(1.2 + 2.3 + 3.4) = 6.9 => 7
1 + 2 + 3 => 6

1 + 3 + 3 => 7
0.2 + 0.7 + 0.4 = 1.3

1 + 2 + 4 => 7
0.2 + 0.3 + 0.6 = 1.1
所以[1,2,4]比[1,3,3]要好
*/

// airbnb
class Solution {
	// 思路就是先把所有floor加起来，然后看差多少，然后把多少个floor转成ceil
	// 转的时候按照num本身与ceil的距离排序
	public static int[] getNearlyArrayWithSameSum(double[] arr) {
		NumWithDiff[] ceilWithDiff = new NumWithDiff[arr.length];
		double sum = 0.0;
		int floorSum = 0;
		for (int i = 0; i < arr.length; i++) {
			int floor = (int) arr[i];
			int ceil = floor;
			if (floor < arr[i])
				ceil++; // 查是不是4.0这种floor/ceil都是本身的
			floorSum += floor;
			sum += arr[i];
			ceilWithDiff[i] = new NumWithDiff(ceil, ceil - arr[i], i); // 把ceil都放在数组里面进行比较
		}
		int sumInInt = (int) Math.round(sum); // 四舍五入
		int diff = sumInInt - floorSum;
		Arrays.sort(ceilWithDiff, new Comparator<NumWithDiff>() {
			public int compare(NumWithDiff n1, NumWithDiff n2) {
				if (n1.diffWithCeil <= n2.diffWithCeil)
					return -1;
				else
					return 1;
			}
		});
		
		int[] res = new int[arr.length];
		int i = 0;
		for (; i < diff; i++) {
			// use index to keep output's order same as input
			int index = ceilWithDiff[i].index;
			res[index] = ceilWithDiff[i].num; // 这些放ceil, 放一个ceil相当于往floorSum加一个1
		}
		for (; i < arr.length; i++) {
			int index = ceilWithDiff[i].index;
			res[index] = ceilWithDiff[i].num - 1; // 剩下的只放floor
		}
		return res;
	}

	public static void main(String[] args) {
		double[] arr = { 1.2, 3.7, 2.3, 4.8 };
		double sum = 0;
		for (double i : arr)
			sum += i;
		System.out.println((int) sum);
		int[] res = getNearlyArrayWithSameSum(arr);
		for (int i : res)
			System.out.print(i + " ");
	}
}

class NumWithDiff {
	int num;
	double diffWithCeil;
	int index;

	public NumWithDiff(int n, double c, int i) {
		this.num = n;
		this.diffWithCeil = c;
		this.index = i;
	}
}