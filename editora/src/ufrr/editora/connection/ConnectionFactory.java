package ufrr.editora.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionFactory {
	
//	public Connection getConnection() {
//		try {
//			return DriverManager.getConnection("jdbc:postgresql://localhost/editora", "postgres", "leo123");
//		} catch (SQLException e) {
//			throw new RuntimeException();
//		}
//	}
	
	// para servidor de teste
	public Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/editora", "editora", "editora");
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

}
