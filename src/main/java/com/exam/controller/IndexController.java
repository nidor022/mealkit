package com.exam.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class IndexController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("IndexController......");
		
		//�α��� �������� ��Ű���� ��������
		Cookie[] cookies = request.getCookies();
		//��Ű name�� "id"�� ��Ű��ü ã��
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("id")) {
					String id = cookie.getValue();
					
					// ���� ���� ��������
					HttpSession session = request.getSession();
					
					// ���ǿ� �α��� ���̵� ���� (�α��� ó��), �α��� ���� ó��(���ǿ� id�� �߰�)
					// jsp��� session��ü�� ������������� ���⼱ �ƴ�.
					session.setAttribute("id", id);
				}
			}
		}
		
		/* ȭ���̵��Ҷ� ������Ʈ�ѷ��� ���ϴ� �̵������� �����̷�Ʈ,
		�����̷�Ʈ�� ���۾��ϰ� ����ó���帧�󿡼� Ư�� jsp�������� �ٷ� �����Ҷ��� (����ġ ���) �Ʒ�������
		-- ��1������ �����̷�Ʈ�ĸ� ������� -- */
		
		// DBó���� ���� Model������ Dao�� ����Ҽ� ����
		
//		return "redirect:/joinForm.do"; // �����̷�Ʈ ����
		
//		return "/WEB-INF/views/index.jsp";
//							  >     < ������̸� ������! 
		return "index"; // ������ jsp������ ����̸� ���� // (�յڷδ� �޾��ٰŴϱ� ���� �� ����!!!)
	}

}
