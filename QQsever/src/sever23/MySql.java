package sever23;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class MySql {
	private static Connection getConn() {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/qq";
		String username = "root";
		String password = "qq2986388386";
		Connection conn = null;
		try {
			Class.forName(driver); // classLoader,���ض�Ӧ����
			conn = (Connection) DriverManager.getConnection(url, username,
					password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static int insert(Client client) {
		Connection conn = getConn();
		int i = 0;
		String sql = "insert into client (QQname,acount,qqpassword) values(?,?,?)";
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, client.getName());// ���1��Ӧ����ĵ�һ���ʺţ����ǰ��ַ����Ž���Ӧ���ʺ�����
			pstmt.setString(2, client.getAcount());// Ŷ������ǽ������׶Σ����Ի�ʶ��������ʺţ����Կ�������
			pstmt.setString(3, client.getPassWord());
			i = pstmt.executeUpdate();// �ѱ�������䷢�͵����ݿ���
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	public static int update(Client client) {// ����QQ�������˻�����
		Connection conn = getConn();
		int i = 0;
		String sql = "update client set acount='" + client.getAcount()
				+ "' where QQname='" + client.getName() + "'";
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			i = pstmt.executeUpdate();
			System.out.println("resutl: " + i);
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	public static Integer getAll() {
		Connection conn = getConn();
		String sql = "select * from client";
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();// �ڴ� PreparedStatement ������ִ��
												// SQL ��ѯ�������ظò�ѯ���ɵ� ResultSet
												// ����
			// ��ʾ���ݿ����������ݱ�ͨ��ͨ��ִ�в�ѯ���ݿ��������ɡ�

			int col = rs.getMetaData().getColumnCount();// ��ȡ�������
			System.out.println("============================");
			while (rs.next()) {// �����ݱ�������������ݽ��б���
				for (int i = 1; i <= col; i++) { // �Ϊʲôû�л��з����Զ��������أ���
					System.out.print(rs.getString(i) + "\t");
					if ((i == 2) && (rs.getString(i).length() < 8)) {// ���ڶ����ͼӶ�һ���Ʊ�����ӳ�
						System.out.print("\t");
					}
				}
				System.out.println("");
			}
			System.out.println("============================");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static int delete(String acount) {// �����˻�����ɾ���ÿͻ�
		Connection conn = getConn();
		int i = 0;
		String sql = "delete from client where acount='" + acount + "'"; // ע������ĵ����ź�˫����
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			i = pstmt.executeUpdate();
			System.out.println("resutl: " + i);
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	public static Client getClient(String acount)// �����˻�����ȡ�ͻ�
													// ,������򷵻ؿͻ���û���򷵻�null
	{
		Client client=new Client();
		Connection conn = getConn();
		String sql = "select * from client where acount='" + acount + "'";
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				System.out.println("��¼����");
				String QQname=rs.getString("QQname");
				String passWord = rs.getString("qqpassword"); 
				System.out.println("��ȡ��QQ����" + QQname);
				System.out.println("��ȡ��������"+passWord);
				client.setAcount(acount);
				client.setName(QQname);
				client.setPassWord(passWord);
				return client;

			} else {
				System.out.println("��¼������");
				
			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		 return null;
	}

}
