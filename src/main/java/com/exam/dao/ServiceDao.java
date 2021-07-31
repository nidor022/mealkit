package com.exam.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import com.exam.vo.ServiceVo;
import com.exam.vo.ServiceAttachVo;

public class ServiceDao {
	// �̱���
	private static ServiceDao instance = new ServiceDao();
	
	public static ServiceDao getInstance() {
		return instance;
	}
	/////////////

	private ServiceDao() {}
	
	
	// �ֱ۾��� �޼���
	public void addService(ServiceVo serviceVo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String sql = "";
		
		try {
			con = JdbcUtils.getConnection();
			
			sql  = "INSERT INTO web.service (id, subject, content, readcount, reg_date, ip, re_ref, re_lev, re_seq) ";
			sql += "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) ";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, serviceVo.getId());
			pstmt.setString(2, serviceVo.getSubject());
			pstmt.setString(3, serviceVo.getContent());
			pstmt.setInt(4, serviceVo.getReadcount());
			pstmt.setTimestamp(5, serviceVo.getRegDate());
			pstmt.setString(6, serviceVo.getIp());
			pstmt.setInt(7, serviceVo.getReRef());
			pstmt.setInt(8, serviceVo.getReLev());
			pstmt.setInt(9, serviceVo.getReSeq());
			// ����
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.close(con, pstmt);
		}
	} // addBoard()
	
	
	public ServiceVo getServiceByNum(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ServiceVo serviceVo = null;
		String sql = "";
		
		try {
			con = JdbcUtils.getConnection();
			
			sql = "SELECT * FROM web.service WHERE num = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				serviceVo = new ServiceVo();
				
				serviceVo.setNum(rs.getInt("num"));
				serviceVo.setId(rs.getString("id"));
				serviceVo.setSubject(rs.getString("subject"));
				serviceVo.setContent(rs.getString("content"));
				serviceVo.setReadcount(rs.getInt("readcount"));
				serviceVo.setRegDate(rs.getTimestamp("reg_date"));
				serviceVo.setIp(rs.getString("ip"));
				serviceVo.setReRef(rs.getInt("re_ref"));
				serviceVo.setReLev(rs.getInt("re_lev")); 
				serviceVo.setReSeq(rs.getInt("re_seq"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.close(con, pstmt, rs);
		}
		return serviceVo;
	} // getNoticeByNum()
	
	
	public void updateReadcount(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String sql = "";
		
		try {
			con = JdbcUtils.getConnection();
			
			sql  = "UPDATE web.service ";
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
	
	
	// ��ü�۰��� ��������
	public int getCountAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int count = 0;
		String sql = "";
		
		try {
			con = JdbcUtils.getConnection();
			
			sql = "SELECT COUNT(*) FROM web.service";
			
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
	
	
	// �˻�� ������ �۰��� ��������
	public int getCountBySearch(String category, String search) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int count = 0;
		String sql = "";
		
		try {
			con = JdbcUtils.getConnection();
			
			sql = "SELECT COUNT(*) FROM web.service ";
			
			// ���� sql ����
			if (category.equals("subject")) {
				sql += "WHERE subject LIKE CONCAT('%', ?, '%') ";
			} else if (category.equals("content")) {
				sql += "WHERE content LIKE CONCAT('%', ?, '%') ";
			} else if (category.equals("id")) {
				sql += "WHERE id LIKE CONCAT('%', ?, '%') ";
			}
			
			pstmt = con.prepareStatement(sql);
			
			if (!category.equals("")) { // �˻�� ������
				pstmt.setString(1, search);  // ����ǥ�� �˻��� ����
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
	
	
	public List<ServiceVo> getServices(int startRow, int pageSize) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<ServiceVo> list = new ArrayList<>();
		String sql = "";
		
		try {
			con = JdbcUtils.getConnection();
			
			sql  = "SELECT * ";
			sql += "FROM web.service ";
			sql += "ORDER BY re_ref DESC, re_seq ASC ";
			sql += "LIMIT ?, ? ";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, pageSize);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ServiceVo serviceVo = new ServiceVo();
				serviceVo.setNum(rs.getInt("num"));
				serviceVo.setId(rs.getString("id"));
				serviceVo.setSubject(rs.getString("subject"));
				serviceVo.setContent(rs.getString("content"));
				serviceVo.setReadcount(rs.getInt("readcount"));
				serviceVo.setRegDate(rs.getTimestamp("reg_date"));
				serviceVo.setIp(rs.getString("ip"));
				serviceVo.setReRef(rs.getInt("re_ref"));
				serviceVo.setReLev(rs.getInt("re_lev")); 
				serviceVo.setReSeq(rs.getInt("re_seq"));
				
				list.add(serviceVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.close(con, pstmt, rs);
		}
		return list;
	} // getNotices()
	
	
	public List<ServiceVo> getServicesBySearch(int startRow, int pageSize, String category, String search) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<ServiceVo> list = new ArrayList<>();
		String sql = "";
		
		try {
			con = JdbcUtils.getConnection();
			
			sql  = "SELECT * ";
			sql += "FROM web.service ";
			// ���� sql ����
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
			
			if (!category.equals("")) { // �˻�� ������
				pstmt.setString(1, search);  // ����ǥ�� �˻��� ����
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, pageSize);
			} else { // �˻�� ������
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, pageSize);
			}
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ServiceVo serviceVo = new ServiceVo();
				serviceVo.setNum(rs.getInt("num"));
				serviceVo.setId(rs.getString("id"));
				serviceVo.setSubject(rs.getString("subject"));
				serviceVo.setContent(rs.getString("content"));
				serviceVo.setReadcount(rs.getInt("readcount"));
				serviceVo.setRegDate(rs.getTimestamp("reg_date"));
				serviceVo.setIp(rs.getString("ip"));
				serviceVo.setReRef(rs.getInt("re_ref"));
				serviceVo.setReLev(rs.getInt("re_lev")); 
				serviceVo.setReSeq(rs.getInt("re_seq"));
				
				list.add(serviceVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.close(con, pstmt, rs);
		}
		return list;
	} // getNoticesBySearch()
	
	
	
	public void updateBoard(ServiceVo serviceVo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String sql = "";
		
		try {
			con = JdbcUtils.getConnection();
			
			sql  = "UPDATE web.service ";
			sql += "SET subject = ?, content = ? ";
			sql += "WHERE num = ? ";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, serviceVo.getSubject());
			pstmt.setString(2, serviceVo.getContent());
			pstmt.setInt(3, serviceVo.getNum());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.close(con, pstmt);
		}
	} // updateBoard
	
	
	
	public void deleteServiceByNum(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "";
		
		try {
			con = JdbcUtils.getConnection();
			
			sql = "DELETE FROM web.service WHERE num = ?";
			
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
			
			sql = "DELETE FROM web.service";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.close(con, pstmt);
		}
	} // deleteAll
	
	
	// ��۾��� �޼���
	public boolean updateAndAddReply(ServiceVo serviceVo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "";
		
		try {
			con = JdbcUtils.getConnection();
			
			con.setAutoCommit(false); // ����Ŀ������ ����
			
			sql  = "UPDATE web.service ";
			sql += "SET re_seq = re_seq + 1 ";
			sql += "WHERE re_ref = ? ";
			sql += "AND re_seq > ? ";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, serviceVo.getReRef());
			pstmt.setInt(2, serviceVo.getReSeq());
			// update ����
			pstmt.executeUpdate();
			// update������ ���� pstmt ��ü �ݱ�
			pstmt.close();
			
			sql  = "INSERT INTO web.service (id, subject, content, readcount, reg_date, ip, re_ref, re_lev, re_seq) ";
			sql += "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) ";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, serviceVo.getId());
			pstmt.setString(2, serviceVo.getSubject());
			pstmt.setString(3, serviceVo.getContent());
			pstmt.setInt(4, serviceVo.getReadcount());
			pstmt.setTimestamp(5, serviceVo.getRegDate());
			pstmt.setString(6, serviceVo.getIp());
			pstmt.setInt(7, serviceVo.getReRef());  // ���� �׷�
			pstmt.setInt(8, serviceVo.getReLev() + 1); // ��۾��� ������ �鿩���� + 1
			pstmt.setInt(9, serviceVo.getReSeq() + 1); // ��۾��� ������ �׷쳻 ���� + 1

			// insert�� ����
			pstmt.executeUpdate();
			
			con.commit(); // Ŀ���ϱ�
			
			con.setAutoCommit(true); // �ڵ�Ŀ���� �⺻���� true�� ����
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			
			try {
				con.rollback(); // �����۾��� ������ ����� �ѹ�(��ü���)�ϱ� 
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			return false;
		} finally {
			JdbcUtils.close(con, pstmt);
		}
	} // updateAndAddReply
	
	
	
	
	public static void main(String[] args) {
		
		ServiceDao serviceDao = ServiceDao.getInstance();
		
		serviceDao.deleteAll(); // ���̺� ���� ��� ����
		
		// �ֱ� 1000�� insert�ϱ�
		for (int i=0; i<1000; i++) {
			ServiceVo serviceVo = new ServiceVo();

			serviceVo.setId("user1");
			serviceVo.setSubject("������" + i);
			serviceVo.setContent("�۳���" + i);
			serviceVo.setReadcount(0);
			serviceVo.setRegDate(new Timestamp(System.currentTimeMillis()));
			serviceVo.setIp("127.0.0.1");
			serviceVo.setReRef(JdbcUtils.getNextNum("web.bnotice"));
			serviceVo.setReLev(0);
			serviceVo.setReSeq(0);
			
			System.out.println(serviceVo);
			
			serviceDao.addService(serviceVo);
		} // for
		
		int count = serviceDao.getCountAll();
		System.out.println("count = " + count);
		
	} // main()
	
}
