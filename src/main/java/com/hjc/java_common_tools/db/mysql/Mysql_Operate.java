package com.hjc.java_common_tools.db.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Mysql_Operate {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager
					.getConnection(
							"jdbc:mysql://10.235.152.49:3506/bbl?characterEncoding=utf8",
							"utf8mb4_test", "egnon2ege");
			stmt = conn.createStatement();
			rs = stmt
					.executeQuery("select * from comments where app_item_id = '46581859800'");
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
			String temp = "insert into bak_COMMENTs(pkid,id,content) values('%s','%s','%s')";
			String insertSql = String.format(temp, columns.get(0),
					columns.get(1), columns.get(6));
			System.out.println(insertSql);
			stmt.executeUpdate(insertSql);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
