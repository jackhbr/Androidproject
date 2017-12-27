package json;

import java.io.ByteArrayOutputStream;  
import java.io.DataInputStream;  
import java.io.DataOutputStream;  
import java.io.IOException;  
import java.net.Socket;  
import java.util.HashMap;  
import java.util.Map;  
import org.json.JSONArray;  
import org.json.JSONException;  
import org.json.JSONObject;  
import java.io.BufferedInputStream;  
import cn.edu.thu.cv.util.Base64Image;  
  
public class Client {  
    public static final String IP_ADDR = "localhost";//服务器地址  这里要改成服务器的ip  
    public static final int PORT = 12345;//服务器端口号    
      
      
    public static int register(String name,String imgPath,int opNum){  
        String imgStr = Base64Image.GetImageStr(imgPath);//是将图片的信息转化为base64编码  
        int isRegSuccess = 0;  
         while (true) {    
                Socket socket = null;  
                try {  
                    //创建一个流套接字并将其连接到指定主机上的指定端口号  
                    socket = new Socket(IP_ADDR, PORT);    
                    System.out.println("连接已经建立");          
                    //向服务器端发送数据    
                    Map<String, String> map = new HashMap<String, String>();  
                    map.put("name",name);  
                    map.put("img",imgStr);  
                    map.put("op",opNum+"");  
                    //将json转化为String类型    
                    JSONObject json = new JSONObject(map);  
                    String jsonString = "";  
                        jsonString = json.toString();  
                    //将String转化为byte[]  
                    //byte[] jsonByte = new byte[jsonString.length()+1];  
                    byte[] jsonByte = jsonString.getBytes();  
                    DataOutputStream outputStream = null;  
                    outputStream = new DataOutputStream(socket.getOutputStream());  
                        System.out.println("发的数据长度为:"+jsonByte.length);  
                    outputStream.write(jsonByte);  
                    outputStream.flush();  
                        System.out.println("传输数据完毕");  
                        socket.shutdownOutput();  
                      
                    //读取服务器端数据    
                    DataInputStream inputStream = null;  
                    String strInputstream ="";  
                    inputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));   
                    strInputstream=inputStream.readUTF();  
                  //  System.out.println("输入信息为："+strInputstream);  
                    JSONObject js = new JSONObject(strInputstream);  
                    System.out.println(js.get("isSuccess"));  
                    isRegSuccess=Integer.parseInt((String) js.get("isSuccess"));   
                    // 如接收到 "OK" 则断开连接    
                    if (js != null) {    
                        System.out.println("客户端将关闭连接");    
                        Thread.sleep(500);    
                        break;    
                    }    
                      
                } catch (Exception e) {  
                    System.out.println("客户端异常:" + e.getMessage());   
                    break;  
                } finally {  
                    if (socket != null) {  
                        try {  
                            socket.close();  
                        } catch (IOException e) {  
                            socket = null;   
                            System.out.println("客户端 finally 异常:" + e.getMessage());   
                        }  
                    }  
                }  
            }  
         return isRegSuccess;     
    }  
      
    public static void main(String[] args) {    
          register("gongyunfei","D:/bgp.jpg",1);//第三个参数为操作类型 服务器能够知道你在进行什么操作  
   }    
}   

