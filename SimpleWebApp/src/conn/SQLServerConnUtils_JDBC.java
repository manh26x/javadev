package conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLServerConnUtils_JDBC {
	
	// Kết nối vào SQL server
	// Sử dụng thư viện điều khiển JTDS
	public static Connection getSQLServerConnection_JTDS() //
		throws SQLException, ClassNotFoundException {
		
		String hostName = "localhost";
		String sqlInstanceName = "SQLEXPRESS";
        String database = "mytest";
        String userName = "sa";
        String password = "12345";
		return getSQLServerConnection_JTDS(hostName, sqlInstanceName, database, userName, password);
	}
	
	private static Connection getSQLServerConnection_JTDS(String hostName, //
			String sqlInstanceName, String database, String userName, String password) //
					throws ClassNotFoundException, SQLException {
		
		Class.forName("net.sourceforge.jtds.jdbc.Driver");
		
		// Cấu trúc URL Connection đối với SQL Server:
        // Ví dụ:
        // jdbc:jtds:sqlserver://localhost:1433/simplehr;instance=SQLEXPRESS
		String connectionURL = "jdbc:jtds:sqlserver://" + hostName + ":1433/" //
				+ database + ";instance=" + sqlInstanceName;
		
		Connection conn = DriverManager.getConnection(connectionURL, userName, password);
		
		return conn;
		
	}

}
