package namoo.board.comm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JDBCUtil {

	// Driver load, Connection
	public static Connection getConnection() {
		//
		String driverName = "org.mariadb.jdbc.Driver";
		String url = "jdbc:mysql://127.0.0.1:3306/emrodb";
		String user = "emrouser";
		String password = "emrouser";

		try {
			Class.forName(driverName);
			return DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			System.out.println("접속 오류 발생.");
			e.printStackTrace();
		}
		return null;
	}

	public static void close(PreparedStatement stmt, Connection con) {
		try {
			if (stmt != null)
				stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			stmt = null;
		}
		try {
			if (con != null)
				con.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con = null;
		}
	}

	public static void close(ResultSet rst, PreparedStatement stmt, Connection con) {
		try {
			if (rst != null)
				rst.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rst = null;
		}
		try {
			if (stmt != null)
				stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			stmt = null;
		}
		try {
			if (con != null)
				con.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con = null;
		}
	}
}
