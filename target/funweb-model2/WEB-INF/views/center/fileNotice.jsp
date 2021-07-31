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
<style>
a.active {
	font-weight: bold;
}
</style>
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
		
	<h1>자료실 게시판 [글갯수: ${ pageDto.count }]</h1>
		
	<table id="notice">
		<tr>
			<th scope="col" class="tno">글번호</th>
			<th scope="col" class="ttitle">글제목</th>
			<th scope="col" class="twrite">작성자</th>
			<th scope="col" class="tdate">작성일자</th>
			<th scope="col" class="tread">조회수</th>
		</tr>
		
		<c:choose>
		<c:when test="${ not empty noticeList }"><%-- ${ pageDto.count gt 0 } --%>
			
			<c:forEach var="notice" items="${ noticeList }">
				<tr>
					<td>${ notice.num }</td>
					<td class="left">
						<c:if test="${ notice.reLev gt 0 }"><%-- 답글이면 --%>
							<img src="../images/level.gif" width="${ notice.reLev * 15 }" height="13">
							<img src="../images/re.gif">
						</c:if>
						<a href="fileContent.do?num=${ notice.num }&pageNum=${ pageNum }">${ notice.subject }</a>
					</td>
					<td>${ notice.id }</td>
					<td><fmt:formatDate value="${ notice.regDate }" pattern="yyyy.MM.dd"/></td>
					<td>${ notice.readcount }</td>
				</tr>
			</c:forEach>
			
		</c:when>		
		<c:otherwise>
			<tr>
				<td colspan="5">게시판 글 없음</td>
			</tr>
		</c:otherwise>
		</c:choose>

	</table>

	<div id="table_search">
		<form action="fileNotice.do" method="get">
			<select name="category">
				<option value="subject" ${ pageDto.category eq 'subject' ? 'selected' : '' }>글제목</option>
				<option value="content" ${ pageDto.category eq 'content' ? 'selected' : '' }>글내용</option>
				<option value="id" ${ pageDto.category eq 'id' ? 'selected' : '' }>작성자ID</option>
			</select>
			<input type="text" class="input_box" name="search" value="${ pageDto.search }">
			<input type="submit" value="검색" class="btn">
			
			<%-- 로그인 했을때만 [글쓰기] 버튼 보이기 --%>
			<c:if test="${ not empty sessionScope.id }">
				<input type="button" value="파일글쓰기" class="btn" onclick="location.href='fileWriteForm.do?pageNum=${ pageNum }'">
			</c:if>

		</form>
	</div>
	
	<div class="clear"></div>
	<div id="page_control">
	
	<%-- 글갯수가 0보다 크면 페이지블록 계산해서 출력하기 --%>
	<c:if test="${ pageDto.count gt 0 }">
		<%-- [이전] --%>
		<c:if test="${ pageDto.startPage gt pageDto.pageBlock }">
			<a href="fileNotice.do?pageNum=${ pageDto.startPage - pageDto.pageBlock }&category=${ pageDto.category }&search=${ pageDto.search }">[이전]</a>
		</c:if>
		
		<%-- 시작페이지 ~ 끝페이지 --%>
		<c:forEach var="i" begin="${ pageDto.startPage }" end="${ pageDto.endPage }" step="1">
			
			<c:choose>
			<c:when test="${ i eq pageNum }">
				<a href="fileNotice.do?pageNum=${ i }&category=${ pageDto.category }&search=${ pageDto.search }" class="active">[${ i }]</a>
			</c:when>
			<c:otherwise>
				<a href="fileNotice.do?pageNum=${ i }&category=${ pageDto.category }&search=${ pageDto.search }">[${ i }]</a>
			</c:otherwise>
			</c:choose>
			
		</c:forEach>
		
		<%-- [다음] --%>
		<c:if test="${ pageDto.endPage lt pageDto.pageCount }">
			<a href="fileNotice.do?pageNum=${ pageDto.startPage + pageDto.pageBlock }&category=${ pageDto.category }&search=${ pageDto.search }">[다음]</a>
		</c:if>
	</c:if>
	
	</div>
		
	</article>
    
	<div class="clear"></div>
	<%-- footer 영역 --%>
	<jsp:include page="/WEB-INF/views/include/bottomFooter.jsp" />
</div>

</body>
</html>   

    