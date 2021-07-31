package com.exam.controller.company;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exam.controller.Controller;

public class HistoryController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("HistoryController......");
		
		

		return "company/history"; // FrontController쪽 2단계 strView로 리턴해서 앞뒤로 달아줌
	}

}
