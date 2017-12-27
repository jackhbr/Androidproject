import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class copyfile {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		copyfile bi=new copyfile();
		String srcName="D:\\java\\adt-bundle-windows-x86_64-20130917\\adt-bundle-windows-x86_64-20130917\\Androidproject\\fileio\\src\\1.CHM";
        String backName="D:\\java\\adt-bundle-windows-x86_64-20130917\\adt-bundle-windows-x86_64-20130917\\Androidproject\\fileio\\src\\2.CHM";
	    boolean result;
		try {
			result = bi.bufferCopyFile(srcName,backName);
			  System.out.println("¸´ÖÆ½á¹û"+result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  
	}
	public boolean copyFile(String srcFile,String destFile) throws IOException
	{
		InputStream ins=new FileInputStream(srcFile);
		OutputStream out=new FileOutputStream(destFile);
		int i=0;
		while((i=ins.read())!=-1)
		{
			out.write(i);
		}
		ins.close();
		out.flush();
		out.close();
		return true;
	}
	
	public boolean bufferCopyFile(String srcFile,String destFile) throws IOException
	{
		File src=new File(srcFile);
		File dest=new File(destFile);
		InputStream ins=new FileInputStream(src);
		BufferedInputStream bis=new BufferedInputStream(ins);
		
		OutputStream out=new FileOutputStream(dest);
		BufferedOutputStream bos=new BufferedOutputStream(out);
		int i=0;
		while((i=bis.read())!=-1)
		{
			bos.write(i);
		}
		
		ins.close();
		out.flush();
		out.close();
		return true;
		
	}

}
