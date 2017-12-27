import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class ObjCopy {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ObjCopy bi=new ObjCopy();
		String srcName="D:\\java\\adt-bundle-windows-x86_64-20130917\\adt-bundle-windows-x86_64-20130917\\Androidproject\\fileio\\src\\file.java";
    
        boolean result;
		try {
			result = bi.writeReadObject(srcName);
			  System.out.println("复制结果"+result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public boolean writeReadObject(String fileName) throws  Exception  //注意这个时候是exception了，不是io的了
	{
		
		int a=0,b=0,c=0;
		Customer customer=new Customer();
		customer.setFirst(a);
		customer.setSecond(b);
		customer.setThird(c);
		FileOutputStream fos=new FileOutputStream(fileName);
		ObjectOutputStream out=new ObjectOutputStream(fos);
		out.writeObject(customer);
		out.flush();
		out.close();
		
		FileInputStream fis=new FileInputStream(fileName);
		ObjectInputStream input=new ObjectInputStream(fis);
		Customer inCus=(Customer)input.readObject();
		
		System.out.println("第一名是"+inCus.getFirst());
		System.out.println("第二名是"+inCus.getSecond());
		System.out.println("第三名是"+inCus.getThird());
		return true;
		
	}
	
	public boolean dataTypeStream(String fileName) throws Exception
	{
		FileOutputStream fos=new FileOutputStream(fileName);
		DataOutputStream dos=new DataOutputStream(fos);
		
		dos.writeBoolean(true);
		dos.writeByte((byte)123);
		dos.writeChar('J');
		dos.writeDouble(3.1415926);
		dos.writeFloat((2.7182f));//后面加个f代表这是单精度浮点
		
//		Java 中 float 与 double 的区别
//		1.float是单精度浮点数，内存分配4个字节，占32位，有效小数位6-7位
//		double是双精度浮点数，内存分配8个字节，占64位，有效小数位15位
//		 
//		2.java中默认声明的小数是double类型的，如double d=4.0
//		如果声明： float x = 4.0则会报错，需要如下写法：float x = 4.0f或者float x = (float)4.0
//		其中4.0f后面的f只是为了区别double，并不代表任何数字上的意义              
//		 
//		3.对编程人员来而，double 和 float 的区别是double精度高，但double消耗内存是float的两倍，且double的运算速度较float稍慢。
		
		dos.writeInt(1234567890);
		dos.writeLong(998877665544332211L);
		dos.writeShort((short)11223);
		
		DataInputStream dis=new DataInputStream(new FileInputStream(fileName));
		System.out.println("\t"+dis.readBoolean());
		System.out.println("\t"+dis.readByte());
		
		
		
		System.out.println("\t"+dis.readChar());
		System.out.println("\t"+dis.readDouble());//这个必须得按照顺序去读取东西。。。
		System.out.println("\t"+dis.readFloat());
		System.out.println("\t"+dis.readInt());
		System.out.println("\t"+dis.readLong());
		System.out.println("\t"+dis.readShort());
		
		
		dis.close();
		return true;
		
	}

}
