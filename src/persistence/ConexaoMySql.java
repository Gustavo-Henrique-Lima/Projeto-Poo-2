package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMySql implements IConexao
{
	private final String USER = "root";
	private final String PASSWORD = "Mototaxi37264365";
	private final String ADDRESS = "localhost";
	private final String PORT = "3306";
	private final String DATABASE = "projetobancopoo";
	
	@Override
	public Connection getConexao() {
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://"+ADDRESS+":"+PORT+"/"+DATABASE, USER, PASSWORD);
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}