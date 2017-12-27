package JlistUse;

import   java.awt.*;   
import   java.awt.event.*;   
import   javax.swing.*;   
import   java.util.Vector;   
  
public   class   TestJList   
{   
        public   TestJList()   
        {   
                String[]   s   =   {"����","ƻ��","��ݮ","�㽶","����"};   
                JFrame   f   =   new   JFrame("JList");   
                Container   contentPane   =   f.getContentPane();   
                JList   list1   =   new   JList(s);   
                list1.setBorder(BorderFactory.createTitledBorder("��ϲ������Щˮ����"));   
                /*������JList�л���ͼ���ڴ˲����У����ǲ���һ��CellRenderer���󣬴˶���ʵ����ListCellRenderer   interface,   
                  *��˿��Է���һ��ListCellRenderer������setCellRenderer()�����Ĳ���.   
                  */   
                list1.setCellRenderer(new   CellRenderer());   
                  
                contentPane.add(new   JScrollPane(list1));   
                f.pack();   
                f.show();   
                f.addWindowListener(new   WindowAdapter()   {   
                        public   void   windowClosing(WindowEvent   e)   {   
                                        System.exit(0);   
                        }   
                });   
        }   
          
        public   static   void   main(String   args[])   
        {   
                new   TestJList();   
        }   
}   
  
class   CellRenderer   extends   JLabel   implements   ListCellRenderer   
{   
      /*��CellRenderer�̳�JLabel��ʵ��ListCellRenderer.������������JLabel���ڲ�ͼ�����ԣ����CellRenderer�̳���JLabel   
        *����JList�е�ÿ����Ŀ����Ϊ��һ��JLabel.   
        */   
        CellRenderer()   
        {   
                setOpaque(true);   
        }   
        /*�����ﵽ������ʵ��getListCellRendererComponent()����*/   
        public   Component   getListCellRendererComponent(JList   list,   
                                                                                                    Object   value,   
                                                                                                    int   index,   
                                                                                                    boolean   isSelected,   
                                                                                                    boolean   cellHasFocus)   
        {         
                /*�����ж�list.getModel().getElementAt(index)�����ص�ֵ�Ƿ�Ϊnull,�����ϸ������У���JList�ı�����"�������   
                  *Щ���ݿ����"����index>=4����Ŀֵ����ȫ����Ϊnull.������������У���Ϊ������nullֵ�������û�м��������   
                  *�ϲ�û�й�ϵ.   
                  */   
                if   (value   !=   null)   
                {   
                        setText(value.toString());   
                        setIcon(new   ImageIcon("d:\\jc.jpg"));   
                }   
                if   (isSelected)   {   
                        setBackground(list.getSelectionBackground());   
                        setForeground(list.getSelectionForeground());   
                }   
                else   {   
                        //����ѡȡ��ȡ��ѡȡ��ǰ���뱳����ɫ.   
                        setBackground(list.getBackground());   
                        setForeground(list.getForeground());   
                }   
                return   this;   
        }           
}