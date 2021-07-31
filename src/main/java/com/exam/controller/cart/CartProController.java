package com.exam.controller.cart;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exam.controller.Controller;
import com.exam.vo.CartDTO;

public class CartProController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("CartProController......");


		request.setCharacterEncoding("utf-8");



		ArrayList<CartDTO> cart = null;
		HttpSession session = request.getSession();

		Object obj = session.getAttribute("cart");	//���� ��ü���� cart ���� �����´�.



		String id = (String) session.getAttribute("id");
		String name = request.getParameter("name");
		String price = request.getParameter("price");
		String pagename = request.getParameter("pagename");


		// ��ȸ�� �̸�
		if (id == null) {

			if(obj == null) {	//���� ������ ������ �迭�� ���� : ���� �ֹ��� ���
				cart = new ArrayList<CartDTO>();
			} else {			//���� ������ ������ ������ ĳ���� : �߰� �ֹ�
				cart = (ArrayList<CartDTO>) obj;
			}

			int pos = -1;	//��ϵ� ��ǰ�� ����
			//��ٱ��� ���ǿ� ������ ��ǰ�� ���� ��� : ����(cnt) ����
			for(int i = 0; i < cart.size(); i++) {
				CartDTO dto = cart.get(i);
				if(dto.getProduct_name().equals(name)) {
					pos = 1;
					dto.setProduct_count((dto.getProduct_count() + 1));
					break;
				}
			}



			//��ٱ��� ���ǿ� ��ϵ� ��ǰ�� ���� ��� : CartDTO ��ü�� �����Ͽ� �迭�� ���(add())
			if(pos == -1) {
				CartDTO dto = new CartDTO();
				dto.setProduct_name(name);
				dto.setProduct_price(Integer.parseInt(price.replace(",", "")));	//1,500 �� 1500 : ��ǥ ���� �� ���������� ����
				dto.setProduct_count(1);
				cart.add(dto);
			}

			//cart ���� ��ü�� ����� �ش�.
			session.setAttribute("cart", cart);




			response.setContentType("text/html; charset=UTF-8");

			PrintWriter out = response.getWriter();

			out.print("<script>alert('��ٱ��Ͽ� ��ҽ��ϴ�. ');");
			out.println("location.href='shopMain.do';</script>\");");

			out.flush();


			// �α����� ������
		}
		else {

			response.setContentType("text/html; charset=UTF-8");

			PrintWriter out = response.getWriter();
			//out.println("<script>alert('ȸ���� ��ٱ��Ͽ� ��ҽ��ϴ�.'); location.href = "+"UserCartProcess.do?id="+ id + "&name =" + name = "&price =" + price +"</script>");
			out.print("<script>alert('ȸ���� ��ٱ��Ͽ� ��ҽ��ϴ�. '); location.href = 'UserCartProcess.do?id=" );
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

		//response.sendRedirect("shopMain.do");
		return  null;
	}

}
