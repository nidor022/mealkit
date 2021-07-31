package com.exam.controller.cart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exam.controller.Controller;
import com.exam.dao.CartDao;
import com.exam.dao.WishDao;
import com.exam.vo.CartDTO;
public class UserWishProController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("UserWishProController....");
		
		
		String id = (request.getParameter("id") == null) ? "test" : request.getParameter("id"); 
		String name = (request.getParameter("name") == null) ? "test" : request.getParameter("name"); 
		String price = (request.getParameter("price") == null) ? "1234" : request.getParameter("price"); 
		String pagename = (request.getParameter("pagename") == null) ? "1" : request.getParameter("pagename");
		
		
		int intPrice = Integer.parseInt(price);
		
		WishDao wishDao = WishDao.getInstance();
		if(wishDao.itemCheck(id,name)) { //없을때
			wishDao.addWish(id,name,intPrice); 
		} else { // 있을때
			wishDao.countPlus(id,name);
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
