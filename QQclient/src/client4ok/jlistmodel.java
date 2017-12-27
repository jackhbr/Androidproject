package client4ok;

import java.util.ArrayList;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

public class jlistmodel implements ListModel<Client>{

	 private ArrayList<Client> client; 
	 public jlistmodel(ArrayList<Client> client)
	 {
		 this.client=client;
	 }
	public int getSize() {
		return client.size();
	}

	public Client getElementAt(int index) {
		return client.get(index);
	}

	public void addListDataListener(ListDataListener l) {
		
	}

	public void removeListDataListener(ListDataListener l) {
		
	}

}
