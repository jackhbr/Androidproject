package arraylist;
import java.util.ArrayList;



/**
 * �Զ������������
 * 
 * @author �ܸ�
 * 
 */
//��ӷ��;�������������ӷ��ţ��ں��������ʱ��Ĳ����ͷ���ֵ��Ϊ���ͣ�����������漰���͵Ĳ�������object��������
//�������з���ֵ�ľͻ��Ƿ��ط���
public class MyArrayList<E> {

	private int size;// ����size���ԣ�������¼��������д洢��Ԫ������
                     //��add��remove��ı��������ֵ
	private Object[] array = new Object[0];// ����Object���飬���ж���ĸ��࣬���Կ���ת��

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

		// ��newArray�������ĵ�ַ����array,�������Ҫ
		array = newArray;

		size++;// ��������1
	}

	/**
	 * �����������ָ��λ�����Ԫ�صķ���
	 * 
	 * @param indexҪָ��λ��Ԫ�ص�����
	 * @return 
	 */
	public boolean add(int index ,E e)
	{
		if (index < 0 || index >= size)
			return false;
		
		// �����µ�Object���飬������array����ĳ��ȼ�1
				Object[] newArray = new Object[array.length + 1];
				for (int i = 0; i < index; i++) {
					newArray[i] = array[i];
				}
				newArray[index] = e;
				for (int i = index+1; i < newArray.length; i++) {
					newArray[i] = array[i-1];
				}
				array = newArray;

				size++;// ��������1
				
		return true;
	}
	
	//����ĳ��λ�õ�ֵ
	boolean update(int index,E e)
	
	{
		if (index < 0 || index >= size)
			return false;
		array[index]=e;
		
		return true;
				
		}
	
	//����ĳ�����еĹ涨��ֵ

	boolean update(E e,E newE)
	{
		int flag=0;//��һ����־λ���Ϳ��Ա������һ����ͬ��ֱ���˳���������
		
		
		if(e.getClass().getName().equals("java.lang.String")) //�ж�������ǲ����ַ���
    	{
    		System.out.println("�������string");
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
    		System.out.println("����Ĳ���string");
    		 for (int index = 0; index < size; index++)
    		 {
	    		if(array[index]==e)
	    		{
	    			array[index] = newE;
	    			flag=1;   //�ظ��ĸ���ͬ��ֵ��������κ�����
	    		}
    		 }
		}
	           
	            //������ַ���������equals,����������������á�==��
	            	/**
	                if (array[index]==e) {      ��ַ�����һ�����������"����b"��һ������ǵ�ַstr,�����ַ�������ǡ�����b��
	                	array[index] = newE;     �����á�==���ǲ��Եģ���equals
	                    flag=1;                  ���������£���������ַ�����������==��
	                   
	                */		
		 if(flag==0)
	        return false;
		 else
			 return true;
		
		
	}
	
	
	
	
	//�ο�arraylist���ƹ����Ĵ��룬�Ƴ����е�ĳ��Ԫ��
	
	boolean remove(E e)
	{
		int flag=0;//��һ����־λ���Ϳ��Ա������һ����ͬ��ֱ���˳���������
			
		
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
