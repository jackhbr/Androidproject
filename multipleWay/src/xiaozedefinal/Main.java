package xiaozedefinal;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import java.awt.FlowLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public  class Main extends JFrame implements KeyListener,ActionListener{
	MyPlane m=new MyPlane();
  
    private PlaneThread pt=null;
    public volatile boolean flag;
    File file=new File("D:\\Android\\phb.txt");
    //JFrame jf=new JFrame();

    private int[]score=new int[4];
    private int[] biao=new int[1];
	private ArrayList<EnemyPlane> al=new ArrayList<EnemyPlane>();
    private ArrayList<Bullet> bl=new ArrayList<Bullet>();
    BulletThread bThread=new BulletThread(bl, m);
    EnemyPlaneThread epThread=new EnemyPlaneThread(this,al);

	
	public static void main(String[] args) {
         new Main().showUI();
         new Music();
	}

	private void showUI() {
		 
		 this.setTitle("飞机大战");
		 this.setSize(800, 900);
		 // 设置退出进程的方法
		 this.setDefaultCloseOperation(3);
		 // 设置居中显示
		 this.setLocationRelativeTo(null);
		 //添加按钮
		 final Button jbu = new Button("开始");
		 Button jbu1= new Button("暂停");
		 Button jbu2 = new Button("继续");
		 Button jbu3 = new Button("排行榜");
		 this.add(jbu);
		 this.add(jbu1);
		 this.add(jbu2);
		 this.add(jbu3);
		 
		 FlowLayout flowLayout=new FlowLayout();
		 this.setLayout(flowLayout);	
		 ImageIcon background = new ImageIcon(this.getClass().getResource("44_1117.jpg"));
		 JLabel label = new JLabel(background); 
		 label.setBounds(0, 0, this.getWidth(), this.getHeight());  
		 JPanel imagePanel = (JPanel) this.getContentPane();
		 imagePanel.setOpaque(false);
		 this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
         
		 
		 this.setVisible(true);
		 this.addKeyListener(this);
		   
		    jbu.addActionListener(this);
			jbu1.addActionListener(this);
			jbu2.addActionListener(this);
			jbu3.addActionListener(this);
	}
		 //ActionListener匿名内部类
		
		 public void actionPerformed(ActionEvent e) {
				String str=e.getActionCommand();
				if(str.equals("开始")){
					//jbu.setLabel("重新开始");
					this.requestFocus();
					if(pt==null){
						   pt=new PlaneThread(this,m);
						   pt.setFile(file);
						   pt.setBiao(biao);
						   pt.setCount(score);
						   pt.setAl(al);
						   pt.setBl(bl);
						   Thread td=new Thread(pt);
						   Thread td1=new Thread(epThread);
						   Thread td2=new Thread(bThread);
						   td2.start();
						   td1.start();
						   td.start();
						}
			
				
				}else if(str.equals("暂停")){
							this.requestFocus();
							flag=true;
							bThread .setFlag(flag);
							epThread.setFlag(flag);
						    pt.setFlag(flag);
				}else if(str.equals("继续")){
							this.requestFocus();
							flag=false;
							pt.setFlag(flag);
							epThread.setFlag(flag);
							bThread .setFlag(flag);
							System .out.println("继续");
				}else if(str.equals("重新开始")){
					   this.requestFocus();
						pt.setSy(false);
						epThread.setSy(false);
						pt=new PlaneThread(this,m);
						ArrayList<Bullet> bl1=new ArrayList<Bullet>();
						BulletThread bThread=new BulletThread(bl1, m);
						ArrayList<EnemyPlane> al1=new ArrayList<EnemyPlane>();
					    EnemyPlaneThread epThread=new EnemyPlaneThread(this,al1);
						pt.setAl(al1);
						pt.setBl(bl1);
						Thread td1=new Thread(epThread);
						 Thread td2=new Thread(bThread);
					    Thread td=new Thread(pt);
					    td1.start();
					    td2.start();
						td.start();
						
					}
				else if(str.equals("排行榜")){
					this.requestFocus();
					
					try {
					  fileInput(file);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					JFrame newjfFrame=new JFrame();
					newjfFrame.setTitle("");
					newjfFrame.setSize(200, 250);
					newjfFrame.setLocationRelativeTo(null);
					FlowLayout flowLayout=new FlowLayout();
					newjfFrame.setLayout(flowLayout);	
					JLabel jl=new JLabel("First  ");
					JTextField textShow = new JTextField("0");
					JTextField textShow1 = new JTextField("0");
					JTextField textShow2 = new JTextField("0");
					textShow.setHorizontalAlignment(JTextField.RIGHT);// 设置文本从右对齐
					textShow.setPreferredSize(new Dimension(100, 50));
					textShow1.setHorizontalAlignment(JTextField.RIGHT);// 设置文本从右对齐
					textShow1.setPreferredSize(new Dimension(100, 50));
					textShow2.setHorizontalAlignment(JTextField.RIGHT);// 设置文本从右对齐
					textShow2.setPreferredSize(new Dimension(100, 50));
					JLabel jl1=new JLabel("Second");
					JLabel jl2=new JLabel("Third");
					newjfFrame.add(jl);
					newjfFrame.add(textShow);
					textShow.setText(score[0]+"");
					newjfFrame.add(jl1);
					newjfFrame.add(textShow1);
					textShow1.setText(score[1]+"");
					newjfFrame.add(jl2);
					newjfFrame.add(textShow2);
					textShow2.setText(score[2]+"");
					newjfFrame.setVisible(true);
					textShow.setEditable(false);
				}
				
			}
		
		
	
	public void keyPressed(KeyEvent e) {
		int Key=e.getKeyCode();
		
		/*if(pt==null){
		   pt=new PlaneThread(jf,m);
		  
		   Thread td=new Thread(pt);
		   td.start();
		  }*/
		
		
		
		switch(Key){
		 case KeyEvent.VK_A:
			 m.setVx(-5);
			
		     break;
		 case KeyEvent.VK_W:
			 m.setVy(-5);
			
		     break;
		 case KeyEvent.VK_S:
			 m.setVy(5);
			
		     break;
		 case KeyEvent.VK_D:
			 m.setVx(5);
			 
		     break;
		 case KeyEvent.VK_J:
			 
			 break;
		}
		
	}

	public void keyReleased(KeyEvent e) {
		int Key=e.getKeyCode();
		switch(Key){
		 case KeyEvent.VK_A:
			 m.setVx(0);
		     break;
		 case KeyEvent.VK_W:
			 m.setVy(0);
		     break;
		 case KeyEvent.VK_S:
			 m.setVy(0);
		     break;
		 case KeyEvent.VK_D:
			 m.setVx(0);  
		     break;
		} 
		
	}
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
    public  void fileInput(File file) throws IOException{
		
		FileInputStream fis=new FileInputStream(file);
		DataInputStream dis=new DataInputStream(fis);
		for(int i=0;i<3;i++){
			score[i]=dis.readInt();
		}
		fis.close();
	}
    public void paint(Graphics g){
    	super.paint(g);
    	biao[0]=1;
    }
    
}
