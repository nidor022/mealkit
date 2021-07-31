<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
// 로그인 상태유지 쿠키정보 가져오기
Cookie[] cookies = request.getCookies();
// 쿠키 name이 "id"인 쿠키객체 찾기
if (cookies != null) {
	for (Cookie cookie : cookies) {
		if (cookie.getName().equals("id")) {
			String id = cookie.getValue();
			
			// 로그인 인증 처리(세션에 id값 추가)
			session.setAttribute("id", id);
		}
	}
}

// 세션값 가져오기
String id = (String) session.getAttribute("id");
// 세션값 있으면  ..님 반가워요~  [로그아웃]으로 바뀜. [회원가입]은 없어짐.
// 세션값 없으면  [로그인]  [회원가입]
%>
<header>
	<div id="login">
		<%
		if (id != null) {
			%>
			<%=id %>님 환영합니다! <!-- 모델2는 직접요청하는게아니라 FrontController로 페이지 요청하는거라 주소가 다름 -->
			<a href="/userLogout.do">로그아웃</a>
			<%
		} else { // id == null
			%>
			<a href="/userLogin.do">로그인</a>
			<a href="/userJoin.do">회원가입</a>
			<%
		}
		%>

	</div>
	<div class="clear"></div>
	<nav id="top_menu">
		<ul>
			<li><a href="/">카테고리</a></li>
			<li><a href="/welcome.do">오늘의 특가</a></li>
			<li><a href="#">이벤트</a></li>
			<li><a href="/notice.do">공지사항</a></li>
			<li><a href="#">고객 센터</a></li>
		</ul>
	</nav>
</header>