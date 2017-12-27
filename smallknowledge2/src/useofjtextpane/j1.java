package useofjtextpane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Enumeration;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.StyleConstants;

public class j1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame jf=new JFrame();
		JPanel left_top_Pane = new JPanel();  
		left_top_Pane.setLayout(new BorderLayout());  
		JTextPane textPane = new JTextPane();  
		left_top_Pane.add(new JScrollPane(textPane), BorderLayout.CENTER);
		
		ImageIcon jImage=new ImageIcon("d:\\jc.jpg"); 
		jImage.setImage(jImage.getImage().getScaledInstance(60, 60, 0));  //设置图片的大小
			
		textPane.insertIcon(jImage);
		
		//Color textColor=new Color(Color.BLUE);
		Document doc = textPane.getStyledDocument();  
		MutableAttributeSet set = new MutableAttributeSet() {
			
			@Override
			public boolean isEqual(AttributeSet attr) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean isDefined(Object attrName) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public AttributeSet getResolveParent() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Enumeration<?> getAttributeNames() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public int getAttributeCount() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public Object getAttribute(Object key) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public AttributeSet copyAttributes() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public boolean containsAttributes(AttributeSet attributes) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean containsAttribute(Object name, Object value) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public void setResolveParent(AttributeSet parent) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void removeAttributes(AttributeSet attributes) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void removeAttributes(Enumeration<?> names) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void removeAttribute(Object name) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void addAttributes(AttributeSet attributes) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void addAttribute(Object name, Object value) {
				// TODO Auto-generated method stub
				
			}
		};  
		StyleConstants.setForeground(set, Color.BLUE);//设置文字颜色  
		StyleConstants.setFontSize(set, 12);//设置字体大小  
		String text="天天开心";
		try{  
		      doc.insertString(doc.getLength(), text, set);//插入文字  
		}catch (BadLocationException e){  
		  
		}  
		
		
		
		jf.add(left_top_Pane);
		jf.setSize(400, 300);
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
	

	}

}
