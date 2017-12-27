package md5;

import java.security.MessageDigest;

public class Fir1 {

	// 测试方法  
    public static void main(String[] args) {  
    String pwd = "123456";  
    System.out.println("加密前： " + pwd);  
    System.err.println("加密后： " + Fir1.getMD5(pwd));  
    }  
  
    /** 
     * 生成md5 
     *  
     * @param message 
     * @return 
     */  
    public static String getMD5(String message) {  
    String md5str = "";  
    try {  
        // 1 创建一个提供信息摘要算法的对象，初始化为md5算法对象  
        MessageDigest md = MessageDigest.getInstance("MD5");  
  
        // 2 将消息变成byte数组  
        byte[] input = message.getBytes();  
        System.out.println("输入的数组的长度是"+input.length);//6
        // 3 计算后获得字节数组,这就是那128位了  
        byte[] buff = md.digest(input);  //8*16=128
        System.out.println("生成的128bit的数组是"+buff.length+"  类型是"+buff.getClass().toString());
        //16
        // 4 把数组每一字节（一个字节占八位）换成16进制连成md5字符串  
        md5str = bytesToHex(buff);  
  
    } catch (Exception e) {  
        e.printStackTrace();  
    }  
    return md5str;  
    }  
  
    /** 
     * 二进制转十六进制 
     *  
     * @param bytes 
     * @return 
     */  
    public static String bytesToHex(byte[] bytes) {  
    StringBuffer md5str = new StringBuffer();  //字符串缓冲器，挺好用的
    // 把数组每一字节换成16进制连成md5字符串  
    int digital;  
    System.out.println("bytesToHex里的byte数组的长度为"+bytes.length);
    for (int i = 0; i < bytes.length; i++) {  
        digital = bytes[i];  
        System.out.print(bytes[i]+"  ");
        if (digital < 0) {  
        digital += 256;  
        }  
        if (digital < 16) {  //加上这个0是为了正确的对应32位的数字本来是01的，计算机会之加上1，而忽略前面的0，所以现在人为加上0
        md5str.append("0");  
        }  
        System.out.print(digital+" ");//8位数，所以在-128到127之间
        
        System.out.print(Integer.toBinaryString(digital)+" ");//计算机中存储的都是补码，我的电脑是32位的数字处理
        System.out.println();
        md5str.append(Integer.toHexString(digital));  
        //MD5就是生成唯一对应的128个bit的0或1
    }  

    System.out.println();
    System.out.println("最后的长度为"+md5str.length());
    
    return md5str.toString().toUpperCase();  
    }  


}
