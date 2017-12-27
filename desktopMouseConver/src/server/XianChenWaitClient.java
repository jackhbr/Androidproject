package server;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.DataInputStream;
import java.io.IOException;
/**
 * 另外开启的线程来等待鼠标信息的传过来
 * @author jack
 *
 */
public class XianChenWaitClient implements Runnable  {
	
	DataInputStream di;
	Robot ro;
	int x,y;
	public XianChenWaitClient(DataInputStream di)
	{
		this.di=di;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			 ro=new Robot();
		} catch (AWTException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		while(true)
		{
			int flag=0;
			try {
				flag=di.readInt();    //Connection reset????
				
				System.out.println("进入鼠标传输,flag="+flag);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			switch (flag) {
			case 1:mouseLeftClickAccept();
				
				break;
			case 2:mouseRightClickAccept();
				
				break;

			default:
				break;
			}
			
//			if(flag==1)
//			{
//				
//				try {
//					flag=di.readInt();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				if(flag==10)
//				{
//					try {
//						x=di.readInt();
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					System.out.println("服务端接收到的x="+x);
//				}
//				
//				try {
//					flag=di.readInt();//同时传输y和x
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				
//				 if(flag==20)
//				{
//					try {
//						y=di.readInt();
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					System.out.println("服务端接收到的y="+y);
//				}
//				 ro.mouseMove(x, y);
//				 ro.mousePress(InputEvent.BUTTON1_MASK );
//				 ro.mouseRelease(InputEvent.BUTTON1_MASK);
//			}
		}
		
	}
	
	public void mouseLeftClickAccept()
	{
			int flag=0;
			System.out.println("进入鼠标左键点击");
		try {
			flag=di.readInt();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(flag==10)
		{
			try {
				x=di.readInt();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("服务端接收到的x="+x);
		}
		
		try {
			flag=di.readInt();//同时传输y和x
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		 if(flag==20)
		{
			try {
				y=di.readInt();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("服务端接收到的y="+y);
		}
		 ro.mouseMove(x, y);
		 ro.mousePress(InputEvent.BUTTON1_MASK );
		 ro.mouseRelease(InputEvent.BUTTON1_MASK);
		
	}
	public void mouseRightClickAccept()
	{
		int flag=0;
		System.out.println("进入鼠标左键点击");
	try {
		flag=di.readInt();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	if(flag==10)
	{
		try {
			x=di.readInt();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("服务端接收到的x="+x);
	}
	
	try {
		flag=di.readInt();//同时传输y和x
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	 if(flag==20)
	{
		try {
			y=di.readInt();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("服务端接收到的y="+y);
	}
	 ro.mouseMove(x, y);
	 ro.mousePress(InputEvent.BUTTON3_MASK );
	 ro.mouseRelease(InputEvent.BUTTON3_MASK);
	}

}
