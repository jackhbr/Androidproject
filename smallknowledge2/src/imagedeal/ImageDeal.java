package imagedeal;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class ImageDeal extends JFrame{
    private int[][] ImageArray;
    private int width;
    private int height;
    private Graphics g;
    private BufferedImage bi;
    public ImageDeal(){
    	this.setSize(new Dimension(500, 600));
    	this.setLocationRelativeTo(null);
    	this.setVisible(true);
    }
    public void paint(Graphics g){
    	
    	super.paint(g);
    	
    	ImageD();
    	
    }
	public static void main(String[] args) {
		ImageDeal id = new ImageDeal();
	}
	public void ImageD(){
		getImagePixel("d:\\1.jpg");
		
//		ImageIcon iic = new ImageIcon("C:\\image\\111.jpg");
		
		g = this.getGraphics();
	
		for (int i = 0; i < width; i+=5)
		{
			for (int j = 0; j < height; j+=5) {
				
				g.drawImage(bi, i, j, 5, 5,new Color(ImageArray[i][j]),null);
			}
		}
//				
//				
//		for (int k = 0; k <10000; k++) {
//			for (int j = 0; j < 10000; j++) {
//				
			
//				
//			}
//		}
//			}
//		}
	}
	public void  getImagePixel(String image) {  
	       //读取图片文件
	        File file = new File(image);  
//	        BufferedImage bi = null;  
	        try {  
	            bi = ImageIO.read(file);  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        //得到图片像su的宽和高 
	        width = bi.getWidth();  
	        height = bi.getHeight(); 
	        ImageArray=new int[width][height];
	        System.out.println("width=" + width + ",height=" + height + ".");  
	        //输出每一个像素点的color
	         for (int i = 0; i < width; i++) {  
	            for (int j = 0; j < height; j++) {  
	            	//i,j位置的Color值
	                int pixel = bi.getRGB(i, j); 
	                ImageArray[i][j]=pixel;
	                 
	            }  
	        }  
	    }  
}
