package saveuserdata;

import java.util.ArrayList;
import java.util.jar.Attributes.Name;

import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

public class jlistmodel implements ListModel<String>{

	 public  ArrayList<String> name;
//	static{  //静态块的使用，得新建对象
//		jlistmodel jjJlistmodel=new jlistmodel();
//		jjJlistmodel.name.add("zasda");
//		jjJlistmodel.name.add("小泽");
//	}
//	
	//静态方法只能使用静态的对象和变量
	 
	 public jlistmodel(ArrayList<String> name)
	 {
		 this.name=name;
	 }
	
	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return name.size();
	}

	@Override
	public String getElementAt(int index) {
		// TODO Auto-generated method stub
		return name.get(index);
	}

	@Override
	public void addListDataListener(ListDataListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeListDataListener(ListDataListener l) {
		// TODO Auto-generated method stub
		
	}

}
