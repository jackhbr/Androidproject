package xiaozedefinal;

import java.applet.AudioClip; 
import java.io .*; 
import java.applet.Applet;
import java.awt.Frame; 
import java.net .MalformedURLException; 
import java.net .URI;
import java.net .URL;
import javax.swing.JFrame;
public class Music extends JFrame{ 
    File f;
    URI uri;
    URL url; 

 public Music(){  
  try {      
	 
      f = new File("D:\\loop551.wav"); 
      uri = f.toURI();
      url = uri.toURL();  //解析地址
      AudioClip aau; 
      aau = Applet.newAudioClip(url);
      aau.loop();  //循环播放
      while(true);  
  } catch (Exception e) 
  { e.printStackTrace();
  } 
}
 public static void main(String args[]) { 
   new Music();
 }
}
 
