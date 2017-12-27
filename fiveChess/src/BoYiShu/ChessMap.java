package BoYiShu;

import java.awt.Container;
import java.awt.Font;
import java.awt.MenuBar;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;
import javax.tools.Tool; 

class ChessMap extends JFrame{
	private ImageIcon map;
	private ImageIcon whitechess;
	private ImageIcon blackchess;
	private ChessPanel cp;
	private JPanel east;
	private JPanel west;
	private static final int FINAL_WIDTH=450;
	private static final int FINAL_HEIGHT=500;
	private JMenuBar menubar;
	private JMenu menu[]={new JMenu("��ʼ"),new JMenu("����"),new JMenu("����")};
	private JMenuItem[] menuitem1={new JMenuItem("���¿�ʼ"),new JMenuItem("����"),new JMenuItem("�˳�")};
	private JMenuItem[] menuitem2={new JMenuItem("����ѡ��"),new JMenuItem("�˻�����"),new JMenuItem("���˶���")};
	private JMenuItem[] menuitem3={new JMenuItem("����"),new JMenuItem("����")};
	private boolean haveai=true;
	Mouseclicked mouseclick =new Mouseclicked();
	MouseMoved mousemoved=new MouseMoved();
	Menuitemclicked menuclick=new Menuitemclicked();
	
	
	
	
	public ChessMap(){
		Font font=new Font("Dialog",Font.PLAIN,12);
		java.util.Enumeration keys=UIManager.getDefaults().keys();
		while(keys.hasMoreElements()){
			Object key=keys.nextElement();
			Object value=UIManager.get(key);
			if(value instanceof FontUIResource)
				UIManager.put(key, value);
			
		}
		setTitle("������");
		setSize(FINAL_WIDTH,FINAL_HEIGHT);
		setResizable(false);
		init();
		setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2
				-FINAL_WIDTH/2,Toolkit.getDefaultToolkit().getScreenSize().height/2
				-FINAL_HEIGHT/2);
		setDefaultCloseOperation(3);
		cp.reset();
		setVisible(true);
	}
	public void init(){
		map=new ImageIcon(this.getClass().getResource("bg.jpg"));
		whitechess=new ImageIcon(this.getClass().getResource("whitechess.GIF"));
		blackchess=new ImageIcon(this.getClass().getResource("blackchess.GIF"));
		System.out.println("white�Ŀ��Ϊ��"+whitechess.getIconHeight());
//		System.out.println("white�Ŀ��Ϊ��"+whitechess.getIconHeight());
		cp=new ChessPanel(map,blackchess,whitechess);
		menubar=new JMenuBar();
		menuitem1[0].setActionCommand("Restart");
		menuitem1[1].setActionCommand("Rollback");
		menuitem1[2].setActionCommand("Exist");
		menuitem2[0].setActionCommand("Forbid");
		menuitem2[1].setActionCommand("Robot");
		menuitem2[2].setActionCommand("Human");
		menuitem3[0].setActionCommand("Rule");
		menuitem3[1].setActionCommand("About");
		for(int i=0;i<3;i++){
			menu[0].add(menuitem1[i]);
		}
		for(int i=0;i<3;i++){
			menu[1].add(menuitem2[i]);
		}
		for(int i=0;i<2;i++){
			menu[2].add(menuitem3[i]);
		}
		for(int i=0;i<3;i++){
			menubar.add(menu[i]);
		}
		Container p=getContentPane();
		setJMenuBar(menubar);
		east=new JPanel();
		west=new JPanel();
		p.add(east,"East");
		p.add(west,"West");
		p.add(cp,"Center");
		cp.addMouseListener(mouseclick);
		cp.addMouseMotionListener(mousemoved);
		menuitem1[0].addActionListener(menuclick);
		menuitem1[1].addActionListener(menuclick);
		menuitem1[2].addActionListener(menuclick);
		menuitem2[0].addActionListener(menuclick);
		menuitem2[1].addActionListener(menuclick);
		menuitem2[2].addActionListener(menuclick);
		menuitem3[0].addActionListener(menuclick);
		menuitem3[1].addActionListener(menuclick);
	}
	class Mouseclicked extends MouseAdapter{
		public void mouseClicked (MouseEvent e){
			if(cp.win==false){
				if(haveai){
					Point p1=new Point();
					p1=cp.getPoint(e.getX(), e.getY());
					int x1=p1.x;
					int y1=p1.y;
					if(cp.isChessOn[x1][y1]!=2)
						return  ;
					System.out.println("cp.BLACK_ONE:"+cp.BLACK_ONE);
					if(cp.able_flag&&cp.bw==0){
						int type=cp.getType(x1, y1, cp.bw);
						String str=null;
						switch(type){
						case 20:
							str="�ڳ������֣���ѡ������λ��";
							break;
						case 21:
							str="�����Ľ��֣���ѡ������λ��";
							break;
						case 22:
							str="���������֣���ѡ������λ��";
							break;
						default :
							break;
						}
						if(str!=null){
							JOptionPane.showMessageDialog(null, str);
							return ;
						}
					}
					boolean flag=cp.haveWin(x1,y1,cp.bw);
					cp.update(x1, y1);
					if(cp.chess_num==1){
						if(x1-1>=0)
							cp.x_min=x1-1;
						if(x1+1<15)
							cp.x_max=x1+1;
						if(y1-1>=0)
							cp.y_min=y1-1;
						if(y1+1<15)
							cp.y_max=y1+1;
					}else
						cp.resetMaxMin(x1,y1);
					
					if(flag){
							cp.wined(1-cp.bw);
							return;
						}
						cp.putOne(cp.bw);
					
				}else{
					Point p1=new Point();
					p1=cp.getPoint(e.getX(), e.getY());
					int x=p1.x;
					int y=p1.y;
					if(cp.isChessOn[x][y]!=2)
						return;
					if(cp.able_flag&&cp.bw==cp.BLACK_ONE){
						int type=cp.getType(x,y,cp.bw);
						String str=null;
						switch(type){
						case 20:
							str="�ڳ������֣���ѡ������λ��";
							break;
						case 21:
							str="�����Ľ��֣���ѡ������λ��";
							break;
						case 22:
							str="���������֣���ѡ������λ��";
							break;
						default :
							break;
						}
						if(str!=null){
							JOptionPane.showMessageDialog(null, str);
							return;
						}
						boolean flag=cp.haveWin(x,y,cp.bw);
						cp.update(x,y);
						if(flag){
							cp.wined(1-cp.bw);
							return;
						}
					}
				}
			}
		}
	}
	class MouseMoved implements MouseMotionListener{

		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			cp.showMousePos(e.getPoint());
		}

		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	class Menuitemclicked implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub]
			JMenuItem target=(JMenuItem)e.getSource();
			String command=e.getActionCommand();
			if(command.equals("Restart")){
				cp.reset();
				if(cp.sbw==cp.WHITE_ONE)
					cp.update(7, 7);
			}
			if(command.equals("Rollback")){
				if(cp.win){
					JOptionPane.showMessageDialog(null, "���Ѿ�����,����һ�̰�");
					return;
				}else{
					if(cp.chess_num>=2&&cp.bw==cp.sbw){
						cp.isChessOn[cp.pre[cp.chess_num-1][0]][cp.pre[cp.chess_num-1][1]]=2;
						cp.isChessOn[cp.pre[cp.chess_num-2][0]][cp.pre[cp.chess_num-2][1]]=2;
						cp.chess_num-=2;
						repaint();
					}
					if(cp.chess_num>=1&&cp.bw!=cp.sbw){
						cp.isChessOn[cp.pre[cp.chess_num-1][0]][cp.pre[cp.chess_num-1][1]]=2;
						cp.chess_num--;
						repaint();
					}
				}
			}
			if(command.equals("Exist"))
				System.exit(1);
			if(command.equals("Forbid")){
				Object[] options={"�н���","û�н���"};
				int sel=JOptionPane.showOptionDialog(null, "���ѡ��", "����ѡ��", JOptionPane.DEFAULT_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				if(sel==1){
					cp.able_flag=false;
				}else
					cp.able_flag=true;
			}
			if(command.equals("Robot")){
				haveai=true;
				Object[] options={"��������","��������"};
				int sel=JOptionPane.showOptionDialog(null, "���ѡ��", "����ѡ��", JOptionPane.DEFAULT_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				if(sel==0){
					cp.sbw=cp.BLACK_ONE;
				}else{
					cp.sbw=cp.WHITE_ONE;
					cp.update(7, 7);
				}
			}
			if(command.equals("Human"))
				cp.setHumanhuman(true);
			if(command.equals("Rule")){
				JOptionPane.showConfirmDialog(null,
		      			"1���޽��֣�" +"\n"+
						"   �ڰ�˫���������ӣ���һ�������������γ����������(���������)���ӵ�һ��Ϊʤ��" +"\n"+
						"2���н��֣����߽��־��䣬���ֲ������ӣ�" +"\n"+
						"   �����޽��ֹ�������ʤ�����ǲ��ϲ���һЩ�������ƺ������е����ƣ���ƽ��ڰ�˫������ʽ��" +"\n"+
						"   ������Ժ���ĸ��ֽ������γɡ�" +"\n"+
						"   ������Ҫ��Ϊ���¼��ࣺ" +"\n"+
						"   (1)�ڳ������֣�������������������ͬ�����ӡ�" +"\n"+
						"   (2)���������֣��������ϵĻ�����" + "\n"+
						"   (3)�����Ľ��֣��������ϵ��ġ�" + "\n"+
						"   ��������Ժ�����Եģ�����û���κν��֡�" ,"����",JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE);
			}
			if(command.equals("About")){
				JOptionPane.showConfirmDialog(null, "���ɵĴ��룬����", "����", JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
			}
			
			
			
		}
		
	}
	public static void main(String[] args){
		new ChessMap();
	}
	
}













