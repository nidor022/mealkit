package com.exam.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.exam.vo.CommentVo;
import com.exam.vo.NoticeVo;

public class NoticeDao {
	// 싱글톤
	private static NoticeDao instance = new NoticeDao();

	public static NoticeDao getInstance() {
		return instance;
	}
	/////////////

	private NoticeDao() {
	}

	// 주글쓰기 메서드
	public void addNotice(NoticeVo noticeVo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		String sql = "";

		try {
			con = JdbcUtils.getConnection();

			sql = "INSERT INTO web.bnotice (id, subject, content, readcount, reg_date, ip, re_ref, re_lev, re_seq) ";
			sql += "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) ";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, noticeVo.getId());
			pstmt.setString(2, noticeVo.getSubject());
			pstmt.setString(3, noticeVo.getContent());
			pstmt.setInt(4, noticeVo.getReadcount());
			pstmt.setTimestamp(5, noticeVo.getRegDate());
			pstmt.setString(6, noticeVo.getIp());
			pstmt.setInt(7, noticeVo.getReRef());
			pstmt.setInt(8, noticeVo.getReLev());
			pstmt.setInt(9, noticeVo.getReSeq());
			// 실행
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.close(con, pstmt);
		}
	} // addBoard()

	public NoticeVo getNoticeByNum(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		NoticeVo noticeVo = null;
		String sql = "";

		try {
			con = JdbcUtils.getConnection();

			sql = "SELECT * FROM web.bnotice WHERE num = ?";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				noticeVo = new NoticeVo();

				noticeVo.setNum(rs.getInt("num"));
				noticeVo.setId(rs.getString("id"));
				noticeVo.setSubject(rs.getString("subject"));
				noticeVo.setContent(rs.getString("content"));
				noticeVo.setReadcount(rs.getInt("readcount"));
				noticeVo.setRegDate(rs.getTimestamp("reg_date"));
				noticeVo.setIp(rs.getString("ip"));
				noticeVo.setReRef(rs.getInt("re_ref"));
				noticeVo.setReLev(rs.getInt("re_lev"));
				noticeVo.setReSeq(rs.getInt("re_seq"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.close(con, pstmt, rs);
		}
		return noticeVo;
	} // getNoticeByNum()

	public void updateReadcount(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;

		String sql = "";

		try {
			con = JdbcUtils.getConnection();

			sql = "UPDATE web.bnotice ";
			sql += "SET readcount = readcount + 1 ";
			sql += "WHERE num = ? ";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.close(con, pstmt);
		}
	} // updateReadcount()

	// 전체글갯수 가져오기
	public int getCountAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int count = 0;
		String sql = "";

		try {
			con = JdbcUtils.getConnection();

			sql = "SELECT COUNT(*) FROM web.bnotice";

			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.close(con, pstmt, rs);
		}
		return count;
	} // getCountAll()

	// 검색어를 적용한 글갯수 가져오기
	public int getCountBySearch(String category, String search) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int count = 0;
		String sql = "";

		try {
			con = JdbcUtils.getConnection();

			sql = "SELECT COUNT(*) FROM web.bnotice ";

			// 동적 sql 구현
			if (category.equals("subject")) {
				sql += "WHERE subject LIKE CONCAT('%', ?, '%') ";
			} else if (category.equals("content")) {
				sql += "WHERE content LIKE CONCAT('%', ?, '%') ";
			} else if (category.equals("id")) {
				sql += "WHERE id LIKE CONCAT('%', ?, '%') ";
			}

			pstmt = con.prepareStatement(sql);

			if (!category.equals("")) { // 검색어가 있을때
				pstmt.setString(1, search); // 물음표에 검색어 설정
			}

			rs = pstmt.executeQuery();

			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.close(con, pstmt, rs);
		}
		return count;
	} // getCountBySearch()

	public List<NoticeVo> getNotices(int startRow, int pageSize) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<NoticeVo> list = new ArrayList<>();
		String sql = "";

		try {
			con = JdbcUtils.getConnection();

			sql = "SELECT * ";
			sql += "FROM web.bnotice ";
			sql += "ORDER BY re_ref DESC, re_seq ASC ";
			sql += "LIMIT ?, ? ";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, pageSize);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				NoticeVo noticeVo = new NoticeVo();
				noticeVo.setNum(rs.getInt("num"));
				noticeVo.setId(rs.getString("id"));
				noticeVo.setSubject(rs.getString("subject"));
				noticeVo.setContent(rs.getString("content"));
				noticeVo.setReadcount(rs.getInt("readcount"));
				noticeVo.setRegDate(rs.getTimestamp("reg_date"));
				noticeVo.setIp(rs.getString("ip"));
				noticeVo.setReRef(rs.getInt("re_ref"));
				noticeVo.setReLev(rs.getInt("re_lev"));
				noticeVo.setReSeq(rs.getInt("re_seq"));

				list.add(noticeVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.close(con, pstmt, rs);
		}
		return list;
	} // getNotices()

	public List<NoticeVo> getNoticesBySearch(int startRow, int pageSize, String category, String search) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<NoticeVo> list = new ArrayList<>();
		String sql = "";

		try {
			con = JdbcUtils.getConnection();

			sql = "SELECT * ";
			sql += "FROM web.bnotice ";
			// 동적 sql 구현
			if (category.equals("subject")) {
				sql += "WHERE subject LIKE CONCAT('%', ?, '%') ";
			} else if (category.equals("content")) {
				sql += "WHERE content LIKE CONCAT('%', ?, '%') ";
			} else if (category.equals("id")) {
				sql += "WHERE id LIKE CONCAT('%', ?, '%') ";
			}
			sql += "ORDER BY re_ref DESC, re_seq ASC ";
			sql += "LIMIT ?, ? ";

			pstmt = con.prepareStatement(sql);

			if (!category.equals("")) { // 검색어가 있을때
				pstmt.setString(1, search); // 물음표에 검색어 설정
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, pageSize);
			} else { // 검색어가 없을때
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, pageSize);
			}

			rs = pstmt.executeQuery();

			while (rs.next()) {
				NoticeVo noticeVo = new NoticeVo();
				noticeVo.setNum(rs.getInt("num"));
				noticeVo.setId(rs.getString("id"));
				noticeVo.setSubject(rs.getString("subject"));
				noticeVo.setContent(rs.getString("content"));
				noticeVo.setReadcount(rs.getInt("readcount"));
				noticeVo.setRegDate(rs.getTimestamp("reg_date"));
				noticeVo.setIp(rs.getString("ip"));
				noticeVo.setReRef(rs.getInt("re_ref"));
				noticeVo.setReLev(rs.getInt("re_lev"));
				noticeVo.setReSeq(rs.getInt("re_seq"));

				list.add(noticeVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.close(con, pstmt, rs);
		}
		return list;
	} // getNoticesBySearch()

	public void updateBoard(NoticeVo noticeVo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		String sql = "";

		try {
			con = JdbcUtils.getConnection();

			sql = "UPDATE web.bnotice ";
			sql += "SET subject = ?, content = ? ";
			sql += "WHERE num = ? ";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, noticeVo.getSubject());
			pstmt.setString(2, noticeVo.getContent());
			pstmt.setInt(3, noticeVo.getNum());

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.close(con, pstmt);
		}
	} // updateBoard

	public void deleteNoticeByNum(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "";

		try {
			con = JdbcUtils.getConnection();

			sql = "DELETE FROM web.bnotice WHERE num = ?";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.close(con, pstmt);
		}
	} // deleteNoticeByNum

	public void deleteAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "";

		try {
			con = JdbcUtils.getConnection();

			sql = "DELETE FROM web.bnotice";

			pstmt = con.prepareStatement(sql);

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.close(con, pstmt);
		}
	} // deleteAll

	// 답글쓰기 메서드
	public boolean updateAndAddReply(NoticeVo noticeVo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "";

		try {
			con = JdbcUtils.getConnection();

			con.setAutoCommit(false); // 수동커밋으로 변경

			sql = "UPDATE web.bnotice ";
			sql += "SET re_seq = re_seq + 1 ";
			sql += "WHERE re_ref = ? ";
			sql += "AND re_seq > ? ";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, noticeVo.getReRef());
			pstmt.setInt(2, noticeVo.getReSeq());
			// update 수행
			pstmt.executeUpdate();
			// update문장을 가진 pstmt 객체 닫기
			pstmt.close();

			sql = "INSERT INTO web.bnotice (id, subject, content, readcount, reg_date, ip, re_ref, re_lev, re_seq) ";
			sql += "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) ";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, noticeVo.getId());
			pstmt.setString(2, noticeVo.getSubject());
			pstmt.setString(3, noticeVo.getContent());
			pstmt.setInt(4, noticeVo.getReadcount());
			pstmt.setTimestamp(5, noticeVo.getRegDate());
			pstmt.setString(6, noticeVo.getIp());
			pstmt.setInt(7, noticeVo.getReRef()); // 같은 그룹
			pstmt.setInt(8, noticeVo.getReLev() + 1); // 답글쓰는 대상글의 들여쓰기 + 1
			pstmt.setInt(9, noticeVo.getReSeq() + 1); // 답글쓰는 대상글의 그룹내 순번 + 1

			// insert문 실행
			pstmt.executeUpdate();

			con.commit(); // 커밋하기

			con.setAutoCommit(true); // 자동커밋은 기본값인 true로 수정

			return true;
		} catch (Exception e) {
			e.printStackTrace();

			try {
				con.rollback(); // 단위작업에 문제가 생기면 롤백(전체취소)하기
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			return false;
		} finally {
			JdbcUtils.close(con, pstmt);
		}
	} // updateAndAddReply

	// 댓글 등록 메소드
	public boolean commentIn(CommentVo commentVo) {
		
		boolean result = false;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;

		try {
			con = JdbcUtils.getConnection();
			
			con.setAutoCommit(false);
			
			String max_sql = "select count(c_num) from web.bcomment where b_num = ? ";
			pstmt = con.prepareStatement(max_sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				count = rs.getInt(1) + 1;
				System.out.println(commentVo.getC_num());
			} else {
				count = 1;
				System.out.println(commentVo.getC_num());
			}
			String sql = "insert into comment values ( ?, ?, ?, now(), ? )";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, count);
			pstmt.setString(2, commentVo.getC_id());
			pstmt.setString(3, commentVo.getC_content());
			pstmt.setInt(4, commentVo.getB_num());
			
			int flag = pstmt.executeUpdate();
			if (flag > 0) {
				result = true;
				con.commit();
			}
		} catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		return result;
	}
	
	// 게시물 댓글 가져오는 메소드
