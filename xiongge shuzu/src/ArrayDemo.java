

import java.util.Random;

public class ArrayDemo {

	public static void main(String[] args) {
		// int[] array = createArray(10);
		// printArray(array);
		// sort(array);
		// System.out.println("�����Ľ����");
		// printArray(array);
		int[][] array = createArray(5, 10);
		printArray(array);
	}

	/**
	 * ����һά����ķ���
	 * 
	 * @param length��ʾһά�����ܴ洢��Ԫ�ظ���
	 * @return ����ʵ��������������
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
	 * ������ά����ķ���
	 * 
	 * @param row��ʾ��ά���������
	 * @param column��ʾ��ά����ÿһ�е�����
	 * @return ����ʵ��������������
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
			int min = i;// ����iλ�õ�����С������
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
	 * ��ӡһά�����д洢��Ԫ��
	 * 
	 * @param arrayҪ��ӡ���������
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
