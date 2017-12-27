package getcurrenttimeformat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Exeample1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Date date=new Date();
		System.out.println(date);
		  DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  String time=format.format(date);
	     System.out.println(time);
	     DateFormat format1=new SimpleDateFormat("HH:mm:ss");
	     String time1=format1.format(date);
	     System.out.println(time1);

	}

}