//	public ArrayList<CommentVo> getComment(int num) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		ArrayList<CommentVo> cVo = new ArrayList<>();
//		
//		try {
//			con = JdbcUtils.getConnection();
//			String sql = " select * from web.bcomment where b_num = "+ num;
//			pstmt = con.prepareStatement(sql);
//			rs = pstmt.executeQuery();
//			
//			while (rs.next()) {
//				CommentVo commentVo = new CommentVo();
//				commentVo.setC_num(rs.getInt("c_num"));
//				commentVo.setC_id(rs.getString("c_id"));
//				commentVo.setC_content(rs.getString("c_content"));
//				commentVo.setC_date(rs.getString("c_date"));
//				commentVo.setB_num(rs.getInt("b_num"));
//				cVo.add(commentVo);
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new RuntimeException(e.getMessage());
//		}
//		
//		return cVo;
//	}
	
	//게시글 가져오는 메소드 제이슨
	public JSONArray getCommentsJSON() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		JSONArray jsonArray = new JSONArray();
		
		try {
			con = JdbcUtils.getConnection();
			String sql = " SELECT * FROM web.bcomment ";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				//저장할놈들을 JSONObject로 준비 (Map 형태)
				JSONObject obj = new JSONObject();
				
				//rs -> JSONObject에 저장
				//Map이니까 put사용
				obj.put("c_num", rs.getInt("c_num"));
				obj.put("c_id", rs.getString("c_id"));
				obj.put("c_content", rs.getString("c_content"));
				obj.put("c_date", rs.getString("c_date"));
				obj.put("b_num", rs.getInt("b_num"));
				
				//JSONObject -> jsonArray 에 한개 추가
				jsonArray.add(obj);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.close(con, pstmt, rs);
		}
		
		return jsonArray;
	} // getComments();
	
	//답변 삭제 메소드
	public void deleteComment(int num, int c_lev) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = JdbcUtils.getConnection();
			String sql = " delete from web.bcomment where c_num = ? and c_lev = ?";
			pstmt.getConnection().prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setInt(2, c_lev);
			pstmt.executeUpdate();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		NoticeDao noticeDao = NoticeDao.getInstance();

		noticeDao.deleteAll(); // 테이블 내용 모두 삭제

		// 주글 1000개 insert하기
		for (int i = 0; i < 1000; i++) {
			NoticeVo noticeVo = new NoticeVo();

			noticeVo.setId("user1");
			noticeVo.setSubject("글제목" + i);
			noticeVo.setContent("글내용" + i);
			noticeVo.setReadcount(0);
			noticeVo.setRegDate(new Timestamp(System.currentTimeMillis()));
			noticeVo.setIp("127.0.0.1");
			noticeVo.setReRef(JdbcUtils.getNextNum("web.bnotice"));
			noticeVo.setReLev(0);
			noticeVo.setReSeq(0);

			System.out.println(noticeVo);

			noticeDao.addNotice(noticeVo);
		} // for

		int count = noticeDao.getCountAll();
		System.out.println("count = " + count);

	} // main()

}
