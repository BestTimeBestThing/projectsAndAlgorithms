package algorithms.dynamic_programming;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
����(��ֵ����)��
	һ������8*8��P�����ӓ��Д��֡������Ͻ��ߵ����½ǣ�ÿ��ֻ��������һ�����������һ��
	Ո��������С���߷��������߿��������߷�����
������
	���ݶ�̬�滮�������ߵ�(7,7)�㣬�����ַ�����(6,7)��(7,7)��(7,6)��(7,7)
	��1��ֻ��һ���߷���Ʃ�磺�ߵ�(0,4)�㣬(0,3)��(0,4)
	��1��ֻ��һ���߷���Ʃ�磺�ߵ�(4,0)�㣬(3,0)��(4,0)
	�Դ��б��ʽ�Ƶ���
	�����о���a[8][8]
	����һ��8*8�ľ���c��Ԫ��ֵc[m][n]�����ߵ���(m,n)������Сֵ��
	c[m][n] = {a[0][0], m==0 && n==0
	          {c[0][n-1]+a[0][n], m==0 && n>0
	          {c[m-1][0]+a[m][0], n==0 && m>0
	          {min(c[m-1][n],c[m][n-1]) + a[m][n], n>0 && m>0
	          {0, others
*/
class Coordinate {
	public int x;
	public int y;

	Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class GridWalkExtremum {

	public static void main(String[] args) {
		int[][] chessboard = { { 0, 1, 3, 2, 3, 4, 5, 2 }, { 4, 2, 1, 4, 6, 2, 0, 3 }, { 1, 9, 9, 9, 9, 9, 9, 1 },
				{ 8, 7, -3, 2, 1, -2, 1, 3 }, { 9, 8, 7, 0, 1, 2, 7, 3 }, { -3, 2, 2, 3, 1, -3, 1, 9 },
				{ 4, 5, 4, 0, 4, 5, 4, 5 }, { 9, 8, 7, 6, 5, 4, 3, 2 } };
		// int[][] chessboard = {{1,3},{2,5}};
		int row = chessboard.length;
		int col = chessboard[0].length;
		int[][] c = new int[row][col];
		char[][] record = new char[row][col];
		DP_solution(chessboard, c, record);
		System.out.println(c[row - 1][col - 1]);
		int m = row - 1;
		int n = col - 1;
		List<Coordinate> list = new ArrayList<Coordinate>();
		Coordinate cor = new Coordinate(m, n);
		list.add(cor);
		while (m != 0 || n != 0) {
			if (record[m][n] == '��') {
				cor = new Coordinate(m, n - 1);
				list.add(cor);
				n--;
			} else if (record[m][n] == '��') {
				cor = new Coordinate(m - 1, n);
				list.add(cor);
				m--;
			}
		}
		Collections.reverse(list);
		System.out.println("��Ӧ��������ǣ�");
		for (Coordinate cor1 : list) {
			System.out.print("[" + cor1.x + "," + cor1.y + "] ");
		}
		System.out.println();

	}

	public static void DP_solution(int[][] chessboard, int[][] c, char[][] record) {
		int row = chessboard.length;
		int col = chessboard[0].length;
		for (int m = 0; m < row; m++) {
			for (int n = 0; n < col; n++) {
				if (m == 0 && n == 0)
					c[m][n] = chessboard[0][0];
				else if (m == 0 && n > 0) {
					c[m][n] = c[0][n - 1] + chessboard[0][n];
					record[m][n] = '��';
				} else if (n == 0 && m > 0) {
					c[m][n] = c[m - 1][0] + chessboard[m][0];
					record[m][n] = '��';
				} else if (n > 0 && m > 0) {
					if (c[m - 1][n] > c[m][n - 1]) {
						c[m][n] = c[m][n - 1] + chessboard[m][n];
						record[m][n] = '��';
					} else {
						c[m][n] = c[m - 1][n] + chessboard[m][n];
						record[m][n] = '��';
					}
				}
			}
		}
	}
}
