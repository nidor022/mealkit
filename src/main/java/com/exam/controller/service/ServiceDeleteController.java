package com.exam.controller.service;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.exam.controller.Controller;
import com.exam.dao.ServiceAttachDao;
import com.exam.dao.ServiceDao;
import com.exam.vo.ServiceAttachVo;

public class ServiceDeleteController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		
		// DAO ��ü �غ�
		ServiceDao serviceDao = ServiceDao.getInstance();
		ServiceAttachDao serviceAttachDao = ServiceAttachDao.getInstance();

		// �Խñ۹�ȣ�� ÷�ε� ÷������ ����Ʈ ��������
		List<ServiceAttachVo> serviceAttachList = serviceAttachDao.getServiceAttachesByNoNum(num);

		String realPath = request.getRealPath("/upload1"); // ���ε� ���ذ��

		// ÷������ �����ϱ�
		for (ServiceAttachVo serviceAttachVo : serviceAttachList) {
			// ������ ������ File Ÿ�� ��ü�� �غ�
			File file = new File(realPath + "/" + serviceAttachVo.getUploadpath(), serviceAttachVo.getFilename());
			
			if (file.exists()) { // �ش� ��ο� ������ �����ϸ�
				file.delete();   // ���� �����ϱ�
			}
		} // for

		// attach ÷�����ϳ��� �����ϱ�
		serviceAttachDao.deleteServiceAttachesByNoNum(num);

		// notice �Խñ� �����ϱ�
		serviceDao.deleteServiceByNum(num);

		// �۸�� fileNotice.jsp �� �̵�
		response.sendRedirect("serviceBoard.do?pageNum=" + pageNum);
		
		
		return null;
	}

}
