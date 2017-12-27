package arraylist;
import java.util.ArrayList;



/**
 * 自定义数组队列类
 * 
 * @author 熊哥
 * 
 */
//添加泛型就是在类名后添加符号，在函数定义的时候的参数和返回值变为泛型，函数里面的涉及类型的操作都由object来操作，
//最后如果有返回值的就还是返回泛型
public class MyArrayList<E> {

	private int size;// 定义size属性，用来记录数组队列中存储的元素总数
                     //在add和remove里改变这个的数值
	private Object[] array = new Object[0];// 定义Object数组，所有对象的父类，所以可以转换

	/**
	 * 向数组队列中添加元素的方法
	 * 
	 * @param e要添加的新的元素
	 */
	public void add(E e) {
		// 定义新的Object数组，长度是array数组的长度加1
		Object[] newArray = new Object[array.length + 1];
		// 把array数组中的数据存入到newArray数组中
		for (int i = 0; i < array.length; i++) {
			newArray[i] = array[i];
		}
		// 将e数据存入到newArray末尾
		newArray[array.length] = e;

		// 将newArray数组名的地址赋给array,这个很重要
		array = newArray;

		size++;// 计数器加1
	}

	/**
	 * 从数组队列中指定位置添加元素的方法
	 * 
	 * @param index要指定位置元素的索引
	 * @return 
	 */
	public boolean add(int index ,E e)
	{
		if (index < 0 || index >= size)
			return false;
		
		// 定义新的Object数组，长度是array数组的长度加1
				Object[] newArray = new Object[array.length + 1];
				for (int i = 0; i < index; i++) {
					newArray[i] = array[i];
				}
				newArray[index] = e;
				for (int i = index+1; i < newArray.length; i++) {
					newArray[i] = array[i-1];
				}
				array = newArray;

				size++;// 计数器加1
				
		return true;
	}
	
	//更换某个位置的值
	boolean update(int index,E e)
	
	{
		if (index < 0 || index >= size)
			return false;
		array[index]=e;
		
		return true;
				
		}
	
	//更换某个所有的规定的值

	boolean update(E e,E newE)
	{
		int flag=0;//用一个标志位，就可以避免出现一次相同就直接退出程序的情况
		
		
		if(e.getClass().getName().equals("java.lang.String")) //判断输入的是不是字符串
    	{
    		System.out.println("输入的是string");
    		 for (int index = 0; index < size; index++)
    		 {
	    		if(array[index].equals(e))
	    		{
	    			array[index] = newE;
	    			flag=1; 
	    		}
    		 }
    	}
    	else 
    	{
    		System.out.println("输入的不是string");
    		 for (int index = 0; index < size; index++)
    		 {
	    		if(array[index]==e)
	    		{
	    			array[index] = newE;
	    			flag=1;   //重复的赋相同的值不会产生任何问题
	    		}
    		 }
		}
	           
	            //如果是字符串，则用equals,如果是其他对象，则用“==”
	            	/**
	                if (array[index]==e) {      地址里面的一个存的是数据"姓名b"，一个存的是地址str,这个地址里面存的是“姓名b”
	                	array[index] = newE;     所以用“==”是不对的，用equals
	                    flag=1;                  大多数情况下，如果不是字符串，都是用==。
	                   
	                */		
		 if(flag==0)
	        return false;
		 else
			 return true;
		
		
	}
	
	
	
	
	//参考arraylist复制过来的代码，移除所有的某个元素
	
	boolean remove(E e)
	{
		int flag=0;//用一个标志位，就可以避免出现一次相同就直接退出程序的情况
			
		
		 if (e == null) {
	            for (int index = 0; index < size; index++){
	            
	                if (array[index] == null) {
	                    remove(index);
	                    flag=1;
	                   
	                } 
	            }
	        } else {
	            for (int index = 0; index < size; index++)
	                if (e.equals(array[index])) {
	                    remove(index);
	                    flag=1;
	                }
	        }
		
		 if(flag==0)
	        return false;
		 else
			 return true;
	        		
	}
	
	
	
	
	
	
	/**
	 * 从数组队列中移除元素的方法
	 * 
	 * @param index要移除元素的索引
	 * @return 返回移除的元素对象
	 */
	public E remove(int index) {
		if (index < 0 || index >= size)
			return null;
		// 定义新的Object数组，长度是array数组的长度减1
		Object[] newArray = new Object[array.length - 1];
		// 把array数组中的数据存入到newArray数组中
		for (int i = 0; i < index; i++) {
			newArray[i] = array[i];
		}
		for (int i = index + 1; i < array.length; i++) {
			newArray[i - 1] = array[i];
		}
		// 获取index位置的数据
		Object str = array[index];
		// 将newArray数组名的地址赋给array
		array = newArray;
		size--;// 计数器减1
		return (E) str;
	}

	public E get(int index) {
		if (index < 0 || index >= size)
			return null;
		return (E) array[index];
	}

	public int size() {
		return size;
	}
}
