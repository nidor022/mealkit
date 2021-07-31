package com.exam.controller.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exam.controller.Controller;
import com.exam.dao.ServiceAttachDao;
import com.exam.dao.ServiceDao;
import com.exam.vo.ServiceAttachVo;
import com.exam.vo.ServiceVo;

public class ServiceModifyFormController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ServiceModifyFormController....");
		
				// ���� ��������
				HttpSession session = request.getSession();
				
				// �α��� ���� Ȯ��
				String id = (String) session.getAttribute("id");
				if (id == null) {
					return "redirect:/serviceBoard.do";
				}
				
				// �Ķ���Ͱ�  pageNum  ��������
				int num = Integer.parseInt(request.getParameter("num"));
				String pageNum = request.getParameter("pageNum");
				
				// DAO �غ�
				ServiceDao serviceDao = ServiceDao.getInstance();
				ServiceAttachDao serviceAttachDao = ServiceAttachDao.getInstance();

				// �۹�ȣ num�� �ش��ϴ� �۳��� VO�� ��������
				ServiceVo serviceVo = serviceDao.getServiceByNum(num);
				// �۹�ȣ num�� �ش��ϴ� ÷������������ ����Ʈ�� ��������
				List<ServiceAttachVo> serviceAttachList = serviceAttachDao.getServiceAttachesByNoNum(num);
				
				
				
				// ��(jsp)���� �ʿ��� ������ request ������ü�� ����
				request.setAttribute("pageNum", pageNum);
				request.setAttribute("serviceAttachList", serviceAttachList);
				request.setAttribute("serviceVo", serviceVo);
		
		
		
				return "service/serviceModifyForm";
	}

}
