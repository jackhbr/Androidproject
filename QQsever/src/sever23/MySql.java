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
			Class.forName(driver); // classLoader,加载对应驱动
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
			pstmt.setString(1, client.getName());// 这个1对应上面的第一个问号，就是把字符串放进对应的问号里面
			pstmt.setString(2, client.getAcount());// 哦，这个是进入编译阶段，所以会识别里面的问号，所以可以设置
			pstmt.setString(3, client.getPassWord());
			i = pstmt.executeUpdate();// 把编译后的语句发送到数据库中
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	public static int update(Client client) {// 根据QQ名更改账户号码
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
			ResultSet rs = pstmt.executeQuery();// 在此 PreparedStatement 对象中执行
												// SQL 查询，并返回该查询生成的 ResultSet
												// 对象。
			// 表示数据库结果集的数据表，通常通过执行查询数据库的语句生成。

			int col = rs.getMetaData().getColumnCount();// 获取表的行数
			System.out.println("============================");
			while (rs.next()) {// 对数据表里面的所有数据进行遍历
				for (int i = 1; i <= col; i++) { // 额，为什么没有换行符就自动换行了呢？？
					System.out.print(rs.getString(i) + "\t");
					if ((i == 2) && (rs.getString(i).length() < 8)) {// 到第二个就加多一个制表符，加长
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

	public static int delete(String acount) {// 根据账户号码删除该客户
		Connection conn = getConn();
		int i = 0;
		String sql = "delete from client where acount='" + acount + "'"; // 注意这里的单引号和双引号
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

	public static Client getClient(String acount)// 根据账户名获取客户
													// ,如果有则返回客户，没有则返回null
	{
		Client client=new Client();
		Connection conn = getConn();
		String sql = "select * from client where acount='" + acount + "'";
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				System.out.println("记录存在");
				String QQname=rs.getString("QQname");
				String passWord = rs.getString("qqpassword"); 
				System.out.println("获取的QQ名是" + QQname);
				System.out.println("获取的密码是"+passWord);
				client.setAcount(acount);
				client.setName(QQname);
				client.setPassWord(passWord);
				return client;

			} else {
				System.out.println("记录不存在");
				
			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		 return null;
	}

}
