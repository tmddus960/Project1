package members_Info;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {

	public static Connection getMySQLConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/javaam?useUnicode=true&characterEncoding=UTF-8";
			String user = "root";
			String password = "0000";
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			System.out.println("����̹� Ŭ������ ���ų� �о�� �� �����ϴ�.");
		} catch (SQLException e) {
			System.out.println("�����ͺ��̽� ���� ������ �ùٸ��� �ʽ��ϴ�.");
		}
		return conn;
	}
	
	public static void close(Connection conn) {
		if(conn != null) { try { conn.close(); } catch (SQLException e) { e.printStackTrace(); } }
	}
	public static void close(Statement stmt) {
		if(stmt != null) { try { stmt.close(); } catch (SQLException e) { e.printStackTrace(); } }
	}
	public static void close(PreparedStatement pstmt) {
		if(pstmt != null) { try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); } }
	}
	public static void close(ResultSet rs) {
		if(rs != null) { try { rs.close(); } catch (SQLException e) { e.printStackTrace(); } }
	}
	
}



















