package com.exam.controller.menu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.exam.controller.Controller;

public class MenuSinglePostController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MenuSinglePostController......");
		
		
							  // join.jsp�� ���� ������ ó���ҰԾ����� view�� �������ٰ���
		return "bueno/single-post"; // FrontController�� 2�ܰ� strView�� �����ؼ� �յڷ� �޾���
	}

}
