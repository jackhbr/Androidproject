package stopthread;

public class ThreadFlag extends Thread  
{  
    public volatile boolean exit = false;   //注意这里用了volatile关键字，这个关键字的目的是使exit同步，也就是说在同一时刻只能由一个线程来修改exit的值，
  
    public void run()  
    {  
        while (!exit);  //平常这里是while（true），这个时候改为判断标志位。
    }  
    public static void main(String[] args) throws Exception  
    {  
        ThreadFlag thread = new ThreadFlag();  
        thread.start();  
        sleep(5000); // 主线程延迟5秒  
        thread.exit = true;  // 终止线程thread  
        thread.join();  //等待这个线程的结束才运行下一步，就是阻塞
        System.out.println("线程退出!");  
    }  
}  
