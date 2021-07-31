package com.exam.controller.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.exam.controller.Controller;
import com.exam.dao.ServiceAttachDao;
import com.exam.dao.ServiceDao;
import com.exam.vo.ServiceVo;

public class ServiceReplyFormController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
			System.out.println("ServiceReplyWriteFormController....");
			
			// 세션 가져오기
			HttpSession session = request.getSession();
			
			// 로그인 여부 확인
			String id = (String) session.getAttribute("id");
			if (id == null) {
				return "redirect:/serviceBoard.do";
			}
			
			// 파라미터값  pageNum  가져오기
			String pageNum = request.getParameter("pageNum");
			int num = Integer.parseInt(request.getParameter("num"));
			int reRef = Integer.parseInt(request.getParameter("reRef"));
			int reLev = Integer.parseInt(request.getParameter("reLev"));
			int reSeq = Integer.parseInt(request.getParameter("reSeq"));
			
			// 뷰(jsp)에서 필요한 정보를 request 영역객체에 저장
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("num", num);
			request.setAttribute("reRef", reRef);
			request.setAttribute("reLev", reLev);
			request.setAttribute("reSeq", reSeq);
			
		return "service/serviceReplyForm";
	}
}
	
			
			//===================================================================

	
