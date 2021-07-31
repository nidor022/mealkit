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


		// ��ȸ�� �̸�
		if (id == null) {

			response.setContentType("text/html; charset=UTF-8");

			PrintWriter out = response.getWriter();

			out.print("<script>alert('�α����� �̿밡���� ����Դϴ�. ');");
			out.println("location.href='userLogin.do';</script>\");");

			out.flush();

			// �α����� ������
		}
		else {

			response.setContentType("text/html; charset=UTF-8");

			PrintWriter out = response.getWriter();
			//out.println("<script>alert('ȸ���� ��ٱ��Ͽ� ��ҽ��ϴ�.'); location.href = "+"UserCartProcess.do?id="+ id + "&name =" + name = "&price =" + price +"</script>");
			out.print("<script>alert('ȸ���� ���Ͽ� �߰������ϴ�. '); location.href = 'UserWishProcess.do?id=" );
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
