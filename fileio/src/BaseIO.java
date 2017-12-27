import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


public class BaseIO {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BaseIO bi=new BaseIO();
		
		//涓轰粈涔堟垜鐨勫彧鑳藉湪椤圭洰鏂囦欢涓墠鎵惧埌鏂囦欢銆傘�傘�傘�傘�傝繕鏈夛紝鎴戝彂鐜板緱鐢ㄥ悓涓�绉嶇紪鐮併�傘�傘��
		
	//	String fileName= "D:\\java\\adt-bundle-windows-x86_64-20130917\\adt-bundle-windows-x86_64-20130917\\Androidproject\\fileio\\src\\file.java";
		
		//String fileName= "c:\\Users\\jack\\Desktop\\file.java";
		String fileName="C:\\Users\\jack\\Desktop\\file.java";
		String result=bi.readFileOneByOne(fileName);
		System.out.println(result);

	}
	
	public String readFile2String(String filename) throws IOException   //涓ょ璇诲彇鏂囦欢娴佺殑鏂规硶
	{
		InputStream ins=new FileInputStream(filename);
		byte[] conentByte=new byte[ins.available()];
		ins.read(conentByte);
		
		String s=new String(conentByte);
		return s;
		
	}
	
	public String readFileOneByOne(String fileName) throws IOException
	{
		InputStream ins=new FileInputStream(fileName);
		int i=-1;
		byte[] conentByte=new byte[ins.available()];
		int count=0;
		while((i=ins.read())!=-1)
		{
			conentByte[count]=(byte)i;
			count++;
		}
		String s=new String(conentByte);
		return s;
	}

}
