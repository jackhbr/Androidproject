package stopthread;

public class ThreadInterrupt extends Thread  
{  
    public void run()  
    {  
        try  
        {  
            sleep(50000);  // �ӳ�50��  
        }  
        catch (InterruptedException e)  //��������Ĺؼ������������catch(interrupted...)
        {                              //�����̵߳��жϷ����������ж�
            System.out.println(e.getMessage());  
        }  
    }  
    public static void main(String[] args) throws Exception  
    {  
        Thread thread = new ThreadInterrupt();  
        thread.start();  
        System.out.println("��50��֮�ڰ�������ж��߳�!");  
        System.in.read();  //����һ������������blocks����ֻ�ж�ȡ�����������루���»س�����������²ſ��Լ���ִ����һ������
        System.out.println("aaa");
        thread.interrupt();  //������������Ϳ��������߳�
        
        thread.join();  //����Ҫ��join��wait,sleep��ĺ��������������жϣ���Ȼ����isinterruprt
        System.out.println("�߳��Ѿ��˳�!");  
    }  
}  
