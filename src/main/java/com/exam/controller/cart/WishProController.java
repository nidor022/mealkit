package com.exam.controller.cart;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exam.controller.Controller;
import com.exam.vo.CartDTO;

public class WishProController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("WishProController......");

		request.setCharacterEncoding("utf-8");

		ArrayList<CartDTO> cart = null;
		HttpSession session = request.getSession();


		String id = (String) session.getAttribute("id");
		String name = request.getParameter("name");
		String price = request.getParameter("price");
		String pagename = request.getParameter("pagename");


		// 비회원 이면
		if (id == null) {

			response.setContentType("text/html; charset=UTF-8");

			PrintWriter out = response.getWriter();

			out.print("<script>alert('로그인후 이용가능한 기능입니다. ');");
			out.println("location.href='userLogin.do';</script>\");");

			out.flush();

			// 로그인을 했으면
		}
		else {

			response.setContentType("text/html; charset=UTF-8");

			PrintWriter out = response.getWriter();
			//out.println("<script>alert('회원님 장바구니에 담았습니다.'); location.href = "+"UserCartProcess.do?id="+ id + "&name =" + name = "&price =" + price +"</script>");
			out.print("<script>alert('회원님 찜목록에 추가햇읍니다. '); location.href = 'UserWishProcess.do?id=" );
			out.print(id);
			out.print("&name=");
			out.print(name);
			out.print("&price=");
			out.print(price);
			out.print("&pagename=");
			out.print(pagename);
			out.print("';</script>");
			out.flush();


		}
		return  null;
	}

}
