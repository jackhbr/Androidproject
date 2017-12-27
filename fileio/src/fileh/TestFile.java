package fileh;

import java.io.File;


public class TestFile {

	public static void main(String[] args) throws Exception {
	File file = new File("C:\\Users\\jack\\Desktop\\jj ");//src为工程的默认包
	TestFile.fileInfo(file);
	}
	
	private static void fileInfo(File file) throws Exception {
	System.out.println("文件名 file.getName()=" + file.getName());
	System.out.println("文件父路径 file.getParent()=" + file.getParent());
	System.out.println("文件路径 ile.getPath()=" + file.getPath());
	System.out.println("文件绝对路径 file.getAbsolutePath()=" + file.getAbsolutePath());
	
	System.out.println("文件是否存在 file.exists()=" + file.exists());
	
	if (file.delete()) {//先删除
	System.out.println("文件已删除！");
	System.out.println("文件是否存在 file.exists()=" + file.exists());
	}
	
	if (!file.exists()) {//后生成
	file.createNewFile();
	System.out.println("文件已生成！");
	System.out.println("是否文件 file.isFile()=" + file.isFile());
	System.out.println("是否目录 file.isDirectory()=" + file.isDirectory());
	System.out.println("是否可读 file.canRead()=" + file.canRead());
	System.out.println("是否可写 file.canWrite()=" + file.canWrite());
	System.out.println("是否隐藏 file.isHidden()=" + file.isHidden());
	System.out.println("文件长度 file.length()=" + file.length());
	System.out.println("修改时间 file.lastModified()=" + file.lastModified());
	}
	
	}
	
	}