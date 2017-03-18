package algorithms.dynamic_programming;

/**
 * �������ַ�����󹫹��ַ����ĳ��Ⱥ���󹫹��ַ���
 * 
 * @param args
 */
public class LongCommonSequence {

	public static void main(String[] args) {
		String str1 = "abcfdsdefg";
		String str2 = "sdadgeg";
		System.out.println("������ַ�������Ϊ��" + longCommonS(str1, str2));
		System.out.println("------���Ϸ���ֻ�Ǽ�¼��ȣ����淽����¼��Ⱥ�������ַ���-------------------");

		String str3 = "abcfdsdefg";
		String str4 = "sdadgeg";
		int row = str3.length() + 1;
		int column = str4.length() + 1;
		int[][] arr2 = new int[row][column];
		char[][] store = new char[row][column];
		longCommonS2(str3, str4, arr2, store);
		System.out.println("������ַ�������Ϊ��" + arr2[row - 1][column - 1]);

		StringBuffer common = new StringBuffer();
		commonString(store, str3, row, column, common);
		common = common.reverse();
		System.out.println("������ַ���Ϊ��" + common);
	}

	private static void commonString(char[][] store, String str1, int i, int j, StringBuffer str2) {
		if (i == 1 && j == 1)
			return;
		if (store[i - 1][j - 1] == '�I') {
			str2.append(str1.charAt(i - 2));
			commonString(store, str1, i - 1, j - 1, str2);
		} else if (store[i - 1][j - 1] == '��') {
			commonString(store, str1, i, j - 1, str2);
		} else if (store[i - 1][j - 1] == '��') {
			commonString(store, str1, i - 1, j, str2);
		}
	}

	/**
	 * ֻ����󹫹��ַ������������ַ�������
	 * @param String str1 ��Ҫ�Ƚϵ��ַ���1
	 * 		  String str2��Ҫ�Ƚϵ��ַ���2
	 *
	 */
	public static int longCommonS(String str1, String str2) {
		int row = str1.length() + 1;
		int column = str2.length() + 1;
		int[][] arr = new int[row][column];
		for (int i = 0; i < row; i++)
			arr[i][0] = 0;
		for (int i = 0; i < column; i++)
			arr[0][i] = 0;

		for (int i = 1; i < row; i++) {
			for (int j = 1; j < column; j++) {
				char c1 = str1.charAt(i - 1);
				char c2 = str2.charAt(j - 1);
				if (c1 == c2)
					arr[i][j] = arr[i - 1][j - 1] + 1;
				else
					arr[i][j] = Math.max(arr[i - 1][j], arr[i][j - 1]);
			}
		}
		return arr[row - 1][column - 1];
	}

	/*
	 * ����󹫹��ַ��������ҹ����ַ�������
	 */
	public static void longCommonS2(String str1, String str2, int[][] arr, char[][] store) {
		int row = str1.length() + 1;
		int column = str2.length() + 1;

		for (int i = 0; i < row; i++)
			arr[i][0] = 0;
		for (int i = 0; i < column; i++)
			arr[0][i] = 0;

		for (int i = 1; i < row; i++) {
			for (int j = 1; j < column; j++) {
				char c1 = str1.charAt(i - 1);
				char c2 = str2.charAt(j - 1);
				if (c1 == c2) {
					arr[i][j] = arr[i - 1][j - 1] + 1;
					store[i][j] = '�I';
				} else {
					if (arr[i - 1][j] <= arr[i][j - 1]) {
						arr[i][j] = arr[i][j - 1];
						store[i][j] = '��';
					} else if (arr[i - 1][j] > arr[i][j - 1]) {
						arr[i][j] = arr[i - 1][j];
						store[i][j] = '��';
					}
				}
			}
		}
	}
}
