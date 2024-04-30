package StaticFiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataBaseFunctions {

	public Connection getConnection(String dbUrl, String username, String password) {
		Connection connection = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection(dbUrl, username, password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	// Close a database connection
	public void closeConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// Execute a query and return result rows as an array of strings
	public static List<String[]> executeQuery(Connection connection, String query) {
		List<String[]> result = new ArrayList<>();

		if (connection != null) {
			try {
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(query);

				int columnCount = resultSet.getMetaData().getColumnCount();
				while (resultSet.next()) {
					String[] row = new String[columnCount];
					for (int i = 0; i < columnCount; i++) {
						row[i] = resultSet.getString(i + 1);
					}
					result.add(row);
				}

				resultSet.close();
				statement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}

}
