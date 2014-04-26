package com.hjc.java_common_tools.db.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Mysql_Operate {
	private static String MYSQL_JDBC_DRIVER_NAME = "com.mysql.jdbc.Driver";

	private static String MYSQL_JDBC_URL_TEMPLATE = "jdbc:mysql://%s:%s/%s";

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		String ip = "localhost";
		int port = 3306;
		String dbName = "test";

		String tableName = "hello";
		try {
			// 实际在现在版本的Mysql(大概是5.0级以上)，用户可以不执行这句话了
			Class.forName(MYSQL_JDBC_DRIVER_NAME);
			conn = DriverManager.getConnection(
					String.format(MYSQL_JDBC_URL_TEMPLATE, ip, port, dbName),
					"root", "root");
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from " + tableName);
			List<String> columns = new ArrayList<String>();
			int columnNum = rs.getMetaData().getColumnCount();

			while (rs.next()) {
				for (int i = 1; i <= columnNum; i++) {
					columns.add(rs.getString(i));
				}
			}
			for (String str : columns) {
				System.out.print(str + "\t");
			}
			String temp = "insert into " + tableName
					+ "(pkid,id,content) values('%s','%s','%s')";
			String insertSql = String.format(temp, columns.get(0),
					columns.get(1), columns.get(6));
			System.out.println(insertSql);
			stmt.executeUpdate(insertSql);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResource(rs, stmt, conn);
		}
	}
}
