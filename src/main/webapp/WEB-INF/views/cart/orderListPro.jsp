<%@page import="com.exam.vo.OrderVo"%>
<%@page import="com.exam.dao.OrderDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.exam.vo.CartDTO"%>
<%@page import="com.exam.dao.CartDao"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="com.exam.vo.UserOrderVo"%>
<%@page import="com.exam.dao.UserOrderDao"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
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
		SimpleDateFormat vans = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS");
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
%>