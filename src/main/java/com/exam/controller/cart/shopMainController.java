package com.exam.controller.cart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exam.controller.Controller;

public class shopMainController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("shopMainController......");
		
		
		String id = request.getParameter("id");

		return "cart/shopMain";
	}

}
