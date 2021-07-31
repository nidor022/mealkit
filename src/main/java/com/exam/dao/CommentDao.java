package com.exam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.exam.vo.AttachVo;
import com.exam.vo.CommentVo;

public class CommentDao {
	
	private static CommentDao instance = new CommentDao();
	
	public static CommentDao getInstance() {
		return instance;
	}

	private CommentDao() {}
	
	
	
	// 댓글 입력
	public void insertComment(CommentVo commentVo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "";
		
		try {
			con = JdbcUtils.getConnection();
//										     쓴놈아이디,   쓴내용,   쓴날자,  게시글번호
			sql  = "INSERT INTO web.bcomment (c_id, c_content, c_date, b_num) ";
			sql += "VALUES (?, ?, ?, ?) ";
			
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, commentVo.getC_id());
			pstmt.setString(2, commentVo.getC_content());
			pstmt.setTimestamp(3, commentVo.getC_date());
			pstmt.setInt(4, commentVo.getB_num());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.close(con, pstmt);
		}
	} // insertAttach
	
		
	// 댓글 본글번호로 불러오기
	public List<CommentVo> getCommentsByNum(int b_num) {
		List<CommentVo> list = new ArrayList<>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		
		try {
			con = JdbcUtils.getConnection();
													// 원게시글 번호
			sql  = "SELECT * FROM web.bcomment WHERE b_num = ? ";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, b_num);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				CommentVo commentVo = new CommentVo();
				commentVo.setC_num(rs.getInt("c_num"));
				commentVo.setC_id(rs.getString("c_id"));
				commentVo.setC_content(rs.getString("c_content"));
				commentVo.setC_date(rs.getTimestamp("c_date"));
				commentVo.setB_num(rs.getInt("b_num"));
				
				list.add(commentVo);
			} // while
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.close(con, pstmt, rs);
		}
		
		return list;
	} // selectCommentsByNum
	
	
	// 총 댓글 갯수 세기
	public int getCommentsNum(int b_num) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		int num = 0;
		
		try {
			con = JdbcUtils.getConnection();
													// 원게시글 번호
			sql  = "SELECT COUNT(*) FROM web.bcomment WHERE b_num = ? ";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, b_num);

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
	} // getCommentsNum
}
