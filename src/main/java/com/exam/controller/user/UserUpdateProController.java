package com.exam.controller.user;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exam.controller.Controller;
import com.exam.dao.UserDao;
import com.exam.vo.UserVo;

public class UserUpdateProController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("CartProController......");


		request.setCharacterEncoding("utf-8");

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		String passwd = request.getParameter("password");
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		int intAge = Integer.parseInt(age);
		String gender = request.getParameter("gender");
		String email = request.getParameter("email");
		String birthDay = request.getParameter("birth");
		String address = request.getParameter("address");
		String tel = request.getParameter("tel");
		
		
		UserDao userDao = UserDao.getInstance();
		
		// 비밀번호체크 생략
		
		userDao.update(id, name, intAge, gender, email, birthDay, address, tel); 
		
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter out = response.getWriter();

		out.print("<script>alert('회원님의 정보가 수정되었습니다. ');");
		out.println("location.href='index.do';</script>\");");

		out.flush();

		return  null;
	}

}
