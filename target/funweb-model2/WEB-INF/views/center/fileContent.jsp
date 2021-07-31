<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<style type="text/css">
#wrap {
	width: 800px;
	margin: 0 auto 0 auto;
}

#detailBoard {
	text-align: center;
}

#title {
	height: 16;
	font-family: '돋움';
	font-size: 12;
	text-align: center;
	background-color: #F7F7F7;
}

#btn {
	font-family: '돋움';
	font-size: 14;
	text-align: center;
}
</style>
</head>
<body>
	<div id="wrap">


		<div class="clear"></div>
		<div id="sub_img_center"></div>

		<div class="clear"></div>


		<article>

			<h1>후기남기기 게시판 상세보기</h1>

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
					<td class="left"><fmt:formatDate value="${ noticeVo.regDate }"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
				</tr>
				<tr>
					<th scope="col" class="ttitle">글제목</th>
					<td class="left">${ noticeVo.subject }</td>
				</tr>
				<tr>
					<th scope="col" class="ttitle">글내용</th>
					<td class="left">${ noticeVo.content }</td>
				</tr>
				<tr>
					<th scope="col" class="ttitle">첨부파일</th>
					<td class="left"><c:if test="${ not empty attachList }">

							<c:forEach var="attach" items="${ attachList }">

								<c:choose>
									<c:when test="${ attach.image eq 'I' }">
										<p>
											<a
												href="/upload1/${ attach.uploadpath }/${ attach.filename }">
												<img
												src="/upload1/${ attach.uploadpath }/${ attach.filename }"
												width="100" height="100">
											</a>
										</p>
									</c:when>
									<c:otherwise>
										<p>
											<a
												href="/upload1/${ attach.uploadpath }/${ attach.filename }">
												${ attach.filename } </a>
										</p>
									</c:otherwise>
								</c:choose>

							</c:forEach>
						</c:if></td>
				</tr>
			</table>

			<div id="replyList"></div>

			<div id="table_CRUD">
				<%-- 로그인 했을때 --%>
				<c:if test="${ not empty id }">
					<%-- 로그인 아이디와 글작성자 아이디가 같을때 --%>
					<c:if test="${ id eq noticeVo.id }">
						<input type="button" value="글수정" class="btn"
							onclick="location.href = 'fileModifyForm.do?num=${ noticeVo.num }&pageNum=${ pageNum }'">
						<input type="button" value="글삭제" class="btn" onclick="remove()">
					</c:if>
				</c:if>
				<input type="button" value="목록보기" class="btn"
					onclick="location.href = 'fileboard.do?pageNum=${ pageNum }'">
			</div>

			<div class="comment_list">
				<div>
					<span><strong>Comments</strong></span> <span id="cCnt"></span>
				</div>

				<%-- 기존 존재하는 댓글들 --%>
				<div>
					<div id="commentList"></div>
				</div>

				<%-- 로그인 했을때 나오는 댓글입력창 --%>
				<c:if test="${ not empty id }">
					<form id="writeCommentForm" name="commentForm" method="post">
						<input type="hidden" id="b_num" name="b_num"
							value="${ noticeVo.num }"> <input type="hidden"
							id="pageNum" name="pageNum" value="${ pageNum }"> <br>
						<br>
						<table>
							<tr>
								<td>
									<%-- 아이디 --%>
									<div>${ id }</div> <%-- 본문 작성 --%>
									<div>
										<textarea id="area_comment" name="comment_content" rows="4"
											cols="70"></textarea>
									</div> <%-- 댓글 등록 버튼 --%>
									<div>
										<a href="#" id="BtnCmt" onclick="writeCmt()">[댓글등록]</a>
									</div>
								</td>
							</tr>
						</table>
					</form>
				</c:if>
			</div>

			<div class="clear"></div>
			<div id="page_control"></div>

		</article>

		<div class="clear"></div>

	</div>

	<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
	<script>
		function remove() {
			let isDelete = confirm('${ noticeVo.num }번 글을 정말 삭제하시겠습니까?');
			if (isDelete) {
				location.href = 'fileDelete.do?num=${ noticeVo.num }&pageNum=${ pageNum }';
			}
		}

		// 댓글 등록
		function writeCmt() {
			var b_num = $("#b_num").val();
			var pageNum = $("#pageNum").val();
			var c_content = $("#area_comment").val();

			$.ajax({
				url : 'jsonCommentInsert.do',
				data : {
					"b_num" : b_num,
					"c_content" : c_content,
					"pageNum" : pageNum
				},
				success : function(data) {
					getCommentList();
					$("#area_comment").val("");
				},
				error : function(request, status, error) {
					//alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				}
			});
		}
		// 초기페이지 로딩시 댓글 불러오기
		//    	$(function() {
		getCommentList();
		//    	});

		// 댓글 불러오기
		function getCommentList() {
			//alert('getCommentList 호출됨');

			var b_num = $("#b_num").val();
			var pageNum = $("#pageNum").val();

			$.ajax({
				url : 'jsonCommentGet.do',
				data : {
					"b_num" : b_num,
					"pageNum" : pageNum
				},
				success : function(data) {
					renderComment(data);
					countComment(data);

				}
			});
		}

		// 댓글 수 세기(JSON)
		function countComment(data) {
			var commentNum = 0;
			var commentNum = data.totalCount;
			$("#cCnt").html(commentNum);
		}


		function renderComment(data) {
			var str = '';

			var commentList = data.commentList;

			if (commentList.length > 0) {
				for (var comment of commentList) {
					str += `
						<div>
							<div>
								<table class='table'>
									<tr>
										<td>
					 						<div> \${comment.c_id} </div>
					 						<div> \${comment.c_content} </div>
				 						</td>
			 						</tr>
		 							<tr>
		 								<td> \${comment.c_date} </td>
	 								</tr>
 								</table>
							</div>
						</div>
					`;
				}
			} else {
				str += `
					<div>
					   <div>
					   	<table class='table'>
					   		<tr>
					   			<td><div><p>등록된 댓글이 없습니다.</p></div></td>
					   		</tr>
				 		</table>
			 		   </div>
			 		</div>
				`;
			}
			$("#commentList").html(str);
		}


	</script>
</body>
</html>



