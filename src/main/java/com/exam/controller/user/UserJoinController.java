package com.exam.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.exam.controller.Controller;

public class UserJoinController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("UserJoinController......");
		
		
							  // join.jsp�� ���� ������ ó���ҰԾ����� view�� �������ٰ���
		return "user/join"; // FrontController�� 2�ܰ� strView�� �����ؼ� �յڷ� �޾���
	}

}
