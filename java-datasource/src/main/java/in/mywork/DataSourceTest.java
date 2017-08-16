package in.mywork;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

public class DataSourceTest {
	public static void main(String[] args) {

	testDataSource("pgsql");
		System.out.println("**********");
		//testDataSource("other");

	}

	private static void testDataSource(String dbType) {
		DataSource ds = null;
		if ("pgsql".equals(dbType)) {
			ds = MyDataSourceFactory.getPgSQLDataSource();
		} else if ("other".equals(dbType)) {
			ds = MyDataSourceFactory.getOthrerSource();
		} else {
			System.out.println("invalid db type");
			return;
		}

		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery("select empid, name from Employee");
			while (rs.next()) {
				System.out.println("Employee ID=" + rs.getInt("empid")
						+ ", Name=" + rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
