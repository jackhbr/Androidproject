package cn.edu.thu.cv.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import sun.misc.BASE64Decoder;  
import sun.misc.BASE64Encoder; //�����ֲ��ܵ��������ʱ������װϵͳ�İ�

public class Base64Image   
{  
    public static String GetImageStr(String imapath)  
    {//��ͼƬ�ļ�ת��Ϊ�ֽ������ַ��������������Base64���봦��  
        InputStream in = null;  
        byte[] data = null;  
        //��ȡͼƬ�ֽ�����  
        try   
        {  
            in = new FileInputStream(imapath);          
            data = new byte[in.available()];  
            in.read(data);  
            in.close();  
        }   
        catch (IOException e)   
        {  
            e.printStackTrace();  
        }  
        //���ֽ�����Base64����  
        BASE64Encoder encoder = new BASE64Encoder();  
        return encoder.encode(data);//����Base64��������ֽ������ַ���  
    }  
    public static boolean GenerateImage(String imgStr, String output)  
    {//���ֽ������ַ�������Base64���벢����ͼƬ  
        if (imgStr == null) //ͼ������Ϊ��  
            return false;  
        BASE64Decoder decoder = new BASE64Decoder();  
        try   
        {  
            //Base64����  
            byte[] b = decoder.decodeBuffer(imgStr);  
            for(int i=0;i<b.length;++i)  
            {  
                if(b[i]<0)  
                {//�����쳣����  
                    b[i]+=256;  
                }  
            }  
            //����jpegͼƬ  
            OutputStream out = new FileOutputStream(output);      
            out.write(b);  
            out.flush();  
            out.close();  
            return true;  
        }   
        catch (Exception e)   
        {  
            return false;  
        }  
    }  
}  