package com.exam.controller.menu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.exam.controller.Controller;

public class MenuCategoryController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MenuCategoryController......");
		
		
							  // join.jsp�� ���� ������ ó���ҰԾ����� view�� �������ٰ���
		return "bueno/category"; // FrontController�� 2�ܰ� strView�� �����ؼ� �յڷ� �޾���
	}

}
