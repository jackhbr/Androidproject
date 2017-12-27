package arrayList;


/**
 * 自定义数组队列类
 * 
 * @author 熊哥
 * 
 */
public class MyArrayList<E> {

	private int size;// 定义size属性，用来记录数组队列中存储的元素总数

	private Object[] array = new Object[0];// 定义Object数组

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

		// 将newArray数组名的地址赋给array
		array = newArray;

		size++;// 计数器加1
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
