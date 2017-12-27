package stopthread;

public class ThreadInterrupt extends Thread  
{  
    public void run()  
    {  
        try  
        {  
            sleep(50000);  // 延迟50秒  
        }  
        catch (InterruptedException e)  //这个方法的关键就在于这里的catch(interrupted...)
        {                              //调用线程的中断方法来跳出中断
            System.out.println(e.getMessage());  
        }  
    }  
    public static void main(String[] args) throws Exception  
    {  
        Thread thread = new ThreadInterrupt();  
        thread.start();  
        System.out.println("在50秒之内按任意键中断线程!");  
        System.in.read();  //这是一个阻塞方法（blocks），只有读取到键盘有输入（按下回车键）的情况下才可以继续执行下一部程序
        System.out.println("aaa");
        thread.interrupt();  //调用这个方法就可以跳出线程
        
        thread.join();  //必须要有join，wait,sleep类的函数才能这样加中断，不然就用isinterruprt
        System.out.println("线程已经退出!");  
    }  
}  
