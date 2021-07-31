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
		
		// ���� ��ü ���� ��������
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		if(id == null) { // ��ȸ��

		// ���ǰ� �ʱ�ȭ. �α׾ƿ� �۾�
		session.getAttribute("cart");
		session.removeAttribute("cart");
		} else { // ȸ���̸�
			System.out.println("����� ������ ���Դϴ�");
			CartDao cartDao = CartDao.getInstance();
			cartDao.del(id);
			// DELETE FROM cart WHERE user_id = ?
		}
		
		
		return "index";
	}

}
