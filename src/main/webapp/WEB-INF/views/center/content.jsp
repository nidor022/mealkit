<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<%-- head 컨텐트 영역 --%>
<jsp:include page="/WEB-INF/views/include/headContent.jsp" />

<link href="/css/subpage.css" rel="stylesheet" type="text/css"  media="all">
</head>
<body>
<div id="wrap">
	<%-- header 영역 --%>
	<jsp:include page="/WEB-INF/views/include/topHeader.jsp" />

	<div class="clear"></div>
	<div id="sub_img_center"></div>
	
	<div class="clear"></div>
	<%-- nav 영역 --%>
	<jsp:include page="/WEB-INF/views/include/submenuBoard.jsp" />
	
	<article>
		
	<h1>텍스트 게시판 상세보기</h1>
		
	<table id="notice">
		<tr>
			<th scope="col" class="tno">글번호</th>
			<td class="left" width="500">${ noticeVo.num }</td>
		</tr>
		<tr>
			<th scope="col" class="tread">조회수</th>
			<td class="left">${ noticeVo.readcount }</td>
		</tr>
		<tr>
			<th scope="col" class="twrite">작성자</th>
			<td class="left">${ noticeVo.id }</td>
		</tr>
		<tr>
			<th scope="col" class="tdate">작성일자</th>
			<td class="left"><fmt:formatDate value="${ noticeVo.regDate }" pattern="yyyy.MM.dd HH:mm:ss"/></td>
		</tr>
		<tr>
			<th scope="col" class="ttitle">글제목</th>
			<td class="left">${ noticeVo.subject }</td>
		</tr>
		<tr>
			<th scope="col" class="ttitle">글내용</th>
			<td class="left">${ noticeVo.content }</td>
		</tr>
	</table>

	<div id="table_search">
		<c:if test="${ not empty id }">
			<%-- 로그인 했을때 --%>
			<c:if test="${ id eq noticeVo.id }">
				<%-- 로그인 아이디와 글작성자 아이디가 같을때 --%>
				<input type="button" value="글수정" class="btn">
				<input type="button" value="글삭제" class="btn">
			</c:if>
			<input type="button" value="답글쓰기" class="btn">
		</c:if>
		<input type="button" value="목록보기" class="btn" onclick="location.href = 'notice.do?pageNum=${ pageNum }'">
	</div>
	
	<div class="clear"></div>
	<div id="page_control">
	</div>
		
	</article>
    
	<div class="clear"></div>
	<%-- footer 영역 --%>
	<jsp:include page="/WEB-INF/views/include/bottomFooter.jsp" />
</div>

</body>
</html>   

    