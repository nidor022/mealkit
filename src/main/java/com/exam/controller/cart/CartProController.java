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

		Object obj = session.getAttribute("cart");	//세션 객체에서 cart 값을 가져온다.



		String id = (String) session.getAttribute("id");
		String name = request.getParameter("name");
		String price = request.getParameter("price");
		String pagename = request.getParameter("pagename");


		// 비회원 이면
		if (id == null) {

			if(obj == null) {	//세션 정보가 없으면 배열을 생성 : 최초 주문한 경우
				cart = new ArrayList<CartDTO>();
			} else {			//세션 정보가 있으면 강제로 캐스팅 : 추가 주문
				cart = (ArrayList<CartDTO>) obj;
			}

			int pos = -1;	//등록된 제품이 없다
			//장바구니 세션에 동일한 제품이 있을 경우 : 수량(cnt) 증가
			for(int i = 0; i < cart.size(); i++) {
				CartDTO dto = cart.get(i);
				if(dto.getProduct_name().equals(name)) {
					pos = 1;
					dto.setProduct_count((dto.getProduct_count() + 1));
					break;
				}
			}



			//장바구니 세션에 등록된 제품이 없을 경우 : CartDTO 객체를 생성하여 배열에 등록(add())
			if(pos == -1) {
				CartDTO dto = new CartDTO();
				dto.setProduct_name(name);
				dto.setProduct_price(Integer.parseInt(price.replace(",", "")));	//1,500 ▶ 1500 : 쉼표 제거 후 정수형으로 랩핑
				dto.setProduct_count(1);
				cart.add(dto);
			}

			//cart 세션 객체를 만들어 준다.
			session.setAttribute("cart", cart);




			response.setContentType("text/html; charset=UTF-8");

			PrintWriter out = response.getWriter();

			out.print("<script>alert('장바구니에 담았습니다. ');");
			out.println("location.href='shopMain.do';</script>\");");

			out.flush();


			// 로그인을 했으면
		}
		else {

			response.setContentType("text/html; charset=UTF-8");

			PrintWriter out = response.getWriter();
			//out.println("<script>alert('회원님 장바구니에 담았습니다.'); location.href = "+"UserCartProcess.do?id="+ id + "&name =" + name = "&price =" + price +"</script>");
			out.print("<script>alert('회원님 장바구니에 담았습니다. '); location.href = 'UserCartProcess.do?id=" );
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
