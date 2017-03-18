package algorithms.dynamic_programming;

/*
����(��������)��
	һ��8*8������P�������Ͻ��ߵ����½ǣ�ÿ��ֻ��������һ�����������һ��Ո���Ў׷N�߷���
������
	���ݶ�̬�滮�������ߵ�(8,8)�㣬�����ַ�����(7,8)��(8,8)��(8,7)��(8,8)
	��1��ֻ��һ���߷���Ʃ�磺�ߵ�(1,4)�㣬(1,3)��(1,4)
	��1��ֻ��һ���߷���Ʃ�磺�ߵ�(4,1)�㣬(3,1)��(4,1)
	�Դ��б��ʽ�Ƶ���
	����һ��9*9�ľ���c��Ԫ��ֵc[m][n]�����ߵ���(m,n)���е��߷�������
	c[m][n] = {0, m==1 && n==1
	          {1, m==1 && n>1
	          {1, n==1 && m>1
	          {c[m-1][n] + c[m][n-1], n>1 && m>1
	          {0, others
��չ���⣺
	���ĳЩ���������ϵK���أ��Ѵ˸��O���㡣
	���Ҳ����������б�����أ���Ӂ�Դ c[i-1][j-1] ��
	��������������������أ������@Ȧ�ӣ����h�����Y�����߷��o�޶�N��	        
 */
public class GridWalkCout {
	public static void main(String[] args) {
		int num_grid = 8;
		int cout = DP_solution(num_grid);
		System.out.println("�ܹ�����" + cout + "�߷�");
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
