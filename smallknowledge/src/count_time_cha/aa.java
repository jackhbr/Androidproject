package count_time_cha;

public class aa {

	/**
	 * @param args
	 */
	public static void main(String[]args){
		  String str="";
		  long starTime=System.currentTimeMillis();
		  //����ѭ��10000��ʱ��
		  for(int i=0;i<10000;i++){
		   str=str+i;
		  }
		  long endTime=System.currentTimeMillis();
		  long Time=endTime-starTime;
		  System.out.println(Time);
		  StringBuilder bulider=new StringBuilder("");
		  starTime=System.currentTimeMillis();
		  for(int j=0;j<10000;j++){
		   bulider.append(j);
		  }
		  endTime=System.currentTimeMillis();
		  System.out.println(endTime);
		  Time=endTime-starTime;
		  System.out.println(Time);
		}

}
