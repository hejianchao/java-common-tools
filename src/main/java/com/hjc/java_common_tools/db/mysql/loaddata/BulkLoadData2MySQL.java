package com.hjc.java_common_tools.db.mysql.loaddata;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BulkLoadData2MySQL {

	private Connection conn = null;

	public static InputStream getTestDataInputStream() {
		StringBuilder builder = new StringBuilder();
		for (int i = 1; i <= 10; i++) {
			for (int j = 0; j <= 1; j++) {
				builder.append("8abc" + i);
				builder.append("\t");
				builder.append(4 + 1);
				builder.append("\t");
				builder.append(4 + 2);
				builder.append("\t");
				builder.append(4 + 3);
				builder.append("\t");
				builder.append(4 + 4);
				builder.append("\t");
				builder.append(4 + 5);
				builder.append("\n");
			}
		}
		System.out.println(builder.toString());
		byte[] bytes = builder.toString().getBytes();
		InputStream is = new ByteArrayInputStream(bytes);
		return is;
	}

	/**
	 * 
	 * load bulk data from InputStream to MySQL
	 */
	public int bulkLoadFromInputStream(String loadDataSql,
			InputStream dataStream) throws SQLException {
		if (dataStream == null) {
			System.out.println("InputStream is null ,No data is imported");
			return 0;
		}
		try {
			conn = getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		PreparedStatement statement = conn.prepareStatement(loadDataSql);

		int result = 0;

		if (statement.isWrapperFor(com.mysql.jdbc.Statement.class)) {

			com.mysql.jdbc.PreparedStatement mysqlStatement = statement
					.unwrap(com.mysql.jdbc.PreparedStatement.class);

			mysqlStatement.setLocalInfileInputStream(dataStream);
			result = mysqlStatement.executeUpdate();
		}
		return result;
	}

	public static Connection getConnection() throws Exception {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://127.0.0.1:3306/test";
		String user = "root";
		String password = "root";
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url, user, password);
		return conn;
	}

	public static void main(String[] args) {
		String testSql = "LOAD DATA LOCAL INFILE 'datax_temp' IGNORE INTO TABLE test.test (a,b,c,d,e,f)";
		InputStream dataStream = getTestDataInputStream();
		BulkLoadData2MySQL dao = new BulkLoadData2MySQL();
		try {
			long beginTime = System.currentTimeMillis();
			int rows = dao.bulkLoadFromInputStream(testSql, dataStream);
			long endTime = System.currentTimeMillis();
			System.out.println("importing " + rows
					+ " rows data into mysql and cost " + (endTime - beginTime)
					+ " ms!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.exit(1);
	}
}