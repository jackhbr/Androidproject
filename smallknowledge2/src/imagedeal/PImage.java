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

public class PImage extends JFrame{
	 //������su
	private  int[][] imIndex;
	
	public static void main(String args[]){
		PImage pImage=new PImage();
		pImage.getImagePixel("C:\\Users\\jack\\Desktop\\timg.jpg");
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
		 
		for(int i=1;i<imIndex.length;i+=3){
			for(int j=0;j<imIndex[i].length;j+=3){
 				int c=imIndex[i][j]; 
	//			c=Math.abs(c%2);
     			 
//     			if(i%10==0){
//     				c+=2;
//     			}
     			Color cc=new Color(c);
 				g.setColor(cc);  
  				g.fillOval(i, j, 1,1);
 				 
 			}
		} 
		
	}
	
	
	/**
	 * ��һ��ͼƬת����һ��int�͵Ķ�ά����
	 * @param image ͼƬ�ļ���
	 * @return ת����Ķ�ά����
	 */
	public int[][]  getImagePixel(String image) {  
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
        imIndex=new int[w][h];
        System.out.println("w=" + w + "  h=" + h );  
        
         for (int i = 0; i < w; i++) {  
            for (int j = 0; j < h; j++) {  
            	//i,jλ�õ�Colorֵ
                int pixel = bi.getRGB(i, j); 
              //���ÿһ�����ص��color
                imIndex[i][j]=pixel;
            }  
        }  
         return imIndex;
    }  
	

	 

}
