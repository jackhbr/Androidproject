package oneBall;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

public class MouseListener extends AllAdapt{
	
//	private Graphics2D g;
	private JFrame jframe;
	
	public void setjframe(JFrame jframe) {
		this.jframe=jframe;
       // this.g=(Graphics2D)this.jframe.getGraphics();
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		
	    threadDemo jDemo=new threadDemo();
	    jDemo.setjframe(jframe);
	    Thread jk=new Thread(jDemo);
	    jk.start();
	    
	    
	}
	 public void mouseClicked(MouseEvent e) 
	 {
		 
	 }
}
