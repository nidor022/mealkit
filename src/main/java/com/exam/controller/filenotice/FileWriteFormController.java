package com.exam.controller.filenotice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exam.controller.Controller;

public class FileWriteFormController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("FileWriteFormController......");
		
		// 세션 가져오기
		HttpSession session = request.getSession();
		
		// 로그인 여부 확인
		String id = (String) session.getAttribute("id");
		if (id == null) {
			return "redirect:/fileboard.do";
		}
		
		// 파라미터값  pageNum  가져오기
		String pageNum = request.getParameter("pageNum");
		
		// 뷰(jsp)에서 필요한 정보를 request 영역객체에 저장
		request.setAttribute("pageNum", pageNum);
		
		return "center/fileWriteForm";
	}

}
