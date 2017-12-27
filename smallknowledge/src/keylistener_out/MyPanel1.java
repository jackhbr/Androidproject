package keylistener_out;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class MyPanel1 extends JPanel implements KeyListener {

	
	 int x = 10;
	    int y = 10;
//
//	    public MyPanel1() {
//	        super();
//	    }

	    @Override
	    public void paint(Graphics g) {
	        super.paint(g);
	        g.setColor(Color.red);
	        g.fillOval(x, y, 20, 20);
	        System.out.println("paint()");
	    }

	    @Override
	    public void keyTyped(KeyEvent e) {
	        // TODO Auto-generated method stub

	    }

	    @Override
	    public void keyPressed(KeyEvent e) {
	        if ( e.getKeyCode()==KeyEvent.VK_DOWN ){
	            y = y+10;
	        } else if (e.getKeyCode()==KeyEvent.VK_UP){
	            y--;
	        } else if (e.getKeyCode()==KeyEvent.VK_RIGHT){
	            x = x+10;
	        } else if (e.getKeyCode()==KeyEvent.VK_LEFT){
	            x--;
	        } else {

	        }
	      this.repaint();//������������󣬻�Ĭ�ϼ�������paint����
	        //��֮������ȫ��������Ȼ�������»��Ǹ�Բ
	       // paint(g);
	       System.out.println("oo");
	    }

	    @Override
	    public void keyReleased(KeyEvent e) {

	    }

}
