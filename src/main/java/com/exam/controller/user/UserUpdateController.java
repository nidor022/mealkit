package com.exam.controller.user;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exam.controller.Controller;

public class UserUpdateController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("UserUpdateController......");
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		String rStr = "";
		
		if(id == null) {
			response.setContentType("text/html; charset=UTF-8");

			PrintWriter out = response.getWriter();

			out.print("<script>alert('로그인후 이용가능한 기능입니다. ');");
			out.println("location.href='userLogin.do';</script>\");");

			out.flush();
			rStr = null;
		} else {
			rStr = "user/UserUpdate";
		}

		return rStr;
	}

}

