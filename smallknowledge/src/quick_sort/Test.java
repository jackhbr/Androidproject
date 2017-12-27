package quick_sort;


 public class Test {
 
    /**
      * @param args
 7      */
     public static void main(String[] args) {
         int[] array = { 43, 64, 21, 6565, 3424, 22, 6523, 345 };
        ArraySort(array, 0, array.length - 1);
        for (int i : array) {
            System.out.print(i + " ");
         }
     }
 
     // 快速排序方法
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
 }