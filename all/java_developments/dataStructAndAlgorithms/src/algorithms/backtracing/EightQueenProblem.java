package algorithms.backtracing;

public class EightQueenProblem {
	public static int cout = 0;

	public static void main(String[] args) {
		boolean[][] solution = new boolean[8][8];// 用于表示8*8的棋盘
		boolean[] flagRow = new boolean[8]; // flagRow[i]表示第i行有没有放了皇后，棋盘共有8行
		boolean[] flagCol = new boolean[8]; // flagCol[i]表示第i列有没有放了皇后，棋盘共有8列
		boolean[] flagLeftDiagonal = new boolean[15]; // flagLeftDiagonal[i]表示第i左斜线有没有放了皇后，共15条左斜线
		boolean[] flagRightDiagonal = new boolean[15]; // flagRightDiagonal[i]表示第i右斜线有没有放了皇后，共15条右斜线
		backTracing(0, 0, 0, solution, flagRow, flagCol, flagLeftDiagonal, flagRightDiagonal);
		System.out.println("总共有" + cout + "种情况符合要求");
	}

	/**
	 * @param x
	 *            起点为x行
	 * @param y
	 *            起点为y列
	 * @param num
	 *            已经在棋盘上摆放的皇后数目
	 * @param solution
	 *            代表棋盘，每个元素的值代表该元素所在位置有没有放皇后
	 * @param flagRow
	 *            flagRow[i]表示第i行有没有放了皇后
	 * @param flagCol
	 *            flagCol[i]表示第i列有没有放了皇后
	 * @param flagLeftDiagonal
	 *            flagLeftDiagonal[i]表示第i左斜线有没有放了皇后
	 * @param flagRightDiagonal
	 *            flagRightDiagonal[i]表示第i右斜线有没有放了皇后
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

		// 根据x,y判断出在第几左斜线上和在第几又斜线上
		int indexLeft = x + y;
		int indexRight = 7 - (x - y);

		// 放置皇后
		if (!flagRow[x] && !flagCol[y] && !flagLeftDiagonal[indexLeft] && !flagRightDiagonal[indexRight]) {
			solution[x][y] = true;
			flagRow[x] = flagCol[y] = flagLeftDiagonal[indexLeft] = flagRightDiagonal[indexRight] = true;
			// 如果放了皇后，相应地在下一次递归时，四个标识都置true
			backTracing(x, y + 1, num + 1, solution, flagRow, flagCol, flagLeftDiagonal, flagRightDiagonal);
			flagRow[x] = flagCol[y] = flagLeftDiagonal[indexLeft] = flagRightDiagonal[indexRight] = false;
			// 上面已经更改四个标识，以方便下一轮递归，所以此处需要复原原值，方便以下该位置不放皇后的情况
		}

		// 不放置皇后
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
