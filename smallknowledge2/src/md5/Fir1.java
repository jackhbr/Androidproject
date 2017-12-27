package md5;

import java.security.MessageDigest;

public class Fir1 {

	// ���Է���  
    public static void main(String[] args) {  
    String pwd = "123456";  
    System.out.println("����ǰ�� " + pwd);  
    System.err.println("���ܺ� " + Fir1.getMD5(pwd));  
    }  
  
    /** 
     * ����md5 
     *  
     * @param message 
     * @return 
     */  
    public static String getMD5(String message) {  
    String md5str = "";  
    try {  
        // 1 ����һ���ṩ��ϢժҪ�㷨�Ķ��󣬳�ʼ��Ϊmd5�㷨����  
        MessageDigest md = MessageDigest.getInstance("MD5");  
  
        // 2 ����Ϣ���byte����  
        byte[] input = message.getBytes();  
        System.out.println("���������ĳ�����"+input.length);//6
        // 3 ��������ֽ�����,�������128λ��  
        byte[] buff = md.digest(input);  //8*16=128
        System.out.println("���ɵ�128bit��������"+buff.length+"  ������"+buff.getClass().toString());
        //16
        // 4 ������ÿһ�ֽڣ�һ���ֽ�ռ��λ������16��������md5�ַ���  
        md5str = bytesToHex(buff);  
  
    } catch (Exception e) {  
        e.printStackTrace();  
    }  
    return md5str;  
    }  
  
    /** 
     * ������תʮ������ 
     *  
     * @param bytes 
     * @return 
     */  
    public static String bytesToHex(byte[] bytes) {  
    StringBuffer md5str = new StringBuffer();  //�ַ�����������ͦ���õ�
    // ������ÿһ�ֽڻ���16��������md5�ַ���  
    int digital;  
    System.out.println("bytesToHex���byte����ĳ���Ϊ"+bytes.length);
    for (int i = 0; i < bytes.length; i++) {  
        digital = bytes[i];  
        System.out.print(bytes[i]+"  ");
        if (digital < 0) {  
        digital += 256;  
        }  
        if (digital < 16) {  //�������0��Ϊ����ȷ�Ķ�Ӧ32λ�����ֱ�����01�ģ��������֮����1��������ǰ���0������������Ϊ����0
        md5str.append("0");  
        }  
        System.out.print(digital+" ");//8λ����������-128��127֮��
        
        System.out.print(Integer.toBinaryString(digital)+" ");//������д洢�Ķ��ǲ��룬�ҵĵ�����32λ�����ִ���
        System.out.println();
        md5str.append(Integer.toHexString(digital));  
        //MD5��������Ψһ��Ӧ��128��bit��0��1
    }  

    System.out.println();
    System.out.println("���ĳ���Ϊ"+md5str.length());
    
    return md5str.toString().toUpperCase();  
    }  


}
