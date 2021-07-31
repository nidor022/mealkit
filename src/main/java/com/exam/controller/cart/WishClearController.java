package com.exam.controller.cart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exam.controller.Controller;
import com.exam.dao.CartDao;
import com.exam.dao.WishDao;


public class WishClearController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("WishClearController......");
		
		// ���� ��ü ���� ��������
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
			WishDao wishDao = WishDao.getInstance();
			wishDao.del(id);
			
		return "redirect:/wishView.do";
	}

}
