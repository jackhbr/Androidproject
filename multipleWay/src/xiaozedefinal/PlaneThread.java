package xiaozedefinal;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class PlaneThread  implements Runnable{
    private Graphics g;
    private MyPlane mp;
    private int[] count=new int[4];
    private int[] biao=new int[1];
    public void setBiao(int[] biao) {
		this.biao = biao;
	}
	public void setCount(int[] count) {
		this.count = count;
	}
	private volatile boolean flag;
    private boolean sy=true;
    private File file;
    public void setFile(File file) {
		this.file = file;
	}
	private EnemyPlane ep;
    private Bullet b;
    private ImageIcon image;
    private Image iBuffer;  
    private JFrame jf;
    private int x,y;
    private ArrayList<EnemyPlane> al;
    private ArrayList<Bullet> bl;
	public void setBl(ArrayList<Bullet> bl) {
		this.bl = bl;
	}
	public void setAl(ArrayList<EnemyPlane> al) {
		this.al = al;
	}
	public PlaneThread(JFrame jf, MyPlane mp) {
		this.jf=jf;
	    g=this.jf.getGraphics();
		this.mp = mp;
	
	}
	public PlaneThread(){}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public void run() {
		while(true){
		if(biao[0]==1){
			System.out.println("11111");
			//����������
		    BufferedImage buffer= new  BufferedImage(jf.getWidth(), jf.getHeight(),BufferedImage.TYPE_INT_RGB);
		    //�������������ʶ���
		    Graphics buf=buffer.getGraphics();
		    try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			image=new ImageIcon(this.getClass().getResource("����4.jpg"));
			buf.drawImage(image.getImage(), x, y,null);
			buf.drawImage(image.getImage(), x, y-image.getIconHeight(),null);
		
			//���л�����
			for(int i=0;i<al.size();i++){
				 ep=al.get(i);
			     ep.drawEnemyPlane(buf);
			}
			//���ӵ�����
			for(int i=0;i<bl.size();i++){
				b=bl.get(i);
				b.drawBullet(buf);
				System.out.println(i);
		    	 System.out.println(bl.size());
				if(b.getY()<-100){
					bl.remove(i);
					
				}
			} 
			//���ҵķɻ�
			mp.drawPlane(buf);
			//����Ļ�ϻ�������������õ�ͼ��
			g.drawImage(buffer, 0, 0,null);
		
			
			
			
			biao[0]=0;
		}
			 if(flag){
				 
					continue;
				}
		if(biao[0]==0){	 
			//����������
		    BufferedImage buffer= new  BufferedImage(jf.getWidth(), jf.getHeight(),BufferedImage.TYPE_INT_RGB);
		    //�������������ʶ���
		    Graphics buf=buffer.getGraphics();
		    try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			image=new ImageIcon(this.getClass().getResource("����4.jpg"));
			buf.drawImage(image.getImage(), x, y,null);
			buf.drawImage(image.getImage(), x, y-image.getIconHeight(),null);
		    y++;
			if(y-image.getIconHeight()==0){
				y=0;
			}
			//���л�����
			for(int i=0;i<al.size();i++){
				 ep=al.get(i);
			     ep.drawEnemyPlane(buf);
			     if(ep.getY()>jf.getHeight()){
					  al.remove(i);
				  }  	
			     double distance=Math.sqrt((ep.getX()-mp.getX())*(ep.getX()-mp.getX())+(ep.getY()-mp.getY())*(ep.getY()-mp.getY()));
			     if(distance<=100){
			    	 System.out.println("ʧ��");
			    	 flag=true;
					    try {
					    	fileInput(file);
					    	Arrays.sort(count);
					       
							fileOutput(file,count);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
			    	 JOptionPane.showMessageDialog(null, "������", "����Ŭ��", JOptionPane.INFORMATION_MESSAGE);
			    	 return;
			 			    
			     }
			}
			//���ӵ�����
			for(int i=0;i<bl.size();i++){
				b=bl.get(i);
				b.drawBullet(buf);
				//System.out.println(i);
		    	 //System.out.println(bl.size());
				for(int j=0;j<al.size();j++){
					ep=al.get(j);
					double distance1=Math.sqrt(((ep.getX()+64)-(b.getX()+25))*((ep.getX()+64)-(b.getX()+25))+((ep.getY()+64)-(b.getY()+59))*((ep.getY()+64)-(b.getY()+59)));
					if(distance1<=100){
				    	 System.out.println("�л�׹��");
				    	 count[3]+=100;
				    	// System.out.println(i);
				    	// System.out.println(bl.size());
				    	 al.remove(j); 
				         b=new Bullet(mp.getX(), mp.getY());
				    	 bl.remove(i);
				    	 
				     }
				}
				if(b.getY()<-100){
					bl.remove(i);
					
				}
			} 
			//���ҵķɻ�
			mp.drawPlane(buf);
			//����Ļ�ϻ�������������õ�ͼ��
			g.drawImage(buffer, 0, 0,null);
		}
		
			if(sy==false){
			  break;	
			}
		}
		
	} 

	
	public void setSy(boolean sy) {
		this.sy = sy;
	}
	public void paint(Graphics g){
		
		
	}
	public void update(Graphics g){
		if(iBuffer==null) {
			
		    
		} 
		
	}
	public  void fileInput(File file) throws IOException{
		
		FileInputStream fis=new FileInputStream("D:\\Android\\phb.txt");
		DataInputStream dis=new DataInputStream(fis);
		for(int i=0;i<3;i++){
			count[i]=dis.readInt();
		}
		fis.close();
	}
public  void fileOutput(File file,int[] score) throws IOException{
		
		FileOutputStream fos=new FileOutputStream("D:\\Android\\phb.txt");
		DataOutputStream dps=new DataOutputStream(fos);
		for(int i=0;i<3;i++){
	    dps.writeInt(score[3-i]);
	    }
	    fos.flush();
	    fos.close();
	    
	}
     
}
