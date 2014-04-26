package com.hjc.java_common_tools.db.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 对数据库连接资源进行关闭的工具类。
 * 
 */
public final class DBUtil {

	public static void closeResultSet(ResultSet rs) {
		if (null != rs) {
			try {
				rs.close();
			} catch (Exception ignored) {
			}
		}
	}

	public static void closeStatement(Statement stmt) {
		if (null != stmt) {
			try {
				stmt.close();
			} catch (Exception ignored) {
			}
		}
	}

	public static void closeConnection(Connection conn) {
		if (null != conn) {
			try {
				conn.close();
			} catch (Exception ignored) {
			}
		}
	}

	public static void closeResource(ResultSet rs, Statement stmt,
			Connection conn) {
		closeResultSet(rs);
		closeStatement(stmt);
		closeConnection(conn);
	}
}
