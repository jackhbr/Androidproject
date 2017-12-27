package hwr3;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.naming.InitialContext;
import javax.swing.JFrame;

public class Aclistener extends AllAdapt{  //���������ǿ��Թ���һ���¼�������ģ�����
	
	private JFrame jf;
	private int x1,x2,y1,y2;//x1 y1�ǰ���ʱ�����꣬x2 y2���϶�ʱ������
	int[][] chessValue = new int[Config.ROWS][Config.COLUMNS];//���Ȩֵ
	private int exeflag=0;
	private String num;
	
	public Aclistener()
	{
		init();
	}
	
	public void init()
	{
		for(int i=0;i<Config.ROWS;i++)
		{
			for(int j=0;j<Config.COLUMNS;j++)
			{
				chessValue[i][j]=0;
			}
		}
	}
	
	public void setPa(JFrame jf)
	{
		this.jf=jf;
	}
	
	 public void actionPerformed(ActionEvent e)
	 {
		 String type=e.getActionCommand();
		 if(type.equals("д��"))
		 {
			 System.out.println("д��");
			 exeflag=0;
			 JFrame jk=new JFrame();//������jf������jf����Ҫ������
			 jk.setTitle("��дʶ���");
			 jk.setSize(400, 420);
			 jk.setDefaultCloseOperation(2);
			 jk.setLocationRelativeTo(null);
			 jk.addMouseMotionListener(this); //�������干��һ���¼�������
			 jk.addMouseListener(this);//�϶��Ͱ��µ���������
			 jf.setResizable(false);  //false���ǲ��ɸı��С
			 jk.setVisible(true);
		 }
		 else if(type.equals("�������"))
		 {
			 System.out.println("�������");
			 for(int i=0;i<Config.ROWS;i++)
				{
					for(int j=0;j<Config.COLUMNS;j++)
					{
						System.out.print(chessValue[j][i]+""); //����������꣬����������꣬����ʱ�Ȱ�ǰ��������������
					}
					System.out.println();
				}
		 }
		 else if(type.equals("����ѵ��"))
		 {
			 System.out.println("����ѵ��");
			 exeflag=1;
			 JFrame jk=new JFrame();//������jf������jf����Ҫ������
			 jk.setTitle("��дʶ���");
			 jk.setSize(400, 420);
			 jk.setDefaultCloseOperation(2);
			 jk.setLocationRelativeTo(null);
			 jk.addMouseMotionListener(this); //�������干��һ���¼�������
			 jk.addMouseListener(this);//�϶��Ͱ��µ���������
			 jf.setResizable(false);  //false���ǲ��ɸı��С
			 jk.setVisible(true);
			 
		 }
	 }
	 public void mouseDragged(MouseEvent e)
	 {
		 x2=e.getX();
		 y2=e.getY();
		 System.out.println("�϶�ʱ��x="+x2+"  y="+y2);
		 
		 for(int i=0;i<Config.ROWS;i++)
			{
				for(int j=0;j<Config.COLUMNS;j++)
				{
					
					int x=Config.X0+Config.SIZE*i;//������ ,��Ӧ��������,xΪˮƽ���ң�yΪ��ֱ����
					int y=Config.Y0+Config.SIZE*j;//������
					//�����ĵ����Ϊsize/2
					if(x2>x-Config.SIZE/2 && x2<x+Config.SIZE/2 && y2>y-Config.SIZE/2 && y2<y+Config.SIZE/2)
					{
						chessValue[i][j]=1;
					}
				}
				
			}
		 
		 
	 }
	 public void mousePressed(MouseEvent e)   //ע�ⴰ��������ı߿�Ҳ���ڴ����С�ڣ��������������ʱȴ���������¼�
	 {
		 x1=e.getX();
		 y1=e.getY();
		 System.out.println("����ʱ��x="+x1+"  y="+y1);
	 }

}
