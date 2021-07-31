package com.exam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.exam.vo.ServiceAttachVo;

public class ServiceAttachDao {

	private static ServiceAttachDao instance = new ServiceAttachDao();
	
	public static ServiceAttachDao getInstance() {
		return instance;
	}

	private ServiceAttachDao() {}
	
	
	public void insertServiceAttach(ServiceAttachVo serviceAttachVo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "";
		
		try {
			con = JdbcUtils.getConnection();
			
			sql  = "INSERT INTO web.serviceattach (filename, uploadpath, image, no_num) ";
			sql += "VALUES (?, ?, ?, ?) ";
			
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, serviceAttachVo.getFilename());
			pstmt.setString(2, serviceAttachVo.getUploadpath());
			pstmt.setString(3, serviceAttachVo.getImage());
			pstmt.setInt(4, serviceAttachVo.getNoNum());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.close(con, pstmt);
		}
	} // insertAttach
	
	
	public ServiceAttachVo getServiceAttachByNum(int num) {
		ServiceAttachVo serviceAttachVo = null;
				
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		
		try {
			con = JdbcUtils.getConnection();
			
			sql = "SELECT * FROM web.serviceattach WHERE num = ? ";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				serviceAttachVo = new ServiceAttachVo();
				serviceAttachVo.setNum(rs.getInt("num"));
				serviceAttachVo.setFilename(rs.getString("filename"));
				serviceAttachVo.setUploadpath(rs.getString("uploadpath"));
				serviceAttachVo.setImage(rs.getString("image"));
				serviceAttachVo.setNoNum(rs.getInt("no_num"));
			} // if
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.close(con, pstmt, rs);
		}
		return serviceAttachVo;
	} // getAttachByNum
	
	
	public List<ServiceAttachVo> getServiceAttachesByNoNum(int noNum) {
		List<ServiceAttachVo> list = new ArrayList<>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		
		try {
			con = JdbcUtils.getConnection();
			
			sql = "SELECT * FROM web.serviceattach WHERE no_num = ? ";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, noNum);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ServiceAttachVo serviceAttachVo = new ServiceAttachVo();
				serviceAttachVo.setNum(rs.getInt("num"));
				serviceAttachVo.setFilename(rs.getString("filename"));
				serviceAttachVo.setUploadpath(rs.getString("uploadpath"));
				serviceAttachVo.setImage(rs.getString("image"));
				serviceAttachVo.setNoNum(rs.getInt("no_num"));
				
				list.add(serviceAttachVo);
			} // while
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.close(con, pstmt, rs);
		}
		return list;
	} // getAttachesByNoNum
	
	
	public void deleteServiceAttachesByNoNum(int noNum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "";
		
		try {
			con = JdbcUtils.getConnection();
			
			sql = "DELETE FROM web.serviceattach where no_num = ? ";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, noNum);
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.close(con, pstmt);
		}
	} // deleteAttachesByNoNum
	
	public void deleteServiceAttachByNum(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "";
		
		try {
			con = JdbcUtils.getConnection();
			
			sql = "DELETE FROM web.serviceattach where num = ? ";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.close(con, pstmt);
		}
	} // deleteAttachByNum
	
}



