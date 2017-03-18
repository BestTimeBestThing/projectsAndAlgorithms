package algorithms.dynamic_programming;

/*
任务(计数问题)：
	一個8*8方格棋盤，從左上角走到右下角，每次只能往右走一格或者往下走一格。請問有幾種走法？
分析：
	根据动态规划方法，走到(8,8)点，有两种方法：(7,8)→(8,8)和(8,7)→(8,8)
	第1行只有一种走法，譬如：走到(1,4)点，(1,3)→(1,4)
	第1列只有一种走法，譬如：走到(4,1)点，(3,1)→(4,1)
	以此有表达式推导：
	建立一个9*9的矩阵c，元素值c[m][n]代表走到点(m,n)可行的走法个数，
	c[m][n] = {0, m==1 && n==1
	          {1, m==1 && n>1
	          {1, n==1 && m>1
	          {c[m-1][n] + c[m][n-1], n>1 && m>1
	          {0, others
扩展问题：
	如果某些格子上有障礙物呢？把此格設為零。
	如果也可以往右下斜角走呢？添加來源 c[i-1][j-1] 。
	如果可以往上下左右走呢？不斷繞圈子，永遠不會結束，走法無限多種。	        
 */
public class GridWalkCout {
	public static void main(String[] args) {
		int num_grid = 8;
		int cout = DP_solution(num_grid);
		System.out.println("总共有种" + cout + "走法");
	}

	private static int DP_solution(int num_grid) {
		int[][] c = new int[num_grid + 1][num_grid + 1];
		for (int i = 0; i < c.length; i++) {
			c[0][i] = 0;
			c[i][0] = 0;
		}
		for(int m = 1;m<c.length;m++){
			for(int n = 1;n<c.length;n++){
				if(m==1 && n==1)
					c[m][n] = 0;
				else if((m==1 && n>1)||((n==1 && m>1)))
					c[m][n] = 1;
				else if(n>1 && m>1)
					c[m][n] = c[m-1][n] + c[m][n-1];					
			}
		}
		return c[num_grid][num_grid];
	}

}
