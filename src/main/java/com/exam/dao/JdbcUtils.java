package com.exam.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class JdbcUtils {

	public static Connection getConnection() throws Exception {

		Connection con = null;

		// DB��������
		String dbUrl = "jdbc:mysql://localhost:3306/web?useUnicode=true&characterEncoding=utf8&allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=Asia/Seoul";
		String dbId = "myid";
		String dbPwd = "mypwd";

		// 1�ܰ�. DB����̹� Ŭ���� �ε�
		Class.forName("com.mysql.cj.jdbc.Driver");
		// 2�ܰ�. DB�� ���� �õ�. ������ Connection��ü�� ������.
		con = DriverManager.getConnection(dbUrl, dbId, dbPwd);
		// ===============================================

		// Ŀ�ؼ� Ǯ ���
//		Context context = new InitialContext();
//		DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/jspdb");
//		con = ds.getConnection(); // Ŀ�ؼ� �Ѱ� ��������
		return con;
	} // getConnection()

	public static void close(Connection con, PreparedStatement pstmt) {
		close(con, pstmt, null);
	}

	public static void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (pstmt != null) {
				pstmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} // close()

	public static int getNextNum(String tableName) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int num = 0;
		String sql = "";

		try {
			con = JdbcUtils.getConnection();

			sql = "SELECT AUTO_INCREMENT ";
			sql += "FROM information_schema.tables ";
			sql += "WHERE table_name = ? ";
			sql += "AND table_schema = DATABASE() ";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, tableName);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				num = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.close(con, pstmt, rs);
		}
		return num;
	} // getNextNum

	// ���� re_seq �ο��ϱ�
	public static int getNextSeq(String tableName, int re_ref) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int re_seq = 0;
		String sql = "";

		try {
			con = JdbcUtils.getConnection();

			sql = " SELECT MAX(re_seq) FROM " + tableName + " WHERE re_ref = ?";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, re_ref);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				re_seq = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.close(con, pstmt, rs);
		}
		re_seq = re_seq + 1;
		return re_seq;
	} // getNextSeq

	// re_ref �ο��ϱ�
	public static int getNumRef(String tableName, int re_ref) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int num = 0;
		String sql = "";

		try {
			con = JdbcUtils.getConnection();

			sql = " SELECT num FROM " + tableName + " WHERE re_lev = 0 AND re_ref = ?";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, re_ref);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				num = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.close(con, pstmt, rs);
		}
		return num;
	} // getNextSeq

}
