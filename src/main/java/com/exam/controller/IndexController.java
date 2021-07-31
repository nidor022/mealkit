package com.exam.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class IndexController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("IndexController......");
		
		//로그인 상태유지 쿠키정보 가져오기
		Cookie[] cookies = request.getCookies();
		//쿠키 name이 "id"인 쿠키객체 찾기
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("id")) {
					String id = cookie.getValue();
					
					// 세션 참조 가져오기
					HttpSession session = request.getSession();
					
					// 세션에 로그인 아이디를 저장 (로그인 처리), 로그인 인증 처리(세션에 id값 추가)
					// jsp경우 session객체가 만들어져있지만 여기선 아님.
					session.setAttribute("id", id);
				}
			}
		}
		
		/* 화면이동할때 서버컨트롤러가 원하는 이동정보는 리다이렉트,
		리다이렉트로 시작안하고 서버처리흐름상에서 특정 jsp페이지를 바로 실행할때는 (디스패치 방식) 아래식으로
		-- 모델1에서는 리다이렉트식만 사용했음 -- */
		
		// DB처리를 위해 Model역할인 Dao를 사용할수 있음
		
//		return "redirect:/joinForm.do"; // 리다이렉트 정보
		
//		return "/WEB-INF/views/index.jsp";
//							  >     < 꺾쇄사이만 리턴함! 
		return "index"; // 실행할 jsp페이지 경로이름 정보 // (앞뒤로는 달아줄거니까 제거 후 리턴!!!)
	}

}
