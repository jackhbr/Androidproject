package stopthread;

public class ThreadFlag extends Thread  
{  
    public volatile boolean exit = false;   //ע����������volatile�ؼ��֣�����ؼ��ֵ�Ŀ����ʹexitͬ����Ҳ����˵��ͬһʱ��ֻ����һ���߳����޸�exit��ֵ��
  
    public void run()  
    {  
        while (!exit);  //ƽ��������while��true�������ʱ���Ϊ�жϱ�־λ��
    }  
    public static void main(String[] args) throws Exception  
    {  
        ThreadFlag thread = new ThreadFlag();  
        thread.start();  
        sleep(5000); // ���߳��ӳ�5��  
        thread.exit = true;  // ��ֹ�߳�thread  
        thread.join();  //�ȴ�����̵߳Ľ�����������һ������������
        System.out.println("�߳��˳�!");  
    }  
}  
