package imagedeal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;


public class Process extends JFrame{
	 int width;
	 int height;
	static int imIndex[][];
	Graphics g;
	static String image="d:\\12.png";
	BufferedImage bi; 
	public static void main(String[] args) {
		Process process=new Process();
		process.getImagePixel(image);
		process.mosaic(imIndex);
	}
	public int[][]  getImagePixel(String image) {  
	       //读取图片文件
	        File file = new File(image);  
	         
	        try {  
	            bi = ImageIO.read(file);  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        //得到图片像su的宽和高 
	         width = bi.getWidth();  
	         height = bi.getHeight(); 
	        imIndex=new int[width][height];
	        System.out.println("width=" + width + ",height=" + height + ".");  
	        //输出每一个像素点的color
	         for (int i = 0; i < width; i++) {  
	            for (int j = 0; j < height; j++) {  
	            	//i,j位置的Color值
	                int pixel = bi.getRGB(i, j); 
	                imIndex[i][j]=pixel;
	            }  
	        }  
	         return imIndex;
	    } 
	public void mosaic(int pix[][]){
		this.setVisible(true);
		this.setSize(width,height);
		this.setDefaultCloseOperation(2);
		g=this.getGraphics();
		for(int i=0;i<pix.length;i+=15){
			for(int j=0;j<pix[i].length;j+=15){
				int c=pix[i][j];
				g.drawImage(bi, i, j,15,15, new Color(c), null);
			}
		}
	}
	public void show(int pix[][]){
		this.setVisible(true);
		this.setSize(width,height);
		g=this.getGraphics();
		for(int i=0;i<pix.length;i+=6){
			for(int j=0;j<pix[i].length;j+=6){
				int c=pix[i][j];
				g.setColor(new Color(c));
				g.drawRect(i,j, 3, 3);
			}
		}
	}
	public void paint(Graphics g){
		super.paint(g);
		this.mosaic(imIndex);
	}
}
