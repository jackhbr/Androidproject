package linkedlist;

public class node<E> {
	private node<E> next;
	private  E dataE;
	public node(E dataE) {
		super();
		this.dataE = dataE;
	
	}
	public E getDataE() {
		return dataE;
	}
	
	public void set(node<E> next)
	{
		this.next=next;
	}
	public node<E> get()
	{
		return next;
	}
	
	

}
