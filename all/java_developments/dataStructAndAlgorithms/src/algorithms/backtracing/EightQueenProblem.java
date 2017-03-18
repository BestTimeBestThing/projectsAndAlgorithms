package algorithms.backtracing;

public class EightQueenProblem {
	public static int cout = 0;

	public static void main(String[] args) {
		boolean[][] solution = new boolean[8][8];// ���ڱ�ʾ8*8������
		boolean[] flagRow = new boolean[8]; // flagRow[i]��ʾ��i����û�з��˻ʺ����̹���8��
		boolean[] flagCol = new boolean[8]; // flagCol[i]��ʾ��i����û�з��˻ʺ����̹���8��
		boolean[] flagLeftDiagonal = new boolean[15]; // flagLeftDiagonal[i]��ʾ��i��б����û�з��˻ʺ󣬹�15����б��
		boolean[] flagRightDiagonal = new boolean[15]; // flagRightDiagonal[i]��ʾ��i��б����û�з��˻ʺ󣬹�15����б��
		backTracing(0, 0, 0, solution, flagRow, flagCol, flagLeftDiagonal, flagRightDiagonal);
		System.out.println("�ܹ���" + cout + "���������Ҫ��");
	}

	/**
	 * @param x
	 *            ���Ϊx��
	 * @param y
	 *            ���Ϊy��
	 * @param num
	 *            �Ѿ��������ϰڷŵĻʺ���Ŀ
	 * @param solution
	 *            �������̣�ÿ��Ԫ�ص�ֵ�����Ԫ������λ����û�зŻʺ�
	 * @param flagRow
	 *            flagRow[i]��ʾ��i����û�з��˻ʺ�
	 * @param flagCol
	 *            flagCol[i]��ʾ��i����û�з��˻ʺ�
	 * @param flagLeftDiagonal
	 *            flagLeftDiagonal[i]��ʾ��i��б����û�з��˻ʺ�
	 * @param flagRightDiagonal
	 *            flagRightDiagonal[i]��ʾ��i��б����û�з��˻ʺ�
	 */
	private static void backTracing(int x, int y, int num, boolean[][] solution, boolean[] flagRow, boolean[] flagCol,
			boolean[] flagLeftDiagonal, boolean[] flagRightDiagonal) {
		if (y == 8) {
			x++;
			y = 0;
		}
		if (x == 8) {
			if (num != 8)
				return;
			 printSolution(solution);
			cout++;
			return;
		}

		// ����x,y�жϳ��ڵڼ���б���Ϻ��ڵڼ���б����
		int indexLeft = x + y;
		int indexRight = 7 - (x - y);

		// ���ûʺ�
		if (!flagRow[x] && !flagCol[y] && !flagLeftDiagonal[indexLeft] && !flagRightDiagonal[indexRight]) {
			solution[x][y] = true;
			flagRow[x] = flagCol[y] = flagLeftDiagonal[indexLeft] = flagRightDiagonal[indexRight] = true;
			// ������˻ʺ���Ӧ������һ�εݹ�ʱ���ĸ���ʶ����true
			backTracing(x, y + 1, num + 1, solution, flagRow, flagCol, flagLeftDiagonal, flagRightDiagonal);
			flagRow[x] = flagCol[y] = flagLeftDiagonal[indexLeft] = flagRightDiagonal[indexRight] = false;
			// �����Ѿ������ĸ���ʶ���Է�����һ�ֵݹ飬���Դ˴���Ҫ��ԭԭֵ���������¸�λ�ò��Żʺ�����
		}

		// �����ûʺ�
		solution[x][y] = false;
		backTracing(x, y + 1, num, solution, flagRow, flagCol, flagLeftDiagonal, flagRightDiagonal);
	}

	private static void printSolution(boolean[][] solution) {
		int len_row = solution.length;
		int len_col = solution[0].length;
		for (int i = 0; i < len_row; i++) {
			for (int j = 0; j < len_col; j++) {
				System.out.print(solution[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println("---------------------------------");
	}
}
