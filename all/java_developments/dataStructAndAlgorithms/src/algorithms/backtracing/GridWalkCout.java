package algorithms.backtracing;

/*
����(��������)��
	һ��8*8������P�������Ͻ��ߵ����½ǣ�ÿ��ֻ��������һ�����������һ��Ո���Ў׷N�߷���
������
	���ݻ��ݷ���һֱ��ǰ�ߣ��»����ң�����������յ㣨x = 7 and y=7�����������1������return
 */

public class GridWalkCout {
	public static int cout = 0;

	public static void main(String[] args) {
		backTracking(8, 0, 0);
		System.out.println("�ܹ�����" + cout + "�߷�");
	}

	public static void backTracking(int n, int x, int y) {
		if (x == n - 1 && y == n - 1) {
			cout++;
			return;
		}
		if (x == n - 1 && y < n - 1) {
			// ����
			backTracking(n, x, y + 1);
		} else if (x < n - 1 && y == n - 1) {
			// ����
			backTracking(n, x + 1, y);
		} else if (x < n - 1 && y < n - 1) {
			// ����
			backTracking(n, x + 1, y);
			// ����
			backTracking(n, x, y + 1);
		}
	}
}
