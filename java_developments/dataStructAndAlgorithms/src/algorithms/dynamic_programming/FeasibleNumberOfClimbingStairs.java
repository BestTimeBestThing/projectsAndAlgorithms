package algorithms.dynamic_programming;
/*
����Ҫ��
	��¥�ݣ�һ�������Կ��1���������������¥�ݵ���������5��¥���ܹ��ж����ַ�����
������
Ҫ��������5�ף���������������������4��5����3��5��ͬ������4��¥��Ҳ������������3��4����2��4,;...;
����2������������������1��ֻ��1��������
���Եõ����ʽ��
f(n) = { 1, n=1;
	   {2,n=2;
	   {f(n-1)+f(n-2),n>2
Ϊ�������һ�£�������Ե��±�һ���0��ʼ�����������0���֣��ֿ��Ƿ�Χ���⣬������ʽ��Ϊ��
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
		System.out.println("�ܹ�����" + cout + "����");
		System.out.println("-----------------------");
		cout = compute1(target, table);
		System.out.println("�ܹ�����" + cout + "����");

	}

	/**
	 * ���϶��µĶ�̬�滮����
	 * 
	 * @param n
	 *            ������target��
	 * @param table
	 *            �洢�������׵�������
	 * @return ������target�׵Ŀ���������
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
	 * ���¶��ϵĶ�̬�滮����
	 * 
	 * @param n
	 *            ������target��
	 * @param table
	 *            �洢�������׵�������
	 * @return ������target�׵Ŀ���������
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
