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
		  // 톰캣서버의 컨테이너가 매핑된 url을 찾아 실행
@WebServlet(urlPatterns = "*.do", loadOnStartup = 1)
public class FrontController extends HttpServlet {

	public void init(ServletConfig config) throws ServletException {
		System.out.println("init() 호출");
		
		// Servelet과 container간의 연동
		// Servelet의 정보 추출을 위해 SeveletContainer에 접근
		ServletContext application = config.getServletContext();
		application.setAttribute("aa", "hi");
		String hello = (String) application.getAttribute("aa");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet() 호출");
		
		String requestURI = request.getRequestURI(); // 프로젝트와 파일 경로까지
		System.out.println("URI " + requestURI);
			// URI : /funweb-model2/index.do
		
		String contextPath = request.getContextPath();  
		System.out.println("contextPath: " + contextPath);
			// contextPath: /funweb-model2
		
		String command = requestURI.substring(contextPath.length());
			// command: /index.do
		command = command.substring(0, command.indexOf(".do"));
		System.out.println("command: " + command);
			// command: /index
		
		Controller controller = null;
		ControllerFactory factory = ControllerFactory.getInstance();  
		String strView = null;								  
															 
		controller = factory.getController(command);
		if (controller  == null) {
			System.out.println(command + "controller 호출");
			return; 
		}
		
		try {
			
			strView = controller.execute(request, response); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		if (strView == null) {
			System.out.println("오류");
			return;
		}
		
		if (strView.startsWith("redirect:")) { 
			String redirectPath = strView.substring("redirect:".length());
			response.sendRedirect(redirectPath);
								// index.do?
		} else {										// index.jsp?
			String jspPath = "/WEB-INF/views/" + strView + ".jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(jspPath);
			dispatcher.forward(request, response); // 대상 자원으로 제어를 넘긴다
		}
		
	} // doGet


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost() ȣ���");
		
		request.setCharacterEncoding("utf-8"); 
		doGet(request, response);
	} // doPost
	
	public void destroy() {
		System.out.println("destroy() ȣ���");
	}

}
