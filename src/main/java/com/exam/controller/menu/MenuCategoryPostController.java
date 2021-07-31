package com.exam.controller.menu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.exam.controller.Controller;

public class MenuCategoryPostController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MenuCategoryPostController......");
		
		
							  // join.jsp에 따로 서버측 처리할게없으니 view만 리턴해줄거임
		return "bueno/category-post"; // FrontController쪽 2단계 strView로 리턴해서 앞뒤로 달아줌
	}

}
