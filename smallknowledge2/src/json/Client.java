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
    public static final String IP_ADDR = "localhost";//��������ַ  ����Ҫ�ĳɷ�������ip  
    public static final int PORT = 12345;//�������˿ں�    
      
      
    public static int register(String name,String imgPath,int opNum){  
        String imgStr = Base64Image.GetImageStr(imgPath);//�ǽ�ͼƬ����Ϣת��Ϊbase64����  
        int isRegSuccess = 0;  
         while (true) {    
                Socket socket = null;  
                try {  
                    //����һ�����׽��ֲ��������ӵ�ָ�������ϵ�ָ���˿ں�  
                    socket = new Socket(IP_ADDR, PORT);    
                    System.out.println("�����Ѿ�����");          
                    //��������˷�������    
                    Map<String, String> map = new HashMap<String, String>();  
                    map.put("name",name);  
                    map.put("img",imgStr);  
                    map.put("op",opNum+"");  
                    //��jsonת��ΪString����    
                    JSONObject json = new JSONObject(map);  
                    String jsonString = "";  
                        jsonString = json.toString();  
                    //��Stringת��Ϊbyte[]  
                    //byte[] jsonByte = new byte[jsonString.length()+1];  
                    byte[] jsonByte = jsonString.getBytes();  
                    DataOutputStream outputStream = null;  
                    outputStream = new DataOutputStream(socket.getOutputStream());  
                        System.out.println("�������ݳ���Ϊ:"+jsonByte.length);  
                    outputStream.write(jsonByte);  
                    outputStream.flush();  
                        System.out.println("�����������");  
                        socket.shutdownOutput();  
                      
                    //��ȡ������������    
                    DataInputStream inputStream = null;  
                    String strInputstream ="";  
                    inputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));   
                    strInputstream=inputStream.readUTF();  
                  //  System.out.println("������ϢΪ��"+strInputstream);  
                    JSONObject js = new JSONObject(strInputstream);  
                    System.out.println(js.get("isSuccess"));  
                    isRegSuccess=Integer.parseInt((String) js.get("isSuccess"));   
                    // ����յ� "OK" ��Ͽ�����    
                    if (js != null) {    
                        System.out.println("�ͻ��˽��ر�����");    
                        Thread.sleep(500);    
                        break;    
                    }    
                      
                } catch (Exception e) {  
                    System.out.println("�ͻ����쳣:" + e.getMessage());   
                    break;  
                } finally {  
                    if (socket != null) {  
                        try {  
                            socket.close();  
                        } catch (IOException e) {  
                            socket = null;   
                            System.out.println("�ͻ��� finally �쳣:" + e.getMessage());   
                        }  
                    }  
                }  
            }  
         return isRegSuccess;     
    }  
      
    public static void main(String[] args) {    
          register("gongyunfei","D:/bgp.jpg",1);//����������Ϊ�������� �������ܹ�֪�����ڽ���ʲô����  
   }    
}   
