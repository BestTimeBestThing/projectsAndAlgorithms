package algorithms.dynamic_programming;

import java.util.ArrayList;
import java.util.Collections;

public class KnapsackProblem {

	public static void main(String[] args) {
		int[] weight = { 3, 5, 2, 6, 4 }; // 物品重量
		int[] cost = { 4, 4, 3, 5, 3 }; // 物品价值
		int w = 12; // 背包容量
		int n = weight.length; // 物品个数
		int[][] c = new int[n + 1][w + 1];// DP表格,c[i][j]表示前i个物品中，在承重j的背包的最大价值
		int[][] path = new int[n + 1][w + 1];
		for (int i = 0; i < c[0].length; i++)
			c[0][i] = 0;
		for (int i = 0; i < c.length; i++)
			c[i][0] = 0;
		// ===============自上而下动态规划测试===============================================
		// int maxValue = knapsack_1(n, w, cost, weight, c, path);
		// System.out.println("最大价值为：" + maxValue);
		// ArrayList<Integer> index = new ArrayList<>();
		// int i = path.length - 1;
		// int j = path[0].length - 1;
		// while (i > 0 && j > 0) {
		// if (path[i][j] == 1) {
		// j = j - weight[i - 1];
		// index.add(i);
		// }
		// i--;
		// }
		// Collections.reverse(index);
		// System.out.println("第" + index.toString() + "件物品装入袋中可得最大价值");
		// ===============自下而上动态规划测试==============================================
		int maxValue = knapsack_2(n, w, cost, weight, c, path);
		System.out.println("最大价值为：" + maxValue);
		// ===============================================================================
		System.out.println("------------------------");
		knapsack_1_1(cost, weight, w);
	}

	/*
	 * 讓背包裡面的物品總價值最大（二） 動態規劃是比較有效率的方法。分割問題的方式很簡單：對某一件物品來說，我們可以選擇放或不放；然後移去這件物品，
	 * 縮小問題範疇。 一件物品不放進背包，背包價值不變、背包耐重不變；一件物品放進背包，背包價值上升、背包耐重下降。遞迴公式為： c(n, w) =
	 * max( c(n-1, w), c(n-1, w-weight[n]) + cost[n] ) ^^^^^^^^^
	 * ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ 不放 -> 0 有放 -> 1 n：第0個到第n個物品要放進背包內。
	 * w：背包耐重限制。 c(n, w)：只有第0個到第n個物品，耐重限制為w，此時的背包問題答案。 weight[n]：第n個物品的重量。
	 * cost[n]：第n個物品的價值。 仔細考慮邊界條件，例如耐重不足的情況、沒有物品的情況： c(n, w) = { -INF , if w < 0
	 * { -INF , if n < 0 { 0 , if n = 0 and w >= 0 { max( c(n-1, w), , if n > 0
	 * and w >= 0 { c(n-1, w-weight[n]) + cost[n] )
	 * 
	 * 避免存取負的物品、負的耐重： c(n, w) = { 0 , if n = 0 { c(n-1, w) , if n > 0 { and
	 * w-weight[n] < 0 { max( c(n-1, w), , if n > 0 { c(n-1, w-weight[n]) +
	 * cost[n] ) and w-weight[n] >= 0
	 */
	// 自上而下的动态规划
	public static int knapsack_1(int n, int w, int[] cost, int[] weight, int[][] c, int[][] path) {
		if (w < 0)
			return -1;
		if (n == 0)
			return 0;
		if (c[n][w] > 0)
			return c[n][w];
		int tmp1 = knapsack_1(n - 1, w, cost, weight, c, path);
		int tmp2 = 0;
		if (w - weight[n - 1] > 0) {
			tmp2 = knapsack_1(n - 1, w - weight[n - 1], cost, weight, c, path) + cost[n - 1];
		}
		if (tmp2 > tmp1) {
			c[n][w] = tmp2;
			path[n][w] = 1;
		} else
			c[n][w] = tmp1;
		return c[n][w];
	}

	// 自下而上的动态规划方法
	public static int knapsack_2(int n, int w, int[] cost, int[] weight, int[][] c, int[][] path) {
		for (int i = 1; i < c.length; i++) {
			for (int j = 1; j < c[0].length; j++) {
				if (j < weight[i - 1])
					c[i][j] = c[i - 1][j];
				else {
					int tmp1 = c[i - 1][j];
					int tmp2 = c[i - 1][j - weight[i - 1]] + cost[i - 1];
					if (tmp2 > tmp1) {
						c[i][j] = tmp2;
					} else {
						c[i][j] = tmp1;
					}
				}
			}
		}
		return c[c.length - 1][c[0].length - 1];
	}

	/*
	 * 仔細觀察計算順序與表格，每次計算只需要上方和左上方的格子。我們可以重複利用記憶體，
	 * 建立一條陣列就夠了；不過計算順序要改成由陣列後端開始，才不會覆蓋左上方的格子。
	 * 時間複雜度O(NW)，空間複雜度O(W)。其中N是物品數量，W是背包重量限制。
	 */
	public static void knapsack_1_1(int[] cost, int[] weight, int w) {
		// cost数组：物品的单价
		// weight数组：单个物品的重量
		// w：背包的总承重
		int n = cost.length; // 物品个数
		int[] c = new int[w + 1]; // c[i]表示承重i的背包能装下的最大价值

		for (int i = 0; i < n; i++) {
			for (int j = w; j - weight[i] >= 0; j--) // 由后往前,注意：由前往后不行
			{
				c[j] = Math.max(c[j], c[j - weight[i]] + cost[i]);
			}
		}

		System.out.println("最大价值为：" + c[w]);
	}
}
