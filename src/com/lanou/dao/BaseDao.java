package com.lanou.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseDao {

	// �ṩ��������Ҫ����Ϣ�Ͷ���
	private String ip = "127.0.0.1";
	private String port = "3306";
	private String dbName;
	private String user = "root";
	private String pwd = "qiaoyutao";
	private String url = null;
	private Connection conn = null;
	private Statement sta = null;
	private ResultSet rs = null;

	// ���췽��
	public BaseDao() {
		this.dbName = "db_student";
		this.url = "jdbc:mysql://" + this.ip + ":" + this.port + "/" + dbName
				+ "?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC";
	}

	public BaseDao(String dbName) {
		this.url = "jdbc:mysql://" + this.ip + ":" + this.port + "/" + dbName
				+ "?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC";
	}

	public BaseDao(String ip, String port, String dbName, String user, String pwd) {
		this.ip = ip;
		this.port = port;
		this.dbName = dbName;
		this.user = user;
		this.pwd = pwd;

		this.url = "jdbc:mysql://" + this.ip + ":" + this.port + "/" + this.dbName
				+ "?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC";
	}

	// �������ݿ�
	private boolean connectionDB() {
		System.out.println(this.url);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.conn = DriverManager.getConnection(this.url, this.user, this.pwd);
			this.sta = this.conn.createStatement();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// �ر����ݿ�
	private void closeDB() {

		try {
			if (this.rs != null && !this.rs.isClosed()) {
				this.rs.close();
				this.rs = null;
			}
			if (this.conn != null && !this.conn.isClosed()) {
				this.conn.close();
				this.conn = null;
			}
			if (this.sta != null && !this.sta.isClosed()) {
				this.sta.close();
				this.sta = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// ����ǲ�ѯ����
	public int upDataSql(String sql) {
		if (!connectionDB()) {
			// ���ݿ�����ʧ��
			closeDB();
		}
		int row = 0;
		try {
			row = this.sta.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} finally {
			closeDB();
		}
		return row;
	}

	// �����ѯ����
	public List<Map<String, Object>> query(String sql) {
		if (!connectionDB()) {
			closeDB();
		}
		ArrayList<Map<String, Object>> list = new ArrayList<>();
		try {
			this.rs = this.sta.executeQuery(sql);
			ResultSetMetaData metaData = this.rs.getMetaData();
			// ��ȡ����
			int row = metaData.getColumnCount();
			while (this.rs.next()) {
				HashMap<String, Object> map = new HashMap<>();
				for (int i = 1; i <= row; i++) {
					Object value = this.rs.getString(i);
					String key = metaData.getColumnName(i);
					map.put(key, value);

				}
				list.add(map);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			closeDB();
		}
		return list;
	}

	// ִ�ж����ǲ�ѯ���
	public int[] updataSqls(String[] sqls) {
		if (!this.connectionDB()) {
			closeDB();
		}
		int rows[] = null;
		try {
			for (String sql : sqls) {
				this.sta.addBatch(sql);
			}
			rows = this.sta.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return rows;
	}

	public int[] updataSqls(String sqls) {
//		if (!this.connectionDB()) {
//			closeDB();
//		}
		String[] sqlArray = sqls.split(";");
		return updataSqls(sqlArray);
//		int rows[] = null;
//		try {
//			for (String sql : sqlArray) {
//				this.sta.addBatch(sqls);
//			}
//			rows = this.sta.executeBatch();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			closeDB();
//		}
//		return rows;
	}

}
