<%@page import="com.exam.vo.UserVo"%>
<%@page import="com.exam.dao.UserDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
// 	int totalPrice = Integer.parseInt(request.getParameter("totalPrice"));
    int tpNum = (Integer)session.getAttribute("tp");
	String id = (String) session.getAttribute("id");
	
	UserDao userDao = UserDao.getInstance();
	UserVo userVo = new UserVo();
	
	userVo = userDao.getUserById(id);
	
	String name = userVo.getName();
	String phone = userVo.getTel();
	String address = userVo.getAddress();
	String email = userVo.getEmail();	
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>주문자 정보를 입력하거라</h2>
  <input type="radio" name="radio" id="new" value="new">새로입력
  <input type="radio" name="radio" id="get" value="get">회원정보와 동일
  
  <form action="kakao.do" method="post">
    <a> 이름 </a>
	  <input type="text" id="name" name="name"> <br>
    <a> 폰번호 </a>
	  <input type="text" id="phone" name="phone"> <br>
    <a> 주소 </a>
	  <input type="text" id="address" name="address"> <br>
    <a> 이메일 </a>
	  <input type="text" id="email" name="email"> <br>
	  
	  <input type="hidden" name="totalPrice" value="<%=tpNum %>">
    <Button type="submit" value="고고">진짜 결제하기</button>
  </form>
  
<script src="/script/jquery-3.5.1.js"></script>
<script>
$(document).ready(function(){
	$("input:radio[name=radio]").click(function(){
		if($("input[name=radio]:checked").val() == "new"){
			$("input:text[name=name]").val('');
   	        $("input:text[name=phone]").val('');
    	    $("input:text[name=address]").val('');
   	        $("input:text[name=email]").val('');
		}
		else if($("input[name=radio]:checked").val() == "get"){
			$("input:text[name=name]").val('<%=name %>');
   	        $("input:text[name=phone]").val('<%=phone %>');
    	    $("input:text[name=address]").val('<%=address %>');
   	        $("input:text[name=email]").val('<%=email %>');
	        }
	    });
	});

 </script>
</body>
</html>