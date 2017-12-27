package array;



import java.util.Arrays;
import java.util.Random;
//һ�����������򷽷���ð�ݣ�ѡ�񣬲��룬ϵͳ�Դ��Ŀ�������
//����ʵ�ֶ�ά����������е��������λ��
public class ArrayDemo {

	public static void main(String[] args) {
		 int[] array = createArray(10);
		 printArray(array);
		 Arrays.sort(array);
		 System.out.println("�����Ľ����");
		 printArray(array);
//		int[][] array = createArray(5, 10);
//		printArray(array);
//		int[] array1= createArray(10000);
//		
//		printArray(array1);
		
		//Arrays.sort(array1);   ������������ڲ������Ŀ������򷽷�
	//	long starTime=System.currentTimeMillis();
		//insertSort(array1);   10000��������40ms
	//	Arrays.sort(array1); // 10000��������1ms
		//bubbleSort(array1);    10000��������227ms
		 // sort(array1);      10000��������52ms   
		
		//ArraySort(array1, 0, array1.length - 1); ������򷽷���ϵͳ�Դ������򷽷����ǿ������򷽷�������ʱ�䶼��һ����
		
//		���˵Ĳ��Խ�����������ҵ�һ��
//		��������10000
//		ð������120ms
//		ѡ������32ms
//		��������20ms
//		��������7ms
//		 long endTime=System.currentTimeMillis();
//		  long Time=endTime-starTime;
//		  System.out.println(Time);
//		printArray(array1);
		
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

	
	//ѡ������  �ܸ�汾
	public static void sort(int[] array) {
		for (int i = 0; i < array.length - 1; i++) {
			int min = i;// ����iλ�õ�����С������
			for (int j = i + 1; j < array.length; j++) {  //ÿ�ζ���ȫ��������ѡ����С����������ǰ�棬ѡ�����һ���ͽ�������
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
	
	
	

	
	
	
	
	//ð������
	public static void bubbleSort(int a[]) {    //����һ��Ҫ�Ӹ�static�ſ���
		  
	    int n = a.length;   
	  
	    for (int i = 0; i < n - 1; i++) {    //ÿһ�ζ������ֵ�������ţ�����n�κ�����������
	                                         //���ڵ��������Ƚϣ�����������
	      for (int j = 0; j < n - 1; j++) {   
	  
	        if (a[j] > a[j + 1]) {   
	  
	          int temp = a[j];   
	  
	          a[j] = a[j + 1];   
	  
	          a[j + 1] = temp;   
	  
	        }   
	  
	      }   
	  
	    }   
	  
	}   
	
	//��������
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

    

    // ��������Ԫ��
    private static void wrap(int[] array, int low, int high) {
        // TODO Auto-generated method stub
        int temp = array[low];
        array[low] = array[high];
        array[high] = temp;
    }
	
	
	
	
	
	
	
	
	
	
	
//	ð�����򣺱Ƚ�O(N2)�����ݽ���O(N2)
//
//	ѡ�����򣺱Ƚ�O(N2)�����ݽ���O(N)
//
//	�������򣺱Ƚ�O(N2)����������O(N)
//��һ������������
//�ڶ�������������
//��������ѡ������
//��������ð������
	
	

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
		int max=0,count=0;
		int hang[]=new int[100];
		int lie[]=new int[100];  //����Ķ���һ��Ҫ��������о��÷ѿռ䣡��
		//�ǵģ�һ��Ҫ���������ⲻ�������˷ѣ�����ֻ���Լ�����ȷ������Ĵ�С���������ܾ��������˷�
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
		for (int i = 0; i < array.length; i++) {       //�����е���������λ��
			for (int j = 0; j < array[i].length; j++) {
			   if(max==array[i][j])
			   {
				   ++count;
				   hang[count]=i+1;
				   lie[count]=j+1;
				//����ʱ��   System.out.println("count="+count);
			   }
			
				
			}
			}
		
		
		
		System.out.println("max="+max);
		for(int k=1;k<=count;k++)
		{
			System.out.println(" ��������"+hang[k]+"  ��������"+lie[k]);
		}
		
		
	}

}
