package algorithms.dynamic_programming;

public class Zero_One_KnapsackProblem {

	public static void main(String[] args) {
		int[] weight = { 3, 5, 2, 6, 4 }; // ��Ʒ����
		int[] val = { 4, 4, 3, 5, 3 }; // ��Ʒ��ֵ
		int m = 12; // ��������
		int n = val.length; // ��Ʒ����
		solve_DP(weight, val, m, n);
	}

	//��̬�滮�㷨��ʱ�临�Ӷ�O(n*m),�ռ临�Ӷ�O(n*m)
	public static void solve_DP(int[] weight, int[] val, int m, int n) {
		int[][] f = new int[n + 1][m + 1]; // f[i][j]��ʾǰi����Ʒ��װ������Ϊj�ı����е�����ֵ
		int[][] path = new int[n + 1][m + 1];
		// ��ʼ����һ�к͵�һ��
		for (int i = 0; i < f.length; i++) {
			f[i][0] = 0;
		}
		for (int i = 0; i < f[0].length; i++) {
			f[0][i] = 0;
		}
		// ͨ����ʽ��������
		for (int i = 1; i < f.length; i++) {
			for (int j = 1; j < f[0].length; j++) {
				if (weight[i - 1] > j)
					f[i][j] = f[i - 1][j];
				else {
					if (f[i - 1][j] < f[i - 1][j - weight[i - 1]] + val[i - 1]) {
						f[i][j] = f[i - 1][j - weight[i - 1]] + val[i - 1];
						path[i][j] = 1;
					} else {
						f[i][j] = f[i - 1][j];
					}
					// f[i][j] = Math.max(f[i-1][j],
					// f[i-1][j-weight[i-1]]+val[i-1]);
				}
			}
		}
		for (int i = 0; i < f.length; i++) {
			for (int j = 0; j < f[0].length; j++) {
				System.out.print(f[i][j] + " ");
			}
			System.out.println();
		}

		int i = f.length - 1;
		int j = f[0].length - 1;
		while (i > 0 && j > 0) {
			if (path[i][j] == 1) {
				System.out.print("��" + i + "����Ʒװ�� ");
				j -= weight[i - 1];
			}
			i--;
		}

	}
}
