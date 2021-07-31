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

		// DB접속정보
		String dbUrl = "jdbc:mysql://localhost:3306/web?useUnicode=true&characterEncoding=utf8&allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=Asia/Seoul";
		String dbId = "myid";
		String dbPwd = "mypwd";

		// 1단계. DB드라이버 클래스 로딩
		Class.forName("com.mysql.cj.jdbc.Driver");
		// 2단계. DB에 연결 시도. 연결후 Connection객체를 리턴함.
		con = DriverManager.getConnection(dbUrl, dbId, dbPwd);
		// ===============================================

		// 커넥션 풀 방식
//		Context context = new InitialContext();
//		DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/jspdb");
//		con = ds.getConnection(); // 커넥션 한개 빌려오기
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

	// 다음 re_seq 부여하기
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

	// re_ref 부여하기
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
