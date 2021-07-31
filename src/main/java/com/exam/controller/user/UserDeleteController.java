package com.exam.controller.user;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exam.controller.Controller;
import com.exam.dao.UserDao;

public class UserDeleteController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("UserUpdateController......");
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter out = response.getWriter();
		
		if(id == null) {


			out.print("<script>alert('�α����� �̿밡���� ����Դϴ�. ');");
			out.println("location.href='userLogin.do';</script>\");");

			out.flush();
		} else {
			
			UserDao userDao = UserDao.getInstance();
			userDao.deleteById(id);
			session.invalidate();
			
			out.print("<script>alert('ȸ������ Ż�� ���ϵ帳�ϴ�. ��');");
			out.println("location.href='index.do';</script>\");");

			out.flush();
		}

		return null;
	}

}
