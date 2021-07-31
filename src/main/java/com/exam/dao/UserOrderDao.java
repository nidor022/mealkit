package com.exam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.exam.vo.CartDTO;
import com.exam.vo.UserOrderVo;

public class UserOrderDao {
	private static UserOrderDao instance = new UserOrderDao();
	
	public static UserOrderDao getInstance() {
		return instance;
	}
	
	////////////////////////////////////////////////////////////////
	private UserOrderDao() {
		
	}
	
	//주문목록에 추가
	public void addOrders (UserOrderVo orderListVo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql ="";
		
		try {
			con= JdbcUtils.getConnection();
			
			sql += "INSERT INTO user_orders(order_date, name, id, address, product, p_count, price) ";
			sql += "VALUES (?, ?, ?, ?, ?, ?, ?) ";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, orderListVo.getOrder_date());
			pstmt.setString(2, orderListVo.getName());
			pstmt.setString(3, orderListVo.getId());
			pstmt.setString(4, orderListVo.getAddress());
			pstmt.setString(5, orderListVo.getProduct());
			pstmt.setInt(6, orderListVo.getP_count());
			pstmt.setInt(7, orderListVo.getPrice());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			JdbcUtils.close(con, pstmt);
		}
	} // addOrders()
	
	public void getNId () {
		
		
	}
	
	// 주문목록 불러오기. 회원일경우
	public ArrayList<UserOrderVo> getOrderById (String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<UserOrderVo> arrOrder = new ArrayList<>();
		String sql ="";
		try {
			con= JdbcUtils.getConnection();
			
			sql += " SELECT order_date, name, address, product, p_count, price FROM user_orders WHERE id = ? ";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();

			while (rs.next()){
				UserOrderVo userOrderVo = new UserOrderVo();

				userOrderVo.setOrder_date(rs.getString("order_date"));
				userOrderVo.setName(rs.getString("name"));
				userOrderVo.setAddress(rs.getString("address"));
				userOrderVo.setProduct(rs.getString("product"));
				userOrderVo.setP_count(rs.getInt("p_count"));
				userOrderVo.setPrice(rs.getInt("price"));


				arrOrder.add(userOrderVo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.close(con, pstmt);
		}
		
		return arrOrder;
		
	}
	
	// 방금 주문한거 목록만 띄우기
	public ArrayList<UserOrderVo> getOrderByDate (String id, String date) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<UserOrderVo> arrDate = new ArrayList<>();
		String sql ="";
		
		try {
			con= JdbcUtils.getConnection();
			
			sql += " SELECT order_date, name, address, product, p_count, price ";
			sql	+= " FROM user_orders WHERE id = ? ";
			sql	+= " AND order_date = ? ";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, date);

			rs = pstmt.executeQuery();
		
			while (rs.next()){
				UserOrderVo userOrderVo = new UserOrderVo();

				userOrderVo.setOrder_date(rs.getString("order_date"));
				userOrderVo.setName(rs.getString("name"));
				userOrderVo.setAddress(rs.getString("address"));
				userOrderVo.setProduct(rs.getString("product"));
				userOrderVo.setP_count(rs.getInt("p_count"));
				userOrderVo.setPrice(rs.getInt("price"));


				arrDate.add(userOrderVo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return arrDate;
	}
	
}
