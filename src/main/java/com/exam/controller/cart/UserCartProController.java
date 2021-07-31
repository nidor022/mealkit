package com.exam.controller.cart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exam.controller.Controller;
import com.exam.dao.CartDao;
import com.exam.vo.CartDTO;
public class UserCartProController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("UserCartProController....");
		
		
		String id = (request.getParameter("id") == null) ? "test" : request.getParameter("id"); 
		String name = (request.getParameter("name") == null) ? "test" : request.getParameter("name"); 
		String price = (request.getParameter("price") == null) ? "1234" : request.getParameter("price"); 
		String pagename = (request.getParameter("pagename") == null) ? "1" : request.getParameter("pagename");
		
		
		int intPrice = Integer.parseInt(price);
		
		CartDao cartDao = CartDao.getInstance();
		if(cartDao.itemCheck(id,name)) { //없을때
			cartDao.addCart(id,name,intPrice); 
		} else { // 있을때
			cartDao.countPlus(id,name);
		}


		if(pagename.equals("1")) {
			return "redirect:/shopMain.do";
		} else if(pagename.equals("2")) {
			return "redirect:/westernFood.do";
		} else if(pagename.equals("3")) {
			return "redirect:/chineseFood.do";
		} else if (pagename.equals("4")){
			return "redirect:/japaneseFood.do";
		}
		return null;
	}

}
