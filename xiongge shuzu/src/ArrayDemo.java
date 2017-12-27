

import java.util.Random;

public class ArrayDemo {

	public static void main(String[] args) {
		// int[] array = createArray(10);
		// printArray(array);
		// sort(array);
		// System.out.println("排序后的结果：");
		// printArray(array);
		int[][] array = createArray(5, 10);
		printArray(array);
	}

	/**
	 * 创建一维数组的方法
	 * 
	 * @param length表示一维数组能存储的元素个数
	 * @return 返回实例化后的数组对象
	 */
	public static int[] createArray(int length) {
		Random rand = new Random();
		int[] array = new int[length];
		for (int i = 0; i < array.length; i++) {
			array[i] = rand.nextInt(100) + 1;
		}
		return array;
	}

	/**
	 * 创建二维数组的方法
	 * 
	 * @param row表示二维数组的行数
	 * @param column表示二维数组每一行的列数
	 * @return 返回实例化后的数组对象
	 */
	public static int[][] createArray(int row, int column) {
		Random rand = new Random();
		int[][] array = new int[row][column];
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				array[i][j] = rand.nextInt(100) + 1;
			}
		}
		return array;
	}

	public static void sort(int[] array) {
		for (int i = 0; i < array.length - 1; i++) {
			int min = i;// 假设i位置的是最小的数据
			for (int j = i + 1; j < array.length; j++) {
				if (array[j] < array[min])
					min = j;
			}
			if (i != min) {
				int temp = array[min];
				array[min] = array[i];
				array[i] = temp;
			}
		}
	}

	/**
	 * 打印一维数组中存储的元素
	 * 
	 * @param array要打印的数组对象
	 */
	public static void printArray(int[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + "\t");
		}
		System.out.println();
	}

	public static void printArray(int[][] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				System.out.print(array[i][j] + "\t");
			}
			System.out.println();
		}
	}

}
