package algorithms.dynamic_programming;

import java.util.ArrayList;
import java.util.Collections;

public class KnapsackProblem {

	public static void main(String[] args) {
		int[] weight = { 3, 5, 2, 6, 4 }; // ��Ʒ����
		int[] cost = { 4, 4, 3, 5, 3 }; // ��Ʒ��ֵ
		int w = 12; // ��������
		int n = weight.length; // ��Ʒ����
		int[][] c = new int[n + 1][w + 1];// DP���,c[i][j]��ʾǰi����Ʒ�У��ڳ���j�ı���������ֵ
		int[][] path = new int[n + 1][w + 1];
		for (int i = 0; i < c[0].length; i++)
			c[0][i] = 0;
		for (int i = 0; i < c.length; i++)
			c[i][0] = 0;
		// ===============���϶��¶�̬�滮����===============================================
		// int maxValue = knapsack_1(n, w, cost, weight, c, path);
		// System.out.println("����ֵΪ��" + maxValue);
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
		// System.out.println("��" + index.toString() + "����Ʒװ����пɵ�����ֵ");
		// ===============���¶��϶�̬�滮����==============================================
		int maxValue = knapsack_2(n, w, cost, weight, c, path);
		System.out.println("����ֵΪ��" + maxValue);
		// ===============================================================================
		System.out.println("------------------------");
		knapsack_1_1(cost, weight, w);
	}

	/*
	 * ׌�����e�����Ʒ���rֵ��󣨶��� �ӑBҎ���Ǳ��^��Ч�ʵķ������ָ�}�ķ�ʽ�ܺ��Σ���ĳһ����Ʒ���f���҂������x��Ż򲻷ţ�Ȼ����ȥ�@����Ʒ��
	 * �sС���}������ һ����Ʒ�����M�����������rֵ��׃���������ز�׃��һ����Ʒ���M�����������rֵ���������������½����fޒ��ʽ�飺 c(n, w) =
	 * max( c(n-1, w), c(n-1, w-weight[n]) + cost[n] ) ^^^^^^^^^
	 * ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ ���� -> 0 �з� -> 1 n����0������n����ƷҪ���M�����ȡ�
	 * w�������������ơ� c(n, w)��ֻ�е�0������n����Ʒ���������ƞ�w���˕r�ı������}�𰸡� weight[n]����n����Ʒ��������
	 * cost[n]����n����Ʒ�ărֵ�� �м����]߅��l�����������ز������r���]����Ʒ����r�� c(n, w) = { -INF , if w < 0
	 * { -INF , if n < 0 { 0 , if n = 0 and w >= 0 { max( c(n-1, w), , if n > 0
	 * and w >= 0 { c(n-1, w-weight[n]) + cost[n] )
	 * 
	 * �����ȡؓ����Ʒ��ؓ�����أ� c(n, w) = { 0 , if n = 0 { c(n-1, w) , if n > 0 { and
	 * w-weight[n] < 0 { max( c(n-1, w), , if n > 0 { c(n-1, w-weight[n]) +
	 * cost[n] ) and w-weight[n] >= 0
	 */
	// ���϶��µĶ�̬�滮
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

	// ���¶��ϵĶ�̬�滮����
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
	 * �м��^��Ӌ������c���ÿ��Ӌ��ֻ��Ҫ�Ϸ������Ϸ��ĸ��ӡ��҂��������}����ӛ���w��
	 * ����һ�l��о͉��ˣ����^Ӌ�����Ҫ�ĳ����������_ʼ���Ų������w���Ϸ��ĸ��ӡ�
	 * �r�g�}�s��O(NW)�����g�}�s��O(W)������N����Ʒ������W�Ǳ����������ơ�
	 */
	public static void knapsack_1_1(int[] cost, int[] weight, int w) {
		// cost���飺��Ʒ�ĵ���
		// weight���飺������Ʒ������
		// w���������ܳ���
		int n = cost.length; // ��Ʒ����
		int[] c = new int[w + 1]; // c[i]��ʾ����i�ı�����װ�µ�����ֵ

		for (int i = 0; i < n; i++) {
			for (int j = w; j - weight[i] >= 0; j--) // �ɺ���ǰ,ע�⣺��ǰ������
			{
				c[j] = Math.max(c[j], c[j - weight[i]] + cost[i]);
			}
		}

		System.out.println("����ֵΪ��" + c[w]);
	}
}
