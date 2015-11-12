package src.main.java.com.venk.core.util;

public class Venk {

	/**
	 * 桶排序 Bucket sort
	 * 
	 * @param data
	 */
	public static void venkBasket(int data[])// data为待排序数组
	{
		int n = data.length;
		int bask[][] = new int[10][n];
		int index[] = new int[10];
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			max = max > (Integer.toString(data[i]).length()) ? max : (Integer
					.toString(data[i]).length());
		}
		String str;
		for (int i = max - 1; i >= 0; i--) {
			for (int j = 0; j < n; j++) {
				str = "";
				if (Integer.toString(data[j]).length() < max) {
					for (int k = 0; k < max
							- Integer.toString(data[j]).length(); k++)
						str += "0";
				}
				str += Integer.toString(data[j]);
				bask[str.charAt(i) - '0'][index[str.charAt(i) - '0']++] = data[j];
			}
			int pos = 0;
			for (int j = 0; j < 10; j++) {
				for (int k = 0; k < index[j]; k++) {
					data[pos++] = bask[j][k];
				}
			}
			for (int x = 0; x < 10; x++)
				index[x] = 0;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long startTime = System.currentTimeMillis();

		int len = 1000000;
		int[] testBasket = new int[len];

		for (int i = 0; i < len; i++) {
			testBasket[i] = (int) (Math.random() * 1000000);
		}

		long sortStartTime = System.currentTimeMillis();
		venkBasket(testBasket);//baskerSort
		long sortEndTime = System.currentTimeMillis();
		int enter = 1;
		for (int t : testBasket) {
			System.out.print(t + "  ");
			if (enter % 10 == 0) {
				System.out.println();
			}enter++;
		}
		long endTime = System.currentTimeMillis();
		System.out.println("\n\n" + "array lenth: " + len + "\nsort time: "
				+ (sortEndTime - sortStartTime) + "\nall time use: "
				+ (endTime - startTime));

	}

}
