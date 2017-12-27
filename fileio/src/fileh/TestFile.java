package fileh;

import java.io.File;


public class TestFile {

	public static void main(String[] args) throws Exception {
	File file = new File("C:\\Users\\jack\\Desktop\\jj ");//srcΪ���̵�Ĭ�ϰ�
	TestFile.fileInfo(file);
	}
	
	private static void fileInfo(File file) throws Exception {
	System.out.println("�ļ��� file.getName()=" + file.getName());
	System.out.println("�ļ���·�� file.getParent()=" + file.getParent());
	System.out.println("�ļ�·�� ile.getPath()=" + file.getPath());
	System.out.println("�ļ�����·�� file.getAbsolutePath()=" + file.getAbsolutePath());
	
	System.out.println("�ļ��Ƿ���� file.exists()=" + file.exists());
	
	if (file.delete()) {//��ɾ��
	System.out.println("�ļ���ɾ����");
	System.out.println("�ļ��Ƿ���� file.exists()=" + file.exists());
	}
	
	if (!file.exists()) {//������
	file.createNewFile();
	System.out.println("�ļ������ɣ�");
	System.out.println("�Ƿ��ļ� file.isFile()=" + file.isFile());
	System.out.println("�Ƿ�Ŀ¼ file.isDirectory()=" + file.isDirectory());
	System.out.println("�Ƿ�ɶ� file.canRead()=" + file.canRead());
	System.out.println("�Ƿ��д file.canWrite()=" + file.canWrite());
	System.out.println("�Ƿ����� file.isHidden()=" + file.isHidden());
	System.out.println("�ļ����� file.length()=" + file.length());
	System.out.println("�޸�ʱ�� file.lastModified()=" + file.lastModified());
	}
	
	}
	
	}