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

    public demo(){   //�ֱ��д�ڹ��췽�������ˣ����ԾͲ��õ��÷�����
        myPanle = new MyPanel1();
       // JPanel kButton=new  JPanel();
      //  this.setLayout(new FlowLayout()); �����������С��ͻ���������
    //   this.add(kButton);
        
        
       this.add(myPanle);//ȥ����仰����ִ�а��������еĺ���������ȴ����ִ��paint��������Ϊ������һ��panel��
         //����Ҫ��ӽ������У�Ȼ����õ��Ǵ��ڵ��ػ淽��paint
        
        
      //����ǰ�ļ�������һ�������½�һ���¼�������̳��¼��Ľӿڣ���������ĺ����г�����
        //Ȼ�����������¼��������������������Ǹ��¼��������ʵ��
        this.addKeyListener(myPanle);//˭�̳���keylistener��addkeyListener˭
        //Ŷ��������¼�Դ���࣬���Ի��������������¼�Դ��Ȼ����¼���������ӵ�������
        //����ǰ���¼�Դ��������������Ԫ�����
        //������thisӦ��Ҳ���Դ���jframe������׼ȷ��˵�����򴰿�ע���˰�������¼�   
        
     System.out.println(this);
        this.setSize(400, 300);
        //�����this����demo�����
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setVisible(true);
    }


}
