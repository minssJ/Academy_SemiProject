package BOOKSTORE;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBC {

	public static Connection DBC() {
		
		Connection con = null;
		
		String user = "MINSOO";
		String password = "1111";
		
		String utl = "jdbc:oracle:thin:@localhost:1521:XE";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			con = DriverManager.getConnection(utl, user, password);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("DB접속 실패 : 드라이버 로딩 실패");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DB접속 실패 : 접속정보 오류");
		}
		
		return con;
		
	}
	
}
