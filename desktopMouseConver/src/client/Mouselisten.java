package client;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataOutputStream;
import java.io.IOException;
/**
 * �ͻ��˵��¼������࣬�������Ϣ���䵽����ˣ����client1��ʹ��
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
		
		  int button=e.getButton(); //����ʱ����������Ҽ��������м�����
		  System.out.println("���µļ�ֵbuttonΪ"+button);
		// TODO Auto-generated method stub
		x[0]=e.getX();x[1]=e.getY();//������������Ĵ���ԭ����Ե�����
		System.out.println("�ͻ��˵�x="+x[0]+"y="+x[1]);
		
		switch (button) {
		case 1:try {   //1�����������
				mouseLeftClick(x[0], x[1]);   
				System.out.println("������һ��");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			break;
		case 3:try {   //1�����������
			mouseRightClick(x[0], x[1]);   
			System.out.println("�Ҽ����һ��");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
			break;

		default:
			break;
		}
		
		
//		x=e.getXOnScreen();y=e.getYOnScreen();  //���������ڵ������棨�����Ͻǣ����Ի�õ�����
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
	public void mouseLeftClick(int x,int y) throws IOException//��ʱ���ú���ģ�黯��
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
