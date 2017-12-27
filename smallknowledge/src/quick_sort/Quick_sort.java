package quick_sort;

  
  
import java.util.Scanner;  
class Sort_Array{  
    //int[] s;  
    //int i,j,key;  
    /*���캯������������û����������*/  
    public Sort_Array(int[] s){                   
        System.out.println("����������ǣ�");  
        for(int i = 0; i <s.length; i++){  
            System.out.print(s[i] + " ");  
        }  
        System.out.println();  
    }  
    /*������������ΪҪ��������顢�������ʼ�������ĩβ*/  
    public void sortfun(int[] arrays,int start, int end){  
          
        if(start>=end){                              //�ж��������ʼ����ֹ�Ƿ���ͬ����ͬ��ʾ�Ѿ���ȫ�����꣬����  
            return;  
        }  
        int i = start;                              //iָ���������ʼλ  
        int j = end;                                //jָ�������ĩλ  
        int key = arrays[i];                        //ѡȡ����ĵ�һλΪ�ؼ���key����׼Ԫ��  
        boolean flag = true;                        //���ñ�־λ�������ж���i++����j--;�������Ҫ  
        //int temp;  
        while(i != j){                              //���i��j����ʾ��û�бȽ��꣬�����ؼ����������໹������С�����  
            if(flag){                     
                if(key>arrays[j]){                   //�Ӻ���ǰ�������ҵ�С��key��ֵ��  
                    swap(arrays,i,j);               //�ҵ�С��key��ֵ��arrays[i]���ֵ����  
                    flag = false;  
                }else{                              //���û���ҵ��Ļ�j--����ǰ����  
                    j--;  
                }  
            }else{                                
                if(key<arrays[i]){                   //��ǰ���������ҵ�����key��ֵ  
                    swap(arrays,i,j);               //����ֵ��arrays[j]���н���  
                    flag = true;  
                }else{                              //���û���ҵ����ͽ�i++,������  
                    i++;  
                }  
            }  
        }  
        sprint(arrays);                             //��ӡÿ������������  
        sortfun(arrays,start,j-1);                  //�ݹ���ã�����׼Ԫ�ص�ǰ����������ô˷�����������ֱ�����ж�����Ϊֹ��  
        sortfun(arrays,i+1,end);                    //�ݹ���ã�����׼Ԫ�صĺ����������ô˷�����������ֱ�����ж�����Ϊֹ��  
//      sortfun(s[0],s[j],s[0]);  
//      sortfun(s[j+1],s[s.length-1],s[j+1]);  
    }  
    /*�������������ڽ��������е�����ֵ��easy,*/  
    public void swap(int[] array,int i,int j){            
        int temp;  
        temp = array[i];  
        array[i] = array[j];  
        array[j] = temp;  
    }  
    /*sprint()�������ڴ�ӡÿ�������Ľ�����Ǳ��룬��������ʾÿ���������*/  
    public void sprint(int[] arrays){             
        System.out.println("�����������ǣ�");  
        for(int i = 0; i <arrays.length;i++){  
            System.out.print(arrays[i] + " ");  
        }  
        System.out.println();   
    }  
      
    /*�ڶ���д����*/  
    private int getMiddle(int[] sortArray,int low,int high){  
        int key = sortArray[low];  
        while(low<high){  
            while(low <high && sortArray[high] >= key){  
                high--;  
            }  
            sortArray[low] = sortArray[high];  
            while(low < high && sortArray[low] <= key){  
                low++;  
            }  
            sortArray[high] = sortArray[low];  
        }  
        sortArray[low] = key;  
        return low;  
    }  
    public void quicksort(int[] sortArray, int low,int high){  
        if(low<high){  
            int middle = getMiddle(sortArray, low, high);  
            quicksort(sortArray, low, middle-1);  
            quicksort(sortArray, middle+1, high);  
        }  
        System.out.println("�ڶ���д���������");  
        sprint(sortArray);  
    }  
}  
public class Quick_sort {  
    public static void main(String args[]){  
    Scanner sc = new Scanner(System.in);            //�Ӽ�����������  
        System.out.println("������Ҫ��������飺");  
        String str = sc.nextLine();                     //����������ת��Ϊ�ַ���  
        String[] temp = str.split(" ");                 //���ַ����á� ���ֿ�ת��Ϊ�ַ�������  
        int[] s = new int[temp.length];                 //����һ����������s  
        for(int i = 0; i<temp.length; i++){              //���ַ�������ǿ��ת��Ϊ��������  
            s[i] = Integer.parseInt(temp[i]);           //���ַ����ǳ�����  
        }  
        Sort_Array sort_array = new Sort_Array(s);      //�������󣬲���������s  
        sort_array.sortfun(s, 0, s.length-1);           //������ķ�����������������  
      
        Sort_Array quickSort = new Sort_Array(s);  
        quickSort.quicksort(s, 0, s.length-1);  
          
        /*�ü��ϵķ������벻�������飺 
        Scanner scanner = new Scanner(System.in);           //��������ɨ���� 
        System.out.println("������Ҫ��������飺"); 
        List  list = new LinkedList(); 
        while(scanner.hasNext()){                       //ѭ������ɨ�赽����һ��Ԫ�ص�ʱ�� 
            int elements = scanner.nextInt();               //�����һ��Ԫ�ز���Ϊ���� 
            if(elements == 0){                              //�������0,��������� 
                break;                                  //�˳�whileѭ�� 
            }else{ 
                list.add(elements);                     //������뼯���� 
            } 
        } 
        Integer[] intArray =  list.toArray(new Integer[0]); 
        for(int i = 0; i<intArray.length;i++){ 
            System.out.println(intArray[i]); 
        } 
    */  
    }  
}  
