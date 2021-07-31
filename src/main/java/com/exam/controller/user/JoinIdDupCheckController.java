package com.exam.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.exam.controller.Controller;
import com.exam.dao.UserDao;

public class JoinIdDupCheckController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("JoinIdDupCheckController......");
		
		// 파라미터값 id 가져오기
		String id = request.getParameter("id");

		// DAO 객체 준비
		UserDao userDao = UserDao.getInstance();

		// 중복아이디 여부 확인
		int count = userDao.getCountById(id);
		
		// (view만드는데 필요한 데이터 id와 count)
		// 세션에 저장해서 뷰에서 사용해도되지만 불필요 메모리를 과다저장할 필요는없지 그래서
		// request객체를 사용
							//Web Application Server
		// * 웹에서의 영역객체 4가지 (수명주기큰순)(톰캣같은 WAS가 관리하는 Map 컬렉션)
		//   application : 웹프로그램 1개당 유지되는 영역객체 
//						(사용자마다 관리할필요없이 전체 공유해야하는것을 여기담음)
		//   session : 사용자 1명당 유지되는 영역객체 
		//   request : 요청 한번마다 유지되는 영역객체 (요청오면 만들고 응답해준 후 폐기)
//						(디스패치방식에 의해 페이지 넘어가고 넘어가고 할 때 request 지속적으로 유지 가능)
		//   page : jsp 페이지 한번 실행때만 유지되는 영역객체 
					
		
		// request 영역객체에 View화면에서 필요한 데이터를 저장
		request.setAttribute("id", id); // 요청-응답후 자동으로 삭제되는 객체라서 사용자가 일일이 지울필요x
		request.setAttribute("count", count); // 세션은 유지되는 객체니까 어느정도 아껴쓰는 편
		
		// 디스패치 방식으로 jsp를 바로실행하면
		// 앞에서 사용한 request객체가 jsp까지 전달되므로
		// 데이터를 사용할 수 있음
		
		return "user/joinIdDupCheck"; // FrontController쪽 2단계 strView로 리턴해서 앞뒤로 달아줌
	}	// request 객체를 꺼내 쓸 수 있는 dispatch 방식

}
