<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	// request 영역객체에 저장한 데이터 가져오기
	// (처음이니까 자바코드 넣어준 것)
// 	String id = (String) request.getAttribute("id");
	int count = (int) request.getAttribute("count");
		//	getParameter > 사용자가 입력한값 가져올때
		//	getAttribute > 서버에 저장한값 가져올때		
%>
<%--   (EL언어는 JSP의 기본 기술이라 따로 라이브러리를 요구하지 않음)
* JSP의 EL언어로 영역객체의 데이터에 접근하는 표현이 가능함
	47번줄!	
		${  }로 표현함
		application -> applicationScope
		session -> sessionScope
		request -> requestScope
		page -> pageScope
		
		Scope 객체 탐색 순서
		( 스코프 명시안하면 이 순으로 탐색, 얕은곳에서 깊은곳까지 )
		( 값이없으면 null값이아닌 빈문자열 ""을 리턴 )
		pageScope>requestScope>sessionScope>applicationScope
		
${ id } ==좌우 동일한 값== ${ requestScope.id } ==좌우 동일한 값== <%=(String) request.getAttribute("id") %> 
 --%>
<!-- 모델2에서는 자바코드는 컨트롤러가 담당해야함 여기애들 다 옮김~ -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>ID 중복확인</h2>
<hr>
<%--
//count == 1  아이디있음  "사용중인 ID 입니다."
//count == 0  아이디없음  "사용가능한 ID 입니다."
 --%>
<c:choose>
	<%-- when=>if, otherwise=>else --%>
	<%-- eq (==) gt (>) lt (<) ge(>=) le(<=) ne(!=) --%>
	<c:when test="${ requestScope.count eq 1 }">
		<p>아이디 중복, 이미 사용중인 ID 입니다.</p>									
	</c:when>
	<c:otherwise>
		<p>사용가능한 ID 입니다.</p>
		<input type="button" value="ID 사용" id="btnUseId">
	</c:otherwise>
</c:choose>
<%--

if (count == 1) {
	<p>아이디 중복, 이미 사용중인 ID 입니다.</p>
} else {
	<p>사용가능한 ID 입니다.</p>
	<input type="button" value="ID 사용" id="btnUseId">
}

--%>

<form action="joinIdDupCheck.do" method="get" name="frm">
	<input type="text" name="id" value="${ requestScope.id }"> <!-- Scope안달아주면 순차적으로 찾음 -->
	<input type="submit" value="ID 중복확인"> 
</form>

<script src="/script/jquery-3.5.1.js"></script>
<script>
	$('#btnUseId').click(function () {
		// 검색한 ID 값 -> 창을 열게해준 부모페이지인 join.jsp의 id 입력상자에 넣기
		window.opener.document.frm.id.value = frm.id.value;
		window.close(); // 현재 창 닫기
	});
</script>
</body>
</html>



