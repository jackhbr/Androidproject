package array;



import java.util.Arrays;
import java.util.Random;
//一共有四种排序方法，冒泡，选择，插入，系统自带的快速排序
//还有实现二维数组的找所有的最大数的位置
public class ArrayDemo {

	public static void main(String[] args) {
		 int[] array = createArray(10);
		 printArray(array);
		 Arrays.sort(array);
		 System.out.println("排序后的结果：");
		 printArray(array);
//		int[][] array = createArray(5, 10);
//		printArray(array);
//		int[] array1= createArray(10000);
//		
//		printArray(array1);
		
		//Arrays.sort(array1);   调用数组里的内部函数的快速排序方法
	//	long starTime=System.currentTimeMillis();
		//insertSort(array1);   10000次是用了40ms
	//	Arrays.sort(array1); // 10000次是用了1ms
		//bubbleSort(array1);    10000次是用了227ms
		 // sort(array1);      10000次是用了52ms   
		
		//ArraySort(array1, 0, array1.length - 1); 这个排序方法和系统自带的排序方法都是快速排序方法，运行时间都是一样的
		
//		别人的测试结果，基本和我的一样
//		测试数据10000
//		冒泡排序：120ms
//		选择排序：32ms
//		插入排序：20ms
//		快速排序：7ms
//		 long endTime=System.currentTimeMillis();
//		  long Time=endTime-starTime;
//		  System.out.println(Time);
//		printArray(array1);
		
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

	
	//选择排序  熊哥版本
	public static void sort(int[] array) {
		for (int i = 0; i < array.length - 1; i++) {
			int min = i;// 假设i位置的是最小的数据
			for (int j = i + 1; j < array.length; j++) {  //每次都从全部数据中选择最小的数据排在前面，选择到最后一个就结束排序
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
	
	
	

	
	
	
	
	//冒泡排序
	public static void bubbleSort(int a[]) {    //这里一定要加个static才可以
		  
	    int n = a.length;   
	  
	    for (int i = 0; i < n - 1; i++) {    //每一次都把最大值往最后面放，放了n次后就完成排序了
	                                         //相邻的两个做比较，大的往后面放
	      for (int j = 0; j < n - 1; j++) {   
	  
	        if (a[j] > a[j + 1]) {   
	  
	          int temp = a[j];   
	  
	          a[j] = a[j + 1];   
	  
	          a[j + 1] = temp;   
	  
	        }   
	  
	      }   
	  
	    }   
	  
	}   
	
	//插入排序
	public static void insertSort(int[] a) {
        for (int i = 1; i < a.length; i++) {
            for (int j = i; j > 0; j--) {
                if (a[j] < a[j - 1]) {
                    int temp = a[j - 1];
                    a[j - 1] = a[j];
                    a[j] = temp;
                }else break;
            }
        }
    }
	
	
    private static void ArraySort(int[] array, int lowIndex, int highIndex) {
        int low = lowIndex;
        int high = highIndex;
        int mid;
        if (lowIndex < highIndex) 
            while (low <= high) {
                mid = array[(lowIndex + highIndex) / 2];
                while ((low < highIndex) && (array[low] < mid)) {
                    ++low;
                }
                while ((high > lowIndex) && (array[high] > mid)) {
                    --high;
                }
                if (low <= high) {
                    wrap(array, low, high);
                    ++low;
                    --high;
                }
            }
            if (low < highIndex) {
                ArraySort(array, low, highIndex);
            }
            if (high > lowIndex) {
                ArraySort(array, lowIndex, high);
            }
        }

    

    // 交换数组元素
    private static void wrap(int[] array, int low, int high) {
        // TODO Auto-generated method stub
        int temp = array[low];
        array[low] = array[high];
        array[high] = temp;
    }
	
	
	
	
	
	
	
	
	
	
	
//	冒泡排序：比较O(N2)　数据交换O(N2)
//
//	选择排序：比较O(N2)　数据交换O(N)
//
//	插入排序：比较O(N2)　复制数据O(N)
//第一名：快速排序
//第二名：插入排序
//第三名：选择排序
//第四名：冒泡排序
	
	

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
		int max=0,count=0;
		int hang[]=new int[100];
		int lie[]=new int[100];  //这里的定义一定要这样嘛？？感觉好费空间！！
		//是的，一定要这样，避免不了这种浪费，所以只能自己合理确定数组的大小，这样才能尽量避免浪费
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				if(max<=array[i][j])
				{
					max=array[i][j];
					
				}
				System.out.print(array[i][j] + "\t");
			}
			System.out.println();
		}
		for (int i = 0; i < array.length; i++) {       //找所有的最大的数的位置
			for (int j = 0; j < array[i].length; j++) {
			   if(max==array[i][j])
			   {
				   ++count;
				   hang[count]=i+1;
				   lie[count]=j+1;
				//调试时用   System.out.println("count="+count);
			   }
			
				
			}
			}
		
		
		
		System.out.println("max="+max);
		for(int k=1;k<=count;k++)
		{
			System.out.println(" 行数等于"+hang[k]+"  列数等于"+lie[k]);
		}
		
		
	}

}
