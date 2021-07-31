package com.exam.controller.cart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exam.controller.Controller;
import com.exam.dao.CartDao;


public class CartClearController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("CartClearController......");
		
		// 세션 객체 참조 가져오기
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		if(id == null) { // 비회원

		// 세션값 초기화. 로그아웃 작업
		session.getAttribute("cart");
		session.removeAttribute("cart");
		} else { // 회원이면
			System.out.println("여기는 엘스의 안입니다");
			CartDao cartDao = CartDao.getInstance();
			cartDao.del(id);
			// DELETE FROM cart WHERE user_id = ?
		}
		
		
		return "index";
	}

}
