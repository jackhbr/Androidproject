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
	 //������su
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
	 * ��һ��ͼƬת����һ��int�͵Ķ�ά����
	 * @param image ͼƬ�ļ���
	 * @return ת����Ķ�ά����
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
        //�õ�ͼƬ��su�Ŀ�͸� 
        int w = bi.getWidth();  
        int h = bi.getHeight(); 
        imIndextemp=new int[w][h];
        System.out.println("w=" + w + "  h=" + h );  
        
         for (int i = 0; i < w; i++) {  
            for (int j = 0; j < h; j++) {  
            	//i,jλ�õ�Colorֵ
                int pixel = bi.getRGB(i, j); 
              //���ÿһ�����ص��color
                imIndextemp[i][j]=pixel;
            }  
        }  
         return imIndextemp;
    }  
	

	 

}
