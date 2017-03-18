package algorithms.backtracing;

import java.util.Arrays;

public class EightQueenProblem_improve1 {
	public static int cout = 0;

	public static void main(String[] args) {
		int[] solution = new int[8];
		boolean[] flagCol = new boolean[8];
		boolean[] flagLeft = new boolean[15];
		boolean[] flagRight = new boolean[15];
		backTracing(0, solution, flagCol, flagLeft, flagRight);
		System.out.println(cout);
	}

	public static void backTracing(int x, int[] solution, boolean[] flagCol, boolean[] flagLeft, boolean[] flagRight) {
		if (x == 8) {
			// print_solution(solution);
			cout++;
			return;
		}
		for (int y = 0; y < 8; y++) {
			int indexLeft = x + y;
			int indexRight = 7 - (x - y);
			if (!flagCol[y] && !flagLeft[indexLeft] && !flagRight[indexRight]) {
				solution[x] = y;
				flagCol[y] = flagLeft[indexLeft] = flagRight[indexRight] = true;
				backTracing(x + 1, solution, flagCol, flagLeft, flagRight);
				flagCol[y] = flagLeft[indexLeft] = flagRight[indexRight] = false;
			}
		}
	}

	public static void print_solution(int[] solution) {
		System.out.println(Arrays.toString(solution));
	}

}
