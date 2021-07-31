package com.exam.controller.cart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exam.controller.Controller;
import com.exam.dao.OrderDao;
import com.exam.vo.OrderVo;

import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class NonUserOrderPageController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String orderNumber = request.getParameter("orderNumber");
		String passWd = request.getParameter("passWd");

		return "cart/nonUserOrderPage";
	}

}
