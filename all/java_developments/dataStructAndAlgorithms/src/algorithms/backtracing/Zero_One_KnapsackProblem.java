package algorithms.backtracing;

import java.util.ArrayList;

public class Zero_One_KnapsackProblem {
	public static int maxValue = 0;

	public static void main(String[] args) {
		int[] weight = { 3, 5, 2, 6, 4 }; // ��Ʒ����
		int[] val = { 4, 4, 3, 5, 3 }; // ��Ʒ��ֵ
		boolean[] solution = new boolean[val.length];
		boolean[] answer = new boolean[val.length];
		int size = 12; // ��������
		backTracing(0, weight, val, size, solution, answer, 0, 0);
		System.out.println("����ֵΪ��" + maxValue);

		ArrayList<Integer> index = new ArrayList<>();
		for (int i = 0; i < val.length; i++) {
			if (answer[i] == true)
				index.add(i + 1);
		}
		System.out.println("װ������ǵ�" + index.toString() + "����Ʒ");

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
		// ��Ʒn�Ž�����
		if (costed + weight[n] <= size) {
			solution[n] = true;
			backTracing(n + 1, weight, val, size, solution, answer, costed + weight[n], currentValue + val[n]);
			solution[n] = false;
		}

		// ��Ʒn���Ž�����
		solution[n] = false;
		backTracing(n + 1, weight, val, size, solution, answer, costed, currentValue);
	}

	private static void storeSolution(boolean[] solution, boolean[] answer) {
		for (int i = 0; i < solution.length; i++) {
			answer[i] = solution[i];
		}
	}
}
