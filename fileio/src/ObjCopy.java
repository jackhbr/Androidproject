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
			  System.out.println("���ƽ��"+result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public boolean writeReadObject(String fileName) throws  Exception  //ע�����ʱ����exception�ˣ�����io����
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
		
		System.out.println("��һ����"+inCus.getFirst());
		System.out.println("�ڶ�����"+inCus.getSecond());
		System.out.println("��������"+inCus.getThird());
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
		dos.writeFloat((2.7182f));//����Ӹ�f�������ǵ����ȸ���
		
//		Java �� float �� double ������
//		1.float�ǵ����ȸ��������ڴ����4���ֽڣ�ռ32λ����ЧС��λ6-7λ
//		double��˫���ȸ��������ڴ����8���ֽڣ�ռ64λ����ЧС��λ15λ
//		 
//		2.java��Ĭ��������С����double���͵ģ���double d=4.0
//		��������� float x = 4.0��ᱨ����Ҫ����д����float x = 4.0f����float x = (float)4.0
//		����4.0f�����fֻ��Ϊ������double�����������κ������ϵ�����              
//		 
//		3.�Ա����Ա������double �� float ��������double���ȸߣ���double�����ڴ���float����������double�������ٶȽ�float������
		
		dos.writeInt(1234567890);
		dos.writeLong(998877665544332211L);
		dos.writeShort((short)11223);
		
		DataInputStream dis=new DataInputStream(new FileInputStream(fileName));
		System.out.println("\t"+dis.readBoolean());
		System.out.println("\t"+dis.readByte());
		
		
		
		System.out.println("\t"+dis.readChar());
		System.out.println("\t"+dis.readDouble());//�������ð���˳��ȥ��ȡ����������
		System.out.println("\t"+dis.readFloat());
		System.out.println("\t"+dis.readInt());
		System.out.println("\t"+dis.readLong());
		System.out.println("\t"+dis.readShort());
		
		
		dis.close();
		return true;
		
	}

}
