package client;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataOutputStream;
import java.io.IOException;
/**
 * 客户端的事件监听类，把鼠标信息传输到服务端，配合client1来使用
 * @author jack
 *
 */
public class Mouselisten implements MouseListener{
	int x[]=new int[2];
	DataOutputStream dos;

	public Mouselisten(DataOutputStream dos)
	{
		this.dos=dos;
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		
		  int button=e.getButton(); //看此时是左键还是右键或者是中键按下
		  System.out.println("按下的键值button为"+button);
		// TODO Auto-generated method stub
		x[0]=e.getX();x[1]=e.getY();//这个是相对于你的窗体原点而言的坐标
		System.out.println("客户端的x="+x[0]+"y="+x[1]);
		
		switch (button) {
		case 1:try {   //1是鼠标左键点击
				mouseLeftClick(x[0], x[1]);   
				System.out.println("左键点击一次");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			break;
		case 3:try {   //1是鼠标左键点击
			mouseRightClick(x[0], x[1]);   
			System.out.println("右键点击一次");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
			break;

		default:
			break;
		}
		
		
//		x=e.getXOnScreen();y=e.getYOnScreen();  //这个是相对于电脑桌面（最左上角）而言获得的坐标
//		System.out.println("onscreenX="+x+"y="+y);
//		try {    
////			dos.writeInt(1);
////			dos.writeInt(10);
////			dos.writeInt(x[0]);
////			dos.writeInt(20);
////			dos.writeInt(x[1]);
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//	}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void mouseLeftClick(int x,int y) throws IOException//是时候用函数模块化了
	{
		dos.writeInt(1);
		dos.writeInt(10);
		dos.writeInt(x);
		dos.writeInt(20);
		dos.writeInt(y);
	}
	public void mouseRightClick(int x,int y) throws IOException
	{
		dos.writeInt(2);
		dos.writeInt(10);
		dos.writeInt(x);
		dos.writeInt(20);
		dos.writeInt(y);
	}

}
