package sever_send_file_to_client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JFileChooser;

public class first_buttonlisten implements ActionListener{

	DataOutputStream dos;
	InputStream ins;
	public first_buttonlisten(DataOutputStream dos)
	{
		this.dos=dos;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JFileChooser jfc=new JFileChooser();
		jfc.showOpenDialog(null);
		File f=jfc.getSelectedFile();
		if(f!=null)
		{
			try {
				ins=new FileInputStream(f); //先把文件读取进程序
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			byte[] filebyte=getBytes(ins);  
			int filelength=filebyte.length;
			System.out.println("读取的文件的长度为"+filelength);
			
			try {
				dos.writeInt(1);
				dos.writeInt(filelength);
				dos.write(filebyte);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		}
		
	}
	
	/** 
     * 获得指定文件的byte数组 
     */  
    private byte[] getBytes(InputStream ins){  
        byte[] buffer = null;  
        try {  
            int num=ins.available();
            ByteArrayOutputStream bos = new ByteArrayOutputStream(num);  
            byte[] b = new byte[num];  
            int n;  
            while ((n = ins.read(b)) != -1) {  
                bos.write(b, 0, n);  
            }  
            ins.close();  
            bos.close();  
            buffer = bos.toByteArray();  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return buffer;  
    }
    

}
