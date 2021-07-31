package com.exam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.exam.vo.CartDTO;
import com.exam.vo.NoticeVo;

public class CartDao {
	private static CartDao instance = new CartDao();
	
	public static CartDao getInstance() {
		return instance;
	}
	
	////////////////////////////////////////////////////////////////
	private CartDao() {
		
	}
	
	public void addCart(String id, String name, int price) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con= JdbcUtils.getConnection();
			
			String sql ="";
			sql += "INSERT INTO cart(user_id, product_name, product_price, product_count) ";
			sql += "VALUES (?, ?, ?, ?) ";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setInt(3, price);
			pstmt.setInt(4, 1);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			
			JdbcUtils.close(con, pstmt);
		}
	}			// addCart
	
	public boolean itemCheck(String userId, String name) {

        int count = 0;

        ResultSet rs = null;
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con= JdbcUtils.getConnection();

            String sql ="";
            sql += "SELECT count(*) FROM cart WHERE user_id = ? AND product_name = ? ";

            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, userId);
            pstmt.setString(2, name);

            rs = pstmt.executeQuery();

            if(rs.next()) {
                count = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close(con, pstmt, rs);
        }
        if(count == 0) {
            return true; // 아이템이 없다
        } else {
            return false; // 아이템이 있다
        }

    }
	
	public boolean cartCheck(String userId) {

        int count = 0;

        ResultSet rs = null;
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con= JdbcUtils.getConnection();

            String sql ="";
            sql += "SELECT count(*) FROM cart WHERE user_id = ? ";

            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, userId);

            rs = pstmt.executeQuery();

            if(rs.next()) {
                count = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close(con, pstmt, rs);
        }
        if(count == 0) {
            return true; // 아이템이 없다
        } else {
            return false; // 아이템이 있다
        }

    }
	
	public void countPlus(String id, String name) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con= JdbcUtils.getConnection();
			
			String sql ="";
			sql += "UPDATE cart SET product_count = product_count + 1 WHERE user_id = ? AND product_name = ? ";
	
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			
			JdbcUtils.close(con, pstmt);
		}
	}			// countPlus
	
	public ArrayList<CartDTO> getItemsById(String id){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<CartDTO> list = new ArrayList<>();
		String sql = "";
		
		try {
			
			con = JdbcUtils.getConnection();
			
			sql += "SELECT user_id, product_name, product_price, product_count FROM cart WHERE user_id = ? ";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				CartDTO cartDto = new CartDTO();
				
				cartDto.setUser_id(rs.getString("user_id"));
				cartDto.setProduct_name(rs.getString("product_name"));
				cartDto.setProduct_price(rs.getInt("product_price"));
				cartDto.setProduct_count(rs.getInt("product_count"));
				
				list.add(cartDto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.close(con, pstmt, rs);
		}
		
		return list;
	}			// getItemsById
	
	public void del(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "";
		
		try {
			con = JdbcUtils.getConnection();
			
			sql = "DELETE FROM cart WHERE user_id = ? ";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.close(con, pstmt);
		}
	} // deleteCartById
	
}
