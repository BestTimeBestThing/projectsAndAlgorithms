package algorithms.backtracing;

/*
任务(计数问题)：
	一個8*8方格棋盤，從左上角走到右下角，每次只能往右走一格或者往下走一格。請問有幾種走法？
分析：
	根据回溯法，一直往前走（下或者右），如果到达终点（x = 7 and y=7），则计数加1，否则return
 */

public class GridWalkCout {
	public static int cout = 0;

	public static void main(String[] args) {
		backTracking(8, 0, 0);
		System.out.println("总共有种" + cout + "走法");
	}

	public static void backTracking(int n, int x, int y) {
		if (x == n - 1 && y == n - 1) {
			cout++;
			return;
		}
		if (x == n - 1 && y < n - 1) {
			// 向右
			backTracking(n, x, y + 1);
		} else if (x < n - 1 && y == n - 1) {
			// 向下
			backTracking(n, x + 1, y);
		} else if (x < n - 1 && y < n - 1) {
			// 向下
			backTracking(n, x + 1, y);
			// 向右
			backTracking(n, x, y + 1);
		}
	}
}
