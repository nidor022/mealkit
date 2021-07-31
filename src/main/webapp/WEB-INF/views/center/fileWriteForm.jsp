<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<style>
span.file-delete {
	color: red;
	background-color: yellow;
	font-weight: bold;
	margin-left: 10px;
	cursor: pointer;
	border-radius: 5px;
	border: 1px solid black;
	padding: 2px;
	display: inline-block;
}
</style>
</head>
<body>
<div id="wrap">
	

	<div class="clear"></div>
	<div id="sub_img_center"></div>
	
	<div class="clear"></div>
	
	
	<article>
		
	<h1>후기 게시판 글쓰기</h1>
		
	<form action="fileWritePro.do" method="post" enctype="multipart/form-data" name="frm">
	<input type="hidden" name="pageNum" value="${ pageNum }">
	<table id="notice">
		<tr>
			<th scope="col" class="twrite">작성자</th>
			<td class="left" width="500">
				<input type="text" name="id" value="${ id }" readonly>
			</td>
		</tr>
		<tr>
			<th scope="col" class="ttitle">글제목</th>
			<td class="left">
				<input type="text" name="subject">
			</td>
		</tr>
		<tr>
			<th scope="col" class="ttitle">글내용</th>
			<td class="left">
				<textarea rows="13" cols="40" name="content"></textarea>
			</td>
		</tr>
		<tr>
			<th scope="col" class="ttitle">파일</th>
			<td class="left">
				<input type="button" value="첨부파일 추가" id="btnAddFile" class="btn">
				<div id="fileBox">
					<div>
						<input type="file" name="filename0">
						<span class="file-delete">X</span>
					</div>
				</div>
			</td>
		</tr>
	</table>

	<div id="table_search">
		<input type="submit" value="파일글쓰기" class="btn">
		<input type="reset" value="다시쓰기" class="btn">
		<input type="button" value="목록보기" class="btn" onclick="location.href = 'fileNotice.do?pageNum=${ pageNum }'">
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
	let fileCount = 1;
	let fileIndex = 1;

	// 정적 이벤트 연결
	$('#btnAddFile').on('click', function () {
		if (fileCount >= 5) {
			alert('첨부파일은 최대 5개 까지만 첨부할 수 있습니다.');
			return;
		}
		
		let str = `
			<div>
				<input type="file" name="filename\${fileIndex}">
				<span class="file-delete">X</span>
			</div>
		`;

		$('#fileBox').append(str);

		fileCount++;
		fileIndex++;
	});


	// 동적 이벤트 연결 (이벤트 등록을 위임하는 방식)
	$('div#fileBox').on('click', 'span.file-delete', function () {
		//alert('span X 클릭됨');

		//$(this).closest('div').remove();
		$(this).parent().remove();
		
		fileCount--;
	});

</script>
</body>
</html>   





    