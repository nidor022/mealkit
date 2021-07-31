package com.exam.controller.service;

import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.exam.controller.Controller;
import com.exam.dao.JdbcUtils;
import com.exam.dao.ServiceAttachDao;
import com.exam.dao.ServiceDao;
import com.exam.vo.ServiceAttachVo;
import com.exam.vo.ServiceVo;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class ServiceReplyProController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ServiceReplyProController......");

		///�ڷ��غ�//////////////////////////////////////////////////////////////////////////////

		// DAO �غ�
		ServiceDao serviceDao = ServiceDao.getInstance();
		ServiceAttachDao serviceAttachDao = ServiceAttachDao.getInstance();

		//VO ��ü �غ�
		ServiceVo serviceVo = new ServiceVo();
		ServiceAttachVo serviceAttachVo = new ServiceAttachVo();

		///�����غ�//////////////////////////////////////////////////////////////////////////////

		//���ε� �� ���� ������ ��� ���ϱ�
		ServletContext application = request.getServletContext(); // application ��ü ���� ���ϱ�
		String realPath = application.getRealPath("/upload1");
		System.out.println("realPath : " + realPath);

		// ���ó�¥ ����� ������ �����ϴ��� Ȯ���ؼ� ������ �����ϱ�
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String strDate = sdf.format(date); // "2020/11/11"

		File dir = new File(realPath, strDate);
		System.out.println("dir : " + dir.getPath());

		if (!dir.exists()) {
			dir.mkdirs();
		}

		//���� ���ε� �ϱ�
		MultipartRequest multi = new MultipartRequest(
				request,
				dir.getPath(),
				1024 * 1024 * 100, // �ִ� ���ε� 100MB�� ����
				"utf-8",
				new DefaultFileRenamePolicy());

		///�Ķ���� ��������(�Ʒ� attach�ϸ鼭 ���ֵ��̶� ��� ��������)//////////////////////////////////////////////////////////////////////////////

		//pageNum �Ķ���Ͱ� ��������
		String pageNum = multi.getParameter("pageNum");
		String num = multi.getParameter("num");

		System.out.println("pageNum: " + pageNum);
		System.out.println("num: " + num);

		//insert�� �۹�ȣ ��������
		int nextNum = JdbcUtils.getNextNum("service");
		serviceVo.setNum(nextNum);
		System.out.println("nextNum: " + nextNum);

		///���ۿ� ���Ϻ��̱�//////////////////////////////////////////////////////////////////////////////

		//Enumeration�� �ݺ��� ��ü. file�� name�Ӽ����� ������ ����
		Enumeration<String> enu = multi.getFileNames();

		while (enu.hasMoreElements()) {
			String fname = enu.nextElement();
			// ���� ���ε�� ���ϸ� ��������
			String filename = multi.getFilesystemName(fname);
			System.out.println("�������ϸ� : " + filename);

			serviceAttachVo.setFilename(filename); // �������ϸ��� VO�� ����
			serviceAttachVo.setUploadpath(strDate); // "��/��/��" ��θ� ����
			serviceAttachVo.setNoNum(nextNum);  // insert�� �Խ��� �۹�ȣ�� ����
			if(filename != null) {
				serviceAttachVo.setImage( isImage(filename) ? "I" : "O" );
			}
			// attachVo�� attach ���̺� insert�ϱ�
			serviceAttachDao.insertServiceAttach(serviceAttachVo);
		} // while

		///�Ķ���Ͱ�(���� ���Ϻ��̱⿡ �Ƚ�εǴ°�)//////////////////////////////////////////////////////////////////////////////

		//�Ķ���Ͱ� �����ͼ� VO�� ����. MultipartRequest �κ��� ã��.
		serviceVo.setId(multi.getParameter("id"));
		serviceVo.setSubject(multi.getParameter("subject"));
		serviceVo.setContent(multi.getParameter("content"));

		//ip  regDate  readcount  �� ����
		serviceVo.setIp(request.getRemoteAddr());
		serviceVo.setRegDate(new Timestamp(System.currentTimeMillis()));
		serviceVo.setReadcount(0);  // ��ȸ��

		//re_ref  re_lev  re_seq
		String ref = multi.getParameter("reRef");
		String lev = multi.getParameter("reLev");
		String seq = multi.getParameter("reSeq");

		int re_seq = JdbcUtils.getNextSeq("service", Integer.parseInt(ref));
		int re_ref = JdbcUtils.getNumRef("service", Integer.parseInt(ref));

		serviceVo.setReRef(re_ref); // �ֱ��϶��� �۹�ȣ�� �׷��ȣ�� ��
		serviceVo.setReLev(Integer.parseInt(lev) + 1); // �ֱ��϶��� �鿩���� ������ 0 (�鿩���� ����)
		serviceVo.setReSeq(re_seq); // �ֱ��϶��� �۱׷� ������ ������ 0 (ù��°)

		// ��� insert�ϱ�
		serviceDao.addService(serviceVo);

		//�۳��� �󼼺��� ȭ�� fileContent.jsp�� �̵�
		//response.sendRedirect("fileContent.jsp?num=" + noticeVo.getNum() + "&pageNum=" + pageNum);
		return "redirect:/serviceBoard.do";
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
