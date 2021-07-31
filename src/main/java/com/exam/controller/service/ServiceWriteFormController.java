package com.exam.controller.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.exam.controller.Controller;

public class ServiceWriteFormController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ServiceWriteFormController......");
		
		// ���� ��������
		HttpSession session = request.getSession();
		
		// �α��� ���� Ȯ��
		String id = (String) session.getAttribute("id");
		if (id == null) {
			return "redirect:/serviceBoard.do";
		}
		
		// �Ķ���Ͱ�  pageNum  ��������
		String pageNum = request.getParameter("pageNum");
		String num = request.getParameter("num");
		String reRef = request.getParameter("reRef");
		String reLev = request.getParameter("reLev");
		String reSeq = request.getParameter("reSeq");
		
		// ��(jsp)���� �ʿ��� ������ request ������ü�� ����
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("num", num);
		request.setAttribute("id", id);
		request.setAttribute("reRef", reRef);
		request.setAttribute("reLev", reLev);
		request.setAttribute("reSeq", reSeq);
		
		return "service/serviceWriteForm";
	}

}
