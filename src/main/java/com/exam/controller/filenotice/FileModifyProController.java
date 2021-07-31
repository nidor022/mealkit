package com.exam.controller.filenotice;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exam.controller.Controller;
import com.exam.dao.AttachDao;
import com.exam.dao.NoticeDao;
import com.exam.vo.AttachVo;
import com.exam.vo.NoticeVo;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class FileModifyProController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
			System.out.println("FileModifyProController....");
			
			// ���� ��������
			HttpSession session = request.getSession();
			
			// �α��� ���� Ȯ��
			String id = (String) session.getAttribute("id");
			if (id == null) {
				return "redirect:/fileboard.do";
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
			NoticeDao noticeDao = NoticeDao.getInstance();
			AttachDao attachDao = AttachDao.getInstance();
			
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
				AttachVo attachVo = new AttachVo();
				attachVo.setFilename(filename);
				attachVo.setUploadpath(strDate);
				attachVo.setImage( isImage(filename) ? "I" : "O" );
				attachVo.setNoNum(noNum);
				
				// ÷���������� insert�ϱ�
				attachDao.insertAttach(attachVo);
			} // while
			
			// �����ؾ� �� ÷�����ϵ� ��ȣ ��������
			String[] delFileNums = multi.getParameterValues("delfile");
			if(delFileNums != null) {
			for (String delFileNum : delFileNums) {
				// ������ ÷������ ��ȣ�� ���ڷ� ��ȯ
				int num = Integer.parseInt(delFileNum);
				// ÷������ ��ȣ�� �ش��ϴ� ÷������ ���� �Ѱ��� VO�� ��������
				AttachVo attachVo = attachDao.getAttachByNum(num);
				
				// ���������� �������� ���翩�� Ȯ���ؼ� �����ϱ�
				File delFile = new File(realPath + "/" + attachVo.getUploadpath(), attachVo.getFilename());
				if (delFile.exists()) {
					delFile.delete();
				}
				
				// ÷������ DB���̺� ÷�����Ϲ�ȣ�� �ش��ϴ� ���ڵ� �Ѱ� �����ϱ�
				attachDao.deleteAttachByNum(num);
			} // for
			}
			
			// NoticeVo ��ü �غ�
			NoticeVo noticeVo = new NoticeVo();

			// �Ķ���Ͱ��� NoticeVo�� ����
			noticeVo.setNum(noNum);
			noticeVo.setId(multi.getParameter("id"));
			noticeVo.setSubject(multi.getParameter("subject"));
			noticeVo.setContent(multi.getParameter("content"));

			// �Խ��� ���̺� �� update�ϱ�
			noticeDao.updateBoard(noticeVo);

			// pageNum �Ķ���Ͱ� ��������
			String pageNum = multi.getParameter("pageNum");

			// �󼼺��� ȭ������ �̵�
			// response.sendRedirect("fileContent.jsp?num=" + noNum + "&pageNum=" + pageNum);
		String rtn = "redirect:/fileboard.do?num=\"+noNum+\"&pagenum=\"+pageNum";
		return "redirect:/fileboard.do?num=\"+noNum+\"&pagenum=\"+pageNum";
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
