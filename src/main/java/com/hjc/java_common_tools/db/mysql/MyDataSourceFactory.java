package com.hjc.java_common_tools.db.mysql;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class MyDataSourceFactory {

	public static void main(String[] args) {
		getMySQLDataSource();
	}

	public static DataSource getMySQLDataSource() {
		Properties props = new Properties();
		FileInputStream fis = null;
		MysqlDataSource mysqlDS = null;
		System.out.println(Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("db_resource/db.properties"));
		try {

			fis = new FileInputStream(Thread.currentThread()
					.getContextClassLoader().getResource("db_resource/db.properties")
					.getPath());
			props.load(fis);
			mysqlDS = new MysqlDataSource();
			mysqlDS.setURL(props.getProperty("MYSQL_DB_URL"));
			mysqlDS.setUser(props.getProperty("MYSQL_DB_USERNAME"));
			mysqlDS.setPassword(props.getProperty("MYSQL_DB_PASSWORD"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mysqlDS;
	}

}
