package com.hjc.java_common_tools.db.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

public class ApacheCommonsDBCPTest {

	public static void main(String[] args) {
		testDBCPDataSource("mysql");
		System.out.println("**********");
		// testDBCPDataSource("oracle");
	}

	private static void testDBCPDataSource(String dbType) {
		DataSource ds = DBCPDataSourceFactory.getDataSource(dbType);

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select empid, name from Employee");
			while (rs.next()) {
				System.out.println("Employee ID=" + rs.getInt("empid")
						+ ", Name=" + rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResource(rs, stmt, conn);
		}
	}

}