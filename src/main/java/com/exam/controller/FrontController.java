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
		  // 이런류의 url사이트 처리, 시작하기전에 미리 준비 1순위로
@WebServlet(urlPatterns = "*.do", loadOnStartup = 1)
public class FrontController extends HttpServlet {

	public void init(ServletConfig config) throws ServletException {
		System.out.println("init() 호출됨");
		
		// application 객체 가져와서 필요한 데이터 저장
		ServletContext application = config.getServletContext();
		application.setAttribute("aa", "안녕");
		String hello = (String) application.getAttribute("aa");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet() 호출됨");
		
		// 요청 주소
		// http://localhost:80/funweb-model2/index.do
		//					  > 이 이후 부분이 URI, 실제웹서비스를하면 유일한 주소여야함
		//						위치를 나타내는 것이 아니라 식별하는 부분, 통상적으로 URL이라고 얘기를 하지만 정확하게는 URI		
		// http://localhost:80/index.do
					  
		/* 1단계) 명령어 command 구하기 */
		// URI 주소 가져오기
		String requestURI = request.getRequestURI();
		System.out.println("URI 주소: " + requestURI);
			// URI 주소: /funweb-model2/index.do
		
		// 프로젝트 이름 경로 가져오기
		String contextPath = request.getContextPath(); // 없으면 빈문자열 "" 들어옴
		System.out.println("contextPath: " + contextPath);
			// contextPath: /funweb-model2
		
		// 요청 명령어 구하기
		String command = requestURI.substring(contextPath.length());
			// command: /index.do
		command = command.substring(0, command.indexOf(".do"));
		System.out.println("command: " + command);
			// command: /index
		
		
		/* 2단계) 명령어 실행하기 */ // << 요눔을 우리가 코딩(키맵)
		// 이런식(if else)로하면 필요없는코드도 다 노출되어서 좋은게 아님
		Controller controller = null;
		ControllerFactory factory = ControllerFactory.getInstance(); // new! 객체생성은 동시접속자를 고려해야함 -> 중요한 싱글톤 
		String strView = null;								 // 한번 뉴로등록한 컨트롤러객체는 자체가 싱글톤이라 걍 꺼내쓰면됨 
															 // 그치만 컨트롤러팩토리는 아니니가 싱글톤 해줘야겠지
		// 명령어에 해당하는 컨트롤러 객체 구하기						
		controller = factory.getController(command);
		if (controller  == null) {
			System.out.println(command + "를 처리하는 컨트롤러가 없습니다.");
			return; // return이니까 여까지하구 서버끝남
		}
		
		try {
			// 키맵에서의 new 어쩌구Controller에 해당하는 컨트롤러 객체 실행하기
			strView = controller.execute(request, response); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		/* 3단계) 화면응답(jsp실행) 또는 리다이렉트(.do) 이동 */
		if (strView == null) {
			System.out.println("이동할 화면정보(View)가 없습니다.");
			return;
		}
		
		if (strView.startsWith("redirect:")) { // .do로 끝나는 경로
			String redirectPath = strView.substring("redirect:".length());
			response.sendRedirect(redirectPath);
								// index.do?
		} else {										// index.jsp?
			String jspPath = "/WEB-INF/views/" + strView + ".jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(jspPath);
			dispatcher.forward(request, response); // 해당 jsp 바로 실행하기 ( 갔다오는 리다이렉트랑은 다름 )
		}
		
		
	} // doGet


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost() 호출됨");
		
		request.setCharacterEncoding("utf-8"); // post요청 파라미터값 한글처리
		doGet(request, response);
	} // doPost
	
	public void destroy() {
		System.out.println("destroy() 호출됨");
	}

}
