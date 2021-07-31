package com.exam.controller.comment;

import java.sql.Timestamp;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exam.controller.Controller;
import com.exam.dao.CommentDao;
import com.exam.vo.CommentVo;
import com.exam.vo.UserVo;

public class CommentInsertController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("CommentInsertController......");
		
		// ���� ���� ��������
		HttpSession session = request.getSession();
		
		// VO DAO �غ�
		CommentVo commentVo = new CommentVo();
		CommentDao commentDao = CommentDao.getInstance();
		
		String pageNum = request.getParameter("pageNum");
		
		String id = (String) session.getAttribute("id");
		String b_num = request.getParameter("b_num");
		String c_content = request.getParameter("c_content");
		
		System.out.println("id:"+id);
		System.out.println("b_num:"+b_num);
		System.out.println("c_content:"+c_content);
		
			commentVo.setC_id(id); // �۾���
			commentVo.setC_content(c_content); // ������
			commentVo.setC_date(new Timestamp(System.currentTimeMillis()));  // ������
			commentVo.setB_num(Integer.parseInt(b_num));
			
			// commentVo�� comment ���̺� insert�ϱ�
			commentDao.insertComment(commentVo);
		
			return "redirect:/fileContent.do?num=" + b_num + "&pageNum=" + pageNum;
	}

}
