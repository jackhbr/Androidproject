package arrayList;


/**
 * �Զ������������
 * 
 * @author �ܸ�
 * 
 */
public class MyArrayList<E> {

	private int size;// ����size���ԣ�������¼��������д洢��Ԫ������

	private Object[] array = new Object[0];// ����Object����

	/**
	 * ��������������Ԫ�صķ���
	 * 
	 * @param eҪ��ӵ��µ�Ԫ��
	 */
	public void add(E e) {
		// �����µ�Object���飬������array����ĳ��ȼ�1
		Object[] newArray = new Object[array.length + 1];
		// ��array�����е����ݴ��뵽newArray������
		for (int i = 0; i < array.length; i++) {
			newArray[i] = array[i];
		}
		// ��e���ݴ��뵽newArrayĩβ
		newArray[array.length] = e;

		// ��newArray�������ĵ�ַ����array
		array = newArray;

		size++;// ��������1
	}

	/**
	 * ������������Ƴ�Ԫ�صķ���
	 * 
	 * @param indexҪ�Ƴ�Ԫ�ص�����
	 * @return �����Ƴ���Ԫ�ض���
	 */
	public E remove(int index) {
		if (index < 0 || index >= size)
			return null;
		// �����µ�Object���飬������array����ĳ��ȼ�1
		Object[] newArray = new Object[array.length - 1];
		// ��array�����е����ݴ��뵽newArray������
		for (int i = 0; i < index; i++) {
			newArray[i] = array[i];
		}
		for (int i = index + 1; i < array.length; i++) {
			newArray[i - 1] = array[i];
		}
		// ��ȡindexλ�õ�����
		Object str = array[index];
		// ��newArray�������ĵ�ַ����array
		array = newArray;
		size--;// ��������1
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
