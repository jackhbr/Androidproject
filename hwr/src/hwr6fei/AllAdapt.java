package hwr6fei;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;

public abstract class AllAdapt extends MouseAdapter implements ActionListener ,KeyListener,ItemListener{
	public void actionPerformed(ActionEvent e){}
	 public void keyTyped(KeyEvent e){}

	    public void keyPressed(KeyEvent e){}

	   
	    public void keyReleased(KeyEvent e){}
	    
	  public  void itemStateChanged(ItemEvent e){}

}
