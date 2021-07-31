package com.exam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.exam.vo.OrderVo;

public class OrderDao {

    private static OrderDao instance = new OrderDao();

    public static OrderDao getInstance() {
        return instance;
    }

    ///////////////////////////////////////////////////////////

    private OrderDao() {
    }

    //주문목록에 추가. 비회원일경우
    public void addOrders2(OrderVo orderVo) {
        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = "";

        try {
            con = JdbcUtils.getConnection();

            sql += "INSERT INTO orders(order_date, name, id, pass, address, product, p_count, price) ";
            sql += "VALUES (?, ?, ?, ?, ?, ?, ?, ?) ";

            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, orderVo.getOrder_date());
            pstmt.setString(2, orderVo.getName());
            pstmt.setString(3, orderVo.getId());
            pstmt.setString(4, orderVo.getPass());
            pstmt.setString(5, orderVo.getAddress());
            pstmt.setString(6, orderVo.getProduct());
            pstmt.setInt(7, orderVo.getP_count());
            pstmt.setInt(8, orderVo.getPrice());

            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            JdbcUtils.close(con, pstmt);
        }
    } // addOrders()


    // 주문목록 불러오기. 비회원일경우
    public ArrayList<OrderVo> getOrderById2(String id, String pass) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        ArrayList<OrderVo> arrOrder = new ArrayList<>();
        String sql = "";
        try {
            con = JdbcUtils.getConnection();

            sql += " SELECT order_date, name, id, address, product, p_count, price FROM orders WHERE id = ? and pass = ? ";

            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, pass);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                OrderVo orderVo = new OrderVo();

                orderVo.setOrder_date(rs.getString("order_date"));
                orderVo.setName(rs.getString("name"));
                orderVo.setId(rs.getString("id"));
                orderVo.setAddress(rs.getString("address"));
                orderVo.setProduct(rs.getString("product"));
                orderVo.setP_count(rs.getInt("p_count"));
                orderVo.setPrice(rs.getInt("price"));


                arrOrder.add(orderVo);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(con, pstmt);
        }

        return arrOrder;

    }

    //비회원일경우 아이디랑 비번 체크하기
    public int idPassCheck(String id, String pass) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        ArrayList<OrderVo> arrOrder = new ArrayList<>();

        int count = 0;

        try {
            con = JdbcUtils.getConnection();

            String sql = "";

            sql += " SELECT IFNULL(max(id), 'no') as id FROM orders WHERE id = ? AND pass = ? ";

            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, pass);

            rs = pstmt.executeQuery();

            while(rs.next()) {

                String idTest = rs.getString("id");

                System.out.println(idTest);

                if (idTest.equals("no")) {
                    count = 0;
                } else {
                    count = 1;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }

}
