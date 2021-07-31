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
			
			// ���� ��������
			HttpSession session = request.getSession();
			
			// �α��� ���� Ȯ��
			String id = (String) session.getAttribute("id");
			if (id == null) {
				return "redirect:/serviceBoard.do";
			}
			
			// �Ķ���Ͱ�  pageNum  ��������
			String pageNum = request.getParameter("pageNum");
			int num = Integer.parseInt(request.getParameter("num"));
			int reRef = Integer.parseInt(request.getParameter("reRef"));
			int reLev = Integer.parseInt(request.getParameter("reLev"));
			int reSeq = Integer.parseInt(request.getParameter("reSeq"));
			
			// ��(jsp)���� �ʿ��� ������ request ������ü�� ����
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("num", num);
			request.setAttribute("reRef", reRef);
			request.setAttribute("reLev", reLev);
			request.setAttribute("reSeq", reSeq);
			
		return "service/serviceReplyForm";
	}
}
	
			
			//===================================================================

	
