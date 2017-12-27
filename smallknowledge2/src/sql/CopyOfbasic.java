package sql;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
public class CopyOfbasic {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 getAll();
		

	}
	private static Connection getConn() {  
	    String driver = "com.mysql.jdbc.Driver";
	    String url = "jdbc:mysql://localhost:3306/samp_db";
	    String username = "root";
	    String password = "qq2986388386";
	    Connection conn = null;
	    try {
	        Class.forName(driver); //classLoader,加载对应驱动
	        conn = (Connection) DriverManager.getConnection(url, username, password);
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return conn;
	}
	
	private static int insert(Student student) {
	    Connection conn = getConn();
	    int i = 0;
	    String sql = "insert into students (Name,Sex,Age) values(?,?,?)";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        pstmt.setString(1, student.getName());//这个1对应上面的第一个问号，就是把字符串放进对应的问号里面
	        pstmt.setString(2, student.getSex());//哦，这个是进入编译阶段，所以会识别里面的问号，所以可以设置
	        pstmt.setString(3, student.getAge());
	        i = pstmt.executeUpdate();//把编译后的语句发送到数据库中
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return i;
	}
	
	private static int update(Student student) {
	    Connection conn = getConn();
	    int i = 0;
	    String sql = "update students set Age='" + student.getAge() + "' where Name='" + student.getName() + "'";
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
	
	private static Integer getAll() {
	    Connection conn = getConn();
	    String sql = "select * from students where name='小红'";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();//在此 PreparedStatement 对象中执行 SQL 查询，并返回该查询生成的 ResultSet 对象。
	      //表示数据库结果集的数据表，通常通过执行查询数据库的语句生成。
	        System.out.println(rs.getMetaData());//这个返回的是表头的说明
	        int col = rs.getMetaData().getColumnCount();//获取表的列数
	        String hang=rs.getMetaData().getColumnName(col);//获取第四列的名字
	        System.out.println("   "+hang);
	        System.out.println(col);
	        System.out.println("============================");
//	        while (rs.next()) {//对数据表里面的所有数据进行遍历
//	            for (int i = 1; i <= col; i++) {   //额，为什么没有换行符就自动换行了呢？？
//	                System.out.print(rs.getString(i) + "\t");
//	                if ((i == 2) && (rs.getString(i).length() < 8)) {//到第二个就加多一个制表符，加长
//	                    System.out.print("\t");
//	                }
//	             }
//	        	
//	        }
	        if(rs.next())
	        {System.out.println("记录存在");
	      
	    	   int pasString=rs.getInt("age"); //只有在while（rs.next()）中才可以运行这句话
	        	System.out.println("年龄是"+pasString);
	    
          
	        }
	        else {
				System.out.println("记录不存在");
			}
	        
	        
	        
	            System.out.println("============================");
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	private static int delete(String name) {
	    Connection conn = getConn();
	    int i = 0;
	    String sql = "delete from students where Name='" + name + "'"; //注意这里的单引号和双引号
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

}
