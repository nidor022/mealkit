package com.exam.controller.user;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.exam.controller.Controller;
import com.exam.dao.UserDao;
import com.exam.vo.UserVo;

public class UserJoinProController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("UserJoinProController......");
		
		// VO 객체 준비
		UserVo vo = new UserVo();
		
		// 액션태그로 VO객체에 파라미터값 저장
		vo.setId(request.getParameter("id"));
		vo.setPwd(request.getParameter("pwd"));
		vo.setName(request.getParameter("name"));
		vo.setAge(Integer.parseInt(request.getParameter("age")));
		vo.setGender(request.getParameter("gender"));
		vo.setEmail(request.getParameter("email"));
		vo.setAddress(request.getParameter("address"));
		vo.setTel(request.getParameter("tel"));
		
		// 생일
		vo.setBirthDay(request.getParameter("birthday"));
		
		// 가입날짜 생성해서 넣기
		vo.setRegDate(new Timestamp(System.currentTimeMillis()));

		// DAO 객체 준비
		UserDao userDao = UserDao.getInstance();
		// 회원가입 메서드 호출
		userDao.addUser(vo);
		
		// 로그인 화면 요청경로로 리다이렉트시키기 위해서
		// 리다이렉트 정보를 리턴함
		return "redirect:/userLogin.do";
//		return "member/login";
		// 이런식으로하면 회원가입이 끝나고 바로 jsp를 실행해서 보는화면이 login인건
		// 똑같음. 대신 새로고침이 발생함. 한번더 insert된다.
	}

}
