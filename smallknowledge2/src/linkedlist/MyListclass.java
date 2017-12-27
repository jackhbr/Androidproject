package linkedlist;

public class MyListclass<E> implements Mylist<E> {

	private node<E> header;
	private node<E> tail;
	private int size;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyListclass<Integer> ak=new MyListclass<Integer>();
		ak.add(33);
		ak.add(34);
		ak.add(44);
		for(int i=0;i<ak.getlength();i++)
		{
			System.out.println(ak.get(i));
		}

	}

	@Override
	public void add(E data) {
		// TODO Auto-generated method stub
		node<E> newNode=new node<E>(data);
		if(size==0)
		{
			header =newNode;
			tail=header;
		}
		size++;
		
		tail.set(newNode);  //把新的节点放在最后尾节点的后面
		tail=newNode; //把尾节点指向新加的节点
		
		
	}

	@Override
	public E get(int index) {
		// TODO Auto-generated method stub
		node<E> temp=header;
		for(int i=0; i<index;i++)
		{
			temp=temp.get();
		
		}
		return temp.getDataE();
		
	}

	@Override
	public int getlength() {
		// TODO Auto-generated method stub
		return size;
	}

}
