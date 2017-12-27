package keylistener_out;

import java.awt.Button;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class demo extends JFrame{

    private MyPanel1 myPanle;

    public static void main(String[] args) {
        demo demo = new demo();
    }

    public demo(){   //额，直接写在构造方法里面了，所以就不用调用方法了
        myPanle = new MyPanel1();
       // JPanel kButton=new  JPanel();
      //  this.setLayout(new FlowLayout()); 加了这个布局小球就画不出来了
    //   this.add(kButton);
        
        
       this.add(myPanle);//去掉这句话可以执行按键方法中的函数，但是却不能执行paint方法，因为这里有一个panel，
         //所以要添加进窗口中，然后调用的是窗口的重绘方法paint
        
        
      //和以前的监听方法一个样，新建一个事件处理类继承事件的接口，这个按键的好像还有抽象类
        //然后给对象添加事件监听方法，参数就是那个事件处理类的实例
        this.addKeyListener(myPanle);//谁继承了keylistener就addkeyListener谁
        //哦。这里的事件源是类，所以画主界面的类就是事件源，然后把事件处理类添加到参数中
        //而以前的事件源对象是容器或者元素组件
        //额，这里的this应该也可以代表jframe，所以准确的说，是向窗口注册了按键这个事件   
        
     System.out.println(this);
        this.setSize(400, 300);
        //这里的this就是demo这个类
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setVisible(true);
    }


}
