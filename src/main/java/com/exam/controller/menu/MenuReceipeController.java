package com.exam.controller.menu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.exam.controller.Controller;

public class MenuReceipeController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MenuReceipeController......");
		
		
							  // join.jsp�� ���� ������ ó���ҰԾ����� view�� �������ٰ���
		return "bueno/receipe"; // FrontController�� 2�ܰ� strView�� �����ؼ� �յڷ� �޾���
	}

}
