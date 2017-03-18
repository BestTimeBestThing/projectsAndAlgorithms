package algorithms.test;

public class BeautifulHouse {

	public static void main(String[] args) {
		int[][] field = { { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 1, 0, 0, 0, 0 }, { 0, 0, 0, 1, 1, 0, 0, 0 },
				{ 0, 1, 0, 1, 0, 0, 0, 0 }, { 0, 1, 0, 1, 1, 1, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 1, 1, 0, 0, 0, 0 }, { 0, 1, 0, 0, 0, 0, 0, 0 } };
		int num = numberBeautifulHouseComputing(field);
		System.out.println("漂亮房子个数为" + num);
	}

	private static int numberBeautifulHouseComputing(int[][] field) {
		int row = field.length;
		int col = field[0].length;
		boolean[][] flag = new boolean[row][col];
		int[][] compute = new int[row][col];
		for (int i = 0; i < row; i++)
			compute[i][0] = 0;
		for (int i = 0; i < col; i++)
			compute[0][i] = 0;
		for (int i = 1; i <= row - 2; i++)
			for (int j = 1; j <= col - 2; j++) {
				
			}

		return 0;
	}

}
