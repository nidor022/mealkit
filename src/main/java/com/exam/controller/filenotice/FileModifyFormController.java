package com.exam.controller.filenotice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exam.controller.Controller;
import com.exam.dao.AttachDao;
import com.exam.dao.NoticeDao;
import com.exam.vo.AttachVo;
import com.exam.vo.NoticeVo;

public class FileModifyFormController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("FileModifyFormController....");
		
		// ���� ��������
				HttpSession session = request.getSession();
				
				// �α��� ���� Ȯ��
				String id = (String) session.getAttribute("id");
				if (id == null) {
					return "redirect:/fileboard.do";
				}
				
				// �Ķ���Ͱ�  pageNum  ��������
				int num = Integer.parseInt(request.getParameter("num"));
				String pageNum = request.getParameter("pageNum");
				
				// DAO �غ�
				NoticeDao noticeDao = NoticeDao.getInstance();
				AttachDao attachDao = AttachDao.getInstance();

				// �۹�ȣ num�� �ش��ϴ� �۳��� VO�� ��������
				NoticeVo noticeVo = noticeDao.getNoticeByNum(num);
				// �۹�ȣ num�� �ش��ϴ� ÷������������ ����Ʈ�� ��������
				List<AttachVo> attachList = attachDao.getAttachesByNoNum(num);
				
				
				
				// ��(jsp)���� �ʿ��� ������ request ������ü�� ����
				request.setAttribute("pageNum", pageNum);
				request.setAttribute("attachList", attachList);
				request.setAttribute("noticeVo", noticeVo);
		
		
		
				return "center/fileModifyForm";
	}

}
