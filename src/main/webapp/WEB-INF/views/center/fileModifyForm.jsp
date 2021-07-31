<%@page import="com.exam.vo.AttachVo"%>
<%@page import="java.util.List"%>
<%@page import="com.exam.dao.AttachDao"%>
<%@page import="com.exam.vo.NoticeVo"%>
<%@page import="com.exam.dao.NoticeDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<!DOCTYPE html>
<html>
<head>

<style>
	span.delete-oldfile, span.delete-addfile {
		color: red;
		background-color: yellow;
		font-weight: bold;
		margin-left: 10px;
		border: 1px solid gray;
		border-radius: 5px;
	}
</style>
</head>
<body>
<div id="wrap">

	<div class="clear"></div>
	<div id="sub_img_center"></div>
	
	<div class="clear"></div>
	
	<article>
		
	<h1>후기 게시판 글수정</h1>
		
	<form action="fileModifyPro.do" method="post" enctype="multipart/form-data" name="frm">
	<input type="hidden" name="pageNum" value="${pageNum} }">
	<input type="hidden" name="num" value="${ noticeVo.num }">
	<table id="notice">
		<tr>
			<th scope="col" class="twrite">작성자</th>
			<td class="left" width="500">
				<input type="text" name="id" value="${ noticeVo.id }" readonly>
			</td>
		</tr>
		<tr>
			<th scope="col" class="ttitle">글제목</th>
			<td class="left">
				<input type="text" name="subject" value="${ noticeVo.subject }">
			</td>
		</tr>
		<tr>
			<th scope="col" class="ttitle">글내용</th>
			<td class="left">
				<textarea rows="13" cols="40" name="content">${ noticeVo.content }</textarea>
			</td>
		</tr>
		<tr>
			<th scope="col" class="ttitle">첨부파일</th>
			<td class="left">
				<div id="oldFileBox">
				
				<c:forEach var="attachVo" items="${ attachList}">
					<input type="hidden" name="oldfile" value="${attachVo.num}">
					<div>
							${attachVo.filename}
							<span class="delete-oldfile">X</span>
						</div>				
				</c:forEach>
				
				
				</div>
				<div id="newFileBox"></div>
				<input type="button" id="btnAddFile" value="첨부파일 추가">
			</td>
		</tr>
	</table>

	<div id="table_search">
		<input type="submit" value="글수정" class="btn">
		<input type="reset" value="다시쓰기" class="btn">
		<input type="button" value="목록보기" class="btn" onclick="location.href = 'fileNotice.jsp?pageNum=${pageNum}'">
	</div>
	</form>
	
	<div class="clear"></div>
	<div id="page_control">
	</div>
		
	</article>
    
	<div class="clear"></div>
</div>

<script src="/script/jquery-3.5.1.js"></script>
<script>
const maxFileCount = 5;  // 최대 첨부파일 갯수
var fileCount = ${fn:length(attachlist)};  // 현재 첨부된 파일 갯수
var fileIndex = 0;

// [첨부파일 추가] 버튼을 클릭할 때
$('#btnAddFile').click(function () {

	if (fileCount >= maxFileCount) {
		alert('첨부파일은 최대 5개 까지만 가능합니다.')
		return;
	}
	
	// 백틱 문자열 안에서 변수값을 표현할때는
	// \${}로 표현함
	var str = `
		<div>
			<input type="file" name="filename\${ fileIndex }">
			<span class="delete-addfile">X</span>
		</div>
	`;

	$('div#newFileBox').append(str);
	
	fileIndex++;
	fileCount++;
});


// 동적 이벤트 바인딩. 이벤트 바인딩 작업을 이미 존재하는 요소에게 위임하기.
// 이미 존재하는 div#newFileBox 요소에게
// 안쪽에 새로운 span.delete-addfile가 들어오면 클릭이벤트 연결하기
$('div#newFileBox').on('click', 'span.delete-addfile', function () {
	$(this).parent().remove();
	fileCount--;
});


// 정적 이벤트 바인딩. 기존 첨부파일에 삭제버튼을 눌렀을때
$('span.delete-oldfile').on('click', function () {
	// 현재 클릭한 요소의 직계부모(parent)의 앞(prev) 요소 
	$(this).parent().prev().prop('name', 'delfile');
	// 현재 클릭한 요소의 직계부모(parent)를 삭제. 현재요소안에 자식요소도 모두 삭제됨
	$(this).parent().remove();
	fileCount--;
});

</script>

</body>
</html>   

    