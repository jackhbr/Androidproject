package saveuserdata;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;



public class FileSaveUserData {
	static ArrayList<UserInfo> us=new ArrayList<UserInfo>();
	private static HashMap<String, String> hm=new HashMap<String, String>();
	static
	{
		for (int i = 0; i < 10; i++) {
			UserInfo user = new UserInfo();
			user.setName("user" + i);
			user.setPwd("pwd" + i);
			hm.put(user.getName(), user.getPwd());
			us.add(user);
		}
	}
	
	public void use() throws IOException
	{
		String file="d:\\use.txt";
		FileOutputStream fos=new FileOutputStream(file);
		byte b[];
		String sname;
		String spassword;
		String fina;
		UserInfo uInfo;
		for(int i=0;i<us.size();i++)
		{
			uInfo=us.get(i);
			sname=uInfo.getName();
			spassword=uInfo.getPwd();
			fina=sname+"#"+spassword+"$";
			b=fina.getBytes();
			fos.write(b);
			fos.flush();
		}
		fos.close();
		
	}

}
