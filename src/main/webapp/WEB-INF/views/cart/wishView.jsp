<%@page import="java.text.DecimalFormat"%>
<%@page import="com.exam.vo.CartDTO"%>
<%@page import="com.exam.dao.WishDao"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");


String id = (String) session.getAttribute("id");

ArrayList<CartDTO> wish = null;


%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cart View</title>
<script type="text/javascript">
function fnPay(){
	alert("결제 API를 발급 받으시기 바랍니다.");
}

function fnClear(){
	if(confirm("찜목록을 비우시겠습니까?")) {
		location.href = "WishClear.do";	////////////////////////////////////////////////
	}
}

function fnGo(){
		location.href = "index.do";
}
</script>
</head>
<body>

	   <header class="header-area">

        <!-- Logo Area -->
        <div class="logo-area" align="center">
            <a href="index.do"><img src="img/core-img/logo.png" alt=""></a>
        </div>
        
    	</header>
    	
<div align="center">
	<h3>[찜목록 보기]</h3>
	<table border="1">
		<tr>
			<th>번호</th>
			<th>과일명</th>
			<th>단가</th>
			<th>주문 수량</th>
			<th>가격</th>
		</tr>
		<%
		
		// 비회원 이면
		if(id == null){
			out.println("<tr align='center'>");
				out.println("<td colspan= '5'>");
					out.println("회원만 이용가능합니다");
					out.println("<a href= 'userLogin.do'>로그인하기</a>");
				out.println("</td>");
			out.println("</tr>");

		} // 비회원이면
		
		else { // 회원이면
			
			WishDao wishDao = WishDao.getInstance();
			// DB(cart)에서 세션id에 해당하는 컬럼들을 Array리스트에 담기 
			wish = wishDao.getItemsById(id);
			
			if(wishDao.wishCheck(id)) { // 카트안에 물품이 없으면
				out.println("<tr align='center'>");
					out.println("<td colspan= '5'>");
						out.println("찜목록에 담긴 상품이 없습니다.");
						out.println("<a href= 'index.do'>주문하기</a>");
					out.println("</td>");
				out.println("</tr>");
			} else {
				
				int totalSum = 0, total = 0;
				DecimalFormat df = new DecimalFormat("￦#,##0");
				for(int i = 0; i < wish.size(); i++) {
					CartDTO dto = wish.get(i);
					out.println("<tr align= 'center'>");
						out.println("<td>" + (i + 1) + "</td>");
						out.println("<td>" + dto.getProduct_name() + "</td>");
						out.println("<td>" + df.format(dto.getProduct_price()) + "</td>");
						out.println("<td>" + dto.getProduct_count() + "</td>");
						total = dto.getProduct_price() * dto.getProduct_count();
						out.println("<td>" + df.format(total) + "</td>");
					out.println("</tr>");
					totalSum += total;
				}
			out.println("<tr align = 'center'>");
				out.println("<td colspan= '4'>");
					out.println("<input type='button' value='찜목록 비우기' onclick='fnClear()' />");
					out.println("<input type='button' value='쇼핑 계속하기' onclick='fnGo()' />");
				out.println("</td>");
				out.println("<td>");
				out.println(df.format(totalSum));
				out.println("</td>");
			out.println("</tr>");
			}//if else
			
		}
		%>
	</table>
</div>
</body>
</html>