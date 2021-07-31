package com.exam.controller.user;

import java.io.PrintWriter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.exam.controller.Controller;
import com.exam.dao.UserDao;

public class UserLoginProController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("UserLoginProController......");
		
		// �Ķ���� id  passwd   keepLogin  ��������
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String strKeepLogin = request.getParameter("keepLogin");

		// DAO ��ü �غ�
		UserDao userDao = UserDao.getInstance();

		// �α��� Ȯ��.
		// check -1  ���� ���̵�
		// check  0  �н����� Ʋ��
		// check  1  ���̵�, �н����� ��� ��ġ
		int check = userDao.userCheck(id, pwd);

		/* �α����� �������� ��� */
		if (check != 1) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter(); // jsp���� out���� �� ��ü
			out.println("<script>");
			out.println("	alert('���̵� �Ǵ� �н����尡 ��ġ���� �ʽ��ϴ�.');");
			out.println("	history.back();");
			out.println("</script>");
			out.close(); // ���� ���۵� �����(Ŭ���̾�Ʈ�� ����)
						 // (close�� ���� flush�� ���������� ���ԵǾ��ֱ⶧���� flush�� �����ص���)
			return null; // controller�� null ���Ϲ����� 3�ܰ���� ������ �ȵ�.
			
//			%>
//			<script>
//				alert('���̵� �Ǵ� �н����尡 ��ġ���� �ʽ��ϴ�.');
//				history.back();
//			</script>	
//			<%
//			return;
		}

		//�α��� �������� ���� Ȯ���ϱ�
		boolean keepLogin = false;
		if (strKeepLogin != null) { // "true"
			keepLogin = Boolean.parseBoolean(strKeepLogin); // "true" -> true
		}

		/* �α����� �������� ��� */
		
		// ���� ���� ��������
		HttpSession session = request.getSession();
		
		// ���ǿ� �α��� ���̵� ���� (�α��� ó��) 
		// jsp��� session��ü�� ������������� ���⼱ �ƴ�.
		session.setAttribute("id", id);

		// �α��� ���������� ���ϸ� ��Ű ���� �� �����ֱ�
		if (keepLogin) { // keepLogin == true
			Cookie cookie = new Cookie("id", id);
			cookie.setMaxAge(60 * 10);  // �ʴ��� ����  10��
			cookie.setPath("/");

			response.addCookie(cookie);
		}

		// index.do�� �����̷�Ʈ
		return "redirect:/index.do";
//		return "redirect:/"; // �������Ϸ� ���ԵǸ鼭 index.jsp > index.do ȣ���� ��
			// ���ΰ�ħ�� ������ �������ǳľȵǳİ� �����̷�Ʈ�� �������� ����
			// Pro���� �������� ó�����̴ϱ� ���ΰ�ħ�� ������ �� �ǹ̾��� �α����� �߻�
			// �׷����̾����� �ٷ� �����̷�Ʈ �������� ���� ��
//		return "index"; 
		// ��������忡�� �Ȱ��� indexȭ�������� �� ����ġ��������ϸ�
		// ���ΰ�ħ�� �α����� �ѹ� �� ��.
	}

}
