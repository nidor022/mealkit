<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int tpNum = (Integer)session.getAttribute("tp");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h2>주문자 정보를 입력하거라</h2>
	
	<form action="kakao.do" method="post">
    <a> 이름 </a>
	  <input type="text" id="name" name="name"> <br>
    <a> 폰번호 </a>
	  <input type="text" id="phone" name="phone"> <br>
    <a> 주소 </a>
	  <input type="text" id="address" name="address"> <br>
    <a> 이메일 </a>
	  <input type="text" id="email" name="email"> <br>
	  
	  <label>비회원 비밀번호</label><br>
	  <input type="password" name="pass"><br>
	  
	  <input type="hidden" name="totalPrice" value="<%=tpNum %>">
    <Button type="submit" value="고고">진짜 결제하기</button>
  </form>
</body>
</html>