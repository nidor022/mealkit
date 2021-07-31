package com.exam.controller.cart;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exam.controller.Controller;
import com.exam.dao.CartDao;
import com.exam.dao.OrderDao;
import com.exam.dao.UserOrderDao;
import com.exam.vo.CartDTO;
import com.exam.vo.OrderVo;
import com.exam.vo.UserOrderVo;

public class OrderListProController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("OrderListProController....");
		
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		
		String name = request.getParameter("name");
	    String email = request.getParameter("email");
	    String phone = request.getParameter("phone");
	    String address = request.getParameter("address");
	    
	    String id = (String) session.getAttribute("id");

	    CartDao cartDao = CartDao.getInstance();
		CartDTO cartDto = new CartDTO();

		if (id != null) // 회원일경우 
	    {
	    	UserOrderDao userOrderDao = UserOrderDao.getInstance();
	    	UserOrderVo userOrderVo = new UserOrderVo();
	    	
		    Date date = new Date();
			SimpleDateFormat vans = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String wdate = vans.format(date);

			session.setAttribute("date", wdate);

			ArrayList<CartDTO> cart = new ArrayList<>();
			cart = cartDao.getItemsById(id);
			
			for(int i = 0; i < cart.size(); i++) {
			CartDTO dto = cart.get(i);
				userOrderVo.setOrder_date(wdate);
				userOrderVo.setName(name);
				userOrderVo.setId(id);
				userOrderVo.setAddress(address);
				userOrderVo.setProduct(dto.getProduct_name());
				userOrderVo.setP_count(dto.getProduct_count());
				userOrderVo.setPrice(dto.getProduct_price());
				userOrderDao.addOrders(userOrderVo);
			}
		    
			//주문완료후 카트 비우기
			cartDao.del(id);
//			return "cart/orderList";
	    } 
		else if (id ==null) // 비회원일경우
	    {
	    	OrderDao orderDao = OrderDao.getInstance();
	    	OrderVo orderVo = new OrderVo();
	    	
	    	String pass = request.getParameter("pass");
	    	
	    	Date date2 = new Date();
			SimpleDateFormat vans2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String wdate2 = vans2.format(date2);
			System.out.println(wdate2);
	    	
			Date date = new Date();
			SimpleDateFormat vans = new SimpleDateFormat("YYYYMMDDHHMMSS");
			String wdate = vans.format(date);

	 	    //id 난수생성. 비회원은 id에 이게 들어감
		    String id2 = "N" + wdate;

			ArrayList<CartDTO> cartList = (ArrayList)session.getAttribute("cart");
			//ArrayList<CartDTO> cart = new ArrayList<>();
			for(int i = 0; i < cartList.size(); i++) {
			CartDTO dto = cartList.get(i);
				orderVo.setOrder_date(wdate2);
				orderVo.setName(name);
				orderVo.setId(id2);
				orderVo.setPass(pass);
				orderVo.setAddress(address);
				orderVo.setProduct(dto.getProduct_name());
				orderVo.setP_count(dto.getProduct_count());
				orderVo.setPrice(dto.getProduct_price()); 
			    orderDao.addOrders2(orderVo);
			    
			    session.setAttribute("id2", id2);
			    session.setAttribute("pass", pass);
			}
	 	
			// 카트세션 비우기
			session.removeAttribute("cart");
			
	    }
		return "cart/orderList";
	}

}
