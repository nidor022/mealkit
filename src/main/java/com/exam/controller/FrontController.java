package com.exam.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
		  // �̷����� url����Ʈ ó��, �����ϱ����� �̸� �غ� 1������
@WebServlet(urlPatterns = "*.do", loadOnStartup = 1)
public class FrontController extends HttpServlet {

	public void init(ServletConfig config) throws ServletException {
		System.out.println("init() ȣ���");
		
		// application ��ü �����ͼ� �ʿ��� ������ ����
		ServletContext application = config.getServletContext();
		application.setAttribute("aa", "�ȳ�");
		String hello = (String) application.getAttribute("aa");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet() ȣ���");
		
		// ��û �ּ�
		// http://localhost:80/funweb-model2/index.do
		//					  > �� ���� �κ��� URI, ���������񽺸��ϸ� ������ �ּҿ�����
		//						��ġ�� ��Ÿ���� ���� �ƴ϶� �ĺ��ϴ� �κ�, ��������� URL�̶�� ��⸦ ������ ��Ȯ�ϰԴ� URI		
		// http://localhost:80/index.do
					  
		/* 1�ܰ�) ��ɾ� command ���ϱ� */
		// URI �ּ� ��������
		String requestURI = request.getRequestURI();
		System.out.println("URI �ּ�: " + requestURI);
			// URI �ּ�: /funweb-model2/index.do
		
		// ������Ʈ �̸� ��� ��������
		String contextPath = request.getContextPath(); // ������ ���ڿ� "" ����
		System.out.println("contextPath: " + contextPath);
			// contextPath: /funweb-model2
		
		// ��û ��ɾ� ���ϱ�
		String command = requestURI.substring(contextPath.length());
			// command: /index.do
		command = command.substring(0, command.indexOf(".do"));
		System.out.println("command: " + command);
			// command: /index
		
		
		/* 2�ܰ�) ��ɾ� �����ϱ� */ // << �䴮�� �츮�� �ڵ�(Ű��)
		// �̷���(if else)���ϸ� �ʿ�����ڵ嵵 �� ����Ǿ ������ �ƴ�
		Controller controller = null;
		ControllerFactory factory = ControllerFactory.getInstance(); // new! ��ü������ ���������ڸ� ����ؾ��� -> �߿��� �̱��� 
		String strView = null;								 // �ѹ� ���ε���� ��Ʈ�ѷ���ü�� ��ü�� �̱����̶� �� ��������� 
															 // ��ġ�� ��Ʈ�ѷ����丮�� �ƴϴϰ� �̱��� ����߰���
		// ��ɾ �ش��ϴ� ��Ʈ�ѷ� ��ü ���ϱ�						
		controller = factory.getController(command);
		if (controller  == null) {
			System.out.println(command + "�� ó���ϴ� ��Ʈ�ѷ��� �����ϴ�.");
			return; // return�̴ϱ� �������ϱ� ��������
		}
		
		try {
			// Ű�ʿ����� new ��¼��Controller�� �ش��ϴ� ��Ʈ�ѷ� ��ü �����ϱ�
			strView = controller.execute(request, response); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		/* 3�ܰ�) ȭ������(jsp����) �Ǵ� �����̷�Ʈ(.do) �̵� */
		if (strView == null) {
			System.out.println("�̵��� ȭ������(View)�� �����ϴ�.");
			return;
		}
		
		if (strView.startsWith("redirect:")) { // .do�� ������ ���
			String redirectPath = strView.substring("redirect:".length());
			response.sendRedirect(redirectPath);
								// index.do?
		} else {										// index.jsp?
			String jspPath = "/WEB-INF/views/" + strView + ".jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(jspPath);
			dispatcher.forward(request, response); // �ش� jsp �ٷ� �����ϱ� ( ���ٿ��� �����̷�Ʈ���� �ٸ� )
		}
		
		
	} // doGet


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost() ȣ���");
		
		request.setCharacterEncoding("utf-8"); // post��û �Ķ���Ͱ� �ѱ�ó��
		doGet(request, response);
	} // doPost
	
	public void destroy() {
		System.out.println("destroy() ȣ���");
	}

}
