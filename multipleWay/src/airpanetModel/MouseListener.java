package airpanetModel;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

public class MouseListener extends AllAdapt{
	
	private Graphics2D g;
	private JFrame jframe;
	public int xb=0,yb=0,flag=0;
	
	public void setjframe(JFrame jframe) {
		this.jframe=jframe;
        this.g=(Graphics2D)this.jframe.getGraphics();
     
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		flag=0;
	    threadDemo jDemo=new threadDemo();
	    jDemo.setjframe(jframe,flag);
	    Thread jk=new Thread(jDemo);
	    jk.start();
	    
	    
	}
	 public void mouseClicked(MouseEvent e) 
	 {
		 
	 }
	 
	 public void mouseDragged(MouseEvent e)
	 {
		  xb=e.getX();
		  yb=e.getY();
		  flag=1;
		  threadDemo jDemo=new threadDemo();
		 jDemo.setxy(jframe,xb,yb,flag);
		  System.out.println(xb+"  "+yb);
		  Thread jk=new Thread(jDemo);
		    jk.start();
//		    
		    
//		 g.setColor(Color.BLACK);
//		 System.out.println("x="+x+"  y="+y);
//		 g.fillOval(x, y, 80, 80);
	 }
	 
	 public void mousePressed(MouseEvent e) 
	 {
		 System.out.println("ÄãºÃ");
	 }
	 
}
