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
		
		// 파라미터 id  passwd   keepLogin  가져오기
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String strKeepLogin = request.getParameter("keepLogin");

		// DAO 객체 준비
		UserDao userDao = UserDao.getInstance();

		// 로그인 확인.
		// check -1  없는 아이디
		// check  0  패스워드 틀림
		// check  1  아이디, 패스워드 모두 일치
		int check = userDao.userCheck(id, pwd);

		/* 로그인이 실패했을 경우 */
		if (check != 1) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter(); // jsp에서 out으로 쓴 객체
			out.println("<script>");
			out.println("	alert('아이디 또는 패스워드가 일치하지 않습니다.');");
			out.println("	history.back();");
			out.println("</script>");
			out.close(); // 내부 버퍼도 비워짐(클라이언트로 보냄)
						 // (close를 쓰면 flush가 내부적으로 포함되어있기때문에 flush를 생략해도됨)
			return null; // controller로 null 리턴받으면 3단계까지 진행이 안됨.
			
//			%>
//			<script>
//				alert('아이디 또는 패스워드가 일치하지 않습니다.');
//				history.back();
//			</script>	
//			<%
//			return;
		}

		//로그인 상태유지 정보 확인하기
		boolean keepLogin = false;
		if (strKeepLogin != null) { // "true"
			keepLogin = Boolean.parseBoolean(strKeepLogin); // "true" -> true
		}

		/* 로그인이 성공했을 경우 */
		
		// 세션 참조 가져오기
		HttpSession session = request.getSession();
		
		// 세션에 로그인 아이디를 저장 (로그인 처리) 
		// jsp경우 session객체가 만들어져있지만 여기선 아님.
		session.setAttribute("id", id);

		// 로그인 상태유지를 원하면 쿠키 생성 후 응답주기
		if (keepLogin) { // keepLogin == true
			Cookie cookie = new Cookie("id", id);
			cookie.setMaxAge(60 * 10);  // 초단위 설정  10분
			cookie.setPath("/");

			response.addCookie(cookie);
		}

		// index.do로 리다이렉트
		return "redirect:/index.do";
//		return "redirect:/"; // 웰컴파일로 가게되면서 index.jsp > index.do 호출이 됨
			// 새로고침을 했을때 문제가되냐안되냐가 리다이렉트를 할지말지 결정
			// Pro같은 페이지는 처리용이니까 새로고침을 했을때 또 의미없는 로그인이 발생
			// 그런일이없도록 바로 리다이렉트 페이지로 가게 함
//		return "index"; 
		// 사용자입장에선 똑같은 index화면이지만 이 디스패치방식으로하면
		// 새로고침시 로그인이 한번 더 됨.
	}

}
