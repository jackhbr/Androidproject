package imagedeal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class PImage2 extends JFrame{
	 //保存相su
	private static  int[][] imIndex;
	private static  int[][] imIndex1;
	
	public static void main(String args[]){
		PImage2 pImage=new PImage2();
		 imIndex=pImage.getImagePixel("C:\\Users\\jack\\Desktop\\timg.jpg");
		 imIndex1=pImage.getImagePixel("C:\\Users\\jack\\Desktop\\jk.jpg");
		System.out.println("load image sucess...");
		pImage.init();
		
		
	}
	
	public void init(){
		this.setSize(1000, 800);
		this.setVisible(true);
		this.setDefaultCloseOperation(3);
	}
	
	public void paint(Graphics g){
		super.paint(g); 
		 
		for(int i=1;i<imIndex.length &&i<imIndex1.length ;i+=1){
			for(int j=0;j<imIndex[i].length&&j<imIndex1[i].length ;j+=1){
				int c;
				if((i+j)%2==1)
				{
 				c=imIndex[i][j]; 
				}
				else {
					c=imIndex1[i][j];
				}
     			Color cc=new Color(c);
 				g.setColor(cc);  
  				g.fillOval(i, j, 1,1);
 				 
 			}
		} 
		
//		for(int i=2;i<imIndex1.length ;i+=2){
//			for(int j=0;j<imIndex1[i].length;j+=2){
// 				int c=imIndex1[i][j]; 
//     			Color cc=new Color(c);
// 				g.setColor(cc);  
//  				g.fillOval(i, j, 1,1);
// 				 
// 			}
//		} 
		
	}
	
	
	/**
	 * 将一张图片转化成一个int型的二维数组
	 * @param image 图片文件名
	 * @return 转化后的二维数组
	 */
	public int[][]  getImagePixel(String image) {  
		int[][] imIndextemp;
        File file = new File(image);  
        BufferedImage bi = null;  
        try {  
            bi = ImageIO.read(file);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        //得到图片像su的宽和高 
        int w = bi.getWidth();  
        int h = bi.getHeight(); 
        imIndextemp=new int[w][h];
        System.out.println("w=" + w + "  h=" + h );  
        
         for (int i = 0; i < w; i++) {  
            for (int j = 0; j < h; j++) {  
            	//i,j位置的Color值
                int pixel = bi.getRGB(i, j); 
              //输出每一个像素点的color
                imIndextemp[i][j]=pixel;
            }  
        }  
         return imIndextemp;
    }  
	

	 

}
