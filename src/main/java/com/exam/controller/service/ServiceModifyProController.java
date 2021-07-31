package com.exam.controller.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exam.controller.Controller;
import com.exam.dao.ServiceAttachDao;
import com.exam.dao.ServiceDao;
import com.exam.vo.ServiceAttachVo;
import com.exam.vo.ServiceVo;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class ServiceModifyProController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
			System.out.println("FileModifyProController....");
			
			// ���� ��������
			HttpSession session = request.getSession();
			
			// �α��� ���� Ȯ��
			String id = (String) session.getAttribute("id");
			if (id == null) {
				return "redirect:/serviceBoard.do";
			}
			
			//���ε� �� ���� ������ ��� ���ϱ�
			ServletContext application = request.getServletContext();
			String realPath = application.getRealPath("/upload1");
			System.out.println("realPath : " + realPath);
			

			//���ó�¥ ����� ������ �����ϴ��� Ȯ���ؼ� ������ �����ϱ�
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			String strDate = sdf.format(date); // "2020/11/11"

			File dir = new File(realPath, strDate);
			System.out.println("dir : " + dir.getPath());

			if (!dir.exists()) {
				dir.mkdirs();
			}
			
			//���� ���ε� �ϱ�. (�ű� ÷�������� ���ε�)
			MultipartRequest multi = new MultipartRequest(
					request,
					dir.getPath(),
					1024 * 1024 * 100, // �ִ� ���ε� 100MB�� ����
					"utf-8",
					new DefaultFileRenamePolicy());
			
			// DAO �غ�
			ServiceDao serviceDao = ServiceDao.getInstance();
			ServiceAttachDao serviceAttachDao = ServiceAttachDao.getInstance();
			
			// �Խ��� �۹�ȣ
			int noNum = Integer.parseInt(multi.getParameter("num"));
			
			// �ű� ÷������ ������ ���̺� insert�ϱ�
			Enumeration<String> enu = multi.getFileNames(); // ���ϼ��û����� name�Ӽ��� ��������
			while (enu.hasMoreElements()) {
				String fname = enu.nextElement();
				String filename = multi.getFilesystemName(fname);
				
				if (filename == null) {
					continue;
				}
				
				// insert�� ���� VO ��ü �غ�
				ServiceAttachVo serviceAttachVo = new ServiceAttachVo();
				serviceAttachVo.setFilename(filename);
				serviceAttachVo.setUploadpath(strDate);
				serviceAttachVo.setImage( isImage(filename) ? "I" : "O" );
				serviceAttachVo.setNoNum(noNum);
				
				// ÷���������� insert�ϱ�
				serviceAttachDao.insertServiceAttach(serviceAttachVo);
			} // while
			
			// �����ؾ� �� ÷�����ϵ� ��ȣ ��������
			String[] delFileNums = multi.getParameterValues("delfile");
			if(delFileNums != null) {
			for (String delFileNum : delFileNums) {
				// ������ ÷������ ��ȣ�� ���ڷ� ��ȯ
				int num = Integer.parseInt(delFileNum);
				// ÷������ ��ȣ�� �ش��ϴ� ÷������ ���� �Ѱ��� VO�� ��������
				ServiceAttachVo serviceAttachVo = serviceAttachDao.getServiceAttachByNum(num);
				
				// ���������� �������� ���翩�� Ȯ���ؼ� �����ϱ�
				File delFile = new File(realPath + "/" + serviceAttachVo.getUploadpath(), serviceAttachVo.getFilename());
				if (delFile.exists()) {
					delFile.delete();
				}
				
				// ÷������ DB���̺� ÷�����Ϲ�ȣ�� �ش��ϴ� ���ڵ� �Ѱ� �����ϱ�
				serviceAttachDao.deleteServiceAttachByNum(num);
			} // for
			}
			
			// NoticeVo ��ü �غ�
			ServiceVo serviceVo = new ServiceVo();

			// �Ķ���Ͱ��� NoticeVo�� ����
			serviceVo.setNum(noNum);
			serviceVo.setId(multi.getParameter("id"));
			serviceVo.setSubject(multi.getParameter("subject"));
			serviceVo.setContent(multi.getParameter("content"));

			// �Խ��� ���̺� �� update�ϱ�
			serviceDao.updateBoard(serviceVo);

			// pageNum �Ķ���Ͱ� ��������
			String pageNum = multi.getParameter("pageNum");

			// �󼼺��� ȭ������ �̵�
			// response.sendRedirect("fileContent.jsp?num=" + noNum + "&pageNum=" + pageNum);
		String rtn = "redirect:/serviceBoard.do?num=\"+noNum+\"&pagenum=\"+pageNum";
		return "redirect:/serviceBoard.do?num=\"+noNum+\"&pagenum=\"+pageNum";
	}

	private boolean isImage(String filename) {
		boolean result = false;
		// ���� Ȯ���� ���ڿ� �����ϱ�
		// aaaa.bbb.ccc.ddd
		int index = filename.lastIndexOf(".");
		String ext = filename.substring(index + 1); // ���� Ȯ���� ���ڿ�
		
		if (ext.equalsIgnoreCase("jpg") 
				|| ext.equalsIgnoreCase("jpeg")
				|| ext.equalsIgnoreCase("gif")
				|| ext.equalsIgnoreCase("png")) {
			result = true;
		}
		return result;
	}
	
}
