package com.exam.controller.cart;

import com.exam.controller.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class japaneseFoodController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("japaneseFoodController......");
		
		
		String id = request.getParameter("id");

		return "cart/japaneseFood";
	}

}
