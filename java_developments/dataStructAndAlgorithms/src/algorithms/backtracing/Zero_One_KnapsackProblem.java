package algorithms.backtracing;

import java.util.ArrayList;

public class Zero_One_KnapsackProblem {
	public static int maxValue = 0;

	public static void main(String[] args) {
		int[] weight = { 3, 5, 2, 6, 4 }; // 物品重量
		int[] val = { 4, 4, 3, 5, 3 }; // 物品价值
		boolean[] solution = new boolean[val.length];
		boolean[] answer = new boolean[val.length];
		int size = 12; // 背包容量
		backTracing(0, weight, val, size, solution, answer, 0, 0);
		System.out.println("最大价值为：" + maxValue);

		ArrayList<Integer> index = new ArrayList<>();
		for (int i = 0; i < val.length; i++) {
			if (answer[i] == true)
				index.add(i + 1);
		}
		System.out.println("装入包的是第" + index.toString() + "个物品");

	}

	public static void backTracing(int n, int[] weight, int[] val, int size, boolean[] solution, boolean[] answer,
			int costed, int currentValue) {
		if (n == val.length) {
			if (currentValue > maxValue) {
				maxValue = currentValue;
				storeSolution(solution, answer);
			}
			return;

		}
		// 物品n放进背包
		if (costed + weight[n] <= size) {
			solution[n] = true;
			backTracing(n + 1, weight, val, size, solution, answer, costed + weight[n], currentValue + val[n]);
			solution[n] = false;
		}

		// 物品n不放进背包
		solution[n] = false;
		backTracing(n + 1, weight, val, size, solution, answer, costed, currentValue);
	}

	private static void storeSolution(boolean[] solution, boolean[] answer) {
		for (int i = 0; i < solution.length; i++) {
			answer[i] = solution[i];
		}
	}
}
