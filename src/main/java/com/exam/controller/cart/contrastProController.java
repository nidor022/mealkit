package com.exam.controller.cart;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exam.controller.Controller;
import com.exam.dao.CartDao;
import com.exam.dao.OrderDao;
import com.exam.dao.UserOrderDao;
import com.exam.vo.CartDTO;
import com.exam.vo.OrderVo;
import com.exam.vo.UserOrderVo;

public class contrastProController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("orderNumber");
		String pass = request.getParameter("passWd");


		OrderDao userOrderDao = OrderDao.getInstance();
    	OrderVo userOrderVo = new OrderVo();

    	Date date2 = new Date();
		SimpleDateFormat vans2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String wdate2 = vans2.format(date2);
		System.out.println(wdate2);



		return null;
	}

}
