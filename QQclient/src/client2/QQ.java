package client2;  
  
import java.awt.Color;  
import java.awt.Container;  
import java.awt.Cursor;  
import java.awt.Font;  
import java.awt.Image;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
  
import javax.swing.ImageIcon;  
import javax.swing.JButton;  
import javax.swing.JCheckBox;  
import javax.swing.JComboBox;  
import javax.swing.JFrame;  
import javax.swing.JLabel;  
import javax.swing.JOptionPane;  
import javax.swing.JPasswordField;  
import javax.swing.JRootPane;  
import javax.swing.JTextField;  
  
public class QQ extends JFrame{  
      
    private static final long serialVersionUID = -6788045638380819221L;  
    //�û���  
    private JTextField ulName;  
    //����  
    private JPasswordField ulPasswd;  
    //С����  
    private JLabel j1;  
    private JLabel j2;  
    private JLabel j3;  
    private JLabel j4;  
    //С��ť  
    private JButton b1;  
    private JButton b2;  
    private JButton b3;  
    //��ѡ��  
    private JCheckBox c1;  
    private JCheckBox c2;  
    //�б��  
    private JComboBox<String> cb1;  
    /** 
     * ��ʼ��QQ��¼ҳ�� 
     * */  
    public QQ(){  
        //���õ�¼���ڱ���  
        this.setTitle("QQ��¼");  
        //ȥ�����ڵ�װ��(�߿�)  
//      this.setUndecorated(true);   
        //����ָ���Ĵ���װ�η��  
        this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);  
        //���������ʼ��  
        init();  
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        //���ò���Ϊ���Զ�λ  
        this.setLayout(null);  
        this.setBounds(0, 0, 355, 265);  
        //���ô����ͼ��  
        Image img0 = new ImageIcon("D:/bgp.jpg").getImage();  
        this.setIconImage(img0);  
        //�����С���ܸı�  
        this.setResizable(false);  
        //������ʾ  
        this.setLocationRelativeTo(null);  
        //������ʾ  
        this.setVisible(true);  
    }  
    /** 
     * ���������ʼ�� 
     * */  
    public void init(){  
        //����һ������,���е�ͼƬ��С��setBounds�������ĸ�����Ҫ����һ��(��Ҫ�Լ�����ü�)  
        Container container = this.getContentPane();  
        j1 = new JLabel();  
        //���ñ���ɫ  
        Image img1 = new ImageIcon("D:/bgp.jpg").getImage();
        j1.setIcon(new ImageIcon(img1));  
        j1.setBounds(0, 0, 355, 265);  
        //qqͷ���趨  
        j2 = new JLabel();  
        Image img2 = new ImageIcon("D:/jc.jpg").getImage();  
        j2.setIcon(new ImageIcon(img2));  
        j2.setBounds(40, 95, 50, 53);  
        //�û��������  
        ulName = new JTextField();  
        ulName.setBounds(100, 100, 150, 20);  
        //ע���˺�  
        j3 = new JLabel("ע���˺�");  
        j3.setBounds(260, 100, 70, 20);  
        //���������  
        ulPasswd = new JPasswordField();  
        ulPasswd.setBounds(100, 130, 150, 20);  
        //�һ�����  
        j4= new JLabel("�һ�����");  
        j4.setBounds(260, 130, 70, 20);  
        //��ס����  
        c1 = new JCheckBox("��ס����");  
        c1.setBounds(105, 155, 80, 15);  
        //�Զ���½  
        c2 = new JCheckBox("�Զ���½");  
        c2.setBounds(185, 155, 80, 15);  
        //�û���½״̬ѡ��  
        cb1 = new JComboBox<String>();  
        cb1.addItem("����");  
        cb1.addItem("����");  
        cb1.addItem("�뿪");  
        cb1.setBounds(40, 150, 55, 20);  
        //��½��ť  
        b1 = new JButton("��¼");  
        //�����������ɫ������ָ��  
        b1.setFont(new Font("����", Font.PLAIN, 12));  
        b1.setForeground(Color.RED);  
        b1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));  
        b1.setBounds(280, 200, 65, 20);  
        //����ť���  
        b1.addActionListener(new ActionListener() {  
            @Override  
            public void actionPerformed(ActionEvent e) {  
                String cmd = e.getActionCommand();  
                if("��¼".equals(cmd)){  
                    String username = ulName.getText();  
                    String userpassword = ulPasswd.getText();  
                    if(username.equals("tskk") && userpassword.equals("123456")){  
                        JOptionPane.showConfirmDialog(null, "��¼�ɹ�");  
                    }else{  
                        JOptionPane.showConfirmDialog(null, "��¼ʧ��");  
                    }  
                }  
            }  
        });  
        //���˺�  
        b2 = new JButton("���˺�");  
        b2.setBounds(5, 200, 75, 20);  
        //����  
        b3 = new JButton("����");  
        b3.setBounds(100, 200, 65, 20);  
        //�������������װ��  
        j1.add(j2);  
        j1.add(j3);  
        j1.add(j4);  
        j1.add(c1);  
        j1.add(c2);  
        j1.add(cb1);  
        j1.add(b1);  
        j1.add(b2);  
        j1.add(b3);  
        container.add(j1);  
        container.add(ulName);  
        container.add(ulPasswd);  
    }  
    public static void main(String[] args) {  
        new QQ();  
    }  
}  
