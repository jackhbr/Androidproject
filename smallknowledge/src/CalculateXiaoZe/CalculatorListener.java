package CalculateXiaoZe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

/**
 * 定义的事件处理类  实现了继续加减乘除的操作。。  有点小bug
 */
public class CalculatorListener implements ActionListener
{

	private JTextField textShow;
	private int flag = 0;// 标识，用来控制点击运算符的次数
	private String value1, value2;// 记录第一个数据和第一个运算符
	private double d;

	public void setTextShow(JTextField textShow)
	{
		this.textShow = textShow;
	}

	/**
	 * 事件处理方法
	 */
	public void actionPerformed(ActionEvent e) 
	{
		// System.out.println("你点击的按钮是" + e.getActionCommand());
		String ac = e.getActionCommand();// 获取按钮上的数据
		String text = textShow.getText();
		if (ac.equals("."))//如果输入点，看之前有没有点，如果有，则此次输入无效，如果没有，则加上去
		{
			if (text.indexOf(".") == -1)// 判断是否出现过. 如果出现过就返回它出现的位置，如果没出现过就返回-1
			{
				textShow.setText(text + ac); //设置文本域的内容
			}
		} 
		else if (ac.equals("+") || ac.equals("-") || ac.equals("*")
				|| ac.equals("/") || ac.equals("=")) 
		{
			if (flag == 0)
			{// 标识第一次点击运算符
				value1 = textShow.getText();//记录下符号前面的数
				value2 = ac;//记录下符号
				textShow.setText("0");//然后把文本域清零，再次等待输入    重点！！！
				flag = 1;
			}
			else if (flag == 1)  
			{
				String value3 = textShow.getText();//获取符号后面的数
				if (value2.equals("+")) //第二次出现符号就代表要马上显示前面运算值了
				{
					double d1 = Double.parseDouble(value1);//double的封装类里面把字符串的变成double类型的函数
					double d3 = Double.parseDouble(value3);
					 d = d1 + d3;
				//	textShow.setText(d + "");//快速把其他基本数据类型转换为字符串的方法
				//	flag=0;
				}
				if (value2.equals("-")) //第二次出现符号就代表要马上显示前面运算值了
				{
					double d1 = Double.parseDouble(value1);//double的封装类里面把字符串的变成double类型的函数
					double d3 = Double.parseDouble(value3);
					 d = d1 - d3;
				//	textShow.setText(d + "");//快速把其他基本数据类型转换为字符串的方法
				}
				if (value2.equals("*")) //第二次出现符号就代表要马上显示前面运算值了
				{
					double d1 = Double.parseDouble(value1);//double的封装类里面把字符串的变成double类型的函数
					double d3 = Double.parseDouble(value3);
					 d = d1 * d3;
				//	textShow.setText(d + "");//快速把其他基本数据类型转换为字符串的方法
				}
				if (value2.equals("/")) //第二次出现符号就代表要马上显示前面运算值了
				{
					double d1 = Double.parseDouble(value1);//double的封装类里面把字符串的变成double类型的函数
					double d3 = Double.parseDouble(value3);
					 d = d1 / d3;
				//	textShow.setText(d + "");//快速把其他基本数据类型转换为字符串的方法
				}
				flag=2;
				textShow.setText(d + "");
				
				
				//flag = 0;
				if (ac.equals("=")) //现在的运算不能进行连续加的情况，但是可以先等出数值后再加,按两次等号就等于清零了
				{
					value1 = d+"";
					value2 = "";
					
					
				} 
				else  
				{
					value1 = d+"";
					value2 = ac;
					//textShow.setText("0");//然后把文本域清零，再次等待输入
				
				}
			}
		} 
		else //剩下的可能情况就是那十个数值了
		{
			// 获取textShow中的数据是否是0
			if (text.equals("0"))  //如果原来的数据是0，那么就把数值直接替换为现在输入这个，如果不是，那么就加上原来的数值
			{                      //请注意，这里的屏幕上是不会显示符号的
				textShow.setText(ac);
			} else {
				flag=1;
				textShow.setText( ac);
			}
		}
	}
}
