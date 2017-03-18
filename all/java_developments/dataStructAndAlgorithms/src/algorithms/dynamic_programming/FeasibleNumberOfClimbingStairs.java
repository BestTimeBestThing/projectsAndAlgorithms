package algorithms.dynamic_programming;
/*
任务要求：
	爬楼梯，一次爬可以跨出1步或者两步，求从楼梯底下爬到第5阶楼梯总共有多少种方法？
分析：
要想爬到第5阶，可以有两种爬法，就是4→5或者3→5；同理，爬到4阶楼梯也有两种爬法，3→4或者2→4,;...;
爬到2阶有两种爬法，爬到1阶只有1种爬法。
所以得到表达式：
f(n) = { 1, n=1;
	   {2,n=2;
	   {f(n-1)+f(n-2),n>2
为了与程序一致（编程语言的下标一般从0开始），所以填充0部分，又考虑范围问题，上面表达式改为：
f(n) = {1, n=0;
	   {1,n=1;
	   {f(n-1)+f(n-2),2<=n<=5
	   {exterior,others
 */

public class FeasibleNumberOfClimbingStairs {

	public static void main(String[] args) {
		int target = 5;
		int[] table = new int[target + 1];
		
		int cout = compute(target, table);
		System.out.println("总共有种" + cout + "爬法");
		System.out.println("-----------------------");
		cout = compute1(target, table);
		System.out.println("总共有种" + cout + "爬法");

	}

	/**
	 * 自上而下的动态规划方法
	 * 
	 * @param n
	 *            爬到第target阶
	 * @param table
	 *            存储爬到各阶的爬法数
	 * @return 爬到第target阶的可行爬法数
	 */
	private static int compute(int n, int[] table) {
		if (n == 0 || n == 1) {
			table[n] = 1;
			return 1;
		}
		if (table[n] > 0)
			return table[n];
		if (n >= 2 && n <= 5) {
			table[n] = compute(n - 1, table) + compute(n - 2, table);
			return table[n];
		}
		return -1;
	}

	/**
	 * 自下而上的动态规划方法
	 * 
	 * @param n
	 *            爬到第target阶
	 * @param table
	 *            存储爬到各阶的爬法数
	 * @return 爬到第target阶的可行爬法数
	 */
	private static int compute1(int n, int[] table) {
		for (int i = 0; i <= n; i++) {
			if (i == 0 || i == 1)
				table[i] = 1;
			else
				table[i] = table[i - 1] + table[i - 2];
			return table[n];
		}
		return -1;
	}
}
