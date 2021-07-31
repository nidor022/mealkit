package com.exam.controller.user;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.exam.controller.Controller;

public class UserLogoutController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("UserLogoutController......");

		// 세션 객체 참조 가져오기
		HttpSession session = request.getSession();
		// 세션값 초기화. 로그아웃 작업
		session.invalidate();
		
		// 로그인 상태유지용 쿠키가 존재하면 삭제하기
		Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("id")) {
					cookie.setMaxAge(0); // 유효기간 0으로 설정 -> 쿠키 삭제처리
					cookie.setPath("/"); // 경로 설정
					
					response.addCookie(cookie); // 쿠키정보 보내기
				}
			}
		}
		
		// index.do로 리다이렉트
		return "redirect:/index.do";
		// 새로고침이슈 생각하면 redirect!가 낫쥐
	}
}
