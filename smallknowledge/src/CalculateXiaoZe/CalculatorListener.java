package CalculateXiaoZe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

/**
 * ������¼�������  ʵ���˼����Ӽ��˳��Ĳ�������  �е�Сbug
 */
public class CalculatorListener implements ActionListener
{

	private JTextField textShow;
	private int flag = 0;// ��ʶ���������Ƶ��������Ĵ���
	private String value1, value2;// ��¼��һ�����ݺ͵�һ�������
	private double d;

	public void setTextShow(JTextField textShow)
	{
		this.textShow = textShow;
	}

	/**
	 * �¼�������
	 */
	public void actionPerformed(ActionEvent e) 
	{
		// System.out.println("�����İ�ť��" + e.getActionCommand());
		String ac = e.getActionCommand();// ��ȡ��ť�ϵ�����
		String text = textShow.getText();
		if (ac.equals("."))//�������㣬��֮ǰ��û�е㣬����У���˴�������Ч�����û�У������ȥ
		{
			if (text.indexOf(".") == -1)// �ж��Ƿ���ֹ�. ������ֹ��ͷ��������ֵ�λ�ã����û���ֹ��ͷ���-1
			{
				textShow.setText(text + ac); //�����ı��������
			}
		} 
		else if (ac.equals("+") || ac.equals("-") || ac.equals("*")
				|| ac.equals("/") || ac.equals("=")) 
		{
			if (flag == 0)
			{// ��ʶ��һ�ε�������
				value1 = textShow.getText();//��¼�·���ǰ�����
				value2 = ac;//��¼�·���
				textShow.setText("0");//Ȼ����ı������㣬�ٴεȴ�����    �ص㣡����
				flag = 1;
			}
			else if (flag == 1)  
			{
				String value3 = textShow.getText();//��ȡ���ź������
				if (value2.equals("+")) //�ڶ��γ��ַ��žʹ���Ҫ������ʾǰ������ֵ��
				{
					double d1 = Double.parseDouble(value1);//double�ķ�װ��������ַ����ı��double���͵ĺ���
					double d3 = Double.parseDouble(value3);
					 d = d1 + d3;
				//	textShow.setText(d + "");//���ٰ�����������������ת��Ϊ�ַ����ķ���
				//	flag=0;
				}
				if (value2.equals("-")) //�ڶ��γ��ַ��žʹ���Ҫ������ʾǰ������ֵ��
				{
					double d1 = Double.parseDouble(value1);//double�ķ�װ��������ַ����ı��double���͵ĺ���
					double d3 = Double.parseDouble(value3);
					 d = d1 - d3;
				//	textShow.setText(d + "");//���ٰ�����������������ת��Ϊ�ַ����ķ���
				}
				if (value2.equals("*")) //�ڶ��γ��ַ��žʹ���Ҫ������ʾǰ������ֵ��
				{
					double d1 = Double.parseDouble(value1);//double�ķ�װ��������ַ����ı��double���͵ĺ���
					double d3 = Double.parseDouble(value3);
					 d = d1 * d3;
				//	textShow.setText(d + "");//���ٰ�����������������ת��Ϊ�ַ����ķ���
				}
				if (value2.equals("/")) //�ڶ��γ��ַ��žʹ���Ҫ������ʾǰ������ֵ��
				{
					double d1 = Double.parseDouble(value1);//double�ķ�װ��������ַ����ı��double���͵ĺ���
					double d3 = Double.parseDouble(value3);
					 d = d1 / d3;
				//	textShow.setText(d + "");//���ٰ�����������������ת��Ϊ�ַ����ķ���
				}
				flag=2;
				textShow.setText(d + "");
				
				
				//flag = 0;
				if (ac.equals("=")) //���ڵ����㲻�ܽ��������ӵ���������ǿ����ȵȳ���ֵ���ټ�,�����εȺž͵���������
				{
					value1 = d+"";
					value2 = "";
					
					
				} 
				else  
				{
					value1 = d+"";
					value2 = ac;
					//textShow.setText("0");//Ȼ����ı������㣬�ٴεȴ�����
				
				}
			}
		} 
		else //ʣ�µĿ������������ʮ����ֵ��
		{
			// ��ȡtextShow�е������Ƿ���0
			if (text.equals("0"))  //���ԭ����������0����ô�Ͱ���ֱֵ���滻Ϊ�������������������ǣ���ô�ͼ���ԭ������ֵ
			{                      //��ע�⣬�������Ļ���ǲ�����ʾ���ŵ�
				textShow.setText(ac);
			} else {
				flag=1;
				textShow.setText( ac);
			}
		}
	}
}
